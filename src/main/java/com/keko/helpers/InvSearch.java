package com.keko.helpers;

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

	public static ItemStack getItemInInv(PlayerEntity user, Item item){
		for (ItemStack stack : user.getInventory().main){
			if (stack.isOf(item)){
				return item.getDefaultStack();
			}
		}
		return null;
	}
}
