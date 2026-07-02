package com.clouds_of_winter.shadow_of_killing.mixin;

import com.google.common.collect.ImmutableMap;
import com.clouds_of_winter.shadow_of_killing.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerHostilesSensor.class)
public abstract class VillagerHostilesSensorMixin {

    @Shadow
    @Final
    @Mutable
    private static ImmutableMap<EntityType<?>, Float> ACCEPTABLE_DISTANCE_FROM_HOSTILES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onClinit(CallbackInfo ci) {
        ImmutableMap.Builder<EntityType<?>, Float> builder = ImmutableMap.builder();
        builder.putAll(ACCEPTABLE_DISTANCE_FROM_HOSTILES);



        builder.put(ModEntities.ELITE_WITHER_ZOMBIE.get(), 8.0F);
        builder.put(ModEntities.ELITE_WITHER_ZOMBIE_ARCHER.get(), 8.0F);

        builder.put(ModEntities.WITHER_ZOMBIE.get(), 8.0F);
        builder.put(ModEntities.WITHER_ZOMBIE_ARCHER.get(), 8.0F);
        builder.put(ModEntities.WITHER_ZOMBIE_GUARD.get(), 8.0F);



        builder.put(ModEntities.ELITE_SKELETON_ZOMBIE.get(), 8.0F);
        builder.put(ModEntities.ELITE_SKELETON_ZOMBIE_ARCHER.get(), 8.0F);

        builder.put(ModEntities.SKELETON_ZOMBIE.get(), 8.0F);
        builder.put(ModEntities.SKELETON_ZOMBIE_ARCHER.get(), 8.0F);
        builder.put(ModEntities.SKELETON_ZOMBIE_GUARD.get(), 8.0F);



        builder.put(ModEntities.DARK_ZOMBIE.get(), 8.0F);



        ACCEPTABLE_DISTANCE_FROM_HOSTILES = builder.build();
    }
}