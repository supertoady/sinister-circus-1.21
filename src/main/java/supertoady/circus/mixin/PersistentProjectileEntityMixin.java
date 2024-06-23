package supertoady.circus.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import supertoady.circus.SinisterCircus;
import supertoady.circus.particle.ModParticles;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin extends Entity{

    public PersistentProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getItemStack();

    @Shadow
    protected boolean inGround = false;

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void overrideTick(CallbackInfo info){
        Vec3d pos = this.getPos();
        if (!this.inGround && this.getCommandTags().contains("trickster-crossbow")){
            SinisterCircus.showParticlesToAll(this.getWorld(), ModParticles.JESTER_CROSSBOW_TRAIL, pos, 0, 1, 0);
        }
    }
}
