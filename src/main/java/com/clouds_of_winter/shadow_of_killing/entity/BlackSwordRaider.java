package com.clouds_of_winter.shadow_of_killing.entity;

import com.clouds_of_winter.shadow_of_killing.handlers.Faction;
import com.clouds_of_winter.shadow_of_killing.init.ModEntities;
import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import com.clouds_of_winter.shadow_of_killing.handlers.ShieldDisableManager;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;


public class BlackSwordRaider extends Zombie {
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    /*
    private static final int COMMAND_TIME_TICKS = 1;
    private int remainingTime;

    public void Command() {
        if (remainingTime > 0) {
            remainingTime--;
        }
        if (remainingTime <= 0) {
            this.CommandsKill();
        }
    }


    private void CommandsKill() {
        if (!this.level.isClientSide && this.level.getServer()!= null) {
            if (!this.level.isClientSide && this.level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel) this.level;
                CommandSourceStack sourceStack = this.level.getServer().createCommandSourceStack().withPermission(10).withSuppressedOutput();

                AABB searchBox = this.getBoundingBox().inflate(10);
                for (Entity entity : this.level.getEntities(this, searchBox)) {
                    if (entity instanceof Zombie) {
                        this.level.getServer().getCommands().performPrefixedCommand(sourceStack, "/kill @e[type= rottencreatures:zap]");
                        this.level.getServer().getCommands().performPrefixedCommand(sourceStack, "/kill @e[type= rottencreatures:immortal]");
                    }
                }
            }
        }
    }


    public void readAdditionalSaveData(CompoundTag p_32595_) {
        super.readAdditionalSaveData(p_32595_);
        this.remainingTime = p_32595_.getInt("RemainingTime");
    }

    public void addAdditionalSaveData(CompoundTag p_32610_) {
        super.addAdditionalSaveData(p_32610_);
        p_32610_.putInt("RemainingTime", this.remainingTime);
    }
    */

