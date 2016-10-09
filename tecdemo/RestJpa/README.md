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
    - http://localhost:8080/jparest/jparest/demo/some_text einfacher Rest-Aufruf
    - http://localhost:8080/jparest/jparest/example_json REST mit JSON
    - http://localhost:8080/jparest/jparest/example_jpa/TWO JPA Beispiel
    - http://localhost:8080/jparest/jparest/example_jpa_di/three JPA DI Beispiel

### Gradle Tasks f&uuml;r den DB-Setup
 - derbyStart: Startet derby
 - derbyCreateDb: legt die Datenbank an die wir benutzen, muss nur einmal aufgerufen werden
 - flywayMigrate: legt die Datenbanktabellen an, muss nur einmal aufgerufen werden

### Erweiterungsm&ouml;glichkeiten:
 - REST-Api mit Swagger beschreiben
 - ...
 
 