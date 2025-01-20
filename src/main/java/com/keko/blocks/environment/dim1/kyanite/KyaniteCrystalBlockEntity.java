package com.keko.blocks.environment.dim1.kyanite;

import com.keko.blocks.ModBlockEntity;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KyaniteCrystalBlockEntity extends BlockEntity {
    public Light light;
    private boolean wasAdded;
    private final BlockPos pos1;

    public KyaniteCrystalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.KYANITE_CRYSTAL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
        pos1 = pos;
        light = new PointLight().setRadius(46.180f).setColor(105/255f,  172/255f, 255/255f).setBrightness(1f);
        wasAdded = false;
    }




    public void tick(World world1, BlockPos pos, BlockState state1) {
        if (!world1.isClient){
            return;
        }
        if (!wasAdded){
            ((PointLight)light).setPosition(pos1.getX() + .5f, pos1.getY() + .5f, pos1.getZ() + .5f);
            wasAdded =true;
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);
        }
    }

}
