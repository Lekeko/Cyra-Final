package com.keko.entities.projectiles.pyriteCube;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.CyraFinal;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.packet.CubeColorPayload;
import com.keko.packet.DegreePayload;
import com.keko.particle.ModParticles;
import foundry.veil.Veil;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.api.quasar.data.ParticleSettings;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.awt.*;
import java.util.HashMap;

import static com.keko.items.weapons.PyritePrimordialCube.getRandomFloat;

public class PCube extends PersistentProjectileEntity {

    private Vec3d target;
    private int timeAlive = 0;
    double speed = .6;
    public  Color color;
    private boolean bouncerStart = false;
    private final Light light = new PointLight().setBrightness(1).setColor(1, 1 ,1).setRadius(10);
    private int variant;
    public int degree;

    public PCube(EntityType<? extends PCube> entityType, World world) {
        super(entityType, world);
        colorHashMap.put(1, new Color(255, 235, 126, 255));
        colorHashMap.put(2, new Color(227, 93, 254, 255));
        colorHashMap.put(3, new Color(61, 255, 175, 255));
        colorHashMap.put(4, new Color(255, 25, 48, 255));
        colorHashMap.put(5, new Color(255, 102, 25, 255));
        colorHashMap.put(6, new Color(113, 217, 41, 255));
    }

    HashMap<Integer, Color> colorHashMap = new HashMap<>();

    @Override
    public boolean isGlowing() {
        return true;
    }



    @Override
    protected float getDragInWater() {
        return  1f;
    }

    public int getVariant() {
        return variant;
    }

