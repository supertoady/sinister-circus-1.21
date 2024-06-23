package supertoady.circus.item.custom;

import net.minecraft.component.Component;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Unit;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TricksterCrossbowItem extends CrossbowItem {
    public TricksterCrossbowItem(Settings settings) {
        super(settings);
    }

    private boolean charged = false;
    private boolean loaded = false;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ChargedProjectilesComponent chargedProjectilesComponent = (ChargedProjectilesComponent)itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.shootAll(world, user, hand, itemStack, getSpeed(chargedProjectilesComponent), 6.0F, (LivingEntity)null);
            return TypedActionResult.consume(itemStack);
        } else if (!user.getProjectileType(itemStack).isEmpty()) {
            this.charged = false;
            this.loaded = false;
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    private static float getSpeed(ChargedProjectilesComponent stack) {
        return stack.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }

    private ItemStack getTippedArrowStack(RegistryEntry<Potion> potion) {
        ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potion));
        return stack;
    }

    @Override
    protected void shootAll(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack, List<ItemStack> projectiles, float speed, float divergence, boolean critical, @Nullable LivingEntity target) {
        float f = EnchantmentHelper.getProjectileSpread(world, stack, shooter, 0.0F);
        float g = projectiles.size() == 1 ? 0.0F : 2.0F * f / (float)(projectiles.size() - 1);
        float h = (float)((projectiles.size() - 1) % 2) * g / 2.0F;
        float i = 1.0F;

        for(int j = 0; j < projectiles.size(); ++j) {
            ItemStack itemStack = (ItemStack)projectiles.get(j);
            if (!itemStack.isEmpty()) {
                float k = h + i * (float)((j + 1) / 2) * g;
                i = -i;
                for (int x = shooter.getRandom().nextBetween(3, 5); x > 0; x--){
                    ItemStack arrowStack = itemStack;

                    shooter.sendMessage(Text.of("" + stack.getItem()));

                    if (itemStack.isOf(Items.ARROW)) {
                        shooter.sendMessage(Text.of("arrow"));
                        if (shooter.getRandom().nextBetween(0, 3) == 1){
                            shooter.sendMessage(Text.of("random"));
                            List<RegistryEntry<Potion>> potionEffects = List.of(Potions.POISON, Potions.HARMING, Potions.SLOWNESS);
                            Random javRandom = new Random();

                            int r = javRandom.nextInt(potionEffects.size());
                            arrowStack = getTippedArrowStack(potionEffects.get(r));
                        }
                    }
                    arrowStack.set(DataComponentTypes.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
                    ProjectileEntity projectileEntity = this.createArrowEntity(world, shooter, stack, arrowStack, critical);
                    shoot(shooter, projectileEntity, j, speed, divergence, k, target);
                    world.spawnEntity(projectileEntity);
                }
                stack.damage(this.getWeaponStackDamage(itemStack), shooter, LivingEntity.getSlotForHand(hand));
                if (stack.isEmpty()) {
                    break;
                }
            }
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }
}
