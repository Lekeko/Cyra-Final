package com.keko.entities.projectiles.electroCharge;

import com.keko.entities.bosses.jellyFishking.JellyFishKingEntity;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import com.keko.sounds.ModSounds;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

import javax.swing.*;

public class ElectroDamager extends Entity {

    public ElectroDamager(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public void tick() {
        if (!getWorld().isClient){
            net.minecraft.util.math.Box box = new net.minecraft.util.math.Box(this.getX() + 6, this.getY() + 6, this.getZ() + 6, this.getX() - 6, this.getY() - 6, this.getZ() - 6);
            for (Entity entity : getWorld().getEntitiesByClass(Entity.class, box, Entity::isAlive)){
                DamageSource source = getWorld().getDamageSources().generic();
                if (!(entity instanceof Electro) && !(entity instanceof JellyFishKingEntity) && !(entity instanceof JellyFishEntity) && !(entity instanceof ItemEntity) && !(entity instanceof ElectroCharge)){
                    entity.damage(source, 3);
                    entity.setPos(getX(), getY() + 1, getZ());
                    entity.setVelocity(0,0.1d, 0);
                    entity.velocityModified = true;
                }
            }

            if (age > 31) discard();
        }
        super.tick();
    }
}
