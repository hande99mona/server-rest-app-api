# Builder stage 
FROM maven as builder
WORKDIR /opt
COPY ./ opt
RUN mvn clean install 

#Final stage
FROM openjdk:11 as final
COPY --from=builder /opt/target/task1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]