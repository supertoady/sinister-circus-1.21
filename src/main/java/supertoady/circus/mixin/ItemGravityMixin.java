package supertoady.circus.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import supertoady.circus.SinisterCircus;
import supertoady.circus.item.ModItems;

import java.util.Random;

@Mixin(ItemEntity.class)
public abstract class ItemGravityMixin extends Entity {

    public ItemGravityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    @Inject(method = "tick", at = @At("HEAD"))
    private void addCloudParticles(CallbackInfo info){
        if (new Random().nextInt(3) == 1 && getStack().isOf(ModItems.BALLOON)){
            SinisterCircus.showParticlesToAll(this.getWorld(), ParticleTypes.CLOUD, this.getPos(), 0, 1, 0);
        }
    }

    @Inject(method = "getGravity", at = @At("HEAD"), cancellable = true)
    private void modifyGravity(CallbackInfoReturnable<Double> info) {

        if (getStack().isOf(ModItems.BALLOON)){
            info.setReturnValue(-0.01);
        }

        if (getStack().isOf(ModItems.BALLOON_ANIMAL)){
            info.setReturnValue(-0.01);
        }

        if (getStack().isOf(ModItems.BALLOON_BOMB)){
            info.setReturnValue(0.02);
        }
    }
}
