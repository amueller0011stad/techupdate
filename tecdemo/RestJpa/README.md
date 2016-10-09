# Beispielapplikation f&uuml;r ein REST-Projekt mit Jersey, Jetty and Gradle

### Voraussetzungen
- [JDK 1.8]
- [Eclipse Neon]

### Starten der Anwendung

 - Download/clone.
 - Im Eclipse: Import as Gradle Project
 - DB Setup -> siehe unten
 - bei den Gradle-Tasks JettyRun aufrufen
 - im Browser dann 
    - http://localhost:8080/RestJpa/corebanking/banks/list Liste aller Banken
    Beispieloutput: [{"id":1,"bankNumber":3321,"description":"Demo","serverAdress":"localhost"}]

### Gradle Tasks f&uuml;r den DB-Setup
 - derbyStart: Startet derby
 - derbyCreateDb: legt die Datenbank an die wir benutzen, muss nur einmal aufgerufen werden
 - flywayMigrate: legt die Datenbanktabellen an, muss nur einmal aufgerufen werden

### Erweiterungsm&ouml;glichkeiten:
 - REST-Api mit Swagger beschreiben
 - ...
 
 