package supertoady.circus.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import supertoady.circus.SinisterCircus;

public class ConfettiParticle {
    @Environment(value = EnvType.CLIENT)
    public static class Provider
            implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprite;

        public Provider(SpriteProvider spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientLevel, double x, double y, double z, double dX, double dY, double dZ) {

            ConfettiPieceParticle overlayParticle = new ConfettiPieceParticle(clientLevel, x, y, z, dX, dY, dZ);
            overlayParticle.setSprite(this.sprite);
            return overlayParticle;
        }
    }

    @Environment(value = EnvType.CLIENT)
    public static class ConfettiPieceParticle extends SpriteBillboardParticle {
        Quaternionf rotation;
        Quaternionf oldRotation;
        Vector3f rotationAxis;
        float rotationSpeed;

        static final int MAX_LIFETIME = 2500;
        static final int MAX_LIFETIME_ON_FLOOR = 500;
        static final float TERMINAL_VELOCITY = .24f;
        static final float GRAVITY = -0.04f;
        static final double MAX_ROTATION_SPEED = .5;
        static final float HORIZONTAL_SPEED = 0.04f;

        ConfettiPieceParticle(ClientWorld clientLevel, double x, double y, double z, double dX, double dY, double dZ) {
            super(clientLevel, x, y, z);

            this.maxAge = MAX_LIFETIME;
            this.gravityStrength = GRAVITY;

            this.velocityX = dX;
            this.velocityY = dY;
            this.velocityZ = dZ;

            this.rotation = new Quaternionf();
            this.oldRotation = this.rotation;
            this.rotation.rotateXYZ((float) (Math.random() * Math.PI), (float) (Math.random() * Math.PI), (float) (Math.random() * Math.PI));
            this.rotationAxis = new Vector3f(random.nextFloat(), random.nextFloat(), random.nextFloat()).normalize();
            this.rotationSpeed = (float) (Math.random() * MAX_ROTATION_SPEED);

            this.setColor((float) Math.random(), (float) Math.random(), (float) Math.random());
        }

        @Override
        public @NotNull ParticleTextureSheet getType() {
            return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
        }

        Quaternionf getRotation(Camera camera, float tickPercentage) {
            if (this.onGround) {
                Quaternionf quaternionf = new Quaternionf();
                quaternionf.rotationXYZ((float) -(Math.PI * .5), 0, 0);

                return faceTowardsCamera(quaternionf, camera);
            } else {
                return faceTowardsCamera(new Quaternionf(oldRotation).slerp(rotation, tickPercentage), camera);
            }

        }

        Quaternionf faceTowardsCamera(Quaternionf rotation, Camera camera) {
            Vector3f particleForward = new Vector3f(0, 0, -1).rotate(rotation);
            Vector3f cameraDelta = new Vector3f((float) x, (float) y, (float) z).sub(camera.getPos().toVector3f());

            if (particleForward.dot(cameraDelta) < 0) {
                rotation.rotateY((float) Math.PI);
            }

            return rotation;
        }

        @Override
        public void tick() {
            boolean wasStoppedByCollision = this.onGround;

            //if (maxAge % 5 == 0) {
                //this.onGround = false;
            //}

            if (wasStoppedByCollision && maxAge > MAX_LIFETIME_ON_FLOOR) {
                maxAge = MAX_LIFETIME_ON_FLOOR;
            }

            this.prevPosX = this.x;
            this.prevPosY = this.y;
            this.prevPosZ = this.z;
            this.oldRotation = new Quaternionf(rotation);

            checkLifetime();

            if (this.dead) {
                return;
            }

            this.velocityY += this.gravityStrength;

            if (-this.velocityY > TERMINAL_VELOCITY) {
                this.velocityY = -TERMINAL_VELOCITY;
            }

            this.velocityY += (Math.random() - .5) * HORIZONTAL_SPEED;

            this.velocityX -= this.velocityX * 0.075;
            this.velocityX += (Math.random() - .5) * HORIZONTAL_SPEED;

            this.velocityZ -= this.velocityZ * 0.075;
            this.velocityZ += (Math.random() - .5) * HORIZONTAL_SPEED;

            if(wasStoppedByCollision) {
                this.velocityX = 0;
                this.velocityZ = 0;
            }

            this.move(this.velocityX, this.velocityY, this.velocityZ);

            Quaternionf deltaRotation = new Quaternionf().rotateAxis(rotationSpeed, rotationAxis.x, rotationAxis.y, rotationAxis.z);
            rotation.mul(deltaRotation);
        }

        @Override
        public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickPercentage) {
            var offset = getOffset();

            offsetY(offset);
            var rotation = getRotation(camera, tickPercentage);
            this.method_60373(vertexConsumer, camera, rotation, tickPercentage);
            offsetY(-offset);
        }

        double getOffset() {
            return ((maxAge / (double) MAX_LIFETIME_ON_FLOOR)) * .02 + rotationAxis.x * .0015;
        }

        void offsetY(double offset) {
            this.prevPosY += offset;
            this.y += offset;
        }

        protected void checkLifetime() {

            if (this.maxAge-- <= 0) {
                this.markDead();
            }
        }

        @Override
        public float getSize(float f) {
            return .1f;
        }
    }
}
