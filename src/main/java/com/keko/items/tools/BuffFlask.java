package com.keko.items.tools;

import com.keko.ComponentTypes.ModDataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;
import java.util.*;

public class BuffFlask extends Item {


    public static HashMap<Integer, StatusEffectInstance> effect = initializeEffects();

    public static HashMap<Integer, StatusEffectInstance> initializeEffects(){
        HashMap<Integer, StatusEffectInstance> effect = new HashMap<>();
        effect.put(1, new StatusEffectInstance(StatusEffects.HASTE, 20 * 60 * 2, 2));
        effect.put(2, new StatusEffectInstance(StatusEffects.SPEED, 20 * 60 * 2, 2));
        effect.put(3, new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 60 * 2, 2));
        effect.put(4, new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * 60 * 2, 2));
        effect.put(5, new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 60 * 2, 2));
        return effect;
    }

    public BuffFlask(Settings settings) {
        super(settings);
        this.getDefaultStack().set(ModDataComponentTypes.EFFECT_BUFF_1, 0);
        this.getDefaultStack().set(ModDataComponentTypes.EFFECT_BUFF_2, 0);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(user.getActiveHand());
        if (stack.get(ModDataComponentTypes.EFFECT_BUFF_1) != null && stack.get(ModDataComponentTypes.EFFECT_BUFF_2) != null){
            user.setStatusEffect(effect.get(stack.get(ModDataComponentTypes.EFFECT_BUFF_1)), user);
            user.setStatusEffect(effect.get(stack.get(ModDataComponentTypes.EFFECT_BUFF_2)), user);

            effect.clear();

            effect.put(1, new StatusEffectInstance(StatusEffects.HASTE, 20 * 60 * 2, 2));
            effect.put(2, new StatusEffectInstance(StatusEffects.SPEED, 20 * 60 * 2, 2));
            effect.put(3, new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 60 * 2, 2));
            effect.put(4, new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * 60 * 2, 2));
            effect.put(5, new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 60 * 2, 2));
            user.getItemCooldownManager().set(stack.getItem(), (int)(20 * 60 * 2.6));

        }else {
            Objects.requireNonNull(user).sendMessage(Text.literal("The Buff Potion does not have any effects!").withColor(new Color(245, 165, 255, 255).getRGB()));

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



    public void useEffect(World world, PlayerEntity user, Hand hand){
        ItemStack stack = user.getStackInHand(hand);

    }
}
