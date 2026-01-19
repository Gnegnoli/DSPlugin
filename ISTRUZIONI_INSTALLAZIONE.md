# 🚀 Instructions for Compiling and Installing the Plugin

## ✅ Step 1: Verify that you have Java installed

Open PowerShell and verify:
```powershell
java-version
```

You must have Java 17 or higher. If you don't have it, download it from: https://adoptium.net/

## ✅ Step 2: Download the Gradle Wrapper JAR (if necessary)

If the `gradle\wrapper\gradle-wrapper.jar` file does not exist:

1. Go to: https://services.gradle.org/distributions/gradle-8.5-bin.zip
2. Or download the JAR directly from: https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar
3. Save the file as `gradle\wrapper\gradle-wrapper.jar` in your project folder.

**OR** Use IntelliJ IDEA to compile (see Alternate Step below).

## ✅ Step 3: Compile the Plugin

Open PowerShell in your project folder and run:

```powershell
.\gradlew.bat buildPlugin
```

The first time it downloads Gradle automatically (this may take a few minutes).

## ✅ Step 4: Find the Compiled Plugin

After compilation, the ZIP file will be located at:
```
build\distributions\DSPlugin-1.0.0.zip
```

## ✅ Step 5: Install in PHPStorm

1. Open **PHPStorm**
2. Go to **File** → **Settings** (or **Preferences** on Mac)
3. Click **Plugins**
4. Click the ⚙️ (gear) icon in the top right
5. Select **Install Plugin from Disk...**
6. Navigate to `build\distributions\DSPlugin-1.0.0.zip`
7. Select the file and click **OK**
8. PHPStorm will ask you to restart - click **Restart IDE**

## ✅ Step 6: Configure the Plugin

After Restart:
1. Go to **Tools** → **Configure Git Celebration**
2. For each action (commit, merge, pull, etc.), verify or configure:
- **Image**: Select the image file (PNG, JPG, GIF, BMP)
- **Sound**: Select the audio file (WAV, MP3, OGG)
3. Click **OK** to save

## ✅ Step 7: Test!

1. Open a Git project in PHPStorm
2. Perform a **commit**, **merge**, **push**, or **pull**
3. You should see the image fade in full screen and hear the sound! 🎉

---

## 🔄 Alternative Step: Compile with IntelliJ IDEA

If you're having trouble with Gradle, you can use IntelliJ IDEA:

1. Open the project in **IntelliJ IDEA** (not PHPStorm)
2. Go to **File** → **Project Structure** → **Project Settings** → **Project**
- Set **SDK**: Java 17
- Set **Language level**: 17
3. Go to **File** → **Settings** → **Build, Execution, Deployment** → **Build Tools** → **Gradle**
- Select **Use Gradle from**: 'gradle-wrapper.properties' file
4. Open a terminal in IntelliJ and run:
```
./gradlew buildPlugin
```
5. The plugin will be in `build/distributions/DSPlugin-1.0.0.zip`

---

## ❌ Troubleshooting

### Error: "JAVA_HOME is not set"
- Install Java 17+ from https://www.java.com/it/download/windows_manual.jsp
- Set the JAVA_HOME environment variable

### Error: "Gradle wrapper JAR not found"
- Manually download `gradle-wrapper.jar` (see Step 2)
- Or use IntelliJ IDEA to compile

### Plugin not appearing in PHPStorm
- Verify that PHPStorm is version 2023.3 or higher
- Verify that the ZIP file was created correctly
- If the plugin is already installed, uninstall and reinstall it

### Celebrations not working
- Verify that the project is a Git repository
- Verify that the images and sounds are in the correct paths
- Open the configuration and verify the file paths.

---

## 📝 Notes

- Images must be in PNG, JPG, GIF, or BMP format.
- Sounds must be in WAV, MP3, or OGG format.
- The plugin only works with Git projects.
- Images are automatically scaled to fit the screen.
