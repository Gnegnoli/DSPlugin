# Guida alla Compilazione e Installazione

## Passo 1: Verifica Prerequisiti

Assicurati di avere:
- Java 17 o superiore installato
- Gradle installato (o usa il wrapper Gradle incluso)

Per verificare Java:
```bash
java -version
```

## Passo 2: Compila il Plugin

Apri un terminale nella cartella del progetto e esegui:

### Su Windows (PowerShell):
```powershell
.\gradlew.bat buildPlugin
```

### Su Linux/Mac:
```bash
./gradlew buildPlugin
```

Se non hai il wrapper Gradle, installalo prima:
```bash
gradle wrapper
```

## Passo 3: Trova il File del Plugin

Dopo la compilazione, il plugin sarà disponibile in:
```
build/distributions/DSPlugin-1.0.0.zip
```

## Passo 4: Installa il Plugin in PHPStorm

1. Apri PHPStorm
2. Vai su **File** → **Settings** (o **Preferences** su Mac)
3. Seleziona **Plugins**
4. Clicca sull'icona ⚙️ (ingranaggio) in alto
5. Seleziona **Install Plugin from Disk...**
6. Naviga fino a `build/distributions/DSPlugin-1.0.0.zip`
7. Seleziona il file e clicca **OK**
8. PHPStorm ti chiederà di riavviare - clicca **Restart IDE**

## Passo 5: Configura il Plugin

Dopo il riavvio:
1. Vai su **Tools** → **Configura Git Celebration**
2. Verifica che le immagini e suoni siano configurati correttamente
3. Se necessario, seleziona i tuoi file personalizzati

## Passo 6: Testa il Plugin

1. Apri un progetto Git in PHPStorm
2. Fai un commit, merge, push o pull
3. Dovresti vedere l'immagine full screen con dissolvenza e sentire il suono!

## Risoluzione Problemi

### Errore di compilazione Java
- Verifica di avere Java 17+: `java -version`
- Se necessario, imposta `JAVA_HOME` nel sistema

### Plugin non appare in PHPStorm
- Verifica che il file ZIP sia stato creato correttamente
- Controlla che PHPStorm sia compatibile (versione 2023.3 o superiore)
- Verifica che il plugin non sia già installato (disinstallalo e reinstalla)

### Le celebrazioni non funzionano
- Verifica che il progetto sia un repository Git
- Controlla che le immagini e suoni siano nei percorsi corretti
- Apri la configurazione e verifica i percorsi dei file

