FROM java

EXPOSE 3000

COPY target/oeditor-0.0.2-SNAPSHOT.jar /usr/src/oeditor-0.0.2-SNAPSHOT.jar

CMD java -jar /usr/src/oeditor-0.0.2-SNAPSHOT.jar
