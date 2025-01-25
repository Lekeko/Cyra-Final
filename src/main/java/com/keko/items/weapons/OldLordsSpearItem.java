package com.keko.items.weapons;

import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.projectiles.oldLordsSpear.OldLordsSpearEntity;
import com.keko.sounds.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OldLordsSpearItem extends Item {

    public OldLordsSpearItem(Settings settings) {
        super(settings);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient && !user.isSubmergedInWater()){
            user.addVelocity(0,0.07f,0);
            user.velocityModified = true;
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity player = (PlayerEntity) user;
        int timeUsed = 40 - remainingUseTicks;
        float charge = Math.min((float) timeUsed / 40.0F, 1.0F);
        float velocityMultiplier = 1.0F + charge * 2.0F;

        if (!world.isClient && !player.getItemCooldownManager().isCoolingDown(stack.getItem())) {

            OldLordsSpearEntity spearEntity = new OldLordsSpearEntity(ModProjectileEntities.OLD_LORDS_SPEAR_ENTITY_ENTITY_TYPE, world);

            spearEntity.setPos(
                    user.getX() + user.getRotationVec(1.0f).x,
                    user.getEyeY() - 0.1,
                    user.getZ() + user.getRotationVec(1.0f).z
            );

            spearEntity.setVelocity(
                    user,
                    user.getPitch(),
                    user.getYaw(),
                    0.0f,
                    1.5f * velocityMultiplier,
                    1.0f
            );
            user.addVelocity(spearEntity.getVelocity().multiply(-1).multiply(0.5f));
            user.velocityModified = true;

            spearEntity.setOwner(user);
            spearEntity.setDamage(5.0 + charge * 5.0);

            world.spawnEntity(spearEntity);

        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.COMPULSION_AXE, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + charge * 0.5F);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        player.getItemCooldownManager().set(stack.getItem(), 6 * 20);

    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }
}