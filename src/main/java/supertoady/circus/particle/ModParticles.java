package supertoady.circus.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;

public class ModParticles {
    public static final SimpleParticleType CONFETTI = FabricParticleTypes.simple();
    public static final SimpleParticleType BONK = FabricParticleTypes.simple();
    public static final SimpleParticleType JESTER_CROSSBOW_TRAIL = FabricParticleTypes.simple();

    public static void registerModParticles(){
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SinisterCircus.MOD_ID, "confetti"), CONFETTI);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SinisterCircus.MOD_ID, "bonk"), BONK);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SinisterCircus.MOD_ID, "jester_crossbow_trail"), JESTER_CROSSBOW_TRAIL);
    }
}
