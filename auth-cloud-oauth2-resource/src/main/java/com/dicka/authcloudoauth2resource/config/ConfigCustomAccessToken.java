package com.dicka.authcloudoauth2resource.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConfigCustomAccessToken implements AccessTokenConverter, JwtAccessTokenConverterConfigurer {

    private boolean includeGrantType;
    private UserAuthenticationConverter userAuthenticationConverter = new ConfigCustomUserConverter();

    public void setIncludeGrantType(boolean includeGrantType){
        this.includeGrantType = includeGrantType;
    }

    public void setUserAuthenticationConverter(UserAuthenticationConverter
                                                       userAuthenticationConverter){
        this.userAuthenticationConverter = userAuthenticationConverter;
    }

    private Set<String> extractScope(Map<String, ?> map){
        Set<String> scope = Collections.emptySet();
        if (map.containsKey(SCOPE)){
            Object scopeObj = map.get(SCOPE);
            if (String.class.isInstance(scopeObj))
                scope = new LinkedHashSet<String>(
                        Arrays.asList(String.class.cast(scopeObj).split(" ")));
            else if (Collections.class.isAssignableFrom(scopeObj.getClass())){
                @SuppressWarnings("unchecked")
                Collection<String> scopColl = (Collection<String>) scopeObj;
                scope = new LinkedHashSet<String>(scopColl);
            }
        }
        return scope;
    }

    private Collection<String> getAudience(Map<String, ?> map){
        Object auds = map.get(AUD);
        if (auds instanceof Collection){
            @SuppressWarnings("unchecked")
            Collection<String> result = (Collection<String>) auds;
            return result;
        }
        return Collections.singleton((String) auds);
    }

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken oAuth2AccessToken,
                                             OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> response = new HashMap<String, Object>();
        OAuth2Request clientToken = oAuth2Authentication.getOAuth2Request();

        if(!oAuth2Authentication.isClientOnly())
            response.putAll(userAuthenticationConverter
                    .convertUserAuthentication(oAuth2Authentication.getUserAuthentication()));
        else if (clientToken.getAuthorities() != null && !clientToken.getAuthorities().isEmpty())
            response.put(userAuthenticationConverter.AUTHORITIES,
                    AuthorityUtils.authorityListToSet(clientToken.getAuthorities()));

        if (oAuth2AccessToken.getScope()!=null)
            response.put(SCOPE, oAuth2AccessToken.getScope());

        if (oAuth2AccessToken.getAdditionalInformation().containsKey(JTI))
            response.put(JTI, oAuth2AccessToken.getAdditionalInformation()
                    .get(JTI));

        if (oAuth2AccessToken.getExpiration() != null)
            response.put(EXP, oAuth2AccessToken.getExpiration().getTime() /1000);

        if (includeGrantType && oAuth2Authentication.getOAuth2Request().getGrantType() != null)
            response.put(GRANT_TYPE, oAuth2Authentication.getOAuth2Request().getGrantType());
            response.putAll(oAuth2AccessToken.getAdditionalInformation());
            response.put(AUD, clientToken.getClientId());

        if (clientToken.getResourceIds() != null && !clientToken.getResourceIds().isEmpty())
            response.put(AUD, clientToken.getResourceIds());
        return response;
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(value);
        Map<String, Object> info = new HashMap<String, Object>(map);

        info.remove(EXP);
        info.remove(AUD);
        info.remove(CLIENT_ID);
        info.remove(SCOPE);

        /** expired token **/
        if (map.containsKey(EXP))
            defaultOAuth2AccessToken.setExpiration(new Date((Long) map.get(EXP) * 1000L));

        if (map.containsKey(JTI))
            info.put(JTI, map.get(JTI));

        defaultOAuth2AccessToken.setScope(extractScope(map));
        defaultOAuth2AccessToken.setAdditionalInformation(info);
        return defaultOAuth2AccessToken;
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        Set<String> scope = extractScope(map);
        Map<String, String> parameters = new HashMap<String, String>();
        Authentication user = userAuthenticationConverter.extractAuthentication(map);

        String clientId = (String) map.get(CLIENT_ID);
        parameters.put(CLIENT_ID, clientId);

        if(includeGrantType && map.containsKey(GRANT_TYPE))
            parameters.put(GRANT_TYPE, (String) map.get(GRANT_TYPE));

        Set<String> resourceIds = new LinkedHashSet<String>(
                map.containsKey(AUD) ? getAudience(map) : Collections.<String>emptySet());

        Collection<? extends GrantedAuthority> authorities = null;

        if (user == null && map.containsKey(AUTHORITIES)){
            String[] roles = ((Collection<String>) map.get(AUTHORITIES))
                    .toArray(new String[0]);
            authorities = AuthorityUtils.createAuthorityList(roles);
        }

        OAuth2Request oauth2Request = new OAuth2Request(
                parameters, clientId, authorities, true, scope, resourceIds, null,
                null, null);

        return new OAuth2Authentication(oauth2Request, user);
    }
}
