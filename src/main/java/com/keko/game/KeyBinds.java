package com.keko.game;

import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.packet.networking.ModMessages;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.jmx.Server;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {

    public static float healing_amount = 3; // Hearts
    public static int cooldown = 30; //seconds
    public static int cooldownBuff = 60 * 4; //seconds

    public static KeyBinding keyBinding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cyra.heal",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "key.cyra.cyramod"
    ));

    public static KeyBinding keyBinding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cyra.buff",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.cyra.cyramod"
    ));

    public static KeyBinding keyBindingDash1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cyra.dash1",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_D,
            "key.cyra.cyramod"
    ));
    public static KeyBinding keyBindingDash2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cyra.dash2",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_A,
            "key.cyra.cyramod"
    ));

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding1.wasPressed()){
                assert client.player != null;
                if (InvSearch.hasItemInInv(client.player, ModItems.HEALING_FLASK)){
                    ItemStack itemStack = InvSearch.getItemInInv(client.player, ModItems.HEALING_FLASK);
                    assert itemStack != null;
                    if (!client.player.getItemCooldownManager().isCoolingDown(itemStack.getItem())){

                        PacketByteBuf buf = PacketByteBufs.create();
                        buf.writeInt(10);

                        ClientPlayNetworking.send(new HealPayload(10));
                    }
                }
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding2.wasPressed()){
                assert client.player != null;
                if (InvSearch.hasItemInInv(client.player, ModItems.BUFF_FLASK)){
                    ItemStack itemStack = InvSearch.getItemInInv(client.player, ModItems.BUFF_FLASK);
                    assert itemStack != null;
                    if (!client.player.getItemCooldownManager().isCoolingDown(itemStack.getItem())){

                        PacketByteBuf buf = PacketByteBufs.create();
                        buf.writeInt(10);

                        ClientPlayNetworking.send(new BuffPayload(10));
                    }
                }
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBindingDash1.wasPressed()){
                assert client.player != null;
                client.player.addVelocity(5, 0, 0);
                client.player.velocityModified = true;
            }
        });
    }

    public static void initializeKeyBinds(){
        registerKeyInputs();
    }
}

