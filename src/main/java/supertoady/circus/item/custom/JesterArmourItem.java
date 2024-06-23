package supertoady.circus.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import supertoady.circus.SinisterCircus;
import supertoady.circus.item.ModArmorMaterials;

import java.util.List;

public class JesterArmourItem extends ArmorItem {
    public JesterArmourItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.isPlayer() && !world.isClient()){
            PlayerEntity player = ((PlayerEntity) entity);
            if (hasCorrectArmorOn(ModArmorMaterials.JESTER, player)){
                if (player.getHealth() <= 6){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 5, 0));
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material
                && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Entity entity = stack.getHolder();
        if (entity != null){
            if (entity.isPlayer()){
                PlayerEntity player = ((PlayerEntity) entity);
                if (hasCorrectArmorOn(ModArmorMaterials.JESTER, player)){
                    tooltip.add(Text.of("§l§cSet Bonus:"));
                    tooltip.add(Text.of("§cGives you Speed when you're low on health."));
                }
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
