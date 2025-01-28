package com.keko.items;

import com.keko.blocks.ModBlockTags;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    ENDERITE(5,3500, 11.0F, 4.0F, 15, ModBlockTags.INCORRECT_FOR_ENDERITE_TOOL, () ->
            Ingredient.ofItems(ModItems.ENDERITE_INGOT)
    ),
    SEA_CRYSTAL(6, 3900, 13.0F, 5.2F, 15,  ModBlockTags.INCORRECT_FOR_SEA_CRYSTAL_TOOL, () ->
            Ingredient.ofItems(ModItems.SEA_CRYSTAL)
    ),
    PYRITE(7, 4200, 19.5F, 5.9F, 15, ModBlockTags.INCORRECT_FOR_PYRITE_TOOL,() ->
         Ingredient.ofItems(ModItems.RESTORED_PYRITE)
    ),
    BAUXITE(8, 6300, 21.5F, 7.2F, 15, ModBlockTags.INCORRECT_FOR_BAUXITE_TOOL,() ->
         Ingredient.ofItems(ModItems.ENDERITE_INGOT)
    ),
    VULCANIUM(9, 7200, 24.5F, 8.1F, 15, ModBlockTags.INCORRECT_FOR_VULCANIUM_TOOL,() ->
         Ingredient.ofItems(ModItems.ENDERITE_INGOT)
    ),
    CYRANIUM(10, 9000, 30.0F, 9.5F, 15, ModBlockTags.INCORRECT_FOR_CYRANIUM_TOOL,() ->
         Ingredient.ofItems(ModItems.ENDERITE_INGOT)
    );

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final TagKey<Block> inverseTag;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, TagKey<Block> inverseTag, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.inverseTag = inverseTag;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.itemDurability;
    }


    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
