package supertoady.circus.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.function.Predicate;

public class DizzyParticle extends SpriteBillboardParticle {

    Entity entityTarget;

    public DizzyParticle(ClientWorld clientWorld, double x, double y, double z, double dx, double dy, double dz) {
        super(clientWorld, x, y, z, dx, dy, dz);

        this.velocityX = dx;
        this.velocityY = dy;
        this.velocityZ = dz;

        this.entityTarget = clientWorld.getClosestPlayer(x, y, z, 999, false);

        //Set max age
        this.maxAge = 250;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public void tick() {
        if (entityTarget != null && false){
            //Vec3d headPos = entityTarget.getEyePos();

            //double x, z;
            //angle = (angle + 1) % 360;

            //double radians = Math.toRadians(angle);
            //x = Math.cos(radians);
            //z = Math.sin(radians);

            //this.setPos(headPos.x + x, headPos.y + 0.5, headPos.z + z);

            Vec3d velocity = entityTarget.getVelocity();
            this.setVelocity(velocity.x, velocity.y, velocity.z);

            this.setPos(this.x, entityTarget.getEyeY() + 0.5, this.z);
        }
        super.tick();
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            DizzyParticle trailParticle = new DizzyParticle(clientWorld, d, e, f, g, h, i);
            trailParticle.setSprite(this.spriteProvider);
            trailParticle.scale(1.0f);
            return trailParticle;
        }
    }
}
