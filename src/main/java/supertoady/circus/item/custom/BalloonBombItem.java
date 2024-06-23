package supertoady.circus.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import supertoady.circus.item.ModItems;

public class BalloonBombItem extends Item {
    public BalloonBombItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()){
            ItemStack stack = user.getStackInHand(hand);
            stack.decrement(1);

            user.giveItemStack(new ItemStack(ModItems.BALLOON_ANIMAL));
        }
        return super.use(world, user, hand);
    }
}
