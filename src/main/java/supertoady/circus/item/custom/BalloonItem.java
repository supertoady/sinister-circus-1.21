package supertoady.circus.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import supertoady.circus.item.ModItems;

public class BalloonItem extends Item {
    public BalloonItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient() && user.isPlayer()){
            stack.decrement(1);
            ((PlayerEntity) user).giveItemStack(ModItems.BALLOON_ANIMAL.getDefaultStack());
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 20 * 3;
    }
}
