# Java and UI Implementation Notes

This document describes the technical approach used in DSGitCelebration.

## Java Version

The plugin is built using:

- Java 17
- IntelliJ Platform SDK 2023.3+

This is required by modern JetBrains IDEs and the Gradle IntelliJ Plugin.

## UI Technology

The plugin uses **Swing** for rendering the celebration overlay.

Key design choices:
- Undecorated `JFrame`
- Transparent PNG support
- Opacity-based fade-in and fade-out
- Non-blocking timers (`javax.swing.Timer`)

JavaFX is intentionally not used to avoid:
- Additional runtime dependencies
- UI thread conflicts inside the IDE

## Transparency and Foreground Behavior

The overlay:
- Uses PNG images with alpha channel
- Is rendered in the foreground using `setAlwaysOnTop(true)`
- Automatically disposes after fade-out
- Does not block IDE input after completion

## Audio Playback

Audio is handled using `javax.sound.sampled.Clip`:
- Supports WAV files
- Plays asynchronously
- Automatically releases resources after playback

If audio fails to load, the plugin falls back gracefully without interrupting
the user workflow.

## Error Handling

All visual and audio effects are optional:
- If a resource cannot be loaded, the plugin fails silently
- No blocking dialogs are shown
- Git actions are never interrupted

## Rationale

The implementation prioritizes:
- Stability
- Compatibility with JetBrains IDEs
- Minimal performance impact
- Clean resource disposal
