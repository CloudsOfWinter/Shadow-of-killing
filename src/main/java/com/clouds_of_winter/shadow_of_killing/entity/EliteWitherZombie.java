package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public class EliteWitherZombie extends EliteWitherZombieArcher {
    public EliteWitherZombie(EntityType<? extends EliteWitherZombie> p_34166_, Level p_34167_) {
        super(p_34166_, p_34167_);
        this.xpReward = 55;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EliteWitherZombieArcher.createAttributes()
                .add(Attributes.MAX_HEALTH, 180.0D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }


    protected void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.STARDEMON_BATTLEAXE.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
    }

    protected AbstractArrow getArrow(ItemStack p_34189_, float p_34190_) {
        AbstractArrow abstractarrow = super.getArrow(p_34189_, p_34190_);
        abstractarrow.setEnchantmentEffectsFromEntity(this, 4.0F);
        return abstractarrow;
    }
}
