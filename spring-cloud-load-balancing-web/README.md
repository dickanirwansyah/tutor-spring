# Example Load balancer

1. Jalankan 1 aplikasi dengan port yang berbeda
`java -jar -Derver.port=10000 spring-cloud-load-balancing-web-0.0.1-SNAPSHOT.jar`
`java -jar -Derver.port=9999 spring-cloud-load-balancing-web-0.0.1-SNAPSHOT.jar`
`java -jar -Derver.port=9998 spring-cloud-load-balancing-web-0.0.1-SNAPSHOT.jar`