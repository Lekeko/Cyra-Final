package com.keko.items.tools;

import com.keko.particle.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ShackleOfImprisonment extends Item {
    double area = 10;


    public ShackleOfImprisonment(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        System.out.println(world.isClient);

            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();

            Box box = new Box(x + area, y + area/3, z + area, x - area, y - area/3, z - area);

            for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, box,  Entity::isAlive)){
                if (entity != user){
                    if (!world.isClient)
                        entity.damage(entity.getWorld().getDamageSources().thrown(entity, user), 20);
                    else
                        spawnParticles(entity, user, world, entity.getX(),  entity.getY(),  entity.getZ());

                }
            }


            user.getItemCooldownManager().set(this, 70);

        return super.use(world, user, hand);
    }

    private void spawnParticles(LivingEntity entity, PlayerEntity user, World world, double x, double y, double z) {
        int particles = 20;
        System.out.println(world.isClient);
        for (int i = 0; i < particles; i++){
            double xx = x ;
            double yy = y ;
            double zz = z ;
            double vx = x - xx;
            double vy = y - yy;
            double vz = z - zz;
            world.addParticle(ModParticles.PYRITE_STAR, xx, yy + entity.getHeight() / 2, zz, vx * 3, -0.5, vz * 3);
        }

    }
}
