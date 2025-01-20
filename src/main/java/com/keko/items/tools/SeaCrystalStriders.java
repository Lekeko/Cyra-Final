package com.keko.items.tools;

import com.keko.game.KeyBinds;
import com.keko.world.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SeaCrystalStriders extends Item {
    public SeaCrystalStriders(Settings settings) {
        super(settings.maxCount(1));
    }

    double boost = 2.0d;
    double notRightDimensionDebuff = 2.0d;
    float cooldown = 5.0f;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user.isSubmergedInWater()){
            Vec3d lookingDirection = user.getRotationVec(1.0f);
            if (world.getRegistryKey() == ModDimensions.MURIEL_KAIA_LEVEL_KEY)
            user.setVelocity(
                        lookingDirection.x * boost,
                        lookingDirection.y * boost,
                        lookingDirection.z * boost
                    );
            else
                user.setVelocity(
                        lookingDirection.x * boost / notRightDimensionDebuff,
                        lookingDirection.y * boost / notRightDimensionDebuff,
                        lookingDirection.z * boost / notRightDimensionDebuff
                );
            user.getItemCooldownManager().set(user.getStackInHand(hand).getItem(), (int) (cooldown * 20));

            user.velocityModified = true;
            user.setSwimming(false);
            user.useRiptide(20, 6, this.getDefaultStack());

        }
        return super.use(world, user, hand);
    }
}
