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
    public static final SoundEvent CUBE_5 = register("cube5");
    public static final SoundEvent CUBE_6 = register("cube6");
    public static final SoundEvent OLD_LORD_TICK = register("old_lord_tick");
    public static final SoundEvent EERY = register("eery");
    public static final SoundEvent ELECTRO_CHARGE = register("electro_charge");
    public static final SoundEvent OLD_LORD_COMBO_HIT = register("old_lord_combo_hit");
    public static final SoundEvent PARRY = register("parry");
    public static final SoundEvent CHARGE_UP = register("charge_up");
    public static final SoundEvent OLD_LORD_SPAWN = register("old_lord_spawn");
    public static final SoundEvent VOID_AMBIANCE = register("void_ambiance");
    public static final SoundEvent VOID_AMBIANCE_MONSTER = register("void_ambiance_monster");
    public static final SoundEvent MURIEL_MUSIC_1 = register("muriel_music_1");
    public static final SoundEvent COMPULSION_SWORD_BLAST = register("compulsion_sword_blast");
    public static final SoundEvent COMPULSION_AXE = register("compulsion_axe");

    private static SoundEvent register(String path) {
        Identifier identifier = Identifier.of(CyraFinal.MOD_ID, path);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));

    }

    public static void registerSounds() {

    }
}
