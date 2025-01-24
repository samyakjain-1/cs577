all: build run

build:
	javac hw1.java

run:
	java hw1

clean: 
	rm -f *.class