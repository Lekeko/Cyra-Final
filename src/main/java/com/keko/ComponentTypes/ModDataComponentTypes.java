package com.keko.ComponentTypes;

import com.keko.CyraFinal;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> VARIANT = register("variant", integerBuilder -> integerBuilder.codec(Codec.INT));
    public static final ComponentType<Integer> RED_NUMBER = register("red_number", integerBuilder -> integerBuilder.codec(Codec.INT));
    public static final ComponentType<Boolean> BURST_RED = register("burst_red", boolBuilder -> boolBuilder.codec(Codec.BOOL));
    public static final ComponentType<Boolean> AXE_ATTACK = register("axe_attack", boolBuilder -> boolBuilder.codec(Codec.BOOL));
    public static final ComponentType<Integer> ELECTRO_VARIANT = register("electro_variant", integerBuilder -> integerBuilder.codec(Codec.INT));
    public static final ComponentType<Integer> EFFECT_BUFF_1 = register("effect_buff_1", integerBuilder -> integerBuilder.codec(Codec.INT));
    public static final ComponentType<Integer> EFFECT_BUFF_2 = register("effect_buff_2", integerBuilder -> integerBuilder.codec(Codec.INT));
    public static final ComponentType<Boolean> HAS_LIGHT = register("has_light", boolBuilder -> boolBuilder.codec(Codec.BOOL));
    public static final ComponentType<Integer> LIGHT_ID = register("light_id", integerBuilder -> integerBuilder.codec(Codec.INT));
    public static final ComponentType<Integer> COMPULSION_WEAPON_STAGE_ID = register("compulsion_weapon_stage_id", integerBuilder -> integerBuilder.codec(Codec.INT));

    public static void registerDataComponents() {

    }

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderUnaryOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CyraFinal.MOD_ID, name),
                builderUnaryOperator.apply(ComponentType.builder()).build());
    }
}
