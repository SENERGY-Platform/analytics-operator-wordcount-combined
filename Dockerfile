FROM maven:3.5-jdk-8-onbuild-alpine
CMD ["java","-jar","/usr/src/app/target/operator-wordcount-combined-jar-with-dependencies.jar"]
