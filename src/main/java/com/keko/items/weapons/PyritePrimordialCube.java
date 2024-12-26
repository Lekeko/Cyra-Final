package com.keko.items.weapons;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.CyraFinal;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.helpers.Directional;
import com.keko.items.ModItems;
import com.keko.sounds.ModSounds;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.block.Block;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PyritePrimordialCube extends Item {
    HashMap<Integer, Color> colorHashMap = new HashMap<>();
    ArrayList<SoundEvent> sound = new ArrayList<>();


    public PyritePrimordialCube(Settings settings) {
        super(settings);
        colorHashMap.put(1, new Color(255, 235, 126, 255));
        colorHashMap.put(2, new Color(227, 93, 254, 255));
        colorHashMap.put(3, new Color(61, 255, 175, 255));
        colorHashMap.put(4, new Color(255, 25, 48, 255));
        sound.add(ModSounds.CUBE_1);
        sound.add(ModSounds.CUBE_2);
        sound.add(ModSounds.CUBE_3);
        sound.add(ModSounds.CUBE_4);

        this.getDefaultStack().set(ModDataComponentTypes.VARIANT, 1);

    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(ModDataComponentTypes.VARIANT) == null){
            stack.set(ModDataComponentTypes.VARIANT, 1);
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((Entity) user, user.getBlockPos(), sound.get(itemStack.get(ModDataComponentTypes.VARIANT) - 1) , SoundCategory.PLAYERS ,  1.0f, 0.2f + world.random.nextFloat() / 2 + (sound.get(itemStack.get(ModDataComponentTypes.VARIANT) - 1) == ModSounds.CUBE_4 ? 1.4f: 0f)) ;

        if (!world.isClient){


            if (user.isSneaking()) {
                if (itemStack.get(ModDataComponentTypes.VARIANT) != null) {
                    int variant = itemStack.get(ModDataComponentTypes.VARIANT);
                    if (variant < 4)
                        itemStack.set(ModDataComponentTypes.VARIANT, variant + 1);
                    else
                        itemStack.set(ModDataComponentTypes.VARIANT, 1);

                } else {
                    itemStack.set(ModDataComponentTypes.VARIANT, 0);

                }
            }else
                spawnCube(world, user , colorHashMap.get(itemStack.get(ModDataComponentTypes.VARIANT)));

        }


        return super.use(world, user, hand);
    }




    private void spawnCube(World world, PlayerEntity user, Color color) {

        Vec3d pos = Directional.rayCast(world, user, user.getRotationVec(1.0f), 20);
        pos = pos.add(0,1,0);

        PCube cube = new PCube(world , (LivingEntity) user, Items.GLASS.getDefaultStack(), new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), color);
        double xOffset = getRandomFloat(-1, 1);
        double zOffset = getRandomFloat(-1, 1);
        Vec3d playerPos = user.getPos();
        float playerYaw = user.getYaw();

        double X = playerPos.x + xOffset * Math.cos(Math.toRadians(playerYaw)) - zOffset * Math.sin(Math.toRadians(playerYaw));
        double Z = playerPos.z + xOffset * Math.sin(Math.toRadians(playerYaw)) + zOffset * Math.cos(Math.toRadians(playerYaw));

        double motionX = X - playerPos.x;
        double motionZ = Z - playerPos.z;



        double distanceX = Math.pow(Math.abs(pos.x) - Math.abs(user.getX()), 2);
        double distanceY = Math.pow(Math.abs(pos.y) - Math.abs(user.getY()), 2);
        double distanceZ = Math.pow(Math.abs(pos.z) - Math.abs(user.getZ()), 2);

        double distance = Math.abs(20 - Math.sqrt(distanceX + distanceY + distanceZ));

        if (distance < 1)
            distance = 1;

        cube.setVelocity(motionX / distance, getRandomFloat(-0.3f, 0.3f), motionZ / distance);


        cube.setPos(X, user.getY() + 1.7, Z);



        world.spawnEntity(cube);
    }

    public static float getRandomFloat(float min, float max) {
        Random random = new Random();
        float range = max - min;
        float scaled = random.nextFloat() * range;
        return scaled + min;
    }
}
