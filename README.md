# Git Celebration Plugin per PHPStorm

Plugin per PHPStorm che celebra le azioni Git con immagini full screen e suoni personalizzati!

## Caratteristiche

- 🎉 **Celebrazioni automatiche**: Ogni volta che esegui un'azione Git (commit, merge, push, pull, ecc.), il plugin mostra un'immagine full screen con dissolvenza e riproduce un suono
- 🖼️ **Immagini personalizzate**: Configura un'immagine diversa per ogni tipo di azione Git
- 🔊 **Suoni personalizzati**: Aggiungi suoni unici per ogni azione
- ⚙️ **Configurabile**: Interfaccia grafica per configurare facilmente immagini, suoni e durate delle animazioni

## Azioni Supportate

- **Commit**: Quando fai un commit
- **Merge**: Quando completi un merge
- **Push**: Quando fai push delle modifiche
- **Pull**: Quando fai pull delle modifiche
- **Rebase**: Quando completi un rebase
- **Checkout**: Quando cambi branch
- **Branch**: Quando crei o elimini un branch

## Installazione

### Build del Plugin

1. Assicurati di avere Java 17+ installato
2. Esegui il build:
```bash
./gradlew buildPlugin
```

3. Il plugin sarà disponibile in `build/distributions/`

### Installazione Manuale

1. Apri PHPStorm
2. Vai su `File` → `Settings` → `Plugins`
3. Clicca su `Install Plugin from Disk...`
4. Seleziona il file `.zip` del plugin
5. Riavvia PHPStorm

## Configurazione

1. Dopo l'installazione, vai su `Tools` → `Configura Git Celebration`
2. Per ogni azione Git, puoi configurare:
   - **Immagine**: Seleziona un file immagine (PNG, JPG, GIF, BMP)
   - **Suono**: Seleziona un file audio (WAV, MP3, OGG)
3. Nella tab "Impostazioni" puoi configurare:
   - Durata visualizzazione (500-10000 ms)
   - Durata dissolvenza in entrata (100-2000 ms)
   - Durata dissolvenza in uscita (100-2000 ms)

## Struttura del Progetto

```
DSPlugin/
├── build.gradle.kts          # Configurazione build Gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dsplugin/gitcelebration/
│   │   │       ├── CelebrationManager.java          # Manager principale
│   │   │       ├── CelebrationConfig.java           # Configurazione persistente
│   │   │       ├── CelebrationDisplay.java          # Display full screen
│   │   │       ├── GitCelebrationStartupActivity.java  # Attività di startup
│   │   │       ├── GitActionListenerManager.java    # Manager listener
│   │   │       ├── GitActionListener.java           # Listener per repository Git
│   │   │       ├── GitCelebrationCheckinHandlerFactory.java  # Factory per commit
│   │   │       ├── GitCelebrationCheckinHandler.java # Handler per commit
│   │   │       └── actions/
│   │   │           └── ConfigureAction.java         # Azione di configurazione
│   │   └── resources/
│   │       └── META-INF/
│   │           └── plugin.xml                       # Configurazione plugin
└── README.md
```

## Sviluppo

### Requisiti

- Java 17+
- Gradle 7+
- IntelliJ IDEA o PHPStorm per lo sviluppo

### Build

```bash
./gradlew buildPlugin
```

### Test

```bash
./gradlew test
```

### Run Plugin

```bash
./gradlew runIde
```

## Come Funziona

1. **Intercettazione Commit**: Il plugin usa `CheckinHandlerFactory` per intercettare i commit Git attraverso `GitCelebrationCheckinHandler`
2. **Intercettazione Altre Azioni**: Per merge, push, pull, ecc., il plugin usa `GitRepositoryChangeListener` per monitorare i cambiamenti nei repository Git
3. **Display**: Quando viene rilevata un'azione Git, `CelebrationDisplay` crea una finestra full screen con l'immagine configurata e riproduce il suono associato
4. **Dissolvenza**: Le animazioni di dissolvenza sono gestite attraverso `JFrame.setOpacity()` con timer per animazioni fluide

## Personalizzazione

Puoi aggiungere immagini e suoni di default nella cartella `src/main/resources/`:
- `images/commit.png`, `images/merge.png`, ecc.
- `sounds/commit.wav`, `sounds/merge.wav`, ecc.

## Licenza

Questo plugin è rilasciato sotto licenza MIT.

## Contributi

I contributi sono benvenuti! Sentiti libero di aprire issue o pull request.

## Note

- Il plugin funziona solo con progetti Git
- Le immagini vengono scalate automaticamente per adattarsi allo schermo mantenendo le proporzioni
- I suoni vengono riprodotti in modo asincrono per non bloccare l'interfaccia

