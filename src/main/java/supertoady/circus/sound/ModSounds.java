package supertoady.circus.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import supertoady.circus.SinisterCircus;

public class ModSounds {

    public static SoundEvent BONK = registerSound("bonk");
    public static SoundEvent SQUEAK = registerSound("squeak");

    private static SoundEvent registerSound(String name){
        Identifier id = Identifier.of(SinisterCircus.MOD_ID, name);
        Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
        return SoundEvent.of(id);
    }

    public static void registerModSounds(){
        SinisterCircus.LOGGER.info("Registering sounds for " + SinisterCircus.MOD_ID);
    }
}
