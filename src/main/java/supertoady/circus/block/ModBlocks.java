package supertoady.circus.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;
import supertoady.circus.block.custom.CottonCandyBlock;

public class ModBlocks {

    public static Block COTTON_CANDY_BLOCK = registerBlock("cotton_candy_block",
            new CottonCandyBlock(Block.Settings.copy(Blocks.WHITE_WOOL)));

    public static Block TENT_BLOCK = registerBlock("tent_block",
            new Block(Block.Settings.copy(Blocks.WHITE_WOOL)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SinisterCircus.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(SinisterCircus.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        SinisterCircus.LOGGER.info("Registering mod blocks for" + SinisterCircus.MOD_ID);
    }
}
