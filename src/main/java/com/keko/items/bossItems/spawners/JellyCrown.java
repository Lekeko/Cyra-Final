package com.keko.items.bossItems.spawners;

import com.keko.entities.ModEntities;
import com.keko.entities.bosses.jellyFishking.JellyFishKingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JellyCrown extends Item {
    public JellyCrown(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            BlockPos pos = findSpawnPos(user);
            if (pos!=null){
                JellyFishKingEntity jellyFishKingEntity = new JellyFishKingEntity(ModEntities.JELLYFISH_KING, world);
                jellyFishKingEntity.setPos(pos.getX(),pos.getY(), pos.getZ());
                world.spawnEntity(jellyFishKingEntity);
                user.getStackInHand(hand).decrementUnlessCreative(1, user);
            }

        }
        return super.use(world, user, hand);
    }

    private BlockPos findSpawnPos(PlayerEntity user) {
        int tries = 5;
        boolean ok = true;
        while (tries > 0){
            tries--;
            double x = user.getWorld().getRandom().nextBetween(10, 15) * (user.getWorld().getRandom().nextBoolean() ? -1 : 1) + user.getX();
            double y = user.getWorld().getRandom().nextBetween(10, 15) * (user.getWorld().getRandom().nextBoolean() ? -1 : 1) + user.getY();
            double z = user.getWorld().getRandom().nextBetween(10, 15) * (user.getWorld().getRandom().nextBoolean() ? -1 : 1) + user.getZ();
            for (int i = -6; i <= 6; i++)
                for (int j = -6; j <= 6; j++)
                    for (int k = -6; k <= 6; k++)
                        if (!(user.getWorld().getBlockState(new BlockPos((int) (x + i), (int) (y + j), (int) (z + k))).isOf(Blocks.WATER)))
                            ok = false;

            if (ok)
                return new BlockPos((int) (x), (int) (y), (int) (z));
            ok = true;
        }
        return null;
    }
}
