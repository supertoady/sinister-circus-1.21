package supertoady.circus.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import supertoady.circus.SinisterCircus;
import supertoady.circus.particle.ModParticles;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkEntityMixin extends Entity {
    public FireworkEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract boolean hasExplosionEffects();

    @Inject(method = "explode", at = @At("HEAD"))
    private void injectExplode(CallbackInfo info){
        if (hasExplosionEffects()){
            SinisterCircus.showParticlesToAll(this.getWorld(), ModParticles.CONFETTI, this.getPos(), 0, 75, 0.25);
        }
    }
}
