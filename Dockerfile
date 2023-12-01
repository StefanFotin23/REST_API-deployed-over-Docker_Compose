# Dockerfile pentru Java Spring Server
FROM ubuntu

# Directorul de lucru în container
WORKDIR /app

# Install OpenJDK JRE21
RUN apt-get update;
RUN apt-get install -y openjdk-21-jre;

# Copy the JAR file to the container
COPY ./target/rest-1.0.jar /app/app.jar

# Run the JAR file here
#CMD ["pwd"]
#CMD ["ls"]
#CMD ["java", "-jar", "app.jar"]
