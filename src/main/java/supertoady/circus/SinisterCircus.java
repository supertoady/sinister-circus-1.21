package supertoady.circus;

import net.fabricmc.api.ModInitializer;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import supertoady.circus.block.ModBlocks;
import supertoady.circus.effect.ModStatusEffects;
import supertoady.circus.entity.ModEntities;
import supertoady.circus.item.ModItemGroups;
import supertoady.circus.item.ModItems;
import supertoady.circus.particle.ModParticles;
import supertoady.circus.sound.ModSounds;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SinisterCircus implements ModInitializer {

	public static final String MOD_ID = "sinister-circus";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("<supertoady> Hello Modfest world!");

		ModSounds.registerModSounds();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		ModEntities.registerModEntities();
		ModBlocks.registerModBlocks();
		ModStatusEffects.registerModStatusEffects();

		ModParticles.registerModParticles();
	}

	public static void showParticlesToAll(World world, ParticleEffect particle, Vec3d pos, double delta, int count, double speed){
		if (world.getServer() == null) return;
		ServerWorld serverWorld = world.getServer().getWorld(world.getRegistryKey());
		if (serverWorld != null){
			serverWorld.spawnParticles(particle, pos.x, pos.y, pos.z, count, delta, delta, delta, speed);
		}
	}
}