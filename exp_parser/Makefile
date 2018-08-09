JAVA=java
JAVAC=javac
JFLEX=jflex
CUPJAR=/home/kbmaster/lenguajes/tools/cup/java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR) <
CP=.:$(CUPJAR)

all: test

test: out.txt
	@(diff out.txt out.ok && echo "Test OK!") || echo "Test failed!"

out.txt: Main.class test.txt
	$(JAVA) -cp $(CP) Main test.txt > out.txt

Main.class: Main.java Lexer.java parser.java

%.class: %.java
	$(JAVAC) -cp $(CP) $^

Lexer.java: lexp.flex
	$(JFLEX) lexp.flex

parser.java: yexp.cup
	$(CUP) yexp.cup

compile: Main.java Lexer.java parser.java
	$(JAVAC) -cp $(CP)  $^

interactive: Main.class
	 $(JAVA) -cp $(CP) Main

clean:
	rm -f parser.java Lexer.java sym.java out.txt *.class *~
