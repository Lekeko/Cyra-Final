package com.keko.screen;

import com.keko.CyraFinal;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AlchemyTableScreenhandler> ALCHEMY_TABLE_SCREENHANDLER_SCREEN_HANDLER_TYPE =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CyraFinal.MOD_ID, "alchemy_table"),
                    new ExtendedScreenHandlerType<>(AlchemyTableScreenhandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenhandler(){}
}
