package com.keko.food;

import net.minecraft.component.type.FoodComponent;

public class ModFoods {
    public static final FoodComponent JELLY = (new FoodComponent.Builder()).nutrition(6).saturationModifier(0.6F).build();

    public static final FoodComponent DEEP_SALMON = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodComponent GROXION = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodComponent SEA_RODENT = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3F).build();
    public static final FoodComponent STRIDELY = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3F).build();

    public static final FoodComponent COOKED_DEEP_SALMON = (new FoodComponent.Builder()).nutrition(9).saturationModifier(1.3F).build();
    public static final FoodComponent COOKED_GROXION = (new FoodComponent.Builder()).nutrition(9).saturationModifier(1.3F).build();
    public static final FoodComponent COOKED_SEA_RODENT = (new FoodComponent.Builder()).nutrition(9).saturationModifier(1.3F).build();
    public static final FoodComponent FRIED_STRIDELY = (new FoodComponent.Builder()).nutrition(9).saturationModifier(1.3F).build();
}
