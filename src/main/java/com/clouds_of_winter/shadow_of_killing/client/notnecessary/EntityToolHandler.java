package com.clouds_of_winter.shadow_of_killing.client.notnecessary;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ShadowofKilling.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityToolHandler {
/**

    // 存储每个实体的最后手持状态
    private static final Map<LivingEntity, Boolean> lastHeldState = new HashMap<>();

    // 所有实体共享的UUID
    private static final UUID ATTACK_RANGE_UUID = UUID.fromString("a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d");

    private static int cleanupTimer = 0;

    // 使用服务器tick事件处理所有实体
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // 处理所有在线实体
            for (ServerLevel level : event.getServer().getAllLevels()) {
                for (Entity entity : level.getEntities().getAll()) {
                    if (entity instanceof LivingEntity) {
                        handleEntityUpdate((LivingEntity) entity);
                    }
                }
            }

            // 清理不再存在的实体（每10秒）
            cleanupTimer++;
            if (cleanupTimer >= 200) {
                cleanupTimer = 0;
                cleanupInvalidEntities();
            }
        }
    }

    // 安全清理无效实体
    private static void cleanupInvalidEntities() {
        Iterator<Map.Entry<LivingEntity, Boolean>> iterator = lastHeldState.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LivingEntity, Boolean> entry = iterator.next();
            LivingEntity entity = entry.getKey();

            // 检查实体是否有效
            if (entity == null || !entity.isAlive() || entity.isRemoved()) {
                iterator.remove();
            }
        }
    }

    // 当实体加入世界时初始化
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            // 初始化状态
            lastHeldState.putIfAbsent(livingEntity, false);
            // 立即处理一次更新
            handleEntityUpdate(livingEntity);
        }
    }

    // 通用的实体更新处理方法
    private static void handleEntityUpdate(LivingEntity entity) {
        // 跳过无效实体
        if (entity == null || !entity.isAlive()) return;

        ItemStack mainHandItem = entity.getMainHandItem();

        // 检查主手是否持有自定义工具
        boolean currentlyHolding = isCustomTool(mainHandItem);

        // 获取实体上次的状态（如果不存在则默认为false）
        boolean previouslyHolding = lastHeldState.getOrDefault(entity, false);

        // 如果状态没有变化，则跳过处理
        if (currentlyHolding == previouslyHolding) {
            return;
        }

        // 更新状态
        lastHeldState.put(entity, currentlyHolding);

        // 获取攻击距离属性
        AttributeInstance attackRange = entity.getAttribute(ForgeMod.ATTACK_RANGE.get());
        if (attackRange == null) return;

        // 移除旧的修饰符（如果存在）
        AttributeModifier currentModifier = attackRange.getModifier(ATTACK_RANGE_UUID);
        if (currentModifier != null) {
            attackRange.removeModifier(ATTACK_RANGE_UUID);
        }

        if (currentlyHolding) {
            // 添加攻击距离增强修饰符
            AttributeModifier modifier = new AttributeModifier(
                    ATTACK_RANGE_UUID,
                    "custom_tool_attack_range",
                    4.0,  // 翻倍距离 (100% 增加)
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            attackRange.addTransientModifier(modifier);
        }
    }

    // 检查是否为自定义工具（根据实际实现修改）
    private static boolean isCustomTool(ItemStack stack) {
        return stack.getItem() instanceof BlackSword; // 替换为你的工具类
    }
 */
}
