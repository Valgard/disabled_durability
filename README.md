<p align="center">
  <img src="sources/Logo 1.png" alt="Disabled Durability Logo" width="128">
</p>

<h1 align="center">Disabled Durability (Update 4)</h1>

<p align="center">
  A Hytale server plugin that disables item durability for all players — tools and equipment never break.
</p>

<p align="center">
  <em>An independent reimplementation of <a href="https://github.com/Serilum-Hytale/Disabled-Durability">Serilum's Disabled Durability</a>, ported for Hytale Update 4.</em>
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
| 1.0.1 | 2026.03.26-89796e57b |
| 1.0.0 | 2026.03.26-89796e57b |

## Credits

Original concept and implementation by **[Serilum](https://github.com/Serilum-Hytale/Disabled-Durability)**. This plugin is a clean-room reimplementation: it reproduces the **behavior** of Serilum's mod and adapts it to Hytale Update 4, but contains **no source code derived from the original**. All credit for the original idea goes to Serilum.

### Differences from the Original

- **No configuration file.** Serilum's mod exposes a `disableItemDurability` toggle; this reimplementation is hardcoded — install means active, uninstall means inactive.
- **Update 4 UI compatibility.** The original repairs items every inventory-change event, which was visually invisible pre-Update-4 but would cause durability-bar flicker on Update 4. This plugin instead sets `MaxDurability` to `0`, which suppresses the bar entirely and matches the original's *intended* visual behavior on the new platform.

## License

All rights reserved.
