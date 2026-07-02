package com.clouds_of_winter.shadow_of_killing.handlers;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShieldDisableManager {
    // 存储实体ID和禁用结束时间
    private static final Map<UUID, Long> disabledEntities = new HashMap<>();
    private static long globalDisableEndTime = 0;

    // 禁用所有实体盾牌5秒
    public static void disableAllShields(ServerLevel level) {
        globalDisableEndTime = level.getGameTime() + 100; // 100 ticks = 5秒
        MinecraftForge.EVENT_BUS.register(new ShieldDisableHandler());
    }

    // 检查实体是否被禁用盾牌
    public static boolean isShieldDisabled(Entity entity) {
        if (entity.level.getGameTime() < globalDisableEndTime) {
            return true;
        }
        Long endTime = disabledEntities.get(entity.getUUID());
        return endTime != null && entity.level.getGameTime() < endTime;
    }

    // 事件处理器（动态注册）
    public static class ShieldDisableHandler {
        @SubscribeEvent
        public void onWorldTick(TickEvent.LevelTickEvent event) {
            if (event.level.getGameTime() >= globalDisableEndTime) {
                MinecraftForge.EVENT_BUS.unregister(this);
            }
        }
    }
}
