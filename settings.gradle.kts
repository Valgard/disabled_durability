rootProject.name = "dev.valgard"

plugins {
    id("dev.scaffoldit") version "0.2.+"
}

hytale {
    usePatchline("release")
    useVersion("latest")

    manifest {
        Group = "Valgard"
        Name = "DisabledDurability"
        Main = "dev.valgard.disableddurability.DisabledDurability"
        Dependencies = mapOf("Hytale:EntityModule" to "*")
    }
}
