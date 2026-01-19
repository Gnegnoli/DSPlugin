# DSGitCelebration

DSGitCelebration is a JetBrains IDE plugin that adds Dark Souls–style visual and
audio celebrations to Git actions such as commit, merge, push, and pull.

When a Git action is executed, the plugin displays a transparent animated
overlay with fade-in and fade-out effects and optionally plays a custom sound.
The experience is immersive but non-blocking, allowing you to continue working
in the IDE immediately after the animation ends.

## Features

- Transparent PNG overlay
- Smooth fade-in and fade-out animation
- Custom image and sound per Git action
- Non-blocking UI (IDE remains usable)
- Foreground display
- Fully configurable from the Tools menu
- Compatible with PhpStorm and other JetBrains IDEs

## Supported IDEs

- PhpStorm 2023.3+
- IntelliJ IDEA (Community & Ultimate)
- Other JetBrains IDEs compatible with the IntelliJ Platform

## Configuration

Open the configuration panel from:
Tools → Configure DSGitCelebration

From there you can:
- Assign images to Git actions
- Assign sounds to Git actions
- Adjust display and fade durations
- Enable or disable celebrations

## Installation

See `ISTRUZIONI_INSTALLAZIONE.md`.

## Building from Source

See `BUILD.md`.

## License

MIT License.
