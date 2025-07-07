FROM openjdk:21
LABEL authors="saleem"

COPY target/urlShortener-0.0.1-SNAPSHOT.jar urlShortener.jar
ENTRYPOINT ["java","-jar","/urlShortener.jar"]
