package supertoady.circus.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;

public class ModParticles {
    public static final SimpleParticleType CONFETTI = registerParticle("confetti");
    public static final SimpleParticleType BONK = registerParticle("bonk");
    public static final SimpleParticleType JESTER_CROSSBOW_TRAIL = registerParticle("jester_crossbow_trail");
    public static final SimpleParticleType DIZZY = registerParticle("dizzy");

    public static SimpleParticleType registerParticle(String name){
        SimpleParticleType particleType = FabricParticleTypes.simple();
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SinisterCircus.MOD_ID, name), particleType);
        return particleType;
    }

    public static void registerModParticles(){
        SinisterCircus.LOGGER.info("Registering Mod Particle for " + SinisterCircus.MOD_ID);
    }
}
