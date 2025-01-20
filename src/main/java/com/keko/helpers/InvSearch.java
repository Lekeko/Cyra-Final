package com.keko.helpers;

import com.keko.items.tools.OxygenTank;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InvSearch {
	public static Boolean hasItemInInv(PlayerEntity user, Item item){
		for (ItemStack stack : user.getInventory().main){
			if (stack.isOf(item)){
				return true;
			}
		}
		return false;
	}

	public static ItemStack hasO2(PlayerEntity user){
		for (ItemStack stack : user.getInventory().main){
			if (stack.getItem() instanceof OxygenTank){
				return stack;
			}
		}
		return null;
	}

	public static ItemStack getItemInInv(PlayerEntity user, Item item){
		for (ItemStack stack : user.getInventory().main){
			if (stack.isOf(item)){
				return item.getDefaultStack();
			}
		}
		return null;
	}
	public static ItemStack getItemStackInInv(PlayerEntity user, Item item){
		for (ItemStack stack : user.getInventory().main){
			if (stack.isOf(item)){
				return stack;
			}
		}
		return null;
	}
}
