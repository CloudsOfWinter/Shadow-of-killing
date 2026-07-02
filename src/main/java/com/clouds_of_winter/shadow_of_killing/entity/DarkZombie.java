package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.handlers.Faction;
import com.clouds_of_winter.shadow_of_killing.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class DarkZombie extends Zombie {
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    public void tick() {
        super.tick(); // 调用父类的 tick 方法

        if (this.level!= null) {
            // 1. 先计算生物尺寸
            double entitySize = this.getBbWidth() + this.getBbHeight();

            // 2. 再乘以基础检测范围
            double detectionRange = 19.6078 * entitySize;

            for (Entity entity : this.level.getEntities(this, this.getBoundingBox().inflate(detectionRange))) {
                ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());
                if (entityTypeKey!= null && entityTypeKey.toString().equals("rottencreatures:immortal") || entityTypeKey.toString().equals("rottencreatures:zap")) {
                    // 对 rottencreatures:immortal 造成 100 点伤害
                    if (this.hasLineOfSight(entity)) {
                        entity.kill();
                    }
                }
            }
        }

        if (this.level!= null) {
            // 1. 先计算生物尺寸
            double entitySize = this.getBbWidth() + this.getBbHeight();

            // 2. 再乘以基础检测范围
            double detectionRange = 0.392156862745098 * entitySize;

            for (Entity entity : this.level.getEntities(this, this.getBoundingBox().inflate(detectionRange))) {
                ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());
                if (entityTypeKey!= null && entityTypeKey.toString().equals("rottencreatures:immortal") || entityTypeKey.toString().equals("rottencreatures:zap")) {
                    // 对 rottencreatures:immortal 造成 100 点伤害
                    /**
                     this.doHurtTarget(((LivingEntity) entity));
                     */
                    entity.kill();
                }
            }
        }

        /**
         if (this.level != null) {

         // 1. 先计算生物尺寸
         double entitySize = this.getBbWidth() + this.getBbHeight();

         // 2. 再乘以基础检测范围
         double range = 5 * entitySize;
         List<Entity> entities = this.level.getEntities(this, this.getBoundingBox().inflate(range));

         for (Entity entity : entities) {
         ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());

         // 检查是否为 rottencreatures:zap 实体
         if (entityTypeKey != null && entityTypeKey.toString().equals("rottencreatures:immortal") || entityTypeKey.toString().equals("rottencreatures:zap")) {
         // 检查该实体是否被闪电击中且在3秒标记时间内
         if (isMarkedByLightning(entity, 60)) { // 60 ticks = 3秒 (20 ticks/秒)
         // 让 zap 实体死亡
         entity.kill();
         // 清除标记
         clearLightningMark(entity);
         }
         }
         }
         }
         */

        //this.convertToDarkZombie();




    }


    public boolean convertToDarkZombie() {
        if (this.level!= null) { // 确保只在服务端执行
            // 获取当前生物周围 10 格范围内的实体

            // 1. 先计算生物尺寸
            double entitySize = this.getBbWidth() + this.getBbHeight();

            // 2. 再乘以基础检测范围
            double detectionRange = 2 * entitySize;

            for (Entity entity : this.level.getEntities(this, this.getBoundingBox().inflate(detectionRange))) {
                ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());

                // 检查实体是否为 rottencreatures:immortal 或 rottencreatures:zap
                if (entityTypeKey != null &&
                        (entityTypeKey.toString().equals("rottencreatures:immortal") ||
                                entityTypeKey.toString().equals("rottencreatures:zap"))) {

                    // 播放村民感染成僵尸村民的声音
                    this.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.0F, 1.0F);

                    // 创建一个新的自定义生物实例
                    DarkZombie dark_zombie = new DarkZombie(ModEntities.DARK_ZOMBIE.get(), this.level);

                    // 设置新生物的位置和旋转角度
                    dark_zombie.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());

                    // 如果需要，可以复制原生物的部分属性（如生命值、装备等）
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        dark_zombie.setHealth(livingEntity.getHealth()); // 复制生命值
                        // 复制其他属性（如装备、状态效果等）




                        // 复制装备（盔甲、手持物品等）
                        for (EquipmentSlot slot : EquipmentSlot.values()) {
                            ItemStack stack = livingEntity.getItemBySlot(slot);
                            if (!stack.isEmpty()) {
                                dark_zombie.setItemSlot(slot, stack.copy());
                            }
                        }



                        // 复制状态效果（药水效果）
                        for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                            dark_zombie.addEffect(new MobEffectInstance(
                                    effect.getEffect(),
                                    effect.getDuration(),
                                    effect.getAmplifier(),
                                    effect.isAmbient(),
                                    effect.isVisible(),
                                    effect.showIcon()));
                        }


                        // 复制名称和自定义名称是否可见
                        if (livingEntity.hasCustomName()) {
                            dark_zombie.setCustomName(livingEntity.getCustomName());
                            dark_zombie.setCustomNameVisible(livingEntity.isCustomNameVisible());
                        }


                        /**
                         // 复制最大生命值（如果你的实体允许修改最大生命值）
                         if (customEntity.getAttribute(Attributes.MAX_HEALTH) != null) {
                         customEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                         livingEntity.getAttribute(Attributes.MAX_HEALTH).getBaseValue());
                         }
                         */

                        /**
                         // 复制移动速度
                         if (livingEntity.getAttribute(Attributes.MOVEMENT_SPEED) != null &&
                         customEntity.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
                         customEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(
                         livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue());
                         }
                         */

                        /**
                         // 复制攻击伤害
                         if (livingEntity.getAttribute(Attributes.ATTACK_DAMAGE) != null &&
                         customEntity.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                         customEntity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(
                         livingEntity.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue());
                         }
                         */
                    }

                    // 移除原来的实体
                    entity.remove(Entity.RemovalReason.DISCARDED);

                    // 将新生物添加到世界中
                    this.level.addFreshEntity(dark_zombie);
                }
            }
        }


        return true;
    }

    /**
    // 检查实体是否在指定时间内被闪电标记
    private boolean isMarkedByLightning(Entity entity, int maxTicksSinceMark) {
        CompoundTag persistentData = entity.getPersistentData();

        // 检查是否有标记时间
        if (persistentData.contains("StruckByLightningTime")) {
            long markedTime = persistentData.getLong("StruckByLightningTime");
            long currentTime = entity.level.getGameTime();

            // 检查是否在标记时间内 (当前时间 - 标记时间 <= 最大允许的ticks)
            return currentTime - markedTime <= maxTicksSinceMark;
        }

        return false;
    }

    // 清除闪电标记
    private void clearLightningMark(Entity entity) {
        CompoundTag persistentData = entity.getPersistentData();
        if (persistentData.contains("StruckByLightningTime")) {
            persistentData.remove("StruckByLightningTime");
        }
    }
    */


    public void aiStep() {
        super.aiStep();
        if (this.isAlive()) {
            boolean flag = this.Sensitiveduringtheday() && this.Burningduringtheday();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }



        /**
        if (!this.isDeadOrDying() && !this.isBaby() && this.getTarget()!= null) {
            double distanceX = this.getTarget().getX() - this.getX();
            double distanceY = this.getTarget().getY() - this.getY();
            double distanceZ = this.getTarget().getZ() - this.getZ();
            double horizontalDistanceSqr = distanceX * distanceX + distanceZ * distanceZ;
            double verticalDistance = Math.abs(distanceY);
            if (horizontalDistanceSqr <= 2.15 && (verticalDistance >= 1.53 && verticalDistance <= 2.16)) {
                // 执行攻击逻辑
                if (attackTick <= 0) {
                    // 执行近战攻击逻辑，调用造成伤害的方法并传入伤害源和伤害量
                    this.doHurtTarget(this.getTarget());
                    // 设置新的攻击间隔，1秒钟对应的20游戏刻
                    attackTick = 20;
                }
            }
        }
        if (attackTick > 0) {
            attackTick--;
        }
        */

        /*
        Level world = this.level;
        if (world!= null) {
            Iterable<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(2, 2.05, 2));
            double facingAngleRadians = Math.toRadians(this.getYRot());
            for (LivingEntity entity : entities) {
                if (isEnemy(entity)) {
                    double dx = entity.getX() - this.getX();
                    double dz = entity.getZ() - this.getZ();
                    double targetAngleRadians = Math.atan2(dz, dx);
                    double angleDiffRadians = Math.abs(targetAngleRadians - facingAngleRadians);
                    if (angleDiffRadians <= Math.toRadians(45)) {
                        // 攻击敌人
                        ItemStack mainHandItem = this.getMainHandItem();
                        if (mainHandItem.getItem() instanceof SwordItem || mainHandItem.getItem() instanceof AxeItem || mainHandItem.getItem() instanceof PickaxeItem || mainHandItem.getItem() instanceof ShovelItem || mainHandItem.getItem() instanceof HoeItem) {
                            this.doHurtTarget(entity);
                            this.setLastHurtMob(entity);
                            this.setLastHurtByMob(entity);
                        }


                    }
                }
            }
        }
        */
    }

    protected boolean Sensitiveduringtheday() {
        return true;
    }

    protected boolean Burningduringtheday() {
        if (this.level.isDay() && !this.level.isClientSide) {
            float f = this.getLightLevelDependentMagicValue();
            BlockPos blockpos = new BlockPos(this.getX(), this.getEyeY(), this.getZ());
            boolean flag = this.isInWaterRainOrBubble() || this.isInPowderSnow || this.wasInPowderSnow;
            if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && this.level.canSeeSky(blockpos)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isSunBurnTick() {
        return false;
    }

    protected boolean isSunSensitive() {
        return false;
    }

    /*
    private boolean isEnemy(LivingEntity entity) {
        // 根据实际情况判断一个实体是否为敌人
        return entity!= this && entity.getTeam()!= this.getTeam();
    }
    */

    /*
    private int attackTick = 0;
    */

    public DarkZombie(EntityType<? extends DarkZombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
                .add(Attributes.ARMOR, 10.0D);
    }
    protected void registerGoals() {
        super.registerGoals();
    }

    protected void addBehaviourGoals() {
        super.addBehaviourGoals();
        // 移除原有的ZombieAttackGoal
        this.goalSelector.getAvailableGoals().removeIf(goal ->
                goal.getGoal() instanceof ZombieAttackGoal
        );
        // 添加自定义的ZombieAttackGoal
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false){
            protected double getAttackReachSqr(LivingEntity target) {
                double baseReach = super.getAttackReachSqr(target);

                ItemStack mainHandItem = this.mob.getMainHandItem();
                if (mainHandItem.getItem() instanceof TieredItem) {
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.5 * mobHeight;
                    double maxY = 1.4 * mobHeight;
                    double yDiff = target.getY() - this.mob.getY();

                    if (yDiff >= minY && yDiff <= maxY) {
                        return baseReach;
                    }
                }else {
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.5 * mobHeight;
                    double maxY = 1.107692307692308 * mobHeight;
                    double yDiff = target.getY() - this.mob.getY();

                    if (yDiff >= minY && yDiff <= maxY) {
                        return baseReach;
                    }
                }

                return baseReach;
            }

            protected void checkAndPerformAttack(LivingEntity target, double distanceSqr) {

                ItemStack mainHandItem = this.mob.getMainHandItem();
                if (mainHandItem.getItem() instanceof TieredItem) {
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.5 * mobHeight;
                    double maxY = 1.4 * mobHeight;
                    double yDiff = target.getY() - this.mob.getY();
                    if (yDiff >= minY && yDiff <= maxY) {
                        double attackReachSqr = this.getAttackReachSqr(target);
                        double dx = target.getX() - this.mob.getX();
                        double dz = target.getZ() - this.mob.getZ();
                        double horizontalDistanceSqr = dx * dx + dz * dz;
                        if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                            this.resetAttackCooldown();
                            this.mob.swing(InteractionHand.MAIN_HAND);
                            this.mob.doHurtTarget(target);
                        }
                    }
                } else {
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.5 * mobHeight;
                    double maxY = 1.107692307692308 * mobHeight;
                    double yDiff = target.getY() - this.mob.getY();
                    if (yDiff >= minY && yDiff <= maxY) {
                        double attackReachSqr = this.getAttackReachSqr(target);
                        double dx = target.getX() - this.mob.getX();
                        double dz = target.getZ() - this.mob.getZ();
                        double horizontalDistanceSqr = dx * dx + dz * dz;
                        if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                            this.resetAttackCooldown();
                            this.mob.swing(InteractionHand.MAIN_HAND);
                            this.mob.doHurtTarget(target);
                        }
                    }
                }





                super.checkAndPerformAttack(target, distanceSqr);
            }
        });
    }

    public boolean doHurtTarget(Entity p_32281_) {
        boolean flag = super.doHurtTarget(p_32281_);
        if (p_32281_ instanceof LivingEntity) {
            ((LivingEntity)p_32281_).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40), this);
        }
        /**
        if (flag && entity instanceof LivingEntity) {
            ItemStack mainHandItem = this.getMainHandItem();
            if (mainHandItem.getItem() instanceof TieredItem){
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                if (attackDamage > 0) {
                    Direction facingDirection = this.getDirection();
                    for (Entity nearbyEntity : this.level.getEntities(this, this.getBoundingBox().inflate(2, 1, 2))) {
                        if (nearbyEntity!= entity && nearbyEntity instanceof LivingEntity &&!(nearbyEntity instanceof DarkZombie)) {
                            LivingEntity nearbyLivingEntity = (LivingEntity) nearbyEntity;
                            if (!isEntityBehind(nearbyLivingEntity, facingDirection) && !(nearbyLivingEntity.equals(this) || nearbyLivingEntity instanceof ZombieVillager)) {

                                this.doHurtTarget(nearbyLivingEntity);
                            }
                        }
                    }
                }
            }
        }
        */

        if (flag && p_32281_ instanceof LivingEntity) {
            ItemStack mainHand = this.getMainHandItem();
            // 条件：物品为 TieredItem 且不是 MossTool
            if (mainHand.getItem() instanceof TieredItem) {

                LivingEntity livingTarget = (LivingEntity) p_32281_;
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                if (attackDamage > 0) {
                    Direction facingDirection = this.getDirection();

                    Class<?> targetClass = livingTarget.getClass();
                    AABB range = this.getBoundingBox().inflate(this.getBbWidth() * 2.571428571428571, this.getBbHeight() * 0.5, this.getBbWidth() * 2.571428571428571);

                    for (Entity nearby : this.level.getEntities(this, range)) {
                        // 排除主目标、自身、非同类、非生物
                        if (nearby != p_32281_ && nearby != this &&
                                nearby.getClass() == targetClass && nearby instanceof LivingEntity) {
                            LivingEntity nearbyLiving = (LivingEntity) nearby;
                            if (this.hasLineOfSight(nearbyLiving)) {
                                if (!isBehind(facingDirection, nearbyLiving) && !isSameTeam(nearbyLiving)) {
                                    // 造成相同伤害
                                    this.doHurtTarget(nearbyLiving);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (flag && p_32281_ instanceof LivingEntity) {
            ItemStack mainHandItem = this.getMainHandItem();

            if (mainHandItem.getItem() instanceof TieredItem) {
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);

                if (attackDamage > 0) {
                    /*
                    Vec3 lookVec = this.getLookAngle();
                    */
                    Direction facingDirection = this.getDirection();

                    AABB area = this.getBoundingBox().inflate(this.getBbWidth() * 2.571428571428571, this.getBbHeight() * 0.5, this.getBbWidth() * 2.571428571428571);

                    for (LivingEntity entity : this.level.getEntitiesOfClass(
                            LivingEntity.class,
                            area,
                            e -> isValidSweepTarget(p_32281_, e)
                    )) {
                        /*
                        Vec3 toEntity = new Vec3(
                                entity.getX() - this.getX(),
                                entity.getY() - this.getEyeY(),
                                entity.getZ() - this.getZ()
                        ).normalize();
                        */

                        if (this.hasLineOfSight(entity)) {
                            if (!isBehind(facingDirection, entity)/*!isEntityBehind(entity, facingDirection) && lookVec.dot(toEntity) > -0.5*/) {
                                this.doHurtTarget(entity);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    private boolean isBehind(Direction facingDirection, Entity target) {
        Vec3 mobPos = this.position();
        Vec3 targetPos = target.position();
        Vec3 relativeDir = targetPos.subtract(mobPos).normalize();
        Vec3 forwardVec = Vec3.atLowerCornerOf(facingDirection.getNormal()); // 水平前方向量

        // 点积 < 0 表示目标在背后半球（正后方、后左、后右、后上、后下均满足）
        return relativeDir.dot(forwardVec) < 0;
    }

    private boolean isBehindOrAboveBelow(Direction facingDirection, Entity target) {
        Vec3 mobPos = this.position();
        Vec3 targetPos = target.position();

        // 计算目标相对于生物的方向向量
        Vec3 relativeDir = targetPos.subtract(mobPos).normalize();

        // 获取生物的正前方向量
        Vec3 forwardVec = Vec3.atLowerCornerOf(facingDirection.getNormal());

        // 计算点积（判断是否在后方）
        double dotProduct = relativeDir.dot(forwardVec);
        if (dotProduct < -0.7) { // 阈值可调整（-1 表示正后方）
            return true; // 在后方
        }

        // 检查是否在后上方或后下方（Y轴差值）
        double yDiff = targetPos.y - mobPos.y;
        if (dotProduct < -0.3 && Math.abs(yDiff) > 0.5) { // 调整阈值
            return true; // 后上方或后下方
        }

        return false;
    }

    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);

        /**
        if (this.getPersistentData().contains("StruckByLightningTime")) {
            p_21484_.putLong("LightningMarkTime", this.getPersistentData().getLong("StruckByLightningTime"));
        }
        */

    }


    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);

        /**
        if (p_21450_.contains("LightningMarkTime")) {
            this.getPersistentData().putLong("StruckByLightningTime", p_21450_.getLong("LightningMarkTime"));
        }
        */

    }

    /*
    private boolean isEntityBehind(Entity entity, Direction facingDirection) {
        // 获取实体相对于CustomMob的方向向量
        Direction entityDirection = Direction.getNearest(
                entity.getX() - this.getX(),
                entity.getY() - this.getY(),
                entity.getZ() - this.getZ()
        );

        // 判断方向向量是否与朝向相反，若是则在后方
        return entityDirection.getOpposite() == facingDirection;
    }
    */

    // 目标验证逻辑（核心过滤）
    private boolean isValidSweepTarget(Entity mainTarget, LivingEntity entity) {
        // 基础排除条件
        if (entity == this || entity == mainTarget) return false;

        if (isSameTeam(entity)) {
            return false; // 直接排除同队成员
        }

        // 凋灵骷髅特殊处理
        if (entity.getType() == EntityType.ZOMBIE_VILLAGER) {
            return isZombieVillagerHostile((ZombieVillager) entity);
        }

        // 自定义生物阵营判断
        if (entity instanceof DarkZombie) {
            return shouldAttackDarkZombie((DarkZombie) entity);
        }

        if (entity instanceof Mob) {
            return isMobHostile((Mob) entity);
        }

        // 默认敌对判断
        return isEnemy(entity);
    }

    // 凋灵骷髅敌对状态深度检测
    private boolean isZombieVillagerHostile(ZombieVillager zombie_villager) {
        // 方法1：检查当前目标是否为自身
        if (zombie_villager.getTarget() == this) return true;

        // 方法2：检查仇恨列表
        return zombie_villager.getLastHurtByMob() == this ||
                zombie_villager.getLastHurtMob() == this;
    }

    private boolean shouldAttackDarkZombie(DarkZombie entity) {


        // 双重判断：阵营关系 + 攻击历史
        return isFactionEnemy(entity) || hasCombatHistory(entity);
    }

    private boolean isMobHostile(Mob mob) {
        if (mob.getTarget() == this) return true;

        return mob.getLastHurtByMob() == this ||
                mob.getLastHurtMob() == this;
    }

    // 新增战斗历史验证
    private boolean hasCombatHistory(DarkZombie entity) {
        if (entity.getTarget() == this) return true;

        // 最近攻击关系双向验证
        return entity.getLastHurtByMob() == this ||  // 该实体最近被本实体攻击
                entity.getLastHurtMob() == this;    // 本实体最近被该实体攻击
    }

    private boolean isFactionEnemy(DarkZombie mob) {
        Faction our = this.getFaction();
        Faction their = mob.getFaction();

        if (isSameTeam(mob)) {
            return false; // 确保队伍优先级最高
        }

        // 空值保护层级
        if (our == null || their == null) {
            // 可添加日志记录或默认处理
            return false;
        }

        // 阵营敌对状态 + 永久敌对标记
        return our.isEnemy(their);
    }

    /*
    // 自定义生物阵营判断
    private boolean isFactionEnemy(DarkZombie mob) {
        Faction theirFaction = mob.getFaction();
        // 空值保护
        if (theirFaction == null) return false;
        return this.getFaction().isEnemy(theirFaction);
    }

     */

    // 增强版通用敌人判断
    public boolean isEnemy(Entity entity) {
        // 原版敌对判断
        /*
        if (entity instanceof Enemy) return true;
        */

        // 队伍系统判断
        if (entity instanceof LivingEntity living) {
            return !isSameTeam(living);
        }
        return false;
    }

    private boolean isSameTeam(LivingEntity entity) {
        return this.isAlliedTo(entity) || entity.isAlliedTo(this);
    }

    public Faction faction;

    public Faction getFaction() {
        return faction;
    }
}
