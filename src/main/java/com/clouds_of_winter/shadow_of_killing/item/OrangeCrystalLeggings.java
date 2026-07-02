package com.clouds_of_winter.shadow_of_killing.item;

import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class OrangeCrystalLeggings extends ArmorItem {
    public OrangeCrystalLeggings(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack itemstack, Level level, Player entity) {
        super.onArmorTick(itemstack,level,entity);
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 0, false, false, true));
        boolean isFullSet = entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ORANGE_CRYSTAL_HELMET.get() && entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.ORANGE_CRYSTAL_CHESTPLATE.get() && entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.ORANGE_CRYSTAL_LEGGINGS.get() && entity.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.ORANGE_CRYSTAL_BOOTS.get();
        if (isFullSet) {
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 1, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2, 1, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2, 1, false, false, true));
        }
    }
}
