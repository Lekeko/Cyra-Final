package com.keko.entities.projectiles.compulsionScythe;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.swing.*;

public class CompulsionScythe extends PersistentProjectileEntity {
    Light light;
    boolean wasAdded = false;

    public CompulsionScythe(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        light = new PointLight().setRadius(4f).setBrightness(0.03f).setColor((float) 255 / 255, (float) 255 / 255, (float) 200 / 255);

    }


    @Override
    public void onRemoved() {
        if (this.getWorld().isClient){
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

        }
        super.onRemoved();
    }
    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public boolean isNoClip() {
        return true;
    }

    @Override
    public void tick() {


        super.tick();
        if (this.getWorld().isClient){
            ((PointLight)light).setPosition(this.getX(), this.getY(), this.getZ());
            if (!wasAdded) {
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
                wasAdded = true;
            }

        }else if (age>7) discard();

        if (!getWorld().isClient && getOwner() == null) discard();

    }


    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
