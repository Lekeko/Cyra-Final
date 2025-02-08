package com.keko.items.weapons;


import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.entities.bosses.oldLord.OldLordEntity;
import com.keko.helpers.CyraBoxHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class PyriteSwordItem extends SwordItem {

    public PyriteSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.getEntitiesByClass(OldLordEntity.class, CyraBoxHelper.createBox(entity.getBlockPos(), 40,40,40), Entity::isAlive).isEmpty())
            stack.set(ModDataComponentTypes.AWAKENED_ARMAMENT, 1);
        else
            stack.set(ModDataComponentTypes.AWAKENED_ARMAMENT, 0);

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
