FROM eclipse-temurin
VOLUME /tmp
ARG JAR_FILE=build/libs/accessing-data-mysql.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]