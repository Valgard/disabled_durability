# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Hytale server plugin ("DisabledDurability") that disables item durability for all players. Built on the ScaffoldIt Gradle plugin (`dev.scaffoldit`) for the Hytale modding toolchain.

**Language:** Java 25 (Amazon Corretto), Gradle 9.2 (via asdf)

## Build & Run

```bash
# Compile
./gradlew build

# Build JARs for all supported Hytale versions (defined in build.gradle.kts → hytaleVersions)
./gradlew buildAll

# Build JAR for a specific Hytale version
./gradlew jar -PtargetVersion=2026.03.26-89796e57b

# Set up dev server (one-time, creates devserver/)
./gradlew setupServer

# Start dev server (hot-swap with -Ddebug)
./gradlew runServer
# or: ./gradlew devServer (alias)
```

No tests — the plugin is verified directly against the dev server.

## Architecture

The plugin follows Hytale's **ECS (Entity Component System)** pattern:

- **`DisabledDurability`** (JavaPlugin entry point): Registers the ECS system with `EntityStoreRegistry` in `setup()`.
- **`DurabilityResetSystem`** (EntityTickingSystem): Runs every server tick for each Player entity. Iterates over all inventory sections (Hotbar, Storage, Armor, Utility, Tool, Backpack) and sets `maxDurability` to 0 for all non-unbreakable items.
  - **System ordering:** Declares `BEFORE PlayerSendInventorySystem` as a dependency so durability values are reset before inventory state is sent to the client.

## Manifest & Versioning

- `manifest.json` is generated from the `hytale {}` DSL in `settings.gradle.kts`.
- `build.gradle.kts` patches the `ServerVersion` field in the manifest at build time, based on `-PtargetVersion`.
- `buildAll` produces separate JARs per Hytale server version with versioned filenames.

## Hytale API Conventions

- Components are read via `ArchetypeChunk.getComponent(index, componentType)` — nullable, always check.
- Entity type queries use static `getComponentType()` methods (e.g. `Player.getComponentType()`).
- Item mutation uses an immutable builder pattern: `stack.withMaxDurability(0)` returns a new ItemStack.
- System dependencies control tick order via `SystemDependency<>(Order.BEFORE/AFTER, TargetSystem.class)`.
