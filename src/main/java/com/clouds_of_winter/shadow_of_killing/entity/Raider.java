package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.init.ModEntities;
import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class Raider extends BlackSwordRaider {
    public Raider(EntityType<? extends Raider> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return BlackSwordRaider.createAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.35F)
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.DARK_IRON_SWORD.get()));
    }

    /**
    private int tickCounter2 = 0;
    */


    protected boolean BlackSwordRaiderGradualHealthRegeneration() {
        return false;
    }

    protected boolean BlackSwordRaiderImmediateHealthRecovery(){
        return false;
    }

    protected boolean BlackSwordRaiderLifeRestored() {
        return false;
    }


    private long lastAttackTime2 = -1;
    protected boolean RaiderGradualHealthRegeneration() {
        long currentTime = level.getGameTime();
        if (lastAttackTime2 != -1 && currentTime > lastAttackTime2 && currentTime - lastAttackTime2 <= 40) {
            if (this.isAlive() && this.tickCount % 2 == 0) {
                this.heal(0.1F);
            }
        }

        return true;
    }

    protected boolean RaiderImmediateHealthRecovery(){
        if (this.isAlive()) {
            this.heal(3.0F);
        }
        lastAttackTime2 = level.getGameTime();

        return true;
    }

    protected boolean RaiderLifeRestored() {
        /**
        if (!this.isAggressive() && !this.isDeadOrDying() && this.getHealth() < this.getMaxHealth()) {
            tickCounter2++;
            if (tickCounter2 >= 8) {
                // 回复 0.1 生命值
                tickCounter2 = 0;
                this.setHealth(this.getHealth() + 0.1F);
                if (this.getHealth() > this.getMaxHealth()) {
                    this.setHealth(this.getMaxHealth());
                }
            }
        }
        */

        if (this.isAlive() && this.tickCount % 8 == 0) {
            if (!this.isAggressive()){
                this.heal(0.1F); // 恢复0.1点生命值
            }
        }

        return true;
    }

    public void tick() {

        /**
        this.RaiderLifeRestored();
        */

        //this.convertToRaider();

        this.RaiderDodgeProjectile();
        this.RaiderDodgeImmuneTimer();


        this.RaiderupdateSkillState();
        super.tick();
    }

    public void aiStep() {
        this.RaiderGradualHealthRegeneration();
        this.RaiderLifeRestored();
        super.aiStep();
    }

    public boolean doHurtTarget(Entity p_34169_) {
        boolean result = super.doHurtTarget(p_34169_);
        if (result) {
            this.RaiderImmediateHealthRecovery();
        }
        return result;
    }

    public boolean killShield(Entity p_34169_){

        return false;
    }

    /*
    public boolean convertToBlackSwordRaider() {

        return false;
    }
    */

    public boolean convertToRaider() {
        if (this.level!= null) { // 确保只在服务端执行
            // 获取当前生物周围 10 格范围内的实体

            // 1. 先计算生物尺寸
            double entitySize = this.getBbWidth() + this.getBbHeight();

            // 2. 再乘以基础检测范围
            double detectionRange = 0.5 * entitySize;

            for (Entity entity : this.level.getEntities(this, this.getBoundingBox().inflate(detectionRange))) {
                ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());

                // 检查实体是否为 rottencreatures:immortal 或 rottencreatures:zap
                if (entityTypeKey != null &&
                        (entityTypeKey.toString().equals("rottencreatures:immortal") ||
                                entityTypeKey.toString().equals("rottencreatures:zap"))) {


                    // 创建一个新的自定义生物实例
                    Raider raider = new Raider(ModEntities.RAIDER.get(), this.level);

                    // 设置新生物的位置和旋转角度
                    raider.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());

                    // 如果需要，可以复制原生物的部分属性（如生命值、装备等）
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        raider.setHealth(livingEntity.getHealth()); // 复制生命值
                        // 复制其他属性（如装备、状态效果等）
                    }

                    // 移除原来的实体
                    entity.remove(Entity.RemovalReason.DISCARDED);

                    // 将新生物添加到世界中
                    this.level.addFreshEntity(raider);
                }
            }
        }
        return true;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.RaiderDodgeProjectileGoal(); // 闪避AI
        this.RaiderSkillActivationGoal();
    }

    public boolean BlackSwordRaiderSkillActivationGoal() {
        return false;
    }

    public boolean BlackSwordRaiderupdateSkillState() {
        return false;
    }

    public boolean RaiderupdateSkillState() {
        switch (skillState) {
            case TARGETING:
                handleTargetingPhase();
                break;

            case DASHING:
                handleDashingPhase();
                break;

            case COOLDOWN:
                updateCooldown();
                break;
        }

        return true;
    }

    // 技能常量配置
    private static final int COOLDOWN_TICKS = 20 * 20; // 20秒冷却
    private static final int TARGETING_DURATION = 10;  // 0.5秒锁定目标时间
    private static final double MAX_DASH_DISTANCE = 6.0;  // 最大冲刺距离
    private static final double DASH_SPEED_PER_TICK = 1.5; // 每tick冲刺速度
    private static final double COLLISION_CHECK_DISTANCE = 1.0; // 碰撞检测距离
    private static final double TRIGGER_RANGE = 6.0;   // 触发范围
    private static final float DAMAGE_AMOUNT = 8.0f;   // 伤害值

    // 技能状态管理
    private int skillCooldown = 0;
    private SkillState skillState = SkillState.READY;
    private Vec3 dashTarget;

    private Class<?> originalTargetClass; // 初始目标的类型（用于同类伤害）

    private int targetingTicks;
    private final Set<UUID> hitEntities = new HashSet<>();// 防止重复伤害


    // 冲刺状态变量
    private double dashDistanceRemaining = 0.0;
    private Vec3 dashDirection;

    // 锁定目标阶段处理
    private void handleTargetingPhase() {
        // 停止所有移动
        this.getNavigation().stop();
        this.setDeltaMovement(Vec3.ZERO);

        // 重置移动控制器
        this.getMoveControl().setWantedPosition(
                this.getX(),
                this.getY(),
                this.getZ(),
                0.0
        );

        // 面向目标
        if (dashTarget != null) {
            this.lookAt(EntityAnchorArgument.Anchor.EYES, dashTarget);
        }

        targetingTicks--;

        if (targetingTicks <= 0) {
            startDashing();
        }
    }


    // 冲刺阶段处理（每tick调用）
    private void handleDashingPhase() {
        // 计算本次tick的冲刺距离
        double moveDistance = Math.min(dashDistanceRemaining, DASH_SPEED_PER_TICK);

        // 计算移动向量
        Vec3 moveVector = dashDirection.scale(moveDistance);
        Vec3 newPosition = this.position().add(moveVector);

        // 检测前方是否有障碍物
        if (isPathBlocked(newPosition)) {
            endSkill();
            return;
        }

        // 应用移动
        this.setPos(newPosition.x, newPosition.y, newPosition.z);
        dashDistanceRemaining -= moveDistance;

        // 检测路径上的碰撞伤害
        checkCollisionDamage();

        // 检查冲刺是否完成
        if (dashDistanceRemaining <= 0.001) {
            endSkill();
        }
    }

    // 检测前方路径是否被阻挡
    private boolean isPathBlocked(Vec3 newPosition) {
        // 检测方块碰撞
        AABB newBoundingBox = this.getBoundingBox().move(newPosition.subtract(this.position()));
        if (!this.level.noCollision(this, newBoundingBox)) {
            return true;
        }

        // 检测高度变化（台阶边缘）
        double groundCheckDistance = 0.5;
        AABB groundCheckBox = new AABB(
                newPosition.x - 0.3,
                newPosition.y - groundCheckDistance,
                newPosition.z - 0.3,
                newPosition.x + 0.3,
                newPosition.y,
                newPosition.z + 0.3
        );

        return this.level.getBlockStates(groundCheckBox)
                .allMatch(state -> state.isAir() || state.getMaterial().isLiquid());
    }

    // 路径碰撞伤害检测
    private void checkCollisionDamage() {
        // 创建碰撞检测区域（沿冲刺方向）
        Vec3 start = this.position();
        Vec3 end = start.add(dashDirection.scale(DASH_SPEED_PER_TICK));
        AABB collisionBox = new AABB(
                Math.min(start.x, end.x) - this.getBbWidth() * 1.5,
                Math.min(start.y, end.y) - this.getBbHeight() * 0.5,
                Math.min(start.z, end.z) - this.getBbWidth() * 1.5,
                Math.max(start.x, end.x) + this.getBbWidth() * 1.5,
                Math.max(start.y, end.y) + this.getBbHeight() * 1.25,
                Math.max(start.z, end.z) + this.getBbWidth() * 1.5
        );

        Direction facingDirection = this.getDirection();

        // 检测路径上的实体
        List<Entity> entities = this.level.getEntities(this, collisionBox);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity && !hitEntities.contains(entity.getUUID())) {

                if (isValidSweepTargetSkill((LivingEntity) entity)) {
                    if (this.hasLineOfSight(entity)) {
                        if (!isBehind(facingDirection, entity)/*!isEntityBehind(entity, facingDirection) && lookVec.dot(toEntity) > -0.5*/) {
                            this.doHurtTarget(entity);
                        }
                    }

                }
                if (!isSameTeam((LivingEntity) entity) && (originalTargetClass == null || originalTargetClass.equals(entity.getClass()))) {
                    if (this.hasLineOfSight(entity)) {
                        if (!isBehind(facingDirection, entity)/*!isEntityBehind(entity, facingDirection) && lookVec.dot(toEntity) > -0.5*/) {
                            this.doHurtTarget(entity);
                        }
                    }
                }

                hitEntities.add(entity.getUUID());
            }
        }
    }

    private boolean isSameTeam(LivingEntity entity) {
        return this.isAlliedTo(entity) || entity.isAlliedTo(this);
    }




    // 开始冲刺（计算实际终点）
    private void startDashing() {
        skillState = SkillState.DASHING;
        hitEntities.clear();

        // 计算冲刺方向
        Vec3 currentPos = this.position();
        dashDirection = new Vec3(
                dashTarget.x - currentPos.x,
                0,
                dashTarget.z - currentPos.z
        ).normalize();

        // 计算实际冲刺终点（考虑障碍物）
        BlockHitResult hitResult = calculateDashEndpoint(currentPos, dashDirection);
        dashTarget = hitResult.getLocation();
        dashDistanceRemaining = currentPos.distanceTo(dashTarget);


    }


    // 计算实际冲刺终点（考虑障碍物）
    private BlockHitResult calculateDashEndpoint(Vec3 startPos, Vec3 direction) {
        // 创建射线检测路径
        Vec3 endPos = startPos.add(direction.scale(MAX_DASH_DISTANCE));

        // 执行方块碰撞检测
        ClipContext context = new ClipContext(
                startPos.add(0, 0.5, 0), // 从生物中心开始检测
                endPos,
                ClipContext.Block.COLLIDER, // 检测碰撞方块
                ClipContext.Fluid.NONE,     // 忽略液体
                this
        );

        return this.level.clip(context);
    }

    // 结束技能并开始冷却
    private void endSkill() {
        skillState = SkillState.COOLDOWN;
        skillCooldown = COOLDOWN_TICKS;
        this.setDeltaMovement(Vec3.ZERO);
        hitEntities.clear();
        dashDistanceRemaining = 0;


        originalTargetClass = null;   // 重置类型

        // 技能结束音效
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.0F, 1.0F);
    }




    // 更新冷却计时
    private void updateCooldown() {
        if (skillCooldown > 0) {
            skillCooldown--;
        } else {
            skillState = SkillState.READY;
        }
    }

    // 技能激活方法
    public void activateSkill(LivingEntity target) {
        if (skillState != SkillState.READY) return;


        skillState = SkillState.TARGETING;
        targetingTicks = TARGETING_DURATION;
        dashTarget = target.position();
        this.originalTargetClass = target.getClass();  // 记录目标类型

        // 锁定目标期间停止其他行为
        this.getNavigation().stop();
        this.setTarget(target);

        // 锁定目标音效
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.CHAIN_PLACE, SoundSource.HOSTILE, 1.0F, 0.8F);
    }

    public boolean RaiderSkillActivationGoal() {
        this.goalSelector.addGoal(1, new RaiderSkillActivationGoal(this));
        return true;
    }

    // 技能目标选择AI
    static class RaiderSkillActivationGoal extends Goal {
        private final Raider mob;
        private LivingEntity target;

        public RaiderSkillActivationGoal(Raider mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK)); // 覆盖移动和视角控制
        }

        @Override
        public boolean canUse() {
            // 只在技能就绪时触发
            if (mob.skillState != SkillState.READY) return false;

            // 获取最近的敌对目标
            target = mob.getTarget();
            return target != null
                    && mob.distanceTo(target) <= TRIGGER_RANGE;
        }

        @Override
        public void start() {
            mob.activateSkill(target);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }


    public boolean BlackSwordRaiderDodgeProjectileGoal() {
        return false;
    }

    public boolean BlackSwordRaiderDodgeProjectile() {
        return false;
    }

    public boolean BlackSwordRaiderDodgeImmuneTimer() {
        return false;
    }

    private static final double DODGE_DISTANCE = 1;
    private int dodgeCooldown = 0;
     // 新增：闪避状态标志
    private int dodgeImmuneTimer = 0; // 添加：闪避免疫计时器


    // 从Goal中调用的闪避方法
    private void performDodgeWithImmunity(Projectile projectile) {
        // 计算闪避方向
        Vec3 toProjectile = projectile.position().subtract(this.position()).normalize();
        Vec3 dodgeDir = new Vec3(-toProjectile.z, 0, toProjectile.x);

        // 随机选择左右方向
        if (this.getRandom().nextBoolean()) {
            dodgeDir = dodgeDir.scale(-1);
        }

        // 应用闪避速度
        this.setDeltaMovement(this.getDeltaMovement().add(dodgeDir.scale(DODGE_DISTANCE)));
        this.hurtMarked = true;

        // 设置闪避冷却
        this.dodgeCooldown = 20;

        // 设置闪避免疫时间：0.2秒 = 4刻
        this.dodgeImmuneTimer = 10;
    }

    // 获取当前是否在闪避免疫状态
    private boolean isDodgeImmuneActive() {
        return dodgeImmuneTimer > 0;
    }

    // Goal类 - 修改performDodge方法
    public class RaiderDodgeProjectileGoal extends Goal {
        private final Raider entity;

        public RaiderDodgeProjectileGoal(Raider entity) {
            this.entity = entity;
        }

        @Override
        public boolean canUse() {
            return entity.dodgeCooldown <= 0 && !entity.isDodgeImmuneActive();
        }

        @Override
        public void tick() {
            Level level = entity.level;

            for (Projectile projectile : level.getEntitiesOfClass(
                    Projectile.class,
                    entity.getBoundingBox().inflate(entity.getBbWidth() * 58.33333333333333)
            )) {
                if (projectile.isInWall() || projectile.horizontalCollision) continue;

                double distance = entity.distanceTo(projectile);
                if (distance > entity.getBbWidth() * 58.33333333333333) continue;

                LivingEntity owner = projectile.getOwner() instanceof LivingEntity ?
                        (LivingEntity) projectile.getOwner() : null;

                if (owner != null && isProjectileTargeting(projectile, entity)) {
                    // 关键修改：检查是否来自后方
                    if (isBehind(entity.getDirection(), owner)) {
                        // 后方抛射物：10%概率闪避
                        if (entity.getRandom().nextFloat() < 0.05f) {
                            entity.performDodgeWithImmunity(projectile); // 调用新的闪避方法
                            return;
                        }
                    } else if (!isBehind(entity.getDirection(), owner)) {
                        if (entity.getRandom().nextFloat() < 0.15f) {
                            entity.performDodgeWithImmunity(projectile); // 调用新的闪避方法
                            return;
                        }
                    }
                }
            }
        }

        private boolean isProjectileTargeting(Projectile projectile, LivingEntity target) {
            Vec3 toTarget = target.position().subtract(projectile.position());
            Vec3 projectileDir = projectile.getDeltaMovement();

            if (projectileDir.lengthSqr() < 0.001) return false;

            double dot = toTarget.normalize().dot(projectileDir.normalize());
            return dot > 0.95;
        }

        private boolean isBehind(Direction facingDirection, Entity target) {
            Vec3 mobPos = entity.position();
            Vec3 targetPos = target.position();
            Vec3 relativeDir = targetPos.subtract(mobPos).normalize();
            Vec3 forwardVec = Vec3.atLowerCornerOf(facingDirection.getNormal());

            return relativeDir.dot(forwardVec) < 0;
        }
    }



    public boolean RaiderDodgeProjectileGoal() {
        this.goalSelector.addGoal(1, new RaiderDodgeProjectileGoal(this));
        return true;
    }

    public boolean RaiderDodgeProjectile() {
        // 更新闪避状态
        if (dodgeCooldown > 0) {
            dodgeCooldown--;
        }

        return true;
    }

    public boolean RaiderDodgeImmuneTimer() {
        // 更新闪避免疫计时器
        if (dodgeImmuneTimer > 0) {
            dodgeImmuneTimer--;
        }

        return true;
    }



    public boolean hurt(DamageSource source, float amount) {
        // 检查是否在闪避免疫期间（0.2秒 = 4刻）
        if (dodgeImmuneTimer > 0) {
            // 闪避期间完全免疫伤害
            return false;
        }
        return super.hurt(source, amount);
    }


    public boolean BlackSwordRaideraddAdditionalSaveData(CompoundTag tag){
        return false;
    }

    public boolean BlackSwordRaiderreadAdditionalSaveData(CompoundTag tag){
        return false;
    }

    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        this.RaideraddAdditionalSaveData(p_21484_);
    }

    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        this.RaiderreadAdditionalSaveData(p_21450_);




    }

    public boolean RaideraddAdditionalSaveData(CompoundTag tag){
        tag.putInt("SkillCooldown", skillCooldown);
        tag.putString("SkillState", skillState.name());



        tag.putInt("DodgeCooldown", dodgeCooldown);
        tag.putInt("DodgeImmuneTimer", dodgeImmuneTimer);
        return true;
    }

    public boolean RaiderreadAdditionalSaveData(CompoundTag tag){
        skillCooldown = tag.getInt("SkillCooldown");
        skillState = SkillState.valueOf(tag.getString("SkillState"));



        // 读取闪避相关数据
        if (tag.contains("DodgeCooldown")) {
            dodgeCooldown = tag.getInt("DodgeCooldown");
        }

        if (tag.contains("DodgeImmuneTimer")) {
            dodgeImmuneTimer = tag.getInt("DodgeImmuneTimer");
        }
        return true;
    }
}
