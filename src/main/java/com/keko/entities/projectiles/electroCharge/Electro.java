package com.keko.entities.projectiles.electroCharge;

import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.items.ModItems;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Electro extends PersistentProjectileEntity {

    private Vec3d initialVelocity;
    Light light = new PointLight().setRadius(10).setBrightness(0.3f).setColor((float) 122 / 255, (float) 255 / 255, (float) 255 / 255);
    private boolean wasAdded = false;


    public Electro(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }


    @Override
    public void tick() {
        if (!getWorld().isClient ){
            if (age < 2){
                this.initialVelocity = getVelocity();

            }

            if (age > 31){
                discard();
            }
            this.setVelocity(this.getVelocity().subtract(initialVelocity.multiply(0.06f)));

        }else {
            if (!wasAdded){
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
                wasAdded = true;
            }
            ((PointLight)light).setPosition(this.getX(), getY(), getZ());

        }


        super.tick();
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        ((PointLight)light).setPosition(this.getX(), getY(), getZ());
    }

    @Override
    public void onRemoved() {
        if (getWorld().isClient)                VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

        super.onRemoved();
    }

    @Override
    protected float getDragInWater() {
        return 1;
    }

    @Override
    public boolean isNoClip() {
        return true;
    }
}