    public void aiStep() {
        super.aiStep();
        /*
        this.Command();
         */
        this.BlackSwordRaiderGradualHealthRegeneration();
        this.BlackSwordRaiderLifeRestored();
        //this.playexplodeandsweepsimultaneously();


        /**
        if (!this.isDeadOrDying() && this.getTarget()!= null) {
            double distanceX = this.getTarget().getX() - this.getX();
            double distanceY = this.getTarget().getY() - this.getY();
            double distanceZ = this.getTarget().getZ() - this.getZ();
            double horizontalDistanceSqr = distanceX * distanceX + distanceZ * distanceZ;
            double verticalDistance = Math.abs(distanceY);
            if (horizontalDistanceSqr <= 2.15 && (verticalDistance >= 1.3 && verticalDistance <= 2.16)) {
                // 执行攻击逻辑
                if (attackTick <= 0) {
                    // 执行近战攻击逻辑，调用造成伤害的方法并传入伤害源和伤害量
                    this.doHurtTarget(this.getTarget());
                    // 设置新的攻击间隔，1秒钟对应的20游戏刻
                    this.swing(InteractionHand.MAIN_HAND);
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

    /*
    private boolean isEnemy(LivingEntity entity) {
        // 根据实际情况判断一个实体是否为敌人
        return entity!= this && entity.getTeam()!= this.getTeam();
    }
    */

    public BlackSwordRaider(EntityType<? extends BlackSwordRaider> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
        /*
        remainingTime = COMMAND_TIME_TICKS;
        */


        this.xpReward = 50;
    }

    /**
    private int attackTick = 0;
    */

    /**
    private int tickCounter = 0;
    */



    private long lastAttackTime = -1;

    protected boolean BlackSwordRaiderGradualHealthRegeneration() {
        long currentTime = level.getGameTime();
        if (lastAttackTime != -1 && currentTime > lastAttackTime && currentTime - lastAttackTime <= 40) {
            if (this.isAlive()) {
                this.heal(0.1F);
            }
        }


        return true;
    }

    protected boolean BlackSwordRaiderImmediateHealthRecovery(){
        if (this.isAlive()) {
            this.heal(6.0F);
        }
        lastAttackTime = level.getGameTime();

        return true;
    }

    protected boolean BlackSwordRaiderLifeRestored() {

        /**
        if (!this.isAggressive() && !this.isDeadOrDying() && this.getHealth() < this.getMaxHealth()) {
            tickCounter++;
            if (tickCounter >= 7) {
                // 回复 0.1 生命值
                tickCounter = 0;
                this.setHealth(this.getHealth() + 0.1F);
                if (this.getHealth() > this.getMaxHealth()) {
                    this.setHealth(this.getMaxHealth());
                }
            }
        }
        */

        if (this.isAlive() && this.tickCount % 7 == 0) {
            if (!this.isAggressive()){
                this.heal(0.1F); // 恢复0.1点生命值
            }
        }


        return true;
    }



    public boolean doHurtTarget(Entity target) {
        boolean result = super.doHurtTarget(target);

        /**
        this.markAttack();
        */

        if (result) {
            this.BlackSwordRaiderImmediateHealthRecovery();
        }
        /**
        if (result && p_34169_ instanceof LivingEntity) {
            ItemStack mainHandItem = this.getMainHandItem();
            if (mainHandItem.getItem() instanceof TieredItem){
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                if (attackDamage > 0) {
                    Direction facingDirection = this.getDirection();
                    for (Entity nearbyEntity : this.level.getEntities(this, this.getBoundingBox().inflate(2, 1, 2))) {
                        if (nearbyEntity!= p_34169_ && nearbyEntity instanceof LivingEntity &&!(nearbyEntity instanceof BlackSwordRaider)) {
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



        if (result && target instanceof LivingEntity) {
            ItemStack mainHand = this.getMainHandItem();
            // 条件：物品为 TieredItem 且不是 MossTool
            if (mainHand.getItem() instanceof TieredItem) {

                LivingEntity livingTarget = (LivingEntity) target;
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                if (attackDamage > 0) {
                    Direction facingDirection = this.getDirection();

                    Class<?> targetClass = livingTarget.getClass();
                    AABB range = this.getBoundingBox().inflate(this.getBbWidth() * 2.571428571428571, this.getBbHeight() * 0.5, this.getBbWidth() * 2.571428571428571);

                    for (Entity nearby : this.level.getEntities(this, range)) {
                        // 排除主目标、自身、非同类、非生物
                        if (nearby != target && nearby != this &&
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



        if (result && target instanceof LivingEntity) {
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
                            e -> isValidSweepTarget(target, e)
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

        ItemStack heldItem = this.getMainHandItem();
        boolean isHoldingSword = heldItem.is(ModItems.BLACK_SWORD.get());

        if (isHoldingSword && target instanceof LivingEntity livingTarget) {
            // 破盾逻辑
            if (isTargetBlockingWithShield(livingTarget)) {
                breakShield(livingTarget);

                // 全局禁用盾牌
                if (!level.isClientSide) {
                    ShieldDisableManager.disableAllShields((ServerLevel)level);
                }
            }
        }

        this.killShield(target);

        return result;
    }

    private boolean isTargetBlockingWithShield(LivingEntity target) {
        ItemStack activeItem = target.getUseItem();
        return target.isBlocking() &&
                activeItem.getItem() instanceof ShieldItem;
    }

    private void breakShield(LivingEntity target) {
        // 强制禁用盾牌
        target.stopUsingItem();

        // 播放破盾效果
        if (target instanceof Player player) {
            player.getCooldowns().addCooldown(Items.SHIELD, 100);
            this.level.broadcastEntityEvent(player, (byte)30);
        }

        // 播放破盾动画和音效
        target.broadcastBreakEvent(target.getUsedItemHand());
    }

    public boolean isBehind(Direction facingDirection, Entity target) {
        Vec3 mobPos = this.position();
        Vec3 targetPos = target.position();
        Vec3 relativeDir = targetPos.subtract(mobPos).normalize();
        Vec3 forwardVec = Vec3.atLowerCornerOf(facingDirection.getNormal()); // 水平前方向量

        // 点积 < 0 表示目标在背后半球（正后方、后左、后右、后上、后下均满足）
        return relativeDir.dot(forwardVec) < 0;
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
        if (entity instanceof BlackSwordRaider) {
            return shouldAttackRaider((BlackSwordRaider) entity);
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

    private boolean shouldAttackRaider(BlackSwordRaider entity) {


        // 双重判断：阵营关系 + 攻击历史
        return isFactionEnemy(entity) || hasCombatHistory(entity);
    }

    private boolean isMobHostile(Mob mob) {
        if (mob.getTarget() == this) return true;

        return mob.getLastHurtByMob() == this ||
                mob.getLastHurtMob() == this;
    }

    // 新增战斗历史验证
    private boolean hasCombatHistory(BlackSwordRaider entity) {
        if (entity.getTarget() == this) return true;

        // 最近攻击关系双向验证
        return entity.getLastHurtByMob() == this ||  // 该实体最近被本实体攻击
                entity.getLastHurtMob() == this;    // 本实体最近被该实体攻击
    }

    private boolean isFactionEnemy(BlackSwordRaider mob) {
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
    private boolean isFactionEnemy(BlackSwordRaider mob) {
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



    public boolean killShield(Entity p_34169_){
        if (p_34169_ instanceof Player player) {
            this.BlackSwordRaidermaybeDisableShield(player, this.getMainHandItem(), player.isUsingItem() ? player.getUseItem() : ItemStack.EMPTY);
        }
        return true;
    }

    private void BlackSwordRaidermaybeDisableShield(Player p_21425_, ItemStack p_21426_, ItemStack p_21427_) {
        if (!p_21426_.isEmpty() && !p_21427_.isEmpty() && p_21426_.is(ModItems.BLACK_SWORD.get()) && p_21427_.is(Items.SHIELD)) {
            float f = 0.25F + (float)EnchantmentHelper.getBlockEfficiency(this) * 0.05F;
            if (this.random.nextFloat() < f) {
                p_21425_.getCooldowns().addCooldown(Items.SHIELD, 100);
                this.level.broadcastEntityEvent(p_21425_, (byte)30);
            }
        }

    }




    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.39F)
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 18.0D)
                .add(Attributes.ARMOR, 16.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }




    protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.BLACK_SWORD.get()));
    }




    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.BlackSwordRaiderDodgeProjectileGoal(); // 闪避AI


        this.BlackSwordRaiderSkillActivationGoal();


        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }


    //effect give @e[type= shadow_of_killing:raider] minecraft:resistance 1000000 255 true

    protected void addBehaviourGoals() {

//我的世界1.19.2forge自定义生物AI发现攻击目标永续寻找，优先攻击最近距离的攻击目标，NearestAttackableTargetGoal

        //this.goalSelector.addGoal(2, new CustomMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false){
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


        this.goalSelector.addGoal(5, new MoveThroughVillageGoal(this, 1.0F, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        //this.targetSelector.addGoal(1, new CustomHurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }


// 我的世界1.19.2forge模组开发自定义生物发现敌人时攻击对自定义生物是敌对的生物进行闪现，并对沿途敌对单位造成伤害，距离8格
    //我的世界1.19.2forge模组开发自定义生物有一个冷却时间为20秒的技能，且生物生成时技能已冷却完毕。技能效果：当敌对生物进入距离自身8米的范围时选定当前敌人坐标，并瞬移到该坐标且会对沿途的敌对生物造成伤害

    //我的世界1.19.2forge模组开发自定义生物有一个冷却时间为20秒的技能，且生物生成时技能已冷却完毕。技能效果：当敌对生物进入距离自身8米的范围时优先选定当前敌人坐标1秒后并冲刺12米远且撞到自定义生物碰撞箱时会对路径上的生物造成伤害,选定当前敌人坐标期间不移动

    public boolean BlackSwordRaiderDodgeProjectileGoal() {
        this.goalSelector.addGoal(1, new BlackSwordRaiderDodgeProjectileGoal(this));
        return true;
    }

    public boolean BlackSwordRaiderSkillActivationGoal() {
        this.goalSelector.addGoal(1, new BlackSwordRaiderSkillActivationGoal(this));
        return true;
    }

    // 技能常量配置
    private static final int COOLDOWN_TICKS = 20 * 20; // 20秒冷却
    private static final int TARGETING_DURATION = 10;  // 0.5秒锁定目标时间
    private static final double MAX_DASH_DISTANCE = 12.0;  // 最大冲刺距离
    private static final double DASH_SPEED_PER_TICK = 1.5; // 每tick冲刺速度
    private static final double COLLISION_CHECK_DISTANCE = 1.0; // 碰撞检测距离
    private static final double TRIGGER_RANGE = 12.0;   // 触发范围
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


    // 技能状态枚举
    public enum SkillState {
        READY,       // 技能就绪
        TARGETING,   // 锁定目标阶段
        DASHING,     // 冲刺阶段
        COOLDOWN     // 冷却中
    }

    public boolean BlackSwordRaiderupdateSkillState() {
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


        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.0F, 1.0F);

        /*
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.0F, 1.0F);
        this.attackSoundTimer = SWEEP_SOUND_DELAY;
        */


    }

    private static final int SWEEP_SOUND_DELAY = 1;    // 横扫声延迟10刻（0.5秒）

    private int attackSoundTimer = -1;


    /*
    private void playexplodeandsweepsimultaneously() {
        // 处理音效计时器
        if (this.attackSoundTimer > 0) {
            this.attackSoundTimer--;
        } else if (this.attackSoundTimer == 0) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.0F, 1.0F);
            this.attackSoundTimer = -1;
        }
    }
    */



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

    // 技能目标选择AI
    static class BlackSwordRaiderSkillActivationGoal extends Goal {
        private final BlackSwordRaider mob;
        private LivingEntity target;

        public BlackSwordRaiderSkillActivationGoal(BlackSwordRaider mob) {
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







    // 目标验证逻辑（核心过滤）
    public boolean isValidSweepTargetSkill(LivingEntity entity) {
        // 基础排除条件
        if (entity == this) return false;

        if (isSameTeamSkill(entity)) return false; // 排除同队成员


        // 自定义生物阵营判断
        if (entity instanceof BlackSwordRaider) {
            return shouldAttackRaiderSkill((BlackSwordRaider) entity);
        }

        if (entity instanceof Mob) {
            return isMobHostileSkill((Mob) entity);
        }

        // 默认敌对判断
        return isEnemySkill(entity);
    }

    private boolean shouldAttackRaiderSkill(BlackSwordRaider entity) {
        // 双重判断：阵营关系 + 攻击历史
        return isFactionEnemySkill(entity) || hasCombatHistorySkill(entity);
    }

    private boolean isMobHostileSkill(Mob mob) {
        if (mob.getTarget() == this) return true;
        return mob.getLastHurtByMob() == this || mob.getLastHurtMob() == this;
    }

    // 新增战斗历史验证
    private boolean hasCombatHistorySkill(BlackSwordRaider entity) {
        if (entity.getTarget() == this) return true;
        // 最近攻击关系双向验证
        return entity.getLastHurtByMob() == this || entity.getLastHurtMob() == this;
    }

    private boolean isFactionEnemySkill(BlackSwordRaider other) {
        if (isSameTeamSkill(other)) return false; // 确保队伍优先级最高

        // 空值保护层级
        if (this.faction == null || other.faction == null) {
            return false; // 默认不攻击无阵营生物
        }

        // 阵营敌对状态
        return this.faction.isEnemy(other.faction);
    }

    // 增强版通用敌人判断
    public boolean isEnemySkill(Entity entity) {
        // 原版敌对判断
        /*
        if (entity instanceof Enemy) return true;
        */

        // 队伍系统判断
        if (entity instanceof LivingEntity) {
            return !isSameTeamSkill((LivingEntity) entity);
        }
        return false;
    }

    private boolean isSameTeamSkill(LivingEntity entity) {
        return this.isAlliedTo(entity) || entity.isAlliedTo(this);
    }







    /**
    // 自定义的HurtByTargetGoal
    public class CustomHurtByTargetGoal extends HurtByTargetGoal {
        private final BlackSwordRaider customMob;

        public CustomHurtByTargetGoal(BlackSwordRaider mob) {
            super(mob);
            this.customMob = mob;
        }

        @Override
        public void start() {
            super.start();

            // 获取当前目标（伤害来源）
            LivingEntity target = this.mob.getTarget();

            // 设置初始目标（如果尚未设置或需要重置）
            if (target != null &&
                    (customMob.getInitialTarget() == null ||
                            customMob.getInitialTarget() != target)) {
                customMob.setInitialTarget(target);
            }
        }

        @Override
        public boolean canContinueToUse() {
            // 如果初始目标有效，则继续锁定
            if (customMob.getInitialTarget() != null &&
                    customMob.getInitialTarget().isAlive()) {
                return true;
            }
            return super.canContinueToUse();
        }
    }

    */




    public class CustomMeleeAttackGoal extends MeleeAttackGoal {
        // 配置常量
        private static final int PATH_RECALCULATION_INTERVAL = 10;
        private static final double MIN_DISTANCE_SQR = 4.0; // 2格距离
        private static final double MAX_DISTANCE_SQR = 2147483647.0; // 10格距离
        private static final int ATTACK_INTERVAL_TICKS = 20; // 1秒攻击间隔

        // 通过反射访问私有字段
        private final double speedModifier;
        private final boolean followTargetEvenIfNotSeen;
        private int attackCooldown; // 本地攻击冷却备份

        private int pathRecalculationTimer;
        private boolean wasPathing;

        // 攻击间隔参数（以ticks为单位）
        private final int attackInterval;

        public CustomMeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followTargetEvenIfNotSeen) {
            this(mob, speedModifier, followTargetEvenIfNotSeen, ATTACK_INTERVAL_TICKS);
        }

        public CustomMeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followTargetEvenIfNotSeen, int attackInterval) {
            super(mob, speedModifier, followTargetEvenIfNotSeen);
            this.speedModifier = speedModifier;
            this.followTargetEvenIfNotSeen = followTargetEvenIfNotSeen;
            this.pathRecalculationTimer = 0;
            this.wasPathing = false;
            this.attackCooldown = 0;
            this.attackInterval = attackInterval; // 设置攻击间隔
        }

        // 通过反射获取攻击冷却字段
        private int getAttackCooldown() {
            try {
                Field field = MeleeAttackGoal.class.getDeclaredField("attackTime");
                field.setAccessible(true);
                return field.getInt(this);
            } catch (Exception e) {
                return attackCooldown; // 回退到本地字段
            }
        }

        // 通过反射设置攻击冷却字段
        private void setAttackCooldown(int value) {
            try {
                Field field = MeleeAttackGoal.class.getDeclaredField("attackTime");
                field.setAccessible(true);
                field.setInt(this, value);
            } catch (Exception e) {
                attackCooldown = value; // 回退到本地字段
            }
        }

        @Override
        public void start() {
            super.start();
            this.pathRecalculationTimer = 0;
            this.wasPathing = false;
            // 初始攻击冷却设为0，确保可以立即攻击
            setAttackCooldown(0);
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.mob.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            }

            double distanceSqr = this.mob.distanceToSqr(target);
            if (distanceSqr > MAX_DISTANCE_SQR) {
                return false; // 目标太远，停止攻击
            }

            if (!this.followTargetEvenIfNotSeen) {
                return this.mob.getSensing().hasLineOfSight(target);
            }

            return true;
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            double distanceSqr = this.mob.distanceToSqr(target);
            boolean hasLineOfSight = this.mob.getSensing().hasLineOfSight(target);
            int currentCooldown = getAttackCooldown();

            // 1. 更新攻击冷却（每tick减少1）
            if (currentCooldown > 0) {
                setAttackCooldown(currentCooldown - 1);
            }

            // 2. 路径重新计算逻辑
            if (--this.pathRecalculationTimer <= 0) {
                this.pathRecalculationTimer = PATH_RECALCULATION_INTERVAL + this.mob.getRandom().nextInt(5);

                if (hasLineOfSight || this.followTargetEvenIfNotSeen) {
                    this.wasPathing = true;
                    this.mob.getNavigation().moveTo(target, this.speedModifier);
                } else {
                    this.stopPathing();
                }
            }

            // 3. 攻击距离管理
            double attackRange = this.getAttackReachSqr(target);

            // 4. 检查并执行攻击
            if (distanceSqr <= attackRange && currentCooldown <= 0) {
                // 在攻击范围内且冷却结束，执行攻击
                setAttackCooldown(attackInterval); // 使用配置的攻击间隔
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(target);
            }

            // 5. 移动控制逻辑
            if (distanceSqr < MIN_DISTANCE_SQR) {
                // 距离目标太近时减速
                Vec3 targetPos = target.position();
                Vec3 mobPos = this.mob.position();
                Vec3 direction = targetPos.subtract(mobPos).normalize();

                double newX = mobPos.x + direction.x * 1;
                double newY = mobPos.y;
                double newZ = mobPos.z + direction.z * 1;

                this.mob.getMoveControl().setWantedPosition(newX, newY, newZ, this.speedModifier * 1);
            } else if (distanceSqr > MAX_DISTANCE_SQR || (!hasLineOfSight && !this.followTargetEvenIfNotSeen)) {
                // 距离太远或视线被阻挡时停止移动
                this.stopPathing();
            }
        }

        // 自定义攻击范围
        protected double getAttackReachSqr(LivingEntity p_25556_) {
            return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + p_25556_.getBbWidth());
        }

