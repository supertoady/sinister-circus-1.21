package supertoady.circus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.CrossbowItem;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import supertoady.circus.entity.ModEntities;
import supertoady.circus.item.ModItems;
import supertoady.circus.item.ModModelPredicates;
import supertoady.circus.particle.ModParticles;
import supertoady.circus.particle.custom.BonkParticle;
import supertoady.circus.particle.custom.ConfettiParticle;
import supertoady.circus.particle.custom.DizzyParticle;
import supertoady.circus.particle.custom.JesterCrossbowTrailParticle;

public class SinisterCircusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BALLOON_BOMB, FlyingItemEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.CONFETTI, ConfettiParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BONK, BonkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.JESTER_CROSSBOW_TRAIL, JesterCrossbowTrailParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.DIZZY, DizzyParticle.Factory::new);

        ModModelPredicates.registerPredicates();
    }
}
