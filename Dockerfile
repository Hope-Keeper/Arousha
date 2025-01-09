# Step 1: Specify the base image
FROM openjdk:21-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /srv

# Step 3: Copy package.json and package-lock.json files
COPY ./target/demo-0.0.1-SNAPSHOT.jar /srv

# Step 6: Expose the port the app runs on
EXPOSE 9192

# Step 7: Define the command to run the application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]