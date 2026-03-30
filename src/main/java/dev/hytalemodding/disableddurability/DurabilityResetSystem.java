package dev.hytalemodding.disableddurability;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.dependency.Dependency;
import com.hypixel.hytale.component.dependency.Order;
import com.hypixel.hytale.component.dependency.SystemDependency;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.modules.entity.player.PlayerSendInventorySystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.Set;

public class DurabilityResetSystem extends EntityTickingSystem<EntityStore> {

    private Query<EntityStore> query;

    private final Set<Dependency<EntityStore>> dependencies = Set.of(
        new SystemDependency<>(Order.BEFORE, PlayerSendInventorySystem.class)
    );

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        if (this.query == null) {
            this.query = Player.getComponentType();
        }
        return this.query;
    }

    @Nonnull
    @Override
    public Set<Dependency<EntityStore>> getDependencies() {
        return this.dependencies;
    }

    @Override
    public void tick(
        float dt,
        int index,
        @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
        @Nonnull Store<EntityStore> store,
        @Nonnull CommandBuffer<EntityStore> commandBuffer
    ) {
        resetSection(archetypeChunk, index, InventoryComponent.Hotbar.getComponentType());
        resetSection(archetypeChunk, index, InventoryComponent.Storage.getComponentType());
        resetSection(archetypeChunk, index, InventoryComponent.Armor.getComponentType());
        resetSection(archetypeChunk, index, InventoryComponent.Utility.getComponentType());
        resetSection(archetypeChunk, index, InventoryComponent.Tool.getComponentType());
        resetSection(archetypeChunk, index, InventoryComponent.Backpack.getComponentType());
    }

    private void resetSection(
        @Nonnull ArchetypeChunk<EntityStore> chunk,
        int index,
        @Nonnull ComponentType<EntityStore, ? extends InventoryComponent> componentType
    ) {
        InventoryComponent inv = chunk.getComponent(index, componentType);
        if (inv == null) {
            return;
        }
        ItemContainer container = inv.getInventory();
        for (short slot = 0; slot < container.getCapacity(); slot++) {
            ItemStack stack = container.getItemStack(slot);
            if (stack != null && !stack.isUnbreakable()) {
                container.setItemStackForSlot(slot, stack.withMaxDurability(0));
            }
        }
    }
}
