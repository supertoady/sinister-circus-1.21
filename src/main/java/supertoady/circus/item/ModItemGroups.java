package supertoady.circus.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;
import supertoady.circus.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SINISTER_CIRCUS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SinisterCircus.MOD_ID, "sinister-circus"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sinister-circus"))
                    .icon(() -> new ItemStack(ModItems.COTTON_CANDY)).entries((displayContext, entries) -> {

                        entries.add(ModItems.JESTER_HAMMER);
                        entries.add(ModItems.TRICKSTER_CROSSBOW);

                        entries.add(ModItems.JESTER_HAT);
                        entries.add(ModItems.JESTER_SHIRT);
                        entries.add(ModItems.JESTER_PANTS);
                        entries.add(ModItems.JESTER_BOOTS);

                        entries.add(ModItems.BALLOON);
                        entries.add(ModItems.BALLOON_ANIMAL);
                        entries.add(ModItems.BALLOON_BOMB);

                        entries.add(ModItems.COTTON_CANDY);
                        entries.add(ModBlocks.COTTON_CANDY_BLOCK);

                        entries.add(ModItems.CHURROS);
                        entries.add(ModItems.SUGAR_FROSTED_CHURROS);
                        entries.add(ModItems.CHOCO_FROSTED_CHURROS);

                    }).build());


    public static void registerItemGroups() {
        SinisterCircus.LOGGER.info("Registering Item Groups for " + SinisterCircus.MOD_ID);
    }
}
