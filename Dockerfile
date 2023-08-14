FROM maven:3.8.6-jdk-8 AS build
COPY . /usr/src/app
COPY pom.xml /usr/src/app
RUN mvn  -f /usr/src/app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:8-jre-slim 



WORKDIR /usr/share/tag

# COPY --from=build /usr/src/app/.  /usr/share/tag/
# ADD    target/seleniumdocker.jar seleniumdocker.jar
# ADD   target/seleniumdocker-tests.jar seleniumdocker-tests.jar
# ADD   target/libs libs
ADD --from=build /usr/src/app/target/seleniumdocker.jar seleniumdocker.jar
# COPY --from=build /usr/src/app/target/seleniumdocker-tests.jar seleniumdocker-tests.jar
# COPY --from=build /usr/src/app/target/libs libs
COPY /usr/src/app/target/ .
# Add the project jar & copy dependencies
# ADD  target/seleniumdocker.jar seleniumdocker.jar
# ADD  target/seleniumdocker-tests.jar seleniumdocker-tests.jar
# ADD  target/libs libs
# Add the suite xmls
ADD testng.xml testng.xml

COPY  src/test/resources/application.properties src/test/resources/application.properties


RUN mkdir seleniumdockerreports

ENTRYPOINT java -cp seleniumdocker.jar:seleniumdocker-tests.jar:libs/* -DseleniumHubHost=selenium-hub -Dbrowser=chrome org.testng.TestNG testng.xml

CMD cp -R /test-output/emailable-report.html seleniumdockerreports

#ADD  application.properties
# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# GRIDHOST = selenium hub hostname / ipaddress

# ENTRYPOINT java -cp seleniumdocker.jar:seleniumdocker-tests.jar:libs/* -DseleniumHubHost=selenium-hub -Dbrowser=chrome org.testng.TestNG testng.xml

# CMD cp -R /test-output/emailable-report.html D:\\seleniumdockerreports
