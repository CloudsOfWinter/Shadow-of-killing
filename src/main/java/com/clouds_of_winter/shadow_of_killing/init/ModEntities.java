package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.entity.projectile.GhostAttack;
import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ShadowofKilling.MOD_ID);

    public static final RegistryObject<EntityType<EliteWitherZombie>> ELITE_WITHER_ZOMBIE = ENTITY_TYPES.register("elite_wither_zombie", ()-> EntityType.Builder.<EliteWitherZombie>of(EliteWitherZombie::new, MobCategory.MONSTER).sized(0.7F, 2.4F).clientTrackingRange(8).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(new ResourceLocation(ShadowofKilling.MOD_ID, "elite_wither_zombie").toString()));
    public static final RegistryObject<EntityType<EliteWitherZombieArcher>> ELITE_WITHER_ZOMBIE_ARCHER = ENTITY_TYPES.register("elite_wither_zombie_archer", ()-> EntityType.Builder.<EliteWitherZombieArcher>of(EliteWitherZombieArcher::new, MobCategory.MONSTER).sized(0.7F, 2.4F).clientTrackingRange(8).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(new ResourceLocation(ShadowofKilling.MOD_ID, "elite_wither_zombie_archer").toString()));
    public static final RegistryObject<EntityType<WitherZombie>> WITHER_ZOMBIE = ENTITY_TYPES.register("wither_zombie", ()-> EntityType.Builder.<WitherZombie>of(WitherZombie::new, MobCategory.MONSTER).sized(0.7F, 2.4F).clientTrackingRange(8).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(new ResourceLocation(ShadowofKilling.MOD_ID, "wither_zombie").toString()));

    public static final RegistryObject<EntityType<WitherZombieArcher>> WITHER_ZOMBIE_ARCHER = ENTITY_TYPES.register("wither_zombie_archer", ()-> EntityType.Builder.<WitherZombieArcher>of(WitherZombieArcher::new, MobCategory.MONSTER).sized(0.7F, 2.4F).clientTrackingRange(8).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(new ResourceLocation(ShadowofKilling.MOD_ID, "wither_zombie_archer").toString()));
    public static final RegistryObject<EntityType<WitherZombieGuard>> WITHER_ZOMBIE_GUARD = ENTITY_TYPES.register("wither_zombie_guard", ()-> EntityType.Builder.<WitherZombieGuard>of(WitherZombieGuard::new, MobCategory.MONSTER).sized(0.7F, 2.4F).clientTrackingRange(8).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(new ResourceLocation(ShadowofKilling.MOD_ID, "wither_zombie_guard").toString()));

    public static final RegistryObject<EntityType<EliteSkeletonZombie>> ELITE_SKELETON_ZOMBIE = ENTITY_TYPES.register("elite_skeleton_zombie", ()-> EntityType.Builder.<EliteSkeletonZombie>of(EliteSkeletonZombie::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "elite_skeleton_zombie").toString()));
    public static final RegistryObject<EntityType<EliteSkeletonZombieArcher>> ELITE_SKELETON_ZOMBIE_ARCHER = ENTITY_TYPES.register("elite_skeleton_zombie_archer", ()-> EntityType.Builder.<EliteSkeletonZombieArcher>of(EliteSkeletonZombieArcher::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "elite_skeleton_zombie_archer").toString()));

    public static final RegistryObject<EntityType<SkeletonZombie>> SKELETON_ZOMBIE = ENTITY_TYPES.register("skeleton_zombie", ()-> EntityType.Builder.<SkeletonZombie>of(SkeletonZombie::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "skeleton_zombie").toString()));
    public static final RegistryObject<EntityType<SkeletonZombieArcher>> SKELETON_ZOMBIE_ARCHER = ENTITY_TYPES.register("skeleton_zombie_archer", ()-> EntityType.Builder.<SkeletonZombieArcher>of(SkeletonZombieArcher::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "skeleton_zombie_archer").toString()));
    public static final RegistryObject<EntityType<SkeletonZombieGuard>> SKELETON_ZOMBIE_GUARD = ENTITY_TYPES.register("skeleton_zombie_guard", ()-> EntityType.Builder.<SkeletonZombieGuard>of(SkeletonZombieGuard::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "skeleton_zombie_guard").toString()));

    public static final RegistryObject<EntityType<DarkZombie>> DARK_ZOMBIE = ENTITY_TYPES.register("dark_zombie", ()-> EntityType.Builder.<DarkZombie>of(DarkZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "dark_zombie").toString()));
    public static final RegistryObject<EntityType<WitherEnderman>> WITHER_ENDERMAN = ENTITY_TYPES.register("wither_enderman", ()-> EntityType.Builder.<WitherEnderman>of(WitherEnderman::new, MobCategory.MONSTER).sized(0.6F, 2.9F).clientTrackingRange(8).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(new ResourceLocation(ShadowofKilling.MOD_ID, "wither_enderman").toString()));
    public static final RegistryObject<EntityType<BlackSwordRaider>> BLACK_SWORD_RAIDER = ENTITY_TYPES.register("black_sword_raider", ()-> EntityType.Builder.<BlackSwordRaider>of(BlackSwordRaider::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "black_sword_raider").toString()));
    public static final RegistryObject<EntityType<Raider>> RAIDER = ENTITY_TYPES.register("raider", ()-> EntityType.Builder.<Raider>of(Raider::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(new ResourceLocation(ShadowofKilling.MOD_ID, "raider").toString()));



    public static final RegistryObject<EntityType<GhostAttack>> GHOST_ATTACK = ENTITY_TYPES.register("ghost_attack", () -> EntityType.Builder.<GhostAttack>of(GhostAttack::new, MobCategory.MISC).sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10).build(new ResourceLocation(ShadowofKilling.MOD_ID, "ghost_attack").toString()));
}