        private void stopPathing() {
            if (this.wasPathing) {
                this.mob.getNavigation().stop();
                this.wasPathing = false;
            }
        }
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
        RandomSource randomsource = p_34297_.getRandom();
        this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random spawn bonus", randomsource.triangle(0.0D, 0.11485000000000001D), AttributeModifier.Operation.MULTIPLY_BASE));
        if (randomsource.nextFloat() < 0.05F) {
            this.setLeftHanded(true);
        } else {
            this.setLeftHanded(false);
        }
        this.spawnType = p_34299_;

        float f = p_34298_.getSpecialMultiplier();
        this.setCanPickUpLoot(randomsource.nextFloat() < 0.55F * f);
        if (p_34300_ == null) {
            p_34300_ = new BlackSwordRaider.RaiderGroupData(getSpawnAsBabyOdds(randomsource), true);
        }

        if (p_34300_ instanceof BlackSwordRaider.RaiderGroupData) {

            this.setCanBreakDoors(this.supportsBreakDoorGoal() && randomsource.nextFloat() < f * 0.1F);
            this.populateDefaultEquipmentSlots(randomsource, p_34298_);
            this.populateDefaultEquipmentEnchantments(randomsource, p_34298_);
        }

        if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && randomsource.nextFloat() < 0.25F) {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(randomsource.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }

        this.handleAttributes(f);














        return p_34300_;
    }

    public static class RaiderGroupData implements SpawnGroupData {
        public final boolean isBaby;
        public final boolean canSpawnJockey;

        public RaiderGroupData(boolean p_34357_, boolean p_34358_) {
            this.isBaby = p_34357_;
            this.canSpawnJockey = p_34358_;
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PILLAGER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return SoundEvents.PLAYER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    protected boolean isSunBurnTick() {
        return false;
    }

    protected boolean isSunSensitive() {
        return false;
    }

    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    public boolean isBaby() {
        return false;
    }

    public boolean canBreakDoors() {
        return false;
    }

    protected boolean convertsInWater() {
        return false;
    }


    public void tick() {

        /**
        this.BlackSwordRaiderLifeRestored();
        */
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

        //this.convertToBlackSwordRaider();








        /**
        if (!this.level.isClientSide) {
            // 检查锁定状态
            if (initialTarget != null) {
                // 目标死亡或消失时重置
                if (!initialTarget.isAlive() || initialTarget.isRemoved()) {
                    resetLock();
                }
                // 8秒未攻击到（160 ticks = 8秒）
                else if (this.tickCount - lockStartTime > 160) {
                    resetLock();
                }
                // 目标有效时增加计时器
                else {
                    noAttackTimer++;
                }
            }
        }
        */







        this.BlackSwordRaiderDodgeProjectile();
        this.BlackSwordRaiderDodgeImmuneTimer();

        this.BlackSwordRaiderupdateSkillState();


        super.tick();
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


    /**
    public int noAttackTimer = 0; // 未攻击计时器
    private LivingEntity initialTarget; // 初始锁定的目标
    private int lockStartTime; // 锁定开始时间

    // 重置锁定状态
    public void resetLock() {
        initialTarget = null;
        noAttackTimer = 0;
        this.setTarget(null);
    }

    // 标记攻击行为
    public void markAttack() {
        noAttackTimer = 0;
        lockStartTime = this.tickCount; // 重置锁定开始时间
    }


    // 设置初始目标
    public void setInitialTarget(LivingEntity target) {
        this.initialTarget = target;
        this.noAttackTimer = 0;
        this.lockStartTime = this.tickCount; // 记录锁定开始时间
    }

    public LivingEntity getInitialTarget() {
        return initialTarget;
    }
    */




    /*
    public class DodgeProjectileGoal extends Goal {
        private final BlackSwordRaider entity;

        public DodgeProjectileGoal(BlackSwordRaider entity) {
            this.entity = entity;
        }

        @Override
        public boolean canUse() {
            return entity.dodgeCooldown <= 0; // 检查冷却
        }

        @Override
        public void tick() {
            // 获取10米内所有抛射物

            // 1. 先计算生物尺寸
            double entitySize = entity.getBbWidth() + entity.getBbHeight();

            // 2. 再乘以基础检测范围
            double detectionRange = 5 * entitySize;


            for (Projectile projectile : level.getEntitiesOfClass(
                    Projectile.class,
                    entity.getBoundingBox().inflate(detectionRange)
            )) {
                // 排除卡在方块中的抛射物
                if (projectile.isInWall() || projectile.horizontalCollision) continue;

                LivingEntity owner = projectile.getOwner() instanceof LivingEntity ?
                        (LivingEntity) projectile.getOwner() : null;

                // 检查抛射物是否瞄准生物
                if (owner != null && isProjectileTargeting(projectile, entity)) {
                    performDodge(projectile);
                    entity.dodgeCooldown = 20; // 1秒冷却
                    return;
                }
            }
        }

        private boolean isProjectileTargeting(Projectile projectile, LivingEntity target) {
            Vec3 toTarget = target.position().subtract(projectile.position());
            Vec3 projectileDir = projectile.getDeltaMovement();

            // 计算抛射物方向与目标方向的夹角
            double dot = toTarget.normalize().dot(projectileDir.normalize());
            return dot > 0.95; // 夹角小于18度
        }

        private void performDodge(Projectile projectile) {
            Vec3 projectileDir = projectile.getDeltaMovement().normalize();

            // 获取垂直方向（左右闪避）
            Vec3 dodgeDir = new Vec3(-projectileDir.z, 0, projectileDir.x);

            // 随机选择左右方向
            if (entity.getRandom().nextBoolean()) {
                dodgeDir = dodgeDir.scale(-1);
            }

            // 应用闪避速度（1.5格距离）
            Vec3 dodgeVelocity = dodgeDir.scale(DODGE_DISTANCE);
            entity.setDeltaMovement(entity.getDeltaMovement().add(dodgeVelocity));
        }
    }

    */


    public boolean hurt(DamageSource source, float amount) {

        // 检查是否在闪避免疫期间（0.2秒 = 4刻）
        if (dodgeImmuneTimer > 0) {
            // 闪避期间完全免疫伤害
            return false;
        }
        return super.hurt(source, amount);
    }


    private static final double DODGE_DISTANCE = 2;
    private int dodgeCooldown = 0;
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
    public class BlackSwordRaiderDodgeProjectileGoal extends Goal {
        private final BlackSwordRaider entity;

        public BlackSwordRaiderDodgeProjectileGoal(BlackSwordRaider entity) {
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
                        if (entity.getRandom().nextFloat() < 0.1f) {
                            entity.performDodgeWithImmunity(projectile); // 调用新的闪避方法
                            return;
                        }
                    } else if (!isBehind(entity.getDirection(), owner)) {
                        if (entity.getRandom().nextFloat() < 0.25f) {
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




    public boolean BlackSwordRaiderDodgeProjectile() {
        // 更新闪避状态
        if (dodgeCooldown > 0) {
            dodgeCooldown--;
        }

        return true;
    }

    public boolean BlackSwordRaiderDodgeImmuneTimer() {
        // 更新闪避免疫计时器
        if (dodgeImmuneTimer > 0) {
            dodgeImmuneTimer--;
        }

        return true;
    }



    public boolean convertToBlackSwordRaider() {
        if (this.level!= null) { // 确保只在服务端执行
            // 获取当前生物周围 10 格范围内的实体

            // 1. 先计算生物尺寸
            double entitySize = this.getBbWidth() + this.getBbHeight();

            // 2. 再乘以基础检测范围
            double detectionRange = 0.5 * entitySize;

            for (Entity entity : this.level.getEntities(this, this.getBoundingBox().inflate(detectionRange))){
                ResourceLocation entityTypeKey = EntityType.getKey(entity.getType());

                // 检查实体是否为 rottencreatures:immortal 或 rottencreatures:zap
                if (entityTypeKey != null &&
                        (entityTypeKey.toString().equals("rottencreatures:immortal") ||
                                entityTypeKey.toString().equals("rottencreatures:zap"))) {


                    // 创建一个新的自定义生物实例
                    BlackSwordRaider black_sword_raider = new BlackSwordRaider(ModEntities.BLACK_SWORD_RAIDER.get(), this.level);

                    // 设置新生物的位置和旋转角度
                    black_sword_raider.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());

                    // 如果需要，可以复制原生物的部分属性（如生命值、装备等）
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        black_sword_raider.setHealth(livingEntity.getHealth()); // 复制生命值
                        // 复制其他属性（如装备、状态效果等）




                        // 复制装备（盔甲、手持物品等）
                        for (EquipmentSlot slot : EquipmentSlot.values()) {
                            ItemStack stack = livingEntity.getItemBySlot(slot);
                            if (!stack.isEmpty()) {
                                black_sword_raider.setItemSlot(slot, stack.copy());
                            }
                        }



                        // 复制状态效果（药水效果）
                        for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                            black_sword_raider.addEffect(new MobEffectInstance(
                                    effect.getEffect(),
                                    effect.getDuration(),
                                    effect.getAmplifier(),
                                    effect.isAmbient(),
                                    effect.isVisible(),
                                    effect.showIcon()));
                        }


                        // 复制名称和自定义名称是否可见
                        if (livingEntity.hasCustomName()) {
                            black_sword_raider.setCustomName(livingEntity.getCustomName());
                            black_sword_raider.setCustomNameVisible(livingEntity.isCustomNameVisible());
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
                    this.level.addFreshEntity(black_sword_raider);
                }
            }
        }
        return true;
    }

    @Nullable
    private MobSpawnType spawnType;

    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        if (this.spawnType != null) {
            p_21484_.putString("forge:spawn_type", this.spawnType.name());
        }

        /**
        if (this.getPersistentData().contains("StruckByLightningTime")) {
            p_21484_.putLong("LightningMarkTime", this.getPersistentData().getLong("StruckByLightningTime"));
        }
        */

        this.BlackSwordRaideraddAdditionalSaveData(p_21484_);
    }

    public boolean BlackSwordRaideraddAdditionalSaveData(CompoundTag tag){
        tag.putInt("SkillCooldown", skillCooldown);
        tag.putString("SkillState", skillState.name());



        tag.putInt("DodgeCooldown", dodgeCooldown);
        tag.putInt("DodgeImmuneTimer", dodgeImmuneTimer);
        return true;
    }

    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        if (p_21450_.contains("forge:spawn_type")) {
            try {
                this.spawnType = MobSpawnType.valueOf(p_21450_.getString("forge:spawn_type"));
            } catch (Exception var4) {
                p_21450_.remove("forge:spawn_type");
            }
        }

        /**
        if (p_21450_.contains("LightningMarkTime")) {
            this.getPersistentData().putLong("StruckByLightningTime", p_21450_.getLong("LightningMarkTime"));
        }
        */


        this.BlackSwordRaiderreadAdditionalSaveData(p_21450_);
    }

    public boolean BlackSwordRaiderreadAdditionalSaveData(CompoundTag tag){
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


