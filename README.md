# Adventure Chronicles

Starter project for a Fabric mod targeting Minecraft 1.21.1 and Java 21.

## Requirements
- JDK 21
- Internet connection for Gradle to download dependencies
- Gradle 8.14.3 (or generate the Gradle Wrapper as described below)

## First setup
This archive includes the Gradle Wrapper scripts and wrapper configuration, but not the binary `gradle-wrapper.jar`.
If Gradle is installed globally, open a terminal in this folder and run:

```powershell
gradle wrapper --gradle-version 8.14.3
```

Then build with:

```powershell
.\gradlew.bat build
```

Run the development client with:

```powershell
.\gradlew.bat runClient
```

The mod registers `/ac hello world` as a small initial test command.
