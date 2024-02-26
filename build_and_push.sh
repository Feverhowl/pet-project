./gradlew clean build
docker build . -t dmitriyzo/pet-project:1.0.0
docker push dmitriyzo/pet-project:1.0.0