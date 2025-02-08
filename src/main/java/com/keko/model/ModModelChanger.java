package com.keko.model;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.CyraFinal;
import com.keko.items.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelChanger {
    public static void register() {
        Identifier variantProperty = Identifier.of(CyraFinal.MOD_ID, "variant");
        ModelPredicateProviderRegistry.register(ModItems.PYRITE_PRIMORDIAL_CUBE, variantProperty, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.VARIANT) == null)
                return 0f;
            else {
                return (float) (itemStack.get(ModDataComponentTypes.VARIANT) - 1) / 10;
            }
        });

        Identifier variantProperty2 = Identifier.of(CyraFinal.MOD_ID, "variant");
        ModelPredicateProviderRegistry.register(ModItems.FLASHLIGHT, variantProperty2, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.HAS_LIGHT) == null)
                return 0f;
            else {
                return itemStack.get(ModDataComponentTypes.HAS_LIGHT) == false ? 0f : 0.1f;
            }
        });

        Identifier variantProperty1 = Identifier.of(CyraFinal.MOD_ID, "variant");
        ModelPredicateProviderRegistry.register(ModItems.ELECTRO_CHARGE, variantProperty1, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.ELECTRO_VARIANT) == null)
                return 0f;

            return (itemStack.get(ModDataComponentTypes.ELECTRO_VARIANT) - 1) / 100f;

        });

        Identifier awakenedProperty = Identifier.of(CyraFinal.MOD_ID, "awakened");
        ModelPredicateProviderRegistry.register(ModItems.PYRITE_SWORD, awakenedProperty, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.AWAKENED_ARMAMENT) == null)
                return 0f;

            return itemStack.get(ModDataComponentTypes.AWAKENED_ARMAMENT)  / 10f;

        });
        ModelPredicateProviderRegistry.register(ModItems.PYRITE_AXE, awakenedProperty, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.AWAKENED_ARMAMENT) == null)
                return 0f;

            return itemStack.get(ModDataComponentTypes.AWAKENED_ARMAMENT)  / 10f;

        });
        ModelPredicateProviderRegistry.register(ModItems.PYRITE_HOE, awakenedProperty, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.AWAKENED_ARMAMENT) == null)
                return 0f;

            return itemStack.get(ModDataComponentTypes.AWAKENED_ARMAMENT)  / 10f;

        });    }
}
