package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public class EliteSkeletonZombie extends EliteSkeletonZombieArcher {
    public EliteSkeletonZombie(EntityType<? extends EliteSkeletonZombie> p_33570_, Level p_33571_) {
        super(p_33570_, p_33571_);
        this.xpReward = 50;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EliteSkeletonZombieArcher.createAttributes()
                .add(Attributes.MAX_HEALTH, 160.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 19.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SOUL_SHATTERING_WARHAMMER.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
    }
    protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
        AbstractArrow abstractarrow = super.getArrow(p_32156_, p_32157_);
        abstractarrow.setEnchantmentEffectsFromEntity(this, 4.0F);
        return abstractarrow;
    }
}
