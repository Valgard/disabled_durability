<p align="center">
  <img src="sources/Logo 1.png" alt="Disabled Durability Logo" width="128">
</p>

<h1 align="center">Disabled Durability</h1>

<p align="center">
  A Hytale server plugin that disables item durability for all players — tools and equipment never break.
</p>

---

## Features

- Resets durability for **all items** across all inventory sections (Hotbar, Storage, Armor, Utility, Tool, Backpack)
- Runs every server tick — items are always protected, even newly acquired ones
- Zero configuration — just install and forget

## How It Works

The plugin registers an ECS ticking system (`DurabilityResetSystem`) that runs **before** `PlayerSendInventorySystem` each tick. It sets `maxDurability` to `0` for every non-unbreakable item stack in every player's inventory.

## Installation

1. Download the JAR matching your server version from [Releases](../../releases)
2. Place it in your server's `mods/` directory
3. Start (or restart) the server

## Building from Source

Requires **Java 25** (Amazon Corretto) and **Gradle 9.2**. Both can be installed via [asdf](https://asdf-vm.com/) using the `.tool-versions` file in this repository.

```bash
# Single build
./gradlew build

# Build for all supported Hytale versions
./gradlew buildAll

# Build for a specific Hytale server version
./gradlew jar -PtargetVersion=2026.03.26-89796e57b
```

Output JARs are placed in `build/libs/`.

## Compatibility

| Plugin Version | Hytale Server Version |
|---|---|
| 1.0.0 | 2026.03.26-89796e57b |

## License

All rights reserved.
