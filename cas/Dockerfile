FROM openjdk:11

EXPOSE 8080

ADD target/cas-server.jar cas-server.jar

ENTRYPOINT [ "java","-jar","cas-server.jar" ]