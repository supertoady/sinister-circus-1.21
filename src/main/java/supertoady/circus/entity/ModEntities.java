package supertoady.circus.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;
import supertoady.circus.entity.custom.ShockBombEntity;

public class ModEntities {
    public static final EntityType<ShockBombEntity> BALLOON_BOMB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SinisterCircus.MOD_ID, "balloon_bomb"),
            EntityType.Builder.<ShockBombEntity>create(ShockBombEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f).build());

    public static void registerModEntities() {
        SinisterCircus.LOGGER.info("Registering Entities for " + SinisterCircus.MOD_ID);
    }
}
