FROM openjdk:17-jdk

ENV TZ=Asia/Seoul
ENV AWS_S3_ACCESS=sldjfhlsjflsjdfl
ENV AWS_S3_SECRET=skdhfewiheyfihiewo
ENV MYSQL_URL=jdbc:mysql://localhost:3306/removal
ENV MYSQL_PASSWORD=sldjfhlsjflsjdfl

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]