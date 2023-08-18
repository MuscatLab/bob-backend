FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace/app

COPY . /workspace/app
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build -x test

FROM eclipse-temurin:17-jdk AS runner
VOLUME /tmp

COPY --from=build /workspace/app/build/libs/*.jar /app/lib/

CMD ["java", "-jar", "/app/lib/bob-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]