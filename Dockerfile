FROM openjdk:8-alpine

COPY target/uberjar/clojure-jobs-luminus.jar /clojure-jobs-luminus/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/clojure-jobs-luminus/app.jar"]
