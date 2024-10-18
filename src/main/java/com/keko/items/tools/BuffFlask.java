package com.keko.items.tools;

import com.keko.game.KeyBinds;
import com.keko.items.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stat;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class BuffFlask extends Item {

    ArrayList<StatusEffectInstance> effects = new ArrayList<>();

    public BuffFlask(Settings settings) {
        super(settings);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (effects.size() > 0){
            if (!world.isClient)
            {
                user.addStatusEffect(effects.get(0));
                user.addStatusEffect(effects.get(1));
                user.getItemCooldownManager().set(user.getStackInHand(hand).getItem(), KeyBinds.cooldownBuff * 20);

            }

        }else {
            if (world.isClient)
                user.sendMessage(Text.literal("The Buff Potion does not have any effects!").withColor(new Color(245, 165, 255, 255).getRGB()));
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }


    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    public void addEffect(StatusEffectInstance effectInstanece1, StatusEffectInstance effectInstance2) {
        effects.clear();
        effects.add(effectInstanece1);
        effects.add(effectInstance2);
    }

    public void useEffect(World world, PlayerEntity user, Hand hand){
        use(world, user, hand);
    }
}
