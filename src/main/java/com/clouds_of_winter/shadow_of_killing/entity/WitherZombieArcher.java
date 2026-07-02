package com.clouds_of_winter.shadow_of_killing.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public class WitherZombieArcher extends EliteWitherZombie {
    public WitherZombieArcher(EntityType<? extends WitherZombieArcher> p_34166_, Level p_34167_) {
        super(p_34166_, p_34167_);
        this.xpReward = 5;
    }



    public static AttributeSupplier.Builder createAttributes() {
        return EliteWitherZombie.createAttributes()
                .add(Attributes.MAX_HEALTH, 38.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0D);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        if (p_219154_.nextFloat() < 0.15F * p_219155_.getSpecialMultiplier()) {
            int i = p_219154_.nextInt(2);
            float f = this.level.getDifficulty() == Difficulty.HARD ? 0.1F : 0.25F;
            if (p_219154_.nextFloat() < 0.095F) {
                ++i;
            }

            if (p_219154_.nextFloat() < 0.095F) {
                ++i;
            }

            if (p_219154_.nextFloat() < 0.095F) {
                ++i;
            }

            boolean flag = true;

            for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
                if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack itemstack = this.getItemBySlot(equipmentslot);
                    if (!flag && p_219154_.nextFloat() < f) {
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

    protected AbstractArrow getArrow(ItemStack p_34189_, float p_34190_) {
        AbstractArrow abstractarrow = super.getArrow(p_34189_, p_34190_);
        abstractarrow.setEnchantmentEffectsFromEntity(this, 1.25F);
        if (abstractarrow instanceof Arrow) {
            lastAttackTime2 = level.getGameTime();
            isHealing3 = true;
        }
        return abstractarrow;
    }

    /**
    private int tickCounter2 = 0;
    */

    // 用于记录上次被攻击的时间
    private long lastAttackedTime2 = 0;
    private boolean isHealing4 = false;


    private long lastAttackTime2 = 0;
    // 标记是否正在恢复生命值
    private boolean isHealing3 = false;

    protected boolean EliteWitherZombiedoHurtTarget() {
        return false;
    }

    protected boolean EliteWitherZombiehurt() {
        return false;
    }

    protected boolean EliteWitherZombieLifeRestored() {
        return false;
    }

    protected boolean WitherZombiedoHurtTarget() {
        long currentTime3 = level.getGameTime();
        if (isHealing3 && currentTime3 - lastAttackTime2 < 41) {
            if ((currentTime3 - lastAttackTime2) % 2 == 0) {
                if (this.isAlive()){
                    this.heal(0.15F);
                }
            }
        } else {
            isHealing3 = false;
        }
        return true;
    }
    protected boolean WitherZombiehurt() {
        long currentTime4 = level.getGameTime();
        if (isHealing4 && currentTime4 - lastAttackedTime2 < 61) {
            if ((currentTime4 - lastAttackedTime2) % 3 == 0) {
                if (this.isAlive()){
                    this.heal(0.1F);
                }
            }
        } else {
            isHealing4 = false;
        }

        return true;
    }

    protected boolean WitherZombieLifeRestored() {
        /**
        if (!this.isAggressive() && !this.isDeadOrDying() && this.getHealth() < this.getMaxHealth()){
            tickCounter2++;
            if (tickCounter2 >= 6) {
                // 回复 0.1 生命值
                tickCounter2 = 0;
                this.setHealth(this.getHealth() + 0.1F);
                if (this.getHealth() > this.getMaxHealth()) {
                    this.setHealth(this.getMaxHealth());
                }
            }
        }
        */


        if (this.isAlive() && this.tickCount % 6 == 0) {
            if (!this.isAggressive()){
                this.heal(0.1F); // 恢复0.1点生命值
            }

        }



        return true;
    }

    public void tick() {

        /**
        this.WitherZombieLifeRestored();
        */


        super.tick();
    }

    public void aiStep() {
        this.WitherZombiedoHurtTarget();
        this.WitherZombiehurt();

        this.WitherZombieLifeRestored();
        super.aiStep();
    }

    public boolean doHurtTarget(Entity p_34169_) {
        boolean result = super.doHurtTarget(p_34169_);
        if (result) {
            lastAttackTime2 = level.getGameTime();
            isHealing3 = true;
        }
        return result;
    }

    public boolean hurt(DamageSource source, float amount) {
        boolean result2 = super.hurt(source, amount);
        if (result2) {
            lastAttackedTime2 = level.getGameTime();
            isHealing4 = true;
        }
        return result2;
    }
}
