package com.clouds_of_winter.shadow_of_killing.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public class SkeletonZombie extends SkeletonZombieGuard {
    public SkeletonZombie(EntityType<? extends SkeletonZombie> p_33570_, Level p_33571_) {
        super(p_33570_, p_33571_);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return SkeletonZombieArcher.createAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0D);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
        if (p_218949_.nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
            int i = p_218949_.nextInt(3);
            if (i == 0) {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            }
        }

        if (p_218949_.nextFloat() < 0.15F * p_218950_.getSpecialMultiplier()) {
            int i = p_218949_.nextInt(2);
            float f = this.level.getDifficulty() == Difficulty.HARD ? 0.1F : 0.25F;
            if (p_218949_.nextFloat() < 0.095F) {
                ++i;
            }

            if (p_218949_.nextFloat() < 0.095F) {
                ++i;
            }

            if (p_218949_.nextFloat() < 0.095F) {
                ++i;
            }

            boolean flag = true;

            for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
                if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack itemstack = this.getItemBySlot(equipmentslot);
                    if (!flag && p_218949_.nextFloat() < f) {
                        break;
                    }

                    flag = false;
                    if (itemstack.isEmpty()) {
                        Item item = getEquipmentForSlot(equipmentslot, i);
                        if (item != null) {
                            this.setItemSlot(equipmentslot, new ItemStack(item));
                        }
                    }
                }
            }
        }
    }

    protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
        AbstractArrow abstractarrow = super.getArrow(p_32156_, p_32157_);
        abstractarrow.setEnchantmentEffectsFromEntity(this, 1.25F);
        return abstractarrow;
    }
}
