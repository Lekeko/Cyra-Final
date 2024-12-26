package com.keko.items.tools;

import com.keko.CyraFinal;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlashLight extends Item {

    /*private short brightness = 0;
    private Light light;
    private boolean added = false;*/

    public FlashLight(Settings settings) {
        super(settings);
        //this.light =  new AreaLight().setDistance(50f).setBrightness(2f).setColor(16777215);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        /*if (world.isClient){
            if (brightness == 0)
                brightness = 1;
            else
                brightness = 0;

            this.light.setBrightness(brightness);
        }
        if (world.isClient && !added){
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);
            added = true;
        }*/


        if (world.isClient){
            spawnParticle(user, Identifier.of(CyraFinal.MOD_ID, "star"));
        }else {

        }



        return super.use(world, user, hand);
    }

    public static void spawnParticle(Entity entity, Identifier id){
        try {
            ParticleSystemManager manager = VeilRenderSystem.renderer().getParticleManager();
            ParticleEmitter emitter = manager.createEmitter(id);
            emitter.setAttachedEntity(entity);
            System.out.println(emitter.getPosition());
            //emitter.setPosition(entity.getX(), entity.getY(), entity.getZ());
            manager.addParticleSystem(emitter);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();

        //if (world.isClient)
            //this.light.setTo(camera);

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
