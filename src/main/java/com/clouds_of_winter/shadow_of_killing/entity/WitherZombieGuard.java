package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class WitherZombieGuard extends WitherZombieArcher {
    public WitherZombieGuard(EntityType<? extends WitherZombieGuard> p_34166_, Level p_34167_) {
        super(p_34166_, p_34167_);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_) {
        this.meleeStack = new ItemStack(Items.IRON_SWORD);
        this.setItemSlot(EquipmentSlot.MAINHAND, this.bowStack.copy());

        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
    }
}