    public PCube(World world, LivingEntity entity, @Nullable ItemStack stack, Vec3d target, Color color, int variant, int degree){
        super(ModProjectileEntities.P_CUBE_ENTITY_TYPE, entity, world, stack, stack);
        this.target = new Vec3d(target.getX(), target.getY()-0.9d, target.getZ());
        this.color = color;
        colorHashMap.put(1, new Color(255, 235, 126, 255));
        colorHashMap.put(2, new Color(227, 93, 254, 255));
        colorHashMap.put(3, new Color(61, 255, 175, 255));
        colorHashMap.put(4, new Color(255, 25, 48, 255));
        colorHashMap.put(5, new Color(255, 102, 25, 255));
        colorHashMap.put(6, new Color(113, 217, 41, 255));
        this.variant = variant;
        this.degree= degree;
        ServerPlayNetworking.send((ServerPlayerEntity) this.getOwner(), new DegreePayload(degree, this.getId()));
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {

        super.onSpawnPacket(packet);
        if (this.getWorld().isClient && variant != 5)
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);

        if (getOwner() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) this.getOwner();
            this.color = colorHashMap.get(player.getStackInHand(player.getActiveHand()).get(ModDataComponentTypes.VARIANT));
        }
    }

    @Override
    protected void checkBlockCollision() {

    }


    @Override
    public boolean hasNoGravity() {
        return true;
    }
    /// /////////////


    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
    }
    

    @Override
    public boolean isNoClip() {
        return true;
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        if (!this.getWorld().isClient && entityHitResult.getEntity() != this.getOwner()) {
            if (variant == 1) {
                entityHitResult.getEntity().setVelocity(0, 1, 0);
                entityHitResult.getEntity().velocityModified = true;
            }
            entityHitResult.getEntity().damage(this.getWorld().getDamageSources().generic(), 10);
        }
    }

    public void setColor(Color color){
        this.color = color;
        if (variant == 5) light.setBrightness(0);
    }





    /// /////////////
    @Override
    public void tick() {




        if (!this.getWorld().isClient && target != null && this.color != null){
            timeAlive++;
            switch (variant){
                case 1->bouncer();
                case 4->burstEight();
                case 3->noraml();
                case 2->starBursts();
                case 5->spin();
                case 6->healer();
            }

            //if (variant!= 5)
               // spawnTrailParticle(this, Identifier.of("cyra:cube_trail"));



            if (timeAlive > (30 + (variant == 4 ? 100 : 0) + (variant == 5 ? 1000 : 0)) || !getOwner().isAlive())
                this.discard();


        }



        if (!this.getWorld().isClient){
            Vec3d vec3d = this.getVelocity();

            Vec3d vec3d3 = this.getPos();
            Vec3d vec3d2 = vec3d3.add(vec3d);
            EntityHitResult entityHitResult = this.getEntityCollision(vec3d3, vec3d2);
            if (entityHitResult != null){
                if (entityHitResult.getEntity() != null && entityHitResult.getEntity() != this.getOwner() && variant != 6)
                    entityHitResult.getEntity().damage(this.getWorld().getDamageSources().generic(), 10);
                if (entityHitResult.getEntity() != this.getOwner()) {
                    if (variant == 1) {
                        entityHitResult.getEntity().setVelocity(0, 1, 0);
                        entityHitResult.getEntity().addVelocity(this.getVelocity());
                        entityHitResult.getEntity().velocityModified = true;
                    }
                }
            }


        }else {

            ((PointLight)this.light).setPosition(this.getX(), this.getY(), this.getZ());
            if (color != null){
                light.setColor(this.color.getRed() * 255 * 255 + this.color.getGreen() * 255 + this.color.getBlue());
                if (this.color.getGreen() == 102)
                    VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

            }
        }




        super.tick();
    }

    private void healer() {
        if (age > 20)
            discard();
    }

    private void spin() {
        int radius = 6;
        PCube cube = null;
        Box box = new Box(getX() + radius, getY() + radius, getZ() + radius, getX() - radius, getY() - radius, getZ() - radius);
        for (LivingEntity entity : getWorld().getEntitiesByClass(LivingEntity.class, box, LivingEntity::isAlive)) {
            if (entity != this.getOwner() && entity.isAttackable()) {
                entity.damage(getWorld().getDamageSources().generic(), (float) 10 / (entity.getArmor() > 0 ? (float) entity.getArmor() / 10f : 1));
                timeAlive +=8;
            }


        }

        this.setPos(getOwner().getX(), getOwner().getY() , getOwner().getZ());
        Vec3d currentPos = this.getPos();
        double distanceX = getOwner().getX() - currentPos.x;
        double distanceY = getOwner().getY() - currentPos.y ;
        double distanceZ = getOwner().getZ() - currentPos.z;
        double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        if (distanceSquared > 1.0) {

            double scale = 0.1 / Math.sqrt(distanceSquared);
            double velocityX = distanceX * scale;
            double velocityY = distanceY * scale;
            double velocityZ = distanceZ * scale;

            this.addVelocity(velocityX, velocityY, velocityZ);
        }
    }

    private void starBursts() {
        spawnParticle(this, Identifier.of("cyra:star"));
        double distance = 9999;
        LivingEntity entity = null;
        Box box = new Box(this.getX() + 20, this.getY() + 20, this.getZ() + 20, this.getX() - 20, this.getY() - 20, this.getZ() - 20);
        for (LivingEntity entity1 : getWorld().getEntitiesByClass(LivingEntity.class, box, Entity::isAlive)){
            double distance1 = Math.sqrt(Math.pow(entity1.getX() - this.getX(), 2) + Math.pow(entity1.getY() - this.getY(), 2) + Math.pow(entity1.getZ() - this.getZ(), 2));
            if (distance > distance1 && entity1 != this.getOwner()){
                distance = distance1;
                entity = entity1;
            }
        }

        if (entity != null){
            PCube cube = new PCube(getWorld() , (LivingEntity) this.getOwner(), Items.GLASS.getDefaultStack(), entity.getPos(), new Color(255, 173, 247, 255), 3, 0);

            double xOffset = getRandomFloat(-1,1);
            double zOffset = getRandomFloat(-1,1);

            Vec3d playerPos = this.getPos();
            float playerYaw = getRandomFloat(1, 360);

            double X = playerPos.x + xOffset * Math.cos(Math.toRadians(playerYaw)) - zOffset * Math.sin(Math.toRadians(playerYaw));
            double Z = playerPos.z + xOffset * Math.sin(Math.toRadians(playerYaw)) + zOffset * Math.cos(Math.toRadians(playerYaw));

            double motionX = X - playerPos.x;
            double motionZ = Z - playerPos.z;



            double distanceX = Math.pow(Math.abs(entity.getX()) - Math.abs(this.getX()), 2);
            double distanceY = Math.pow(Math.abs(entity.getY() + 1) - Math.abs(this.getY()), 2);
            double distanceZ = Math.pow(Math.abs(entity.getZ()) - Math.abs(this.getZ()), 2);

            double distance2 = Math.abs(20 - Math.sqrt(distanceX + distanceY + distanceZ));

            if (distance2 < 1)
                distance2 = 1;

            cube.setVelocity(motionX / distance2, getRandomFloat(-0.3f, 0.3f), motionZ / distance2);

            cube.setPos(X, this.getY(), Z);



            getWorld().spawnEntity(cube);
        }


    }

    public void spawnParticle(Entity entity, Identifier id){
        try {
            ParticleSystemManager manager = VeilRenderSystem.renderer().getParticleManager();
            ParticleEmitter emitter = manager.createEmitter(id);
            emitter.setAttachedEntity(entity);
            emitter.setParticleSettings(new ParticleSettings(0.1f+ (id.toString().contains("red") ? 0.3f : 0), 0.1f + (id.toString().contains("red") ? 0.3f : 0), 0.05f,
                    20, 0f,
                    new Vector3f(
                            this.getWorld().random.nextFloat() - .5f,
                            (this.getWorld().random.nextFloat() - .5f)  * 5f,
                            this.getWorld().random.nextFloat() - .5f),
                    false,true,true,true,false));
            manager.addParticleSystem(emitter);
        } catch (Exception ignored) {
        }
    }

    private void noraml() {
        Vec3d currentPos = this.getPos();
        double distanceX = target.getX() - currentPos.x;
        double distanceY = target.getY() - currentPos.y;
        double distanceZ = target.getZ() - currentPos.z;
        double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        if (distanceSquared > 1.0) {

            double scale = speed / Math.sqrt(distanceSquared);
            double velocityX = distanceX * scale;
            double velocityY = distanceY * scale;
            double velocityZ = distanceZ * scale;

            this.addVelocity(velocityX, velocityY, velocityZ);
        }
    }

    private void burstEight() {

        if (timeAlive > 50){
            this.setVelocity(new Vec3d(target.getX(), target.getY(), target.getZ()).subtract(this.getX(), this.getY(), this.getZ()).normalize().multiply(10f));
        }else {
            this.addVelocity(0,0.3f, 0);
        }
        double distance = Math.sqrt(Math.pow(target.x - this.getX(), 2) + Math.pow(target.y - this.getY(), 2) + Math.pow(target.z - this.getZ(), 2));

        if (distance < 5 && timeAlive > 50){
            createCustomExplosion(this, this.getX(), this.getY(), this.getZ(), 9);
            discard();
        }
    }

    private void createCustomExplosion(PCube pCube, double x, double y, double z, int i) {
        int radius = i;
        Box box = new Box(x + radius, y + radius, z + radius, x - radius, y - radius, z - radius);
        for (LivingEntity entity : getWorld().getEntitiesByClass(LivingEntity.class, box, LivingEntity::isAlive)) {
            for (int m  =0 ; m < 20; m++)
                spawnParticlePos(entity.getPos(), Identifier.of("cyra:star_red"));
            entity.damage(getWorld().getDamageSources().generic(), (float) 30 / (entity.getArmor() > 0 ? (float) entity.getArmor() / 10f : 1));
            if (getWorld() instanceof ServerWorld)
                ((ServerWorld) getWorld()).spawnParticles(ParticleTypes.END_ROD, x,y,z, 50, random.nextFloat() - .5f, random.nextFloat() - .5f, random.nextFloat() - .5f, 2);
            getWorld().playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 10, 1);
        }

    }

    private void spawnParticlePos(Vec3d pos, Identifier id) {
        try {
            ParticleSystemManager manager = VeilRenderSystem.renderer().getParticleManager();
            ParticleEmitter emitter = manager.createEmitter(id);
            emitter.setPosition(pos.x, pos.y, pos.z);
            emitter.setParticleSettings(new ParticleSettings(0.1f+ (id.toString().contains("red") ? 0.3f : 0), 0.1f + (id.toString().contains("red") ? 0.3f : 0), 0.05f,
                    20, 0f,
                    new Vector3f(
                            (this.getWorld().random.nextFloat() - .5f)  * 5f,
                            (this.getWorld().random.nextFloat() - .5f)  * 5f,
                            (this.getWorld().random.nextFloat() - .5f)  * 5f),
                    false,true,true,true,false));
            manager.addParticleSystem(emitter);
        } catch (Exception ignored) {
        }
    }

    private void bouncer() {
        if (!bouncerStart){
            bouncerStart = true;
            this.setVelocity(new Vec3d(target.getX(), target.getY(), target.getZ()).subtract(this.getX(), this.getY(), this.getZ()).normalize().multiply(2f));
        }
    }

    @Override
    public void onRemoved() {
        if (this.getWorld().isClient)
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

    }

}
