# Mathematik-Vertiefungskurs

1. Installation der Anforderungen bzw. notwendigen Software

Fuer die Projekte, die npm nutzen bzw. zum Grossteil in JavaScript geschrieben worden sind, empfiehlt es sich folgende Schritte zu befolgen:

Installation von npm (Pakete Manager fuer JavaScript Abhaengigkeiten);
Installation der Abhaengigkeiten durch den Befehl npm install (im jeweiligen Verzeichnis der Projekte)
-> Eine manuelle Installation ist unter keinen Umstaenden zu empfehlen; Wenn, dann bitte die Inhalte der Datei package.json beachten.

Fuer die Projekte, die lediglich Java nutzen ist lediglich eine funktionierende lokale Java Installation notwendig.
Hier lassen sich die einzelnen Schritte bei Oracle nachlesen. Dort sind die Installationshinweise fuer alle verbreiteten Betriebssysteme vorhanden:
https://www.oracle.com/java/technologies/downloads/

2. Ausfuehren der Projekte / Wie bekomme ich das "Ding" zum Funktionieren?

2.1 JavaScript
(Man muss sich im passenden Verzeichnis befinden.)
Befehl:
`npm run dev`

2.2 Java
Befehl:
`java julia.java`
`java mandel.java`

3. Die Datei FraktalErforscher.java macht im Prinzip das Selbe wie die anderen zwei Dateien (julia.java und mandel.java).

- Philosophie:
  -> Simplifizieren der Anwendung des Codes. (Nur noch ein Befehl notwendig statt zwei.)
  -> Einfacheres Design.
