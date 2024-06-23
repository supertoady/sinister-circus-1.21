package supertoady.circus.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import supertoady.circus.item.ModItems;

public class CottonCandyBlock extends Block {
    public CottonCandyBlock(Block.Settings settings) {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.STICK) && !world.isClient()){

            stack.decrement(1);
            player.giveItemStack(new ItemStack(ModItems.COTTON_CANDY));

            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
