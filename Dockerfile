FROM eclipse-temurin:17-jdk

COPY . /project
RUN  cd /project && ./mvnw spring-boot:run

#run the spring boot application
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/project/target/demo-0.0.1-SNAPSHOT.jar"]