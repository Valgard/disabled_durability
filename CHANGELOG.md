# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## 1.0.1 — 2026-04-26

### Changed
- Rebranded as **Disabled Durability (Update 4)** to mark this as a clean-room
  reimplementation of Serilum's original mod, ported for Hytale Update 4.
- Manifest description updated to reflect the reimplementation status.

### Added
- Credits section in README acknowledging Serilum's original mod.
- "Differences from the Original" section documenting the hardcoded behavior
  (no config file) and the `MaxDurability=0` approach used for Update 4 UI
  compatibility.

### Fixed
- `IncludesAssetPack` manifest flag corrected from `true` to `false`. The plugin
  only ships a UI customization icon under `Common/UI/Custom/`, not a full asset
  pack — the previous flag was misleading.

## 1.0.0 — 2026-04-01

### Added
- Initial release.
- `DurabilityResetSystem` — an ECS ticking system that sets `MaxDurability` to
  `0` for all non-unbreakable items in player inventories every server tick.
- Inventory coverage: Hotbar, Storage, Armor, Utility, Tool, Backpack.
- System dependency: runs before `PlayerSendInventorySystem` so client-side
  inventory state reflects the durability reset.
- Multi-version build setup via the `buildAll` Gradle task and the
  `hytaleVersions` list in `build.gradle.kts`.
- Compatibility with Hytale Server `2026.03.26-89796e57b`.
