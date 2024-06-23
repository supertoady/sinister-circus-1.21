package supertoady.circus.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import supertoady.circus.item.ModItems;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void getArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> infoReturnable){
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isEmpty() && !(player.getActiveHand() == hand && player.getItemUseTimeLeft() > 0)){
            if (itemStack.isOf(ModItems.TRICKSTER_CROSSBOW)) {
                if (!player.handSwinging && CrossbowItem.isCharged(itemStack)){
                    infoReturnable.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
                }
            }
        }
    }
}
