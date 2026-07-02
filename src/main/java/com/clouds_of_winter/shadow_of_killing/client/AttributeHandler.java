package com.clouds_of_winter.shadow_of_killing.client;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = ShadowofKilling.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeHandler {

    /**


    private static final UUID ATTACK_RANGE_UUID = UUID.fromString("a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d");
    private static final UUID REACH_DISTANCE_UUID = UUID.fromString("1a1b1c1d-2e2f-3a3b-4c4d-5e5f6a6b7c8d");

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();

            // 检查主手是否持有自定义工具
            boolean hasTool = mainHandItem.getItem() == ModItems.BLACK_SWORD.get() || mainHandItem.getItem() == ModItems.ORANGE_CRYSTAL_SWORD.get(); // 替换为你的工具类

            AttributeInstance attackRange = player.getAttribute(ForgeMod.ATTACK_RANGE.get());
            if (attackRange == null) return;

            AttributeModifier attackRangecurrentModifierTwogrids = attackRange.getModifier(ATTACK_RANGE_UUID);

            if (hasTool) {
                // 添加或刷新修饰符
                if (attackRangecurrentModifierTwogrids == null) {
                    AttributeModifier newModifier = new AttributeModifier(
                            ATTACK_RANGE_UUID,
                            "orange_crystal_sword_and_black_sword_attack_range_boost",
                            0.5,  // 翻倍 (MULTIPLY_TOTAL = 基础值 * (1 + 1.0))
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );
                    attackRange.addTransientModifier(newModifier);
                }
            } else {
                // 移除修饰符
                if (attackRangecurrentModifierTwogrids != null) {
                    attackRange.removeModifier(ATTACK_RANGE_UUID);
                }
            }



            AttributeInstance reachDistance = player.getAttribute(ForgeMod.REACH_DISTANCE.get());

            if (reachDistance == null) return;

            AttributeModifier reachDistancecurrentModifierTwogrids = reachDistance.getModifier(REACH_DISTANCE_UUID);

            if (hasTool) {
                // 添加或刷新修饰符
                if (reachDistancecurrentModifierTwogrids == null) {
                    AttributeModifier newModifier = new AttributeModifier(
                            REACH_DISTANCE_UUID,
                            "orange_crystal_sword_and_black_sword_reach_distance_boost",
                            0.5,  // 翻倍 (MULTIPLY_TOTAL = 基础值 * (1 + 1.0))
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    );
                    reachDistance.addTransientModifier(newModifier);
                }
            } else {
                // 移除修饰符
                if (reachDistancecurrentModifierTwogrids != null) {
                    reachDistance.removeModifier(REACH_DISTANCE_UUID);
                }
            }




        }

    }
    */




    // 黑曜石工具UUID (必须唯一)
    private static final UUID ORANGE_CRYSTAL_SWORD_AND_BLACK_SWORD_ATTACK_RANGE_UUID = UUID.fromString("a1b2c3d4-5e6f-7a8b-9c0d-ef1234567890");
    private static final UUID ORANGE_CRYSTAL_SWORD_AND_BLACK_SWORD_REACH_DISTANCE_UUID = UUID.fromString("1a1b1c1d-2e2f-3a3b-4c4d-5e5f6a6b7c8d");

    // 末影工具UUID (必须唯一)
    private static final UUID DARK_IRON_SWORD_AND_SOUL_SHATTERING_WARHAMMER_AND_ORANGE_CRYSTAL_HOE_AND_ORANGE_CRYSTAL_PICKAXE_AND_ORANGE_CRYSTAL_SHOVEL_AND_ORANGE_CRYSTAL_AXE_ATTACK_RANGE_UUID = UUID.fromString("b1c2d3e4-5f6a-7b8c-9d0e-fa1234567890");
    private static final UUID DARK_IRON_SWORD_AND_SOUL_SHATTERING_WARHAMMER_AND_ORANGE_CRYSTAL_HOE_AND_ORANGE_CRYSTAL_PICKAXE_AND_ORANGE_CRYSTAL_SHOVEL_AND_ORANGE_CRYSTAL_AXE_REACH_DISTANCE_UUID = UUID.fromString("a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d");

    private static final UUID STARDEMON_BATTLEAXE_ATTACK_RANGE_UUID = UUID.fromString("c2a48879-59ff-4d94-85cf-5a3838f5d57a");
    private static final UUID STARDEMON_BATTLEAXE_REACH_DISTANCE_UUID = UUID.fromString("947a9652-fc14-4c54-8d69-de94d184ab8c");

    @SubscribeEvent
    public static void applyAttributeModifiers(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();

        // 只处理主手和副手装备槽
        if (event.getSlotType() != EquipmentSlot.MAINHAND) {
            return;
        }
        // 处理黑曜石工具 (2倍距离)
        if (stack.getItem() == ModItems.ORANGE_CRYSTAL_SWORD.get() || stack.getItem() == ModItems.BLACK_SWORD.get()) {
            // 原版基础攻击距离为3.0，增加3.0达到6.0 (2倍)
            event.addModifier(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(ORANGE_CRYSTAL_SWORD_AND_BLACK_SWORD_ATTACK_RANGE_UUID, "orange_crystal_sword_and_black_sword_attack_range_boost", 2.0, AttributeModifier.Operation.ADDITION));
            event.addModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ORANGE_CRYSTAL_SWORD_AND_BLACK_SWORD_REACH_DISTANCE_UUID, "orange_crystal_sword_and_black_sword_reach_distance_boost", 2.0, AttributeModifier.Operation.ADDITION));
        }
        // 处理末影工具 (1.5倍距离)
        else if (stack.getItem() == ModItems.DARK_IRON_SWORD.get() || stack.getItem() == ModItems.SOUL_SHATTERING_WARHAMMER.get() || stack.getItem() == ModItems.ORANGE_CRYSTAL_HOE.get() || stack.getItem() == ModItems.ORANGE_CRYSTAL_PICKAXE.get() || stack.getItem() == ModItems.ORANGE_CRYSTAL_SHOVEL.get() || stack.getItem() == ModItems.ORANGE_CRYSTAL_AXE.get()) {
            // 增加1.5达到4.5 (1.5倍)
            event.addModifier(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(DARK_IRON_SWORD_AND_SOUL_SHATTERING_WARHAMMER_AND_ORANGE_CRYSTAL_HOE_AND_ORANGE_CRYSTAL_PICKAXE_AND_ORANGE_CRYSTAL_SHOVEL_AND_ORANGE_CRYSTAL_AXE_ATTACK_RANGE_UUID, "dark_iron_sword_and_soul_shattering_warhammer_and_orange_crystal_hoe_and_orange_crystal_pickaxe_and_orange_crystal_shovel_and_orange_crystal_axe_attack_range_boost", 1.0, AttributeModifier.Operation.ADDITION));
            event.addModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(DARK_IRON_SWORD_AND_SOUL_SHATTERING_WARHAMMER_AND_ORANGE_CRYSTAL_HOE_AND_ORANGE_CRYSTAL_PICKAXE_AND_ORANGE_CRYSTAL_SHOVEL_AND_ORANGE_CRYSTAL_AXE_REACH_DISTANCE_UUID, "dark_iron_sword_and_soul_shattering_warhammer_and_orange_crystal_hoe_and_orange_crystal_pickaxe_and_orange_crystal_shovel_and_orange_crystal_axe_reach_distance_boost", 1.0, AttributeModifier.Operation.ADDITION));
        }

        // 处理末影工具 (1.5倍距离)
        else if (stack.getItem() == ModItems.STARDEMON_BATTLEAXE.get()) {
            // 增加1.5达到4.5 (1.5倍)
            event.addModifier(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(STARDEMON_BATTLEAXE_ATTACK_RANGE_UUID, "stardemon_battleaxe_attack_range_boost", 1.5, AttributeModifier.Operation.ADDITION));
            event.addModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(STARDEMON_BATTLEAXE_REACH_DISTANCE_UUID, "stardemon_battleaxe_reach_distance_boost", 1.5, AttributeModifier.Operation.ADDITION));
        }
    }
}
//https://www.uuidgenerator.net/version4#google_vignette