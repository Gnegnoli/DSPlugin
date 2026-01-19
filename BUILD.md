📘 Build and Installation Guide — DSGitCelebration

This guide explains how to build, install, and test the DSGitCelebration plugin in PhpStorm or other JetBrains IDEs.

Step 1: Verify Prerequisites

Before building the plugin, make sure you have:

Java 17 or higher installed

Gradle installed (or use the included Gradle wrapper)

To check your Java version, run:

java -version


Tip: On Windows, you may need to set the JAVA_HOME environment variable to your JDK path.

Step 2: Build the Plugin

Open a terminal in the project folder and run the following command:

On Windows (PowerShell)
.\gradlew.bat buildPlugin

On Linux / macOS
./gradlew buildPlugin


If you don’t have the Gradle wrapper, you can generate it first:

gradle wrapper


Tip: Use the wrapper whenever possible to ensure consistent Gradle versions across machines.

Step 3: Locate the Plugin File

After the build completes successfully, the plugin archive will be located at:

build/distributions/DSPlugin-1.0.0.zip


This is the file you will install in PhpStorm.

Step 4: Install the Plugin in PhpStorm

Open PhpStorm.

Go to File → Settings (or Preferences on macOS).

Select Plugins.

Click the ⚙️ (gear) icon in the top-right corner.

Choose Install Plugin from Disk…

Navigate to build/distributions/DSPlugin-1.0.0.zip.

Select the file and click OK.

Restart PhpStorm when prompted.

Tip: If you already installed a previous version, uninstall it first to avoid conflicts.

Step 5: Configure the Plugin

After restarting:

Open Tools → Configure DSGitCelebration.

Verify that the default images and sounds are correctly set.

If needed, select your own custom images and audio files for each Git action.

Adjust the fade-in/out durations and display time to your preference.

Pro Tip: Use transparent PNGs for the best visual effect and WAV files for reliable audio playback.

Step 6: Test the Plugin

Open a Git-enabled project in PhpStorm.

Perform a Git action, such as:

Commit

Merge

Push

Pull

You should see:

Full-screen celebration overlay

Smooth fade-in and fade-out animation

Audio playback during the celebration

The IDE should remain fully usable; no dialogs will block your workflow.

Troubleshooting
Java Compilation Errors

Ensure Java 17 or higher is installed:

java -version


Set the JAVA_HOME environment variable if necessary.

Plugin Not Showing in PhpStorm

Confirm that the ZIP file was generated correctly.

Check that your PhpStorm version is 2023.3 or higher.

If the plugin was previously installed, uninstall it and reinstall.

Celebrations Do Not Trigger

Verify that your project is a Git repository.

Ensure images and audio files exist at the configured paths.

Open the configuration panel and double-check all file paths.

Additional Tips & Notes

The plugin is non-blocking: Git actions are never interrupted.

Use high-resolution PNGs for better visual quality on large monitors.

WAV audio files are recommended; MP3 may not always play reliably in Swing.

You can configure different images and sounds for each Git action for maximum fun!

Always test in a sandbox IDE before using in production.