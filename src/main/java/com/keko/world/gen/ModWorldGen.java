package com.keko.world.gen;

import com.keko.world.ModBiomeModification;
import com.keko.world.ModConfiguredFeatures;

public class ModWorldGen {
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
        ModBiomeModification.load();
    }
}
