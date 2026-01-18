# ⚠️ Problema: Java Version

## Il Problema

L'errore indica che Gradle sta usando **Java 8**, ma il plugin IntelliJ Gradle richiede **Java 11+**.

## Soluzione 1: Installa Java 17 (CONSIGLIATO)

Il plugin stesso richiede Java 17 per compilare, quindi è meglio installare Java 17:

1. **Scarica Java 17** da: https://adoptium.net/temurin/releases/?version=17
2. **Installa Java 17**
3. **Imposta JAVA_HOME**:
   - Apri **Impostazioni di Sistema** → **Variabili d'ambiente**
   - Crea/modifica `JAVA_HOME` e imposta il percorso di Java 17 (es: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`)
   - Aggiungi `%JAVA_HOME%\bin` al `PATH`
4. **Verifica**:
   ```powershell
   java -version
   ```
   Dovresti vedere Java 17

5. **Riprova la compilazione**:
   ```powershell
   .\gradlew.bat buildPlugin
   ```

## Soluzione 2: Usa Java 11+ Temporaneamente

Se hai già Java 11 o superiore installato ma non è quello predefinito:

1. **Trova il percorso di Java 11+**:
   ```powershell
   where java
   ```

2. **Imposta JAVA_HOME temporaneamente**:
   ```powershell
   $env:JAVA_HOME = "C:\percorso\a\java11"
   ```

3. **Riprova la compilazione**

## Soluzione 3: Usa IntelliJ IDEA per Compilare

Se hai IntelliJ IDEA installato:

1. Apri il progetto in **IntelliJ IDEA** (non PHPStorm)
2. IntelliJ IDEA gestirà automaticamente la versione di Java corretta
3. Vai su **Build** → **Build Project** o usa il terminale integrato:
   ```
   ./gradlew buildPlugin
   ```

## Verifica Versione Java

Per verificare quale versione di Java stai usando:

```powershell
java -version
javac -version
```

Se vedi Java 8, devi installare Java 17.

