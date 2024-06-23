package supertoady.circus.item;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModModelPredicates {
    public static void registerPredicates(){
        ModelPredicateProviderRegistry.register(ModItems.TRICKSTER_CROSSBOW, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.getActiveItem() != stack ? 0.0F : (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
        });

        ModelPredicateProviderRegistry.register(ModItems.TRICKSTER_CROSSBOW, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });

        ModelPredicateProviderRegistry.register(ModItems.TRICKSTER_CROSSBOW, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            ChargedProjectilesComponent chargedProjectilesComponent = (ChargedProjectilesComponent)stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
            return chargedProjectilesComponent.isEmpty() ? 0.0F : 1.0F;
        });

        ModelPredicateProviderRegistry.register(ModItems.TRICKSTER_CROSSBOW, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            boolean hasFirework = false;
            ChargedProjectilesComponent chargedProjectilesComponent = (ChargedProjectilesComponent)stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
            List<ItemStack> items = chargedProjectilesComponent.getProjectiles();

            for (ItemStack item : items){
                if (item.isOf(Items.FIREWORK_ROCKET)) {
                    hasFirework = true;
                    break;
                }
            }
            return hasFirework ? 1.0F : 0.0F;
        });
    }
}
