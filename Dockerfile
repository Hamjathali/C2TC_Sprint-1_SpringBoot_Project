# FROM eclipse-temurin

# WORKDIR /app

# COPY target/StudentService-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

# ENTRYPOINT ["java","-jar","app.jar"]

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/StudentService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]