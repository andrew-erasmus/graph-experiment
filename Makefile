# Makefile for the Graph assignment program 
# Andrew Erasmus
# 25 April 2023

JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath $(SRCDIR) $<
CLASSES=Vertex.class \
		Edge.class \
		Path.class \
		GraphException.class \
		Graph.class \
		DataGenerator.class \
		GraphExperiment
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
default: $(CLASS_FILES)
clean:
	rm $(BINDIR)/*.class
run: $(CLASS_FILES)
	java -cp bin GraphExperiment
