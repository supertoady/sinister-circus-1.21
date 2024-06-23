package supertoady.circus.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import supertoady.circus.SinisterCircus;
import supertoady.circus.effect.ModStatusEffects;
import supertoady.circus.particle.ModParticles;
import supertoady.circus.sound.ModSounds;

import java.util.Random;

public class JesterHammerItem extends ToolItem {
    AttributeModifiersComponent attributeModifiers;

    public JesterHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, settings);
        this.attributeModifiers = createAttributeModifiers(toolMaterial, attackDamage, attackSpeed);
    }

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)baseAttackDamage + material.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        builder.add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
        return builder.build();
    }

    @Override
    public AttributeModifiersComponent getAttributeModifiers() {
        return this.attributeModifiers;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.isPlayer()){
            PlayerEntity player = ((PlayerEntity) attacker);

            if (player.getVelocity().y < -0.1 && !player.isSprinting()){
                target.getWorld().playSound(null, target.getBlockPos(), ModSounds.BONK, SoundCategory.PLAYERS, 0.4f, 1);
                if (new Random().nextInt(3) == 1) {
                    target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.DIZZY, 20 * 6, 0));
                }

                Vec3d dir = attacker.getRotationVector().multiply(0.2f);
                dir = new Vec3d(dir.x, 0.075, dir.z);

                target.addVelocity(dir);

                Random random = new Random();
                double range = 0.75;
                double xOff = random.nextDouble(-range, range);
                double zOff = random.nextDouble(-range, range);

                Vec3d pos = target.getEyePos();
                pos = pos.add(xOff, 0.4, zOff);

                SinisterCircus.showParticlesToAll(target.getWorld(), ModParticles.BONK, pos, 0, 1, 0);
            }
        }

        return super.postHit(stack, target, attacker);
    }
}
