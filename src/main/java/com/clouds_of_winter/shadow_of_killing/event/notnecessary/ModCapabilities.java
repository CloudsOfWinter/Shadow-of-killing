/*
package com.winter_general_is_the_best.shadow_of_killing.event;

import com.winter_general_is_the_best.shadow_of_killing.entity.BlackswordRaider;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModCapabilities {
    public static final Capability<TargetData> TARGET_DATA = CapabilityManager.get(new CapabilityToken<>(){});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(TargetData.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof BlackswordRaider) {
            event.addCapability(new ResourceLocation(Shadowofkilling.MOD_ID, "target_data"), new TargetDataProvider());
        }
    }

    private static class TargetDataProvider implements ICapabilitySerializable<CompoundTag> {
        private final TargetData data = new TargetData();
        private final LazyOptional<TargetData> optional = LazyOptional.of(() -> data);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return ModCapabilities.TARGET_DATA.orEmpty(cap, optional);
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            if (data.getFirstTargetId() != null) {
                tag.putUUID("firstTarget", data.getFirstTargetId());
            }
            tag.putLong("firstTargetTime", data.getFirstTargetTime());
            tag.putInt("noDamageTicks", data.getNoDamageTicks());
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            if (nbt.hasUUID("firstTarget")) {
                data.setFirstTargetId(nbt.getUUID("firstTarget"));
            }
            data.setFirstTargetTime(nbt.getLong("firstTargetTime"));
            data.setNoDamageTicks(nbt.getInt("noDamageTicks"));
        }
    }
}
*/