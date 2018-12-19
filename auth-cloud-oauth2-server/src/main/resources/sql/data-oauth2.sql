/** data oauth2 sql **/
INSERT INTO OAUTH_CLIENT_DETAILS (
	CLIENT_ID,
	CLIENT_SECRET,
	RESOURCE_IDS,
	SCOPE,
	AUTHORIZED_GRANT_TYPES,
	WEB_SERVER_REDIRECT_URI,
	AUTHORITIES,
	ACCESS_TOKEN_VALIDITY,
	REFRESH_TOKEN_VALIDITY,
	ADDITIONAL_INFORMATION,
	AUTOAPPROVE)
	VALUES(
    'USER_CLIENT_APP',
    '$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
	'USER_CLIENT_RESOURCE,USER_ADMIN_RESOURCE',
	'role_admin,role_user',
	'authorization_code,password,refresh_token,implicit',
	NULL,
	NULL,
	900,
	3600,
	'{}',
	NULL);
	
/** permission **/
	INSERT INTO PERMISSIONS (NAME) VALUES
('can_create_user'),
('can_update_user'),
('can_read_user'),
('can_delete_user');

/** role **/
INSERT INTO ROLES (NAME) VALUES
('role_admin'),('role_user');

/** permission role **/
 INSERT INTO PERMISSIONS_ROLES (PERMISSIONS_ID, ROLES_ID) VALUES
    (1,1), /* can_create_user assigned to role_admin */
    (2,1), /* can_update_user assigned to role_admin */
    (3,1), /* can_read_user assigned to role_admin */
    (4,1), /* can_delete_user assigned to role_admin */
(3,2); /* can_read_user assigned to role_user */

/** users **/
INSERT INTO USERS (
    USERNAME,
    PASSWORD,
    EMAIL,
    ENABLED,
    ACCOUNT_EXPIRED,
    CREDENTIALS_EXPIRED,
    ACCOUNT_LOCKED) 
    VALUES (
    'admin','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
    'dickanirwansyah@gmail.com',1,0,0,0),
    ('user','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
'dicky@gmail.com',1,0,0,0);

/** users roles **/
    INSERT INTO USERS_ROLES(ROLES_ID, USERS_ID)
    VALUES
    (1, 1) /* role_admin assigned to admin users */,
(2, 2) /* role_user assigned to user users */ ;