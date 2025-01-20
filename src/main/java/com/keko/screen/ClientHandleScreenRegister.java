package com.keko.screen;

import com.keko.screen.alchemyTableScreen.AlchemyTableScreen;
import com.keko.screen.pyriteFabricatorScreen.PyriteFabricatorScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ClientHandleScreenRegister {
    public static void register (){
        HandledScreens.register(ModScreenHandlers.ALCHEMY_TABLE_SCREENHANDLER_SCREEN_HANDLER_TYPE, AlchemyTableScreen::new);
        HandledScreens.register(ModScreenHandlers.PYRITE_FABRICATOR_SCREENHANDLER_SCREEN_HANDLER_TYPE, PyriteFabricatorScreen::new);
    }
}
