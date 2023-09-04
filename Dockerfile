## Primeira etapa: Construção da aplicação com compilação e empacotamento.
#FROM maven:3-openjdk-17 as build-image
#
#WORKDIR /to-build-app
#
#COPY . .
#
#RUN ./mvnw dependency:go-offline
#RUN ./mvnw clean package
#
#
## Segunda etapa: Criação da imagem de execução otimizada.
#FROM eclipse-temurin:17-jre-alpine
#
#WORKDIR /app
#
#COPY --from=build-image /to-build-app/target/*.jar ./app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.jar"]