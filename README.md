# Disabled Durability

A Hytale server plugin that disables item durability for all players. Every server tick, all items in player inventories have their durability reset — tools and equipment never break.

## How It Works

The plugin registers an ECS ticking system (`DurabilityResetSystem`) that runs **before** `PlayerSendInventorySystem` each tick. It scans all inventory sections (Hotbar, Storage, Armor, Utility, Tool, Backpack) and sets `maxDurability` to `0` for every non-unbreakable item stack.

## Requirements

- Java 25 (Amazon Corretto)
- Gradle 9.2

Both can be installed via [asdf](https://asdf-vm.com/) using the `.tool-versions` file in this repository.

## Build

```bash
# Build for the latest supported Hytale version
./gradlew build

# Build JARs for all supported versions at once
./gradlew buildAll

# Build for a specific Hytale server version
./gradlew jar -PtargetVersion=2026.03.26-89796e57b
```

Output JARs are placed in `build/libs/`.

## Installation

Copy the built JAR into your Hytale server's `mods/` directory.

The plugin depends on `Hytale:EntityModule` and will be enabled by default.

## Development

```bash
# Set up the local dev server (one-time)
./gradlew setupServer

# Run the dev server (use -Ddebug for hot-swap debugging)
./gradlew runServer
```

## Supported Versions

| Hytale Server Version    |
|--------------------------|
| `2026.03.26-89796e57b`   |
