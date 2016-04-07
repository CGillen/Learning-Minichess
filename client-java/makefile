all:
	javac -cp libs/*:. *.java */*.java
	jar cvfm client.jar manifest *.class */*.class
	rm *.class */*.class

run: all
	java -jar -ea client.jar
