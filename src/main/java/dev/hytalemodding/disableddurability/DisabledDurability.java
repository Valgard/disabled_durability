package dev.hytalemodding.disableddurability;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class DisabledDurability extends JavaPlugin {

    public DisabledDurability(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        getEntityStoreRegistry().registerSystem(new DurabilityResetSystem());
        getLogger().atInfo().log("Disabled Durability aktiv — Haltbarkeit für alle Tools deaktiviert.");
    }
}
