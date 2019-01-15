#DEPLOY SPRING BOOT - MONGODB - USING DOCKER - MAVEN

# 1. project spring boot
Buatlah atau generate project baru spring boot di `start.spring.io`

# 2. Begin code
kemudian coding proses bisnis sesuai case

# 3. Buat Dockerfile
buatlah sebuah Dockerfile pada project root spring boot setara dengan pom.xml
kemudian konfigurasikan Dockerfile

# 4. Clean Maven project
kemudian ketik `mvn clean install` pada project spring boot
ini akan membuat sebuah folder baru yaitu target, dimana target ini
berisi hasil compile project.

# 5. Build Docker images
kemudian build docker images `docker build -t spring-boot-mongodb-docker .`
jika berhasil outputnya akan seperti ini `Successfully built 52c329cb1125`

# 6. Check docker images
check docker images pastikan images yang telah dibuat sudah benar benar ada,
ketik ini di cmd `docker images`

# 7. kemudian jalankan container mongodb
pastikan di laptop sudah di pull mongodb dari dockernya,
ketik ini untuk menjalankan container mongodb
`docker run -d -p 27000:27017 --name mongo mongo`

# 8. kemudian jalankan keduanya spring boot dan mongodb dari container
ketik ini di cmd `docker run -p 8080:8080 --name spring-boot-mongodb-docker --link=mongo  spring-boot-mongodb-docker`

# 9. Finish
kemudian test aplikasi dengan jalankan link ini `http://localhost:8080/`