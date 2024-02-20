FROM eclipse-temurin:17

LABEL mentainer="fscsilva0@gmail.com"

WORKDIR /app

COPY target/authors-0.0.1-0.jar /app/authors.jar

ENTRYPOINT ["java", "-jar", "authors.jar"]