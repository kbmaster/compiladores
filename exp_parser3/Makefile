JAVA=java
JAVAC=javac
JFLEX=jflex
CUPJAR=/home/kbmaster/lenguajes/tools/cup/java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR) <
CP=.:$(CUPJAR)

Main.class: Lexer.java parser.java *.java

%.class: %.java
	$(JAVAC) -cp $(CP) $^

Lexer.java: lexp.flex
	$(JFLEX) lexp.flex

parser.java: yexp.cup
	$(CUP) yexp.cup

compile: Lexer.java parser.java *.java
	$(JAVAC) -cp $(CP)  $^

interactive: Main.class
	 $(JAVA) -cp $(CP) Main

clean:
	rm -f parser.java Lexer.java sym.java out.txt *.class *~
