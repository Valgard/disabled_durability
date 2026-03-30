rootProject.name = "dev.hytalemodding"

plugins {
    id("dev.scaffoldit") version "0.2.+"
}

hytale {
    usePatchline("release")
    useVersion("latest")

    manifest {
        Group = "HytaleModding"
        Name = "DisabledDurability"
        Main = "dev.hytalemodding.disableddurability.DisabledDurability"
    }
}
