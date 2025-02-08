package com.keko.mixin;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
	@Shadow
	protected abstract void loadItemModel(ModelIdentifier id);

	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V"))
	public void addCyraTrident(BlockColors blockColors, Profiler profiler, Map jsonUnbakedModels, Map blockStates, CallbackInfo ci) {
		this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of("cyra:zombie_leader_battle_axe_inv")));
		this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of("cyra:skeleton_leader_battle_bow_inv")));
		this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of("cyra:awakened_pyrite_sword_inv")));
		this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of("cyra:awakened_pyrite_axe_inv")));
		this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of("cyra:awakened_pyrite_hoe_inv")));
	}
}

