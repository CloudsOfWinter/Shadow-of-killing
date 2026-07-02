/**

 package com.winter_general_is_the_best.shadow_of_killing.event;

import com.winter_general_is_the_best.shadow_of_killing.all.ModEntities;
import com.winter_general_is_the_best.shadow_of_killing.entity.EliteWitherZombieShooter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Shadowofkilling.MOD_ID)
public class StructureEvents {
    private static final ResourceLocation LORD_CASTLE3 = new ResourceLocation(Shadowofkilling.MOD_ID, "lord_castle3");

    private static final Set<Long> processedChunks = new HashSet<>();

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        ChunkAccess chunk = event.getChunk();
        if (chunk == null) return;

        // 直接获取区块坐标而不依赖chunk.getPos()
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        long chunkKey = ChunkPos.asLong(chunkX, chunkZ);

        if (processedChunks.contains(chunkKey)) {
            return;
        }

        StructureManager structureManager = level.structureManager();

        // 获取目标结构
        Optional<Structure> structureOpt = level.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY)
                .getOptional(LORD_CASTLE3);

        if (structureOpt.isEmpty()) {
            return;
        }
        Structure structure = structureOpt.get();

        // 创建SectionPos来获取结构起点
        SectionPos sectionPos = SectionPos.of(chunkX, 0, chunkZ);

        boolean structureFound = false;

        // 检查此区块是否有目标结构
        for (StructureStart start : structureManager.startsForStructure(sectionPos, structure)) {
            if (start != null && start.isValid()) {
                structureFound = true;
                CastleData data = CastleData.get(level);

                if (!data.hasSpawnedVindicator()) {
                    BoundingBox box = start.getBoundingBox();

                    // 使用安全位置查找
                    BlockPos spawnPos = findSafeSpawnPosition(level, box);

                    // 生成卫道士
                    EliteWitherZombieShooter vindicator = ModEntities.ELITE_WITHER_ZOMBIE_SHOOTER.get().create(level);
                    if (vindicator != null) {
                        vindicator.setPos(spawnPos.getX() + 1.5, spawnPos.getY() + 1.5, spawnPos.getZ() + 1.5);
                        level.addFreshEntity(vindicator);

                        // 设置生成标记
                        data.setHasSpawnedVindicator(true);
                    }
                } else {
                }

                // 标记此区块已处理
                processedChunks.add(chunkKey);
                break; // 处理完一个结构后跳出循环
            }
        }

        if (!structureFound) {
        }
    }

    private static BlockPos findSafeSpawnPosition(ServerLevel level, BoundingBox box) {
        int centerX = (box.minX() + box.maxX()) / 2;
        int centerZ = (box.minZ() + box.maxZ()) / 2;

        // 从底部向上搜索空气位置
        for (int y = box.minY(); y <= box.maxY(); y++) {
            BlockPos testPos = new BlockPos(centerX, y, centerZ);
            if (level.isEmptyBlock(testPos)) {
                return testPos;
            }
        }

        // 如果没找到空气位置，使用默认位置
        return new BlockPos(centerX, box.minY() + 1, centerZ);
    }


}

*/