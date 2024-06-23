package supertoady.circus.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import supertoady.circus.SinisterCircus;
import supertoady.circus.effect.ModStatusEffects;
import supertoady.circus.entity.ModEntities;
import supertoady.circus.item.ModItems;
import supertoady.circus.particle.ModParticles;

import java.util.List;
import java.util.Random;

public class ShockBombEntity extends ThrownItemEntity {

    public ShockBombEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShockBombEntity(World world, LivingEntity owner) {
        super(ModEntities.BALLOON_BOMB, owner, world); // null will be changed later
    }

    @Override
    protected double getGravity() {
        return 0.0075;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BALLOON_BOMB;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {
        return super.createSpawnPacket(entityTrackerEntry);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

        World world = this.getWorld();
        Vec3d pos = this.getPos();

        if (!world.isClient()){
            world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_TWINKLE, SoundCategory.MASTER, 0.5f, 1);

            SinisterCircus.showParticlesToAll(world, ModParticles.CONFETTI, pos, 0, 120, 0.2);
            SinisterCircus.showParticlesToAll(world, ParticleTypes.EXPLOSION, pos, 0, 1, 0);

            double boxSize = 2.5;
            Box omgthefrogisintheBox = new Box(pos.add(-boxSize, -boxSize, -boxSize), pos.add(boxSize, boxSize, boxSize));

            List<Entity> entities = world.getOtherEntities(null, omgthefrogisintheBox);

            for (Entity entity : entities){
                if (entity.isLiving()){
                    LivingEntity livingEntity = ((LivingEntity) entity);
                    if (new Random().nextInt(3) == 1) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(ModStatusEffects.DIZZY, 20 * 6, 0));
                    }

                    Vec3d dir = livingEntity.getPos().subtract(pos).normalize().multiply(0.5);
                    dir = new Vec3d(dir.x, (dir.y / 2) + 0.2, dir.z);
                    livingEntity.addVelocity(dir);
                }
            }

            this.kill();
        }

        super.onBlockHit(blockHitResult);
    }
}
