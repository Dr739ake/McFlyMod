# MC Fly Mod

Minimal Fabric mod that adds a client keybind. Press `G` in game to call a Java function and print a status message to the action bar.

## Files to edit

- `src/main/java/com/example/mcflymod/McFlyModClient.java`: change `runCustomFunction` to do whatever you want the keybind to trigger.

## Build notes

This repository includes the source and Gradle build files, but it does not include a Gradle wrapper because `gradle` is not installed in this environment.

If you have Gradle installed locally, generate the wrapper with:

```bash
gradle wrapper
```

Then run the development client with:

```bash
./gradlew runClient
```