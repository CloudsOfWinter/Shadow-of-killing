package com.clouds_of_winter.shadow_of_killing.handlers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerCooldownEvent extends PlayerEvent {
    private final Item item;
    private final int cooldown;

    public PlayerCooldownEvent(ServerPlayer player, Item item, int cooldown) {
        super(player);
        this.item = item;
        this.cooldown = cooldown;
    }

    public Item getItem() {
        return item;
    }

    public int getCooldown() {
        return cooldown;
    }
}
