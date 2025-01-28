package com.keko.items.weapons;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.packet.AxeparticlesLordPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ZombieLeaderBattleAxeItem extends AxeItem {
    public ZombieLeaderBattleAxeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
        this.getDefaultStack().set(ModDataComponentTypes.AXE_ATTACK, false);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && !user.getItemCooldownManager().isCoolingDown(this))
            if (stack.get(ModDataComponentTypes.AXE_ATTACK)) {
                user.setVelocity(user.getRotationVec(1.0f).normalize().multiply(1.3f).add(0, -1, 0));
                user.velocityModified = true;
                user.fallDistance = 0;
                if (!user.isCreative())
                    user.getItemCooldownManager().set(this, 5 * 20);
            } else {
                stack.set(ModDataComponentTypes.AXE_ATTACK, true);
                user.setVelocity(user.getRotationVec(1.0f).normalize().multiply(0.3f).add(0, 2, 0));
                user.velocityModified = true;
                user.fallDistance = - 10;
            }


        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(ModDataComponentTypes.AXE_ATTACK) == null)
            stack.set(ModDataComponentTypes.AXE_ATTACK, false);

        if (!world.isClient && entity.isOnGround() && stack.get(ModDataComponentTypes.AXE_ATTACK) == true && entity.getVelocity().getY() < 0) {
            damageArround(world, entity);
            stack.set(ModDataComponentTypes.AXE_ATTACK, false);
            if (entity instanceof PlayerEntity)
                if (!((PlayerEntity)entity).isCreative())
                    ((PlayerEntity)entity).getItemCooldownManager().set(this, 5 * 20);

        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void damageArround(World world, Entity entity) {
        BlockPos pos = entity.getBlockPos();
        int area = 8;
        Box box = new Box(pos.getX() + area, pos.getY() + (double) area /2, pos.getZ() + area, pos.getX() - area, pos.getY() - (double) area /2, pos.getZ() - area);
        for (Entity entity1 : world.getEntitiesByClass(Entity.class, box, Entity::isAlive))
            if (entity1 != entity) {
                float distance = entity1.distanceTo(entity);
                entity1.damage(world.getDamageSources().generic(), 24 - distance);
            }
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.PLAYERS, 1,1.3f);
        ServerPlayNetworking.send((ServerPlayerEntity) entity, new AxeparticlesLordPayload(entity.getId()));
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof ZombieEntity){
            int chanche = target.getWorld().random.nextBetween(1, 10);
            if (chanche <= 3){
                for (int i = 0; i < chanche; i++){
                    ItemEntity item = new ItemEntity(target.getWorld(), target.getX(), target.getY(), target.getZ(), Items.IRON_INGOT.getDefaultStack(), target.getWorld().random.nextFloat() - .5, target.getWorld().random.nextFloat() - .5, target.getWorld().random.nextFloat() - .5);
                    target.getWorld().spawnEntity(item);
                }
            }
        }
        super.postDamageEntity(stack, target, attacker);
    }
}
