package supertoady.circus.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import supertoady.circus.effect.ModStatusEffects;

import java.util.Random;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Inject(method = "getMovementMultiplier", at = @At("HEAD"), cancellable = true)
    private static void movementMultiplierInjector(boolean positive, boolean negative, CallbackInfoReturnable<Float> info){

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        boolean hasDizzy = false;
        for (StatusEffectInstance effects : player.getStatusEffects()){
            if (effects.getEffectType() == ModStatusEffects.DIZZY){
                hasDizzy = true;
            }
        }

        if (hasDizzy){
            if (positive == negative) {
                info.setReturnValue(0.0F);
            } else {

                info.setReturnValue(positive ? -1.0F : 0.1F);
            }
        }
    }
}
