package supertoady.circus.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;
import supertoady.circus.item.custom.*;

public class ModItems {

    public static Item BALLOON = registerItem("balloon", new BalloonItem(
            new Item.Settings()));

    public static Item BALLOON_ANIMAL = registerItem("balloon_animal", new Item(
            new Item.Settings()));

    public static Item BALLOON_BOMB = registerItem("balloon_bomb", new ShockBombItem(
            new Item.Settings().maxCount(16)));


    public static Item COTTON_CANDY = registerItem("cotton_candy", new Item(
            new Item.Settings().maxCount(1).food(ModFoodComponent.COTTON_CANDY)));

    public static Item CHURROS = registerItem("churros", new Item(
            new Item.Settings().maxCount(16).food(ModFoodComponent.CHURROS)));

    public static Item SUGAR_FROSTED_CHURROS = registerItem("sugar_frosted_churros", new Item(
            new Item.Settings().maxCount(16).food(ModFoodComponent.SUGAR_FROSTED_CHURROS)));

    public static Item CHOCO_FROSTED_CHURROS = registerItem("choco_frosted_churros", new Item(
            new Item.Settings().maxCount(16).food(ModFoodComponent.CHOCO_FROSTED_CHURROS)));


    public static Item JESTER_HAMMER = registerItem("jester_hammer", new JesterHammerItem(
            ModToolMaterials.JESTER, 4, -3.2f, new Item.Settings()));

    public static Item TRICKSTER_CROSSBOW = registerItem("trickster_crossbow", new TricksterCrossbowItem(
            new Item.Settings().maxCount(1)));

    public static Item JESTER_HAT  = registerItem("jester_hat",
            new JesterArmourItem(ModArmorMaterials.JESTER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static Item JESTER_SHIRT  = registerItem("jester_shirt",
            new JesterArmourItem(ModArmorMaterials.JESTER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static Item JESTER_PANTS  = registerItem("jester_pants",
            new JesterArmourItem(ModArmorMaterials.JESTER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static Item JESTER_BOOTS  = registerItem("jester_boots",
            new JesterArmourItem(ModArmorMaterials.JESTER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(SinisterCircus.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SinisterCircus.LOGGER.info("Registering mod items for " + SinisterCircus.MOD_ID);
    }
}
