package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.entity.projectile.GhostAttack;
import com.clouds_of_winter.shadow_of_killing.handlers.Faction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class WitherEnderman extends EnderMan  {

    public WitherEnderman(EntityType<? extends WitherEnderman> p_32485_, Level p_32486_) {
        super(p_32485_, p_32486_);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
    }

    /**
    private int attackTick = 0;
    */

    public static AttributeSupplier.Builder createAttributes() {
        return EnderMan.createAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D);
    }

    public boolean canBeAffected(MobEffectInstance p_34192_) {
        return p_34192_.getEffect() != MobEffects.WITHER && super.canBeAffected(p_34192_);
    }

    protected void registerGoals() {
        super.registerGoals();
        // 移除原有的ZombieAttackGoal
        this.goalSelector.getAvailableGoals().removeIf(goal ->
                goal.getGoal() instanceof MeleeAttackGoal
        );
        // 添加自定义的ZombieAttackGoal
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false) {
            protected double getAttackReachSqr(LivingEntity target) {
                double baseReach = super.getAttackReachSqr(target);

                ItemStack mainHandItem = this.mob.getMainHandItem();
                if (mainHandItem.getItem() instanceof TieredItem) {
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.3333333333333333 * mobHeight;
                    double maxY = 1.4 * mobHeight;
                    double yDiff = target.getY() - this.mob.getY();

                    if (yDiff >= minY && yDiff <= maxY) {
                        return baseReach;
                    }
                }else{
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.3333333333333333 * mobHeight;
                    double maxY = 1.120689655172414 * mobHeight;
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
                    double minY = 0.3333333333333333 * mobHeight;
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
                } else{
                    double mobHeight = this.mob.getBbHeight();
                    double minY = 0.3333333333333333 * mobHeight;
                    double maxY = 1.120689655172414 * mobHeight;
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

        // 第一步：移除原有的 HurtByTargetGoal
        this.targetSelector.getAvailableGoals().removeIf(goal -> {
            return goal.getGoal() instanceof HurtByTargetGoal;
        });
        // 第二步：添加新的 HurtByTargetGoal 并设置警报
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
    }

    public boolean doHurtTarget(Entity p_32281_) {
        boolean flag = super.doHurtTarget(p_32281_);
        if (p_32281_ instanceof LivingEntity) {
            ((LivingEntity)p_32281_).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
        }
        /*
        if (flag && entity instanceof LivingEntity) {
            ItemStack mainHandItem = this.getMainHandItem();
            if (mainHandItem.getItem() instanceof TieredItem){
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                if (attackDamage > 0) {
                    Direction facingDirection = this.getDirection();
                    for (Entity nearbyEntity : this.level.getEntities(this, this.getBoundingBox().inflate(2, 1, 2))) {
                        if (nearbyEntity!= entity && nearbyEntity instanceof LivingEntity &&!(nearbyEntity instanceof WitherEnderman)) {
                            LivingEntity nearbyLivingEntity = (LivingEntity) nearbyEntity;
                            if (!isEntityBehind(nearbyLivingEntity, facingDirection) ) {
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

        // 自定义生物阵营判断
        if (entity instanceof WitherEnderman) {
            return shouldAttackWitherEnderman((WitherEnderman) entity);
        }

        if (entity instanceof Mob) {
            return isMobHostile((Mob) entity);
        }

        // 默认敌对判断
        return isEnemy(entity);
    }

    private boolean shouldAttackWitherEnderman(WitherEnderman entity) {


        // 双重判断：阵营关系 + 攻击历史
        return isFactionEnemy(entity) || hasCombatHistory(entity);
    }

    private boolean isMobHostile(Mob mob) {
        if (mob.getTarget() == this) return true;

        return mob.getLastHurtByMob() == this ||
                mob.getLastHurtMob() == this;
    }

    // 新增战斗历史验证
    private boolean hasCombatHistory(WitherEnderman entity) {
        if (entity.getTarget() == this) return true;

        // 最近攻击关系双向验证
        return entity.getLastHurtByMob() == this ||  // 该实体最近被本实体攻击
                entity.getLastHurtMob() == this;    // 本实体最近被该实体攻击
    }

    private boolean isFactionEnemy(WitherEnderman mob) {
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
    private boolean isFactionEnemy(WitherEnderman mob) {
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

    public void aiStep() {
        super.aiStep();
        /**
        if (!this.isDeadOrDying() && !this.isBaby() && this.getTarget()!= null) {
            double distanceX = this.getTarget().getX() - this.getX();
            double distanceY = this.getTarget().getY() - this.getY();
            double distanceZ = this.getTarget().getZ() - this.getZ();
            double horizontalDistanceSqr = distanceX * distanceX + distanceZ * distanceZ;
            double verticalDistance = Math.abs(distanceY);
            if (horizontalDistanceSqr <= 2.15 && (verticalDistance >= 2.2 && verticalDistance <= 3.25)) {
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
            Iterable<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(2, 2, 2));
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

    /*
    private boolean isEnemy(LivingEntity entity) {
        // 根据实际情况判断一个实体是否为敌人
        return entity!= this && entity.getTeam()!= this.getTeam();
    }
    */










    private int teleportCooldown = 0;
    private static final int TELEPORT_INTERVAL = 400; // 20秒 = 400 tick
    private static final String TELEPORT_COOLDOWN_TAG = "TeleportCooldown";

    private void attemptTeleportToTarget(LivingEntity target) {
        // 获取目标位置
        double targetX = target.getX();
        double targetY = target.getY();
        double targetZ = target.getZ();

        // 在目标周围寻找安全的瞬移位置
        Vec3 teleportPos = findSafeTeleportPosition(targetX, targetY, targetZ);

        if (teleportPos != null) {
            // 执行瞬移
            boolean teleportSuccess = this.randomTeleport(
                    teleportPos.x,
                    teleportPos.y,
                    teleportPos.z,
                    true
            );

            if (teleportSuccess) {
                // 瞬移成功后的效果
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
        }
    }

    private Vec3 findSafeTeleportPosition(double x, double y, double z) {
        // 在目标周围5格范围内寻找安全位置
        for (int i = 0; i < 10; i++) {
            double offsetX = x + (this.random.nextDouble() - 0.5) * 10;
            double offsetZ = z + (this.random.nextDouble() - 0.5) * 10;
            double offsetY = y + this.random.nextInt(3) - 1;

            // 检查位置是否安全（有站立空间且不会受到伤害）
            if (isSafePosition(offsetX, offsetY, offsetZ)) {
                return new Vec3(offsetX, offsetY, offsetZ);
            }
        }
        return null;
    }

    private boolean isSafePosition(double x, double y, double z) {
        BlockPos pos = new BlockPos(x, y, z);
        BlockPos belowPos = pos.below();

        // 检查脚下是否有固体方块且目标位置没有危险
        return this.level.getBlockState(belowPos).isSolidRender(this.level, belowPos) &&
                !this.level.getBlockState(pos).isSuffocating(this.level, pos) &&
                !this.level.getBlockState(pos.above()).isSuffocating(this.level, pos.above()) &&
                !this.level.containsAnyLiquid(new AABB(x - 0.3, y, z - 0.3, x + 0.3, y + 2, z + 0.3));
    }












    @Override
    protected boolean teleport() {

/*
        // 调用父类的瞬移方法（如果适用）

        // 或者在瞬移后获取新位置
        double newX = this.getX();
        double newY = this.getY();
        double newZ = this.getZ();


        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, newX, newY, newZ);
        areaeffectcloud.setRadius(3);
        areaeffectcloud.setRadiusOnUse(-0.5F);
        areaeffectcloud.setWaitTime(10);
        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 160));
        this.level.addFreshEntity(areaeffectcloud);
*/


        /**
        if (witherSkullCooldown == 0 && this.getTarget() != null) {
            tryShootWitherSkull();
        }
        */

/*
        // 如果有目标且冷却完成
        if (this.getTarget() != null && witherSkullCooldown <= 0) {
            shootWitherSkull(this.getTarget());
            // 设置冷却时间
            witherSkullCooldown = WITHER_SKULL_COOLDOWN;
        }
*/

        return super.teleport();
    }

    @Override
    public boolean randomTeleport(double x, double y, double z, boolean particleEffects) {
        // 先执行瞬移
        boolean teleportSuccess = super.randomTeleport(x, y, z, particleEffects);

        // 如果瞬移成功，在当前位置生成凋零效果
        if (teleportSuccess && this.getTarget() != null && witherSkullCooldown <= 0) {
            createWitherCloudAtCurrentPosition();
        }

        if (teleportSuccess && this.getTarget() != null && witherSkullCooldown <= 0) {
            shootWitherSkull(this.getTarget());
            // 设置冷却时间
            witherSkullCooldown = WITHER_SKULL_COOLDOWN;
        }

        return teleportSuccess;
    }

    private void createWitherCloudAtCurrentPosition() {
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
        areaeffectcloud.setRadius(3);
        areaeffectcloud.setRadiusOnUse(-0.5F);
        areaeffectcloud.setWaitTime(10);
        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 160));
        this.level.addFreshEntity(areaeffectcloud);
    }

    










    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof GhostAttack) {
            GhostAttack skull = (GhostAttack) source.getDirectEntity();
            if (skull.getOwner() == this) {
                return false; // 完全免疫
            }
        }


        return super.hurt(source, amount);
    }



    /**
    private int witherSkullCooldown = 0;

    @Override
    public void tick() {
        super.tick();

        // 处理冷却时间
        if (witherSkullCooldown > 0) {
            witherSkullCooldown--;
        }


    }



    private void tryShootWitherSkull() {
        LivingEntity target = this.getTarget();
        if (target == null || !target.isAlive()) return;

        // 检查距离和视线
        double distance = this.distanceToSqr(target);
        if (distance < 4.0D || distance > 256.0D) return;
        if (!this.hasLineOfSight(target)) return;

        shootWitherSkull(target);
        witherSkullCooldown = 200; // 10秒冷却 (20 ticks/秒 × 10秒)
    }

    private void shootWitherSkull(LivingEntity target) {
        // 计算方向
        Vec3 lookVec = this.getViewVector(1.0F);
        double dX = target.getX() - (this.getX() + lookVec.x * 2.0D);
        double dY = target.getY(0.5D) - (this.getY(0.5D));
        double dZ = target.getZ() - (this.getZ() + lookVec.z * 2.0D);

        // 创建自定义凋零之首
        CustomWitherSkull witherSkull = new CustomWitherSkull(
                this.level, this, dX, dY, dZ
        );

        // 设置位置和速度
        witherSkull.setPos(
                this.getX() + lookVec.x * 2.0D,
                this.getY(0.5D),
                this.getZ() + lookVec.z * 2.0D
        );

        this.level.addFreshEntity(witherSkull);

        // 播放音效
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.WITHER_SHOOT, SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("WitherSkullCooldown")) {
            this.witherSkullCooldown = compound.getInt("WitherSkullCooldown");
        }

    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("WitherSkullCooldown", this.witherSkullCooldown);


    }
    */

    // 冷却时间（10秒 = 200 ticks）
    private static final int WITHER_SKULL_COOLDOWN = 400;
    private int witherSkullCooldown = 0;

    @Override
    public void tick() {
        super.tick();

        // 更新冷却时间
        if (witherSkullCooldown > 0) {
            witherSkullCooldown--;
        }




        if (!this.level.isClientSide) {
            // 更新冷却时间
            if (teleportCooldown > 0) {
                teleportCooldown--;
            }

            // 每20秒检查并执行瞬移
            if (teleportCooldown <= 0) {
                LivingEntity target = this.getTarget();
                if (target != null && this.canAttack(target)) {
                    attemptTeleportToTarget(target);
                    teleportCooldown = TELEPORT_INTERVAL;
                }
            }
        }



    }

    private void shootWitherSkull(LivingEntity target) {
        // 计算方向
        Vec3 lookVec = this.getViewVector(1.0F);
        double dX = target.getX() - (this.getX() + lookVec.x * 2.0D);
        double dY = target.getY(0.5D) - (this.getY(0.5D));
        double dZ = target.getZ() - (this.getZ() + lookVec.z * 2.0D);

        // 创建自定义凋零之首
        GhostAttack witherSkull = new GhostAttack(
                this.level, this, dX, dY, dZ
        );

        // 设置位置和速度
        witherSkull.setPos(
                this.getX() + lookVec.x * 2.0D,
                this.getY(0.5D),
                this.getZ() + lookVec.z * 2.0D
        );

        this.level.addFreshEntity(witherSkull);

        // 播放音效
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.WITHER_SHOOT, SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    // 可选：添加数据保存方法以保持冷却时间
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("WitherSkullCooldown", witherSkullCooldown);


        compound.putInt(TELEPORT_COOLDOWN_TAG, teleportCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("WitherSkullCooldown")) {
            witherSkullCooldown = compound.getInt("WitherSkullCooldown");
        }


        if (compound.contains(TELEPORT_COOLDOWN_TAG, Tag.TAG_INT)) {
            teleportCooldown = compound.getInt(TELEPORT_COOLDOWN_TAG);
        }

        // 确保冷却时间不会为负数
        if (teleportCooldown < 0) {
            teleportCooldown = 0;
        }
    }
}
