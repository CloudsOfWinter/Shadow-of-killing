package com.clouds_of_winter.shadow_of_killing.mixin;

import com.clouds_of_winter.shadow_of_killing.entity.EliteSkeletonZombieArcher;
import com.clouds_of_winter.shadow_of_killing.entity.EliteWitherZombieArcher;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.WanderingTrader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin {
    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void onRegisterGoals(CallbackInfo ci) {
        WanderingTrader wanderingtrader = (WanderingTrader) (Object) this;

        wanderingtrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingtrader, EliteWitherZombieArcher.class,  8.0F,  (double)0.5F, (double)0.5F));
        wanderingtrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingtrader, EliteSkeletonZombieArcher.class,  8.0F,  (double)0.5F, (double)0.5F));

    }

    /**
    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V")
    private void onInitGoalSelector(CallbackInfo info) {
        WanderingTrader wanderingtrader = (WanderingTrader) (Object) this;
        if (wanderingtrader.level != null && !wanderingtrader.level.isClientSide) {
            wanderingtrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingtrader, EliteWitherZombieArcher.class,  8.0F,  (double)0.5F, (double)0.5F));
            wanderingtrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingtrader, EliteSkeletonZombieArcher.class,  8.0F,  (double)0.5F, (double)0.5F));
        }
    }
    */
}
