package com.keko.items.weapons;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.entities.testVeilRenderEntity.cubeEntity.CubeEntity;
import com.keko.helpers.Directional;
import com.keko.midnightLibConfigs.MidnightConfigCyra;
import com.keko.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PyritePrimordialCube extends Item {
    HashMap<Integer, Color> colorHashMap = new HashMap<>();
    HashMap<Integer, Integer> cooldownHashMap = new HashMap<>();
    ArrayList<SoundEvent> sound = new ArrayList<>();

    private final int RED_CUBES = 10;


    public PyritePrimordialCube(Settings settings) {
        super(settings.maxCount(1));
        colorHashMap.put(1, new Color(255, 235, 126, 255));
        colorHashMap.put(2, new Color(227, 93, 254, 255));
        colorHashMap.put(3, new Color(61, 255, 175, 255));
        colorHashMap.put(4, new Color(255, 25, 48, 255));
        colorHashMap.put(5, new Color(255, 102, 25, 255));
        colorHashMap.put(6, new Color(113, 217, 41, 255));
        cooldownHashMap.put(1, 0);
        cooldownHashMap.put(2, MidnightConfigCyra.cooldown_cube_purple * 20);
        cooldownHashMap.put(3, 0);
        cooldownHashMap.put(4, MidnightConfigCyra.cooldown_cube_red * 20);
        cooldownHashMap.put(5, MidnightConfigCyra.cooldown_cube_orange * 20);
        cooldownHashMap.put(6, MidnightConfigCyra.cooldown_cube_green * 20);
        sound.add(ModSounds.CUBE_1);
        sound.add(ModSounds.CUBE_2);
        sound.add(ModSounds.CUBE_3);
        sound.add(ModSounds.CUBE_4);
        sound.add(ModSounds.CUBE_5);
        sound.add(ModSounds.CUBE_6);

        this.getDefaultStack().set(ModDataComponentTypes.VARIANT, 1);
        this.getDefaultStack().set(ModDataComponentTypes.BURST_RED, false);
        this.getDefaultStack().set(ModDataComponentTypes.RED_NUMBER, 0);

    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(ModDataComponentTypes.VARIANT) == null || stack.get(ModDataComponentTypes.BURST_RED) == null || stack.get(ModDataComponentTypes.RED_NUMBER) == null){
            stack.set(ModDataComponentTypes.VARIANT, 1);
            stack.set(ModDataComponentTypes.BURST_RED, false);
            stack.set(ModDataComponentTypes.RED_NUMBER, 0);
        }

        updateCooldownMap();

        if (!world.isClient){
            if (stack.get(ModDataComponentTypes.BURST_RED) != null && stack.get(ModDataComponentTypes.RED_NUMBER) != null) {
                if (stack.get(ModDataComponentTypes.BURST_RED) && stack.get(ModDataComponentTypes.RED_NUMBER) < RED_CUBES) {
                    spawnCube(world, (PlayerEntity) entity, colorHashMap.get(stack.get(ModDataComponentTypes.VARIANT)), stack, true);
                    world.playSound((Entity) null, entity.getBlockPos(), sound.get(stack.get(ModDataComponentTypes.VARIANT) - 1) , SoundCategory.PLAYERS ,  1.0f, (float) (0.5 + world.random.nextFloat() * 1.2F)) ;
                    stack.set(ModDataComponentTypes.RED_NUMBER, stack.get(ModDataComponentTypes.RED_NUMBER) + 1);
                } else {
                    stack.set(ModDataComponentTypes.RED_NUMBER, 0);
                    stack.set(ModDataComponentTypes.BURST_RED, false);

                }
            }
        }


        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void updateCooldownMap() {
        cooldownHashMap.clear();
        cooldownHashMap.put(1, 0);
        cooldownHashMap.put(2, MidnightConfigCyra.cooldown_cube_purple * 20);
        cooldownHashMap.put(3, 0);
        cooldownHashMap.put(4, MidnightConfigCyra.cooldown_cube_red * 20);
        cooldownHashMap.put(5, MidnightConfigCyra.cooldown_cube_orange * 20);
        cooldownHashMap.put(6, MidnightConfigCyra.cooldown_cube_green * 20);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((Entity) user, user.getBlockPos(), sound.get(itemStack.get(ModDataComponentTypes.VARIANT) - 1) , SoundCategory.PLAYERS ,  1.0f, (float) (0.5 + world.random.nextFloat() * 1.2F)) ;

        if (!world.isClient){


            if (user.isSneaking()) {
                if (itemStack.get(ModDataComponentTypes.VARIANT) != null) {
                    int variant = itemStack.get(ModDataComponentTypes.VARIANT);
                    if (variant < 6)
                        itemStack.set(ModDataComponentTypes.VARIANT, variant + 1);
                    else
                        itemStack.set(ModDataComponentTypes.VARIANT, 1);

                } else {
                    itemStack.set(ModDataComponentTypes.VARIANT, 0);

                }
            }else
                if (!user.getItemCooldownManager().isCoolingDown(itemStack.getItem())){
                    if (!user.isCreative())
                        user.getItemCooldownManager().set(itemStack.getItem(), cooldownHashMap.get(itemStack.get(ModDataComponentTypes.VARIANT)));

                    if (itemStack.get(ModDataComponentTypes.VARIANT) == 4) {
                        itemStack.set(ModDataComponentTypes.BURST_RED, true);

                    } else if (itemStack.get(ModDataComponentTypes.VARIANT) == 5) {
                        spawnOrangeCube(world, user, colorHashMap.get(itemStack.get(ModDataComponentTypes.VARIANT)), itemStack, false);
                    } else if (itemStack.get(ModDataComponentTypes.VARIANT) == 6) {
                        for (int i = 0; i < 20; i++) {
                            spawnCube(world, user, colorHashMap.get(itemStack.get(ModDataComponentTypes.VARIANT)), itemStack, false);
                        }
                        Box box = new Box(user.getX() + 30, user.getY() + 30, user.getZ() + 30, user.getX() - 30, user.getY() - 30, user.getZ() - 30);
                        for (PlayerEntity players : world.getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive))
                            players.heal(10);
                    } else
                        spawnCube(world, user, colorHashMap.get(itemStack.get(ModDataComponentTypes.VARIANT)), itemStack, false);
                }
        }


        return super.use(world, user, hand);
    }

    private void spawnOrangeCube(World world, PlayerEntity user, Color color, ItemStack itemStack, boolean b) {
        for (int i = 0 ; i < 4; i++){
            PCube cube = new PCube(world , (LivingEntity) user, Items.GLASS.getDefaultStack(), new Vec3d(user.getX(), user.getY(), user.getZ()), color, itemStack.get(ModDataComponentTypes.VARIANT), 90 * i);
            cube.setPos(user.getX(), user.getY(), user.getZ());
            world.spawnEntity(cube);
        }

    }


    private void spawnCube(World world, PlayerEntity user, Color color, ItemStack itemStack, boolean inFront) {

        Vec3d pos = Directional.rayCast(world, user, user.getRotationVec(1.0f), 20);
        pos = pos.add(0,1,0);
        if (inFront){
            pos = Directional.rayCast(world, user, user.getRotationVec(1.0f), 100);
            pos = pos.add(0,1,0);
            PCube cube1 = new PCube(world , (LivingEntity) user, Items.GLASS.getDefaultStack(), pos, color, itemStack.get(ModDataComponentTypes.VARIANT) ,0);
            PCube cube2 = new PCube(world , (LivingEntity) user, Items.GLASS.getDefaultStack(), pos, color, itemStack.get(ModDataComponentTypes.VARIANT) ,0);


            double xOffset = 3.0;
            double zOffset = 3.0;

            Vec3d playerPos = user.getPos();
            float playerYaw = user.getYaw();

            double leftX = playerPos.x + xOffset * Math.cos(Math.toRadians(playerYaw)) - zOffset * Math.sin(Math.toRadians(playerYaw));
            double leftZ = playerPos.z + xOffset * Math.sin(Math.toRadians(playerYaw)) + zOffset * Math.cos(Math.toRadians(playerYaw));

            double rightX = playerPos.x - xOffset * Math.cos(Math.toRadians(playerYaw)) - zOffset * Math.sin(Math.toRadians(playerYaw));
            double rightZ = playerPos.z - xOffset * Math.sin(Math.toRadians(playerYaw)) + zOffset * Math.cos(Math.toRadians(playerYaw));

            double distanceX = Math.pow(Math.abs(pos.x) - Math.abs(user.getX()), 2);
            double distanceY = Math.pow(Math.abs(pos.y) - Math.abs(user.getY()), 2);
            double distanceZ = Math.pow(Math.abs(pos.z) - Math.abs(user.getZ()), 2);

            double distance = Math.abs(20 - Math.sqrt(distanceX + distanceY + distanceZ));

            if (distance < 1)
                distance = 1;

            double motionLeftX = leftX - playerPos.x;
            double motionLeftZ = leftZ - playerPos.z;

            double motionRightX = rightX - playerPos.x;
            double motionRightZ = rightZ - playerPos.z;

            cube1.setPos(leftX, user.getY(), leftZ);
            cube2.setPos(rightX, user.getY(), rightZ);

            cube1.setNoGravity(true);
            cube2.setNoGravity(true);


            cube1.setVelocity(motionLeftX / distance, getRandomFloat(-0.3f, 0.3f), motionLeftZ / distance);
            cube2.setVelocity(motionRightX / distance, getRandomFloat(-0.3f, 0.3f), motionRightZ / distance);

            cube1.setVelocity(cube1.getVelocity().multiply(0.4f));
            cube2.setVelocity(cube2.getVelocity().multiply(0.4f));

            world.spawnEntity(cube1);
            world.spawnEntity(cube2);
            return;
        }

        PCube cube = new PCube(world , (LivingEntity) user, Items.GLASS.getDefaultStack(), pos, color, itemStack.get(ModDataComponentTypes.VARIANT) ,0 );

        double xOffset = getRandomFloat(-1,1);
        double zOffset = getRandomFloat(-1,1);

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



        cube.setVelocity(motionX / distance, (itemStack.get(ModDataComponentTypes.VARIANT) == 2 ? 0.2 : getRandomFloat(-0.3f, 0.3f)), motionZ / distance);

        cube.setPos(X, user.getY() + 1.7, Z);

        if (itemStack.get(ModDataComponentTypes.VARIANT) == 6) {
            cube.setVelocity(world.getRandom().nextBoolean() ? getRandomFloat(-12, -10) : getRandomFloat(12, 10), getRandomFloat(-1, 1), world.getRandom().nextBoolean() ? getRandomFloat(-12, -10) : getRandomFloat(12, 10));
        }
        world.spawnEntity(cube);
    }

    public static float getRandomFloat(float min, float max) {
        Random random = new Random();
        float range = max - min;
        float scaled = random.nextFloat() * range;
        return scaled + min;
    }
}
