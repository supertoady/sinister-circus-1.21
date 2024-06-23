package supertoady.circus.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;
import supertoady.circus.effect.custom.DizzyStatusEffect;
import supertoady.circus.effect.custom.SugarRushStatusEffect;

public class ModStatusEffects {

    public static RegistryEntry<StatusEffect> DIZZY = registerStatusEffect("dizzy",
            new DizzyStatusEffect(StatusEffectCategory.HARMFUL, 16553248)
                .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of(SinisterCircus.MOD_ID, "effect.dizzy"), -0.125, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static RegistryEntry<StatusEffect> SUGAR_RUSH = registerStatusEffect("sugar_rush",
            new SugarRushStatusEffect(StatusEffectCategory.NEUTRAL, 16448500)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of(SinisterCircus.MOD_ID, "effect.sugar_rush"), 0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(SinisterCircus.MOD_ID, name), statusEffect);
    }

    public static void registerModStatusEffects() {
        SinisterCircus.LOGGER.info("Registering mod effects for " + SinisterCircus.MOD_ID);
    }
}
