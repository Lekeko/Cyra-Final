package com.keko.model;

import com.keko.model.FOTOL.FOTOLModel;
import com.keko.model.compulsion.CompulsionAxeModel;
import com.keko.model.compulsion.CompulsionSwordModel;
import com.keko.model.electroCharge.ElectroChargeModel;
import com.keko.model.lordStar.LordStarModel;
import com.keko.model.pCube.PCubeModel;
import com.keko.model.seaBolt.SeaBoltModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

import static com.keko.model.ModModelLayer.*;

public class ModModelsRegister {

    public static void register() {
        EntityModelLayerRegistry.registerModelLayer(SEA_BOLT, SeaBoltModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FOTOL, FOTOLModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(COMPULSION_SWORD, CompulsionSwordModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(COMPULSION_AXE, CompulsionAxeModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.PCUBE, PCubeModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.ELECTRO_CHARGE, ElectroChargeModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.LORD_STAR, LordStarModel::getTexturedModelData);
    }
}
