FROM openjdk:8-jre-slim

WORKDIR /usr/share/tag

# Add the project jar & copy dependencies
ADD  target/seleniumdocker.jar seleniumdocker.jar
ADD  target/seleniumdocker-tests.jar seleniumdocker-tests.jar
ADD  target/libs libs
# Add the suite xmls
ADD testng.xml testng.xml

COPY src/test/resources/application.properties src/test/resources/application.properties

#ADD  application.properties
# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# GRIDHOST = selenium hub hostname / ipaddress

RUN mkdir D:\\seleniumdockerreports

ENTRYPOINT java -cp seleniumdocker.jar:seleniumdocker-tests.jar:libs/* -DseleniumHubHost=selenium-hub -Dbrowser=chrome org.testng.TestNG testng.xml

CMD cp -R /test-output/emailable-report.html D:\\seleniumdockerreports