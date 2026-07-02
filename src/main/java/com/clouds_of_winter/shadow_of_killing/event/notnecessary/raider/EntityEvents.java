package com.clouds_of_winter.shadow_of_killing.event.notnecessary.raider;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityEvents {
    /**
    @SubscribeEvent
    public static void onLightningStrike(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());

        // 检查被闪电击中的实体是否为 rottencreatures:zap
        if (entityTypeKey != null && entityTypeKey.toString().equals("rottencreatures:immortal") || entityTypeKey.toString().equals("rottencreatures:zap")) {
            // 记录当前游戏时间作为标记时间
            entity.getPersistentData().putLong("StruckByLightningTime", entity.level.getGameTime());
        }
    }
    */
}
