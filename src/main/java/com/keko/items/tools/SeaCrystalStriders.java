package com.keko.items.tools;

import com.keko.game.KeyBinds;
import com.keko.world.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SeaCrystalStriders extends Item {
    public SeaCrystalStriders(Settings settings) {
        super(settings.maxCount(1));
    }

    double boost = 17.0d;
    double bonusSideBoost = 2.0d;
    double notRightDimensionDebuff = 4.0d;
    float cooldown = 3.0f;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user.isSubmergedInWater()){
            if (!(world.getRegistryKey() == ModDimensions.MURIEL_KAIA_LEVEL_KEY))
            user.setVelocity(
                    user.getMovement().x * (boost * bonusSideBoost) / notRightDimensionDebuff,
                    user.getVelocity().y * (boost * bonusSideBoost) / notRightDimensionDebuff,
                    user.getMovement().z * (boost * bonusSideBoost) / notRightDimensionDebuff);
            else
                user.setVelocity(
                        user.getMovement().x * (boost * bonusSideBoost) ,
                        user.getVelocity().y * (boost * bonusSideBoost),
                        user.getMovement().z * (boost * bonusSideBoost));
            user.getItemCooldownManager().set(user.getStackInHand(hand).getItem(), (int) (cooldown * 20));

            user.velocityModified = true;
        }
        return super.use(world, user, hand);
    }
}
