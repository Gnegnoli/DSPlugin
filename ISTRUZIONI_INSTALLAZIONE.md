# 🚀 Istruzioni per Compilare e Installare il Plugin

## ✅ Passo 1: Verifica che hai Java installato

Apri PowerShell e verifica:
```powershell
java -version
```

Devi avere Java 17 o superiore. Se non ce l'hai, scaricalo da: https://adoptium.net/

## ✅ Passo 2: Scarica Gradle Wrapper JAR (se necessario)

Se il file `gradle\wrapper\gradle-wrapper.jar` non esiste:

1. Vai su: https://services.gradle.org/distributions/gradle-8.5-bin.zip
2. Oppure scarica direttamente il JAR da: https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar
3. Salva il file come `gradle\wrapper\gradle-wrapper.jar` nella cartella del progetto

**OPPURE** usa IntelliJ IDEA per compilare (vedi Passo Alternativo sotto)

## ✅ Passo 3: Compila il Plugin

Apri PowerShell nella cartella del progetto e esegui:

```powershell
.\gradlew.bat buildPlugin
```

La prima volta scaricherà Gradle automaticamente (può richiedere alcuni minuti).

## ✅ Passo 4: Trova il Plugin Compilato

Dopo la compilazione, il file ZIP sarà in:
```
build\distributions\DSPlugin-1.0.0.zip
```

## ✅ Passo 5: Installa in PHPStorm

1. Apri **PHPStorm**
2. Vai su **File** → **Settings** (o **Preferences** su Mac)
3. Clicca su **Plugins**
4. Clicca sull'icona ⚙️ (ingranaggio) in alto a destra
5. Seleziona **Install Plugin from Disk...**
6. Naviga fino a `build\distributions\DSPlugin-1.0.0.zip`
7. Seleziona il file e clicca **OK**
8. PHPStorm ti chiederà di riavviare - clicca **Restart IDE**

## ✅ Passo 6: Configura il Plugin

Dopo il riavvio:
1. Vai su **Tools** → **Configura Git Celebration**
2. Per ogni azione (commit, merge, pull, ecc.), verifica o configura:
   - **Immagine**: Seleziona il file immagine (PNG, JPG, GIF, BMP)
   - **Suono**: Seleziona il file audio (WAV, MP3, OGG)
3. Clicca **OK** per salvare

## ✅ Passo 7: Testa!

1. Apri un progetto Git in PHPStorm
2. Fai un **commit**, **merge**, **push** o **pull**
3. Dovresti vedere l'immagine full screen con dissolvenza e sentire il suono! 🎉

---

## 🔄 Passo Alternativo: Compila con IntelliJ IDEA

Se hai problemi con Gradle, puoi usare IntelliJ IDEA:

1. Apri il progetto in **IntelliJ IDEA** (non PHPStorm)
2. Vai su **File** → **Project Structure** → **Project Settings** → **Project**
   - Imposta **SDK**: Java 17
   - Imposta **Language level**: 17
3. Vai su **File** → **Settings** → **Build, Execution, Deployment** → **Build Tools** → **Gradle**
   - Seleziona **Use Gradle from**: 'gradle-wrapper.properties' file
4. Apri il terminale in IntelliJ e esegui:
   ```
   ./gradlew buildPlugin
   ```
5. Il plugin sarà in `build/distributions/DSPlugin-1.0.0.zip`

---

## ❌ Risoluzione Problemi

### Errore: "JAVA_HOME is not set"
- Installa Java 17+ da https://adoptium.net/
- Imposta la variabile d'ambiente JAVA_HOME

### Errore: "Gradle wrapper JAR not found"
- Scarica manualmente `gradle-wrapper.jar` (vedi Passo 2)
- Oppure usa IntelliJ IDEA per compilare

### Il plugin non appare in PHPStorm
- Verifica che PHPStorm sia versione 2023.3 o superiore
- Controlla che il file ZIP sia stato creato correttamente
- Se il plugin è già installato, disinstallalo e reinstalla

### Le celebrazioni non funzionano
- Verifica che il progetto sia un repository Git
- Controlla che le immagini e suoni siano nei percorsi corretti
- Apri la configurazione e verifica i percorsi dei file

---

## 📝 Note

- Le immagini devono essere in formato: PNG, JPG, GIF o BMP
- I suoni devono essere in formato: WAV, MP3 o OGG
- Il plugin funziona solo con progetti Git
- Le immagini vengono scalate automaticamente per adattarsi allo schermo

