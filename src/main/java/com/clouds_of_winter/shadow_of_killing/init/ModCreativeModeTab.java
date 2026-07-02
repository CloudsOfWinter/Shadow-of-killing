package com.clouds_of_winter.shadow_of_killing.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab SHADOW_OF_KILLING_ITEM_GROUP = new CreativeModeTab("shadow_of_killing") {
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SHADOW_OF_KILLING_ITEM_GROUP.get());
        }
    };
}
