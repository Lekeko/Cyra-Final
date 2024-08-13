package com.keko;

import com.keko.game.HealCooldown;
import com.keko.game.HealPayload;
import com.keko.game.KeyBinds;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.screen.AlchemyTableScreen;
import com.keko.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.item.ItemStack;

public class CyraModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playC2S().register(HealPayload.ID, HealPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(HealPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                context.player().heal(2 * KeyBinds.healing_amount);
                ItemStack itemStack = InvSearch.getItemInInv(context.player(), ModItems.HEALING_FLASK);
                assert itemStack != null;
                context.player().getItemCooldownManager().set(itemStack.getItem(), KeyBinds.cooldown * 20);
            });
        });

        HandledScreens.register(ModScreenHandlers.ALCHEMY_TABLE_SCREENHANDLER_SCREEN_HANDLER_TYPE, AlchemyTableScreen::new);

        HudRenderCallback.EVENT.register(new HealCooldown());
    }
}
