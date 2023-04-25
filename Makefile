# Makefile for the Graph assignment program 
# Andrew Erasmus
# 25 April 2023
JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
$(BINDIR)/%.class:$(SRCDIR)/%.java
$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
CLASSES=GraphException.class Edge.class Path.class Vertex.class Graph.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
default: $(CLASS_FILES)
clean:
rm $(BINDIR)/*.class
run: $(CLASS_FILES)
java -cp bin Graph
