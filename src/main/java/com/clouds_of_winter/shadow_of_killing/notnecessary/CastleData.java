/*
package com.winter_general_is_the_best.shadow_of_killing;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

public class CastleData extends SavedData {
    private boolean hasSpawnedVindicator = false;

    public CastleData() {}

    public CastleData(CompoundTag nbt) {
        load(nbt);
    }

    public void load(CompoundTag nbt) {
        hasSpawnedVindicator = nbt.getBoolean("hasSpawnedVindicator");
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putBoolean("hasSpawnedVindicator", hasSpawnedVindicator);
        return nbt;
    }

    public boolean hasSpawnedVindicator() {
        return hasSpawnedVindicator;
    }

    public void setHasSpawnedVindicator(boolean value) {
        hasSpawnedVindicator = value;
        setDirty();
    }

    public static CastleData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                CastleData::new,
                CastleData::new,
                "lord_castle"
        );
    }
}
*/