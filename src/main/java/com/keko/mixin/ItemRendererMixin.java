package com.keko.mixin;

import com.keko.CyraFinal;
import com.keko.items.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useCyraModel(BakedModel value, ItemStack stack, ModelTransformationMode modelTransformation, boolean leftHanded, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay){
		if (stack.isOf(ModItems.ZOMBIE_AXE) && modelTransformation == ModelTransformationMode.GUI) {
			return ((ItemRendererAccessor) this).cyra$getModel().getModelManager().getModel(new ModelIdentifier(Identifier.of(CyraFinal.MOD_ID, "zombie_leader_battle_axe_inv"), "inventory"));
		}
		if (stack.isOf(ModItems.SKELETON_BOW) && modelTransformation == ModelTransformationMode.GUI) {
			return ((ItemRendererAccessor) this).cyra$getModel().getModelManager().getModel(new ModelIdentifier(Identifier.of(CyraFinal.MOD_ID, "skeleton_leader_battle_bow_inv"), "inventory"));
		}
		return value;
	}
}
