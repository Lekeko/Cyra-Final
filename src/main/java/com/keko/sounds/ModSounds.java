package com.keko.sounds;

import com.keko.CyraFinal;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent CUBE_1 = register("cube1");
    public static final SoundEvent CUBE_2 = register("cube2");
    public static final SoundEvent CUBE_3 = register("cube3");
    public static final SoundEvent CUBE_4 = register("cube4");

    private static SoundEvent register(String path) {
        Identifier identifier = Identifier.of(CyraFinal.MOD_ID, path);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));

    }

    public static void registerSounds() {

    }
}
