package supertoady.circus.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class JesterCrossbowTrailParticle extends SpriteBillboardParticle {
    JesterCrossbowTrailParticle(ClientWorld clientWorld, double x, double y, double z, double dx, double dy, double dz) {
        super(clientWorld, x, y, z, dx, dy, dz);

        this.velocityX = dx;
        this.velocityY = dy;
        this.velocityZ = dz;

        //Set max age
        this.maxAge = 15;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        return this.scale * (float) easeOutElastic(f);
    }

    double easeOutElastic(double x) {
        double c4 = (2 * Math.PI) / 3;

        if (x == 0) {
            return 0;
        } else if (x == 1) {
            return 1;
        } else {
            return Math.pow(2, -10 * x) * Math.sin((x * 10 - 0.75) * c4) + 1;
        }
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            JesterCrossbowTrailParticle trailParticle = new JesterCrossbowTrailParticle(clientWorld, d, e, f, g, h, i);
            trailParticle.setSprite(this.spriteProvider);
            trailParticle.scale(1.0f);
            return trailParticle;
        }
    }
}
