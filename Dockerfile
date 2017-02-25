FROM frolvlad/alpine-oraclejdk8:slim

# Install maven
RUN apk add --update maven &>/dev/null

WORKDIR /code

# Prepare by downloading dependencies
ADD pom.xml /code/pom.xml  
RUN ["mvn", "-q", "dependency:resolve"]  
RUN ["mvn", "-q", "verify"]

# Adding source, compile and package
ADD src /code/src  
RUN ["mvn","-q", "package"]

# Go to target folder
WORKDIR /code/target

# Execute app
CMD java -jar irackbot-1.0.jar
