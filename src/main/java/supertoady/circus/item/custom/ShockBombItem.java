package supertoady.circus.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import supertoady.circus.entity.custom.ShockBombEntity;

public class ShockBombItem extends Item{
    public ShockBombItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (user.getItemCooldownManager().isCoolingDown(this)){ return super.use(world, user, hand); }

        if (!user.isCreative()) user.getItemCooldownManager().set(this, 20 * 3);

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

        if (!world.isClient) {
            ShockBombEntity shockBombEntity = new ShockBombEntity(world, user);
            shockBombEntity.setItem(itemStack);
            shockBombEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.2f, 1.0f);
            world.spawnEntity(shockBombEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
