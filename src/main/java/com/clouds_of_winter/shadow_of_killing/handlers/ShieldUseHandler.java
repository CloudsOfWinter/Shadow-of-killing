package com.clouds_of_winter.shadow_of_killing.handlers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ShieldUseHandler {
    @SubscribeEvent
    public static void onShieldUse(LivingEntityUseItemEvent.Start event) {
        ItemStack item = event.getItem();
        LivingEntity entity = event.getEntity();

        if (item.getItem() instanceof ShieldItem &&
                ShieldDisableManager.isShieldDisabled(entity)) {
            event.setCanceled(true); // 阻止盾牌使用
        }
    }

    @SubscribeEvent
    public static void onShieldBlock(LivingEntityUseItemEvent.Tick event) {
        ItemStack item = event.getItem();
        LivingEntity entity = event.getEntity();

        if (item.getItem() instanceof ShieldItem &&
                ShieldDisableManager.isShieldDisabled(entity)) {
            entity.stopUsingItem(); // 强制停止正在进行的格挡
        }
    }
}
