package supertoady.circus.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import supertoady.circus.effect.ModStatusEffects;

public class ModFoodComponent {
    public static final FoodComponent COTTON_CANDY = new FoodComponent.Builder().saturationModifier(0.15F).nutrition(3).usingConvertsTo(Items.STICK)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.SUGAR_RUSH, 20 * 25), 0.80F).alwaysEdible().build();

    public static final FoodComponent PEPPERMINT = new FoodComponent.Builder().saturationModifier(0.1F).nutrition(1).snack()
            .statusEffect(new StatusEffectInstance(ModStatusEffects.SUGAR_RUSH, 20 * 15), 0.95F).alwaysEdible().build();

    public static final FoodComponent CHURROS = new FoodComponent.Builder().saturationModifier(0.25F).nutrition(4)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.SUGAR_RUSH, 20 * 17), 0.60F).alwaysEdible().build();

    public static final FoodComponent SUGAR_FROSTED_CHURROS = new FoodComponent.Builder().saturationModifier(0.25F).nutrition(5)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.SUGAR_RUSH, 20 * 22), 1F).alwaysEdible().build();

    public static final FoodComponent CHOCO_FROSTED_CHURROS = new FoodComponent.Builder().saturationModifier(0.3F).nutrition(7)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.SUGAR_RUSH, 20 * 20), 0.8F).alwaysEdible().build();
}
