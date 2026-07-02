package com.clouds_of_winter.shadow_of_killing.entity;

import com.google.common.collect.Multimap;
import com.clouds_of_winter.shadow_of_killing.init.ModItems;
import com.clouds_of_winter.shadow_of_killing.handlers.Faction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeHooks;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.enchantItem;

// "  /data merge entity @e[type=shadow_of_killing:wither_zombie,limit=1,sort=nearest] {PersistenceRequired:1}  "
public class EliteWitherZombieArcher extends AbstractSkeleton {
    /**
    private final RangedBowAttackGoal<EliteWitherZombieArcher> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);

    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            EliteWitherZombieArcher.this.setAggressive(false);
        }

        public void start() {
            super.start();
            EliteWitherZombieArcher.this.setAggressive(true);
        }

        protected double getAttackReachSqr(LivingEntity target) {
            double baseReach = super.getAttackReachSqr(target);
            if (!this.mob.isBaby())
            {
                double yDiff = target.getY() - this.mob.getY();
                if (yDiff >= 1.8 && yDiff <= 2.7) {
                    return baseReach;
                }
            }


            return baseReach;
        }

        protected void checkAndPerformAttack(LivingEntity target, double distanceSquared) {

            if (!this.mob.isBaby()){
                double attackReachSqr = this.getAttackReachSqr(target);
                double yDiff = target.getY() - this.mob.getY();
                if (yDiff >= 1.8 && yDiff <= 2.7) {
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



                super.checkAndPerformAttack(target, distanceSquared);

        }
    };
    */

    private final RangedBowAttackGoal<EliteWitherZombieArcher> bowGoal = new RangedBowAttackGoal<>(this, 1.0F, 20, 15.0F);

    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            EliteWitherZombieArcher.this.setAggressive(false);
        }

        public void start() {
            super.start();
            EliteWitherZombieArcher.this.setAggressive(true);
        }

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
            }else{
                double mobHeight = this.mob.getBbHeight();
                double minY = 0.5 * mobHeight;
                double maxY = 1.125 * mobHeight;
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
            } else{
                double mobHeight = this.mob.getBbHeight();
                double minY = 0.5 * mobHeight;
                double maxY = 1.125 * mobHeight;
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
    };

    public void WitherZombiereassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem));
            if (itemstack.is(Items.BOW)) {
                int i = 20;
                if (this.level.getDifficulty() != Difficulty.HARD) {
                    i = 40;
                }

                this.bowGoal.setMinAttackInterval(i);
                this.goalSelector.addGoal(4, this.bowGoal);
            } else {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }
        }
    }

    @Override
    public void reassessWeaponGoal() {}

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_32146_, DifficultyInstance p_32147_, MobSpawnType p_32148_, @Nullable SpawnGroupData p_32149_, @Nullable CompoundTag p_32150_) {
        p_32149_ = super.finalizeSpawn(p_32146_, p_32147_, p_32148_, p_32149_, p_32150_);
        this.WitherZombiereassessWeaponGoal();
        return p_32149_;
    }


    // NBT数据加载 - 游戏加载/区块加载时调用

    public void readAdditionalSaveData(CompoundTag p_32152_) {
        super.readAdditionalSaveData(p_32152_);
        this.WitherZombiereassessWeaponGoal();




        this.WitherZombiereadAdditionalSaveData(p_32152_);
    }


    // NBT数据保存 - 游戏退出/区块卸载时调用
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        this.WitherZombieaddAdditionalSaveData(p_21484_);
    }

    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack);

        if (!this.level.isClientSide) {
            this.WitherZombiereassessWeaponGoal();
        }

    }





    public EliteWitherZombieArcher(EntityType<? extends EliteWitherZombieArcher> p_32278_, Level p_32279_) {
        super(p_32278_, p_32279_);
        this.xpReward = 55;
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.WitherZombiereassessWeaponGoal();

    }

    private static final String STORED_BOW_TAG = "StoredBow";

    // 存储弓的临时容器（非物品栏槽位）
    private ItemStack storedBow = ItemStack.EMPTY;

    /**
    private int attackTick = 0;
    */

    /**
    private int tickCounter = 0;
    */

    // 用于记录上次被攻击的时间
    private long lastAttackedTime = 0;
    private boolean isHealing2 = false;

    private long lastAttackTime = 0;
    // 标记是否正在恢复生命值
    private boolean isHealing = false;

    protected boolean EliteWitherZombiedoHurtTarget() {
        long currentTime = level.getGameTime();
        if (isHealing && currentTime - lastAttackTime < 41) {
            // 2秒内（游戏中每秒20刻，2秒即40刻）
            if (this.isAlive()){
                this.heal(0.15F);
            }
        } else {
            isHealing = false;
        }

        return true;
    }

    protected boolean EliteWitherZombiehurt() {
        long currentTime2 = level.getGameTime();
        if (isHealing2 && currentTime2 - lastAttackedTime < 61) {
            if ((currentTime2 - lastAttackedTime) % 3 == 0) {
                if (this.isAlive()){
                    this.heal(0.2F);
                }
                // 2秒内（游戏中每秒20刻，2秒即40刻）
            }

        } else {
            isHealing2 = false;
        }

        return true;
    }//3//4

    protected boolean EliteWitherZombieLifeRestored() {

        /**
        if (!this.isAggressive() && !this.isDeadOrDying() && this.getHealth() < this.getMaxHealth()) {
            tickCounter++;
            if (tickCounter >= 5) {
                // 回复 0.1 生命值
                tickCounter = 0;
                this.setHealth(this.getHealth() + 0.1F);
                if (this.getHealth() > this.getMaxHealth()) {
                    this.setHealth(this.getMaxHealth());
                }
            }
        }
        */

        if (this.isAlive() && this.tickCount % 5 == 0) {
            if (!this.isAggressive()){
                this.heal(0.1F); // 恢复0.1点生命值
            }

        }

        return true;
    }


    public void tick() {

        /**
        this.EliteWitherZombieLifeRestored();
        */



        super.tick();
    }


    public void aiStep() {
        this.EliteWitherZombiedoHurtTarget();
        this.EliteWitherZombiehurt();


        this.EliteWitherZombieLifeRestored();

        /**
        if (!(this.getMainHandItem().getItem() == Items.BOW) && !(this.getOffhandItem().getItem() == Items.BOW) && !this.isDeadOrDying() && !this.isBaby() && this.getTarget()!= null) {
            double distanceX = this.getTarget().getX() - this.getX();
            double distanceY = this.getTarget().getY() - this.getY();
            double distanceZ = this.getTarget().getZ() - this.getZ();
            double horizontalDistanceSqr = distanceX * distanceX + distanceZ * distanceZ;
            double verticalDistance = Math.abs(distanceY);
            if (horizontalDistanceSqr <= 2.66 && (verticalDistance >= 1.8 && verticalDistance <= 2.7)) {
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


        this.WitherZombieaiStep();
        super.aiStep();
    }

    /*
    private boolean isEnemy(LivingEntity entity) {
        // 根据实际情况判断一个实体是否为敌人
        return entity!= this && entity.getTeam()!= this.getTeam();
    }
    */

    public boolean doHurtTarget(Entity p_32281_) {
        boolean flag = super.doHurtTarget(p_32281_);
        if (p_32281_ instanceof LivingEntity) {
            ((LivingEntity)p_32281_).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
        }
        if (flag) {
            lastAttackTime = level.getGameTime();
            isHealing = true;
        }
        /*
        if ($$ && $ instanceof LivingEntity) {
            ItemStack mainHandItem = this.getMainHandItem();
            if (mainHandItem.getItem() instanceof TieredItem){
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                if (attackDamage > 0) {
                    Direction facingDirection = this.getDirection();
                    for (Entity nearbyEntity : this.level.getEntities(this, this.getBoundingBox().inflate(2, 1, 2))) {
                        if (nearbyEntity!= $ && nearbyEntity instanceof LivingEntity &&!(nearbyEntity instanceof EliteWitherZombieArcher)) {
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
    private boolean isEntityInFront(Entity entity, Direction facingDirection) {
        // 获取目标相对于当前实体的方向向量
        Direction targetDirection = Direction.getNearest(
                entity.getX() - this.getX(),
                entity.getY() - this.getY(),
                entity.getZ() - this.getZ()
        );

        // 判断方向向量是否与当前朝向相同（即在前方）
        return targetDirection == facingDirection;
    }
    */

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
        if (entity instanceof EliteWitherZombieArcher livingentity) {
            return shouldAttackWitherZombie(livingentity);
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

    private boolean shouldAttackWitherZombie(EliteWitherZombieArcher entity) {


        // 双重判断：阵营关系 + 攻击历史
        return isFactionEnemy(entity) || hasCombatHistory(entity);
    }

    private boolean isMobHostile(Mob mob) {
        if (mob.getTarget() == this) return true;

        return mob.getLastHurtByMob() == this ||
                mob.getLastHurtMob() == this;
    }

    // 新增战斗历史验证
    private boolean hasCombatHistory(EliteWitherZombieArcher entity) {
        if (entity.getTarget() == this) return true;

        // 最近攻击关系双向验证
        return entity.getLastHurtByMob() == this ||  // 该实体最近被本实体攻击
                entity.getLastHurtMob() == this;    // 本实体最近被该实体攻击
    }


    // 修改后的阵营判断（增加安全防护）
    private boolean isFactionEnemy(EliteWitherZombieArcher mob) {
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
        return our.isEnemy(their)/** || mob.isPermanentlyHostile()*/;
    }


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


    /*
    // 新增永久敌对标记
    private boolean isPermanentlyHostile;

    public void setPermanentlyHostile(boolean hostile) {
        this.isPermanentlyHostile = hostile;
    }

    public boolean isPermanentlyHostile() {
        return this.isPermanentlyHostile;
    }

     */

    public Faction faction;

    public Faction getFaction() {
        return faction;
    }

    public boolean hurt(DamageSource source, float amount) {
        boolean wasHurt = super.hurt(source, amount);
        if (source == DamageSource.WITHER)
            return false;



        if (wasHurt) {
            if (!source.isFire()) {
                this.spawnLingeringCloudup();
                this.spawnLingeringCloudmiddle();
                this.spawnLingeringClouddown();
            }
        }




        if (wasHurt) {
            if (!source.isFire()) {
                lastAttackedTime = level.getGameTime();
                isHealing2 = true;
            }
        }



        /*
        if ($.getEntity() instanceof EliteWitherZombieArcher) {
            // 当受到同类攻击时标记为永久敌对
            if (!this.isPermanentlyHostile) {
                this.setPermanentlyHostile(true);
                // 可选：同步到其他同类实体
            }
        }

         */
        return wasHurt;
    }

    protected boolean isSunBurnTick() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractSkeleton.createAttributes()
                .add(Attributes.MAX_HEALTH, 180.0D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    public boolean canBeAffected(MobEffectInstance mobeffectinstance) {
        return mobeffectinstance.getEffect() != MobEffects.WITHER && super.canBeAffected(mobeffectinstance);
    }

    protected void registerGoals() {
        super.registerGoals();
        // 第一步：移除原有的 HurtByTargetGoal
        this.targetSelector.getAvailableGoals().removeIf(goal -> {
            return goal.getGoal() instanceof HurtByTargetGoal;
        });
        // 第二步：添加新的 HurtByTargetGoal 并设置警报
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, EliteWitherZombieArcher.class).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Wolf.class, true));
        this.RemoveRestrictSunGoal();
        this.RemoveFleeSunGoal();
        this.RemoveAvoidEntityGoals();
    }

    public boolean RemoveRestrictSunGoal() {
        this.goalSelector.getAvailableGoals().removeIf(w -> w.getGoal() instanceof RestrictSunGoal);
        return true;
    }

    public boolean RemoveFleeSunGoal() {
        this.goalSelector.getAvailableGoals().removeIf(w -> w.getGoal() instanceof FleeSunGoal);
        return true;
    }

    public boolean RemoveAvoidEntityGoals() {
        this.goalSelector.getAvailableGoals().removeIf(w -> w.getGoal() instanceof AvoidEntityGoal
        );
        return true;
    }




    protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
        this.meleeStack = new ItemStack(ModItems.STARDEMON_BATTLEAXE.get());
        this.setItemSlot(EquipmentSlot.MAINHAND, this.bowStack.copy());

        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
    }

    protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
        AbstractArrow abstractarrow = super.getArrow(p_32156_, p_32157_);
        abstractarrow.setSecondsOnFire(100);
        abstractarrow.setEnchantmentEffectsFromEntity(this, 4.0F);
        if (abstractarrow instanceof Arrow) {
            lastAttackTime = level.getGameTime();
            isHealing = true;
        }
        return abstractarrow;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_32309_) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }


    public void die(DamageSource source) {
        super.die(source);
        this.spawnLingeringCloudup();
        this.spawnLingeringCloudmiddle();
        this.spawnLingeringClouddown();

        this.WitherZombiedie(source);
    }

    private void spawnLingeringCloudup() {
        double mobHeight = this.getBbHeight();
        double yOffset = mobHeight * 1;
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY() + yOffset, this.getZ());
        areaeffectcloud.setRadius(3);
        areaeffectcloud.setRadiusOnUse(-0.5F);
        areaeffectcloud.setWaitTime(10);
        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 160));
        this.level.addFreshEntity(areaeffectcloud);
    }

    private void spawnLingeringCloudmiddle() {
        double mobHeight = this.getBbHeight();
        double yOffset = mobHeight * 0.5;
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY() + yOffset, this.getZ());
        areaeffectcloud.setRadius(3);
        areaeffectcloud.setRadiusOnUse(-0.5F);
        areaeffectcloud.setWaitTime(10);
        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 160));
        this.level.addFreshEntity(areaeffectcloud);
    }

    private void spawnLingeringClouddown() {
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
        areaeffectcloud.setRadius(3);
        areaeffectcloud.setRadiusOnUse(-0.5F);
        areaeffectcloud.setWaitTime(10);
        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 160));
        this.level.addFreshEntity(areaeffectcloud);
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.875F; // 示例：眼睛高度为模型高度的90%
    }

    // 控制物品拾取逻辑
    // 控制生物是否能装备/拾取特定物品
    // 重写拾取逻辑




    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getItem();
        EquipmentSlot slot = Mob.getEquipmentSlotForItem(stack);

        if (slot == EquipmentSlot.MAINHAND){
            ItemStack mainHand = this.getMainHandItem();
            if (!mainHand.isEmpty()) {
                double currentDamage = calculateAttackDamage(mainHand);
                double newDamage = calculateAttackDamage(stack);
                // 如果当前武器的伤害高于要捡起的剑，则放弃拾取
                if (currentDamage > newDamage) {
                    return; // 不捡起物品
                }
            }
        }

        if (slot == EquipmentSlot.MAINHAND) {
            ItemStack mainHand = getMainHandItem();
            if (mainHand.getItem() == Items.BOW && stack.getItem() != Items.BOW) {
                return; // 不拾取
            }
        }

        super.pickUpItem(itemEntity);
    }

    /**
     * 计算物品主手攻击伤害总和（基于属性修饰符）
     */
    private double calculateAttackDamage(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
        return modifiers.get(Attributes.ATTACK_DAMAGE).stream()
                .mapToDouble(AttributeModifier::getAmount)
                .sum();
    }

    /**
    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack newStack = itemEntity.getItem();
        EquipmentSlot slot = Mob.getEquipmentSlotForItem(newStack);
        ItemStack currentMainHand = this.getItemBySlot(EquipmentSlot.MAINHAND);


        // 仅处理将要放入主手的物品
        if (slot == EquipmentSlot.MAINHAND) {
            // 当前主手持有弓
            if (currentMainHand.getItem() == Items.BOW) {
                // 新物品必须是弓，否则阻止拾取
                if (newStack.getItem() != Items.BOW) {
                    return;
                }

                // 新物品是弓
                if (newStack.hasTag()) {
                    // 新弓有NBT → 允许替换
                    super.pickUpItem(itemEntity);
                    return;
                } else {
                    // 新弓无NBT：仅当当前弓也无NBT且耐久更低时才允许替换
                    if (!currentMainHand.hasTag()) {
                        int currentDamage = currentMainHand.getDamageValue(); // 当前弓已损耗耐久
                        int newDamage = newStack.getDamageValue();            // 新弓已损耗耐久
                        if (currentDamage > newDamage) {
                            // 当前弓更破损，新弓更好 → 允许替换
                            super.pickUpItem(itemEntity);
                            return;
                        }
                    }
                    // 否则阻止拾取
                    return;
                }
            }
            // 当前主手不是弓 → 允许正常拾取任何主手物品（包括弓），交给父类处理
        }




        // 只处理主手物品
        if (slot == EquipmentSlot.MAINHAND) {
            // 当前主手持有斧
            if (currentMainHand.getItem() instanceof AxeItem) {
                // 新物品不是斧 → 阻止拾取
                if (!(newStack.getItem() instanceof AxeItem)) {
                    return;
                }

                double currentDamage = calculateAttackDamage(currentMainHand);
                double newDamage = calculateAttackDamage(newStack);

                if (newDamage > currentDamage) {
                    // 伤害更高 → 允许拾取
                    super.pickUpItem(itemEntity);
                    return;
                } else if (newDamage == currentDamage) {
                    // 伤害相等 → 优先选有NBT的
                    if (newStack.hasTag()) {
                        super.pickUpItem(itemEntity);
                        return;
                    } else {
                        // 都无NBT，比较耐久度（新斧更耐用才替换）
                        int currentDura = currentMainHand.getDamageValue(); // 当前已损耗耐久
                        int newDura = newStack.getDamageValue();            // 新物品已损耗耐久
                        if (newDura < currentDura) { // 新斧更耐用（损耗更少）
                            super.pickUpItem(itemEntity);
                            return;
                        }
                        // 否则阻止
                        return;
                    }
                } else {
                    // 伤害更低 → 阻止
                    return;
                }
            }
            // 当前主手不是斧（如空手、剑等） → 允许拾取任何主手物品，交由父类处理
        }


        // 非主手物品（如头盔、靴子等）或主手非斧的情况，使用默认行为
        super.pickUpItem(itemEntity);
    }

    //计算物品主手攻击伤害总和（基于属性修饰符）
    private double calculateAttackDamage(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
        return modifiers.get(Attributes.ATTACK_DAMAGE).stream()
                .mapToDouble(AttributeModifier::getAmount)
                .sum();
    }
    */

    /**
    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack itemstack = itemEntity.getItem();
        EquipmentSlot slot = Mob.getEquipmentSlotForItem(itemstack);
        ItemStack mainhandItem = this.getItemBySlot(EquipmentSlot.MAINHAND);

        // 只处理主手槽位
        if (slot == EquipmentSlot.MAINHAND) {
            // 当前主手是弓的情况
            if (mainhandItem.getItem() == Items.BOW) {
                if (itemstack.getItem() == Items.BOW) {
                    int currentPower = EnchantmentHelper.getItemEnchantmentLevel(
                            Enchantments.POWER_ARROWS, mainhandItem);
                    int newPower = EnchantmentHelper.getItemEnchantmentLevel(
                            Enchantments.POWER_ARROWS, itemstack);

                    // 只有新弓的力量附魔等级更高时才替换
                    if (newPower > currentPower) {
                        this.spawnAtLocation(mainhandItem); // 丢弃旧弓
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.copy());
                        itemEntity.discard();
                        return;
                    }
                }
                // 非弓物品或附魔不足，阻止拾取
                return;
            }
        }
        // 非弓物品或非主手槽位使用默认逻辑




        if (slot == EquipmentSlot.MAINHAND) {
            // 当前主手是斧的情况
            if (mainhandItem.getItem() instanceof AxeItem) {
                // 新物品也是斧
                if (itemstack.getItem() instanceof AxeItem) {
                    // 计算攻击伤害
                    double currentDamage = calculateAttackDamage(mainhandItem);
                    double newDamage = calculateAttackDamage(itemstack);

                    // 计算锋利附魔等级
                    int currentSharpness = EnchantmentHelper.getItemEnchantmentLevel(
                            Enchantments.SHARPNESS, mainhandItem);
                    int newSharpness = EnchantmentHelper.getItemEnchantmentLevel(
                            Enchantments.SHARPNESS, itemstack);

                    // 判断是否应该替换
                    if (shouldReplaceAxe(currentDamage, newDamage, currentSharpness, newSharpness)) {
                        this.spawnAtLocation(mainhandItem); // 丢弃旧斧头
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.copy());
                        itemEntity.discard();
                        return;
                    }
                }
                // 非斧物品或条件不满足，阻止拾取
                return;
            }
        }

        super.pickUpItem(itemEntity);
    }

    // 计算武器的攻击伤害（基础+附魔）
    private double calculateAttackDamage(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = stack.getAttributeModifiers(
                EquipmentSlot.MAINHAND);

        return modifiers.get(Attributes.ATTACK_DAMAGE).stream()
                .mapToDouble(AttributeModifier::getAmount)
                .sum();
    }

    // 判断是否应该替换斧头
    private boolean shouldReplaceAxe(double currentDamage, double newDamage,
                                     int currentSharpness, int newSharpness) {
        // 1. 新斧头伤害更高
        if (newDamage > currentDamage) {
            return true;
        }
        // 2. 伤害相同但锋利等级更高
        else if (newDamage == currentDamage && newSharpness > currentSharpness) {
            return true;
        }
        return false;
    }
    */


    //我的世界1.19.2forge模组开发protected float getStandingEyeHeight(Pose $, EntityDimensions $$) {return 2.1F * (随机生物模型大小) ;}


    // /summon shadow_of_killing:wither_zombie ~ ~ ~ {Attributes:[{Name:"generic.attack_damage",Base:5},{Name:"generic.max_health",Base:20}]}




































/**
Minecraft 1.19.2 Forge模组开发自定义骷髅。这个生物初始会携带一把远程武器（以弓为例），并且储存有一把近战武器（如游戏中剑、斧、镐、铲、锄。
这些类型的物品，有武器的回答以铁剑为例）或者无武器的徒手状态，生物初始情况下手持弓。当手持弓的时候自定义生物与攻击目标在2格范围内时将手里的弓换
成另一把自身储存的近战武器（例如铁剑/空手），在拾取范围内（与游戏原版生物的拾取范围一致，如果有区别则以骷髅拾取范围为例）近战模式下生物可以选择
比自己手中伤害高的物品进行替换（比如范围内有一把原伤害为5但是算上附魔伤害为7的物品，这件物品比该生物手中伤害为6的铁剑高，则拾取范围内伤害为7的
那件物品，将手中原有的物品掉落），完成替换将会把这个生物个体以前的近战武器储存更新为当前手中的近战武器。杀完攻击目标时或攻击目标离开了2格范围内
时，将储存的近战武器换回进入近战模式前持有的弓（包括持有的弓的属性：附魔、耐久、命名等。从远程模式变为近战时，近战武器同理）。在生物死亡时，除
了掉设定掉落物外，无论死亡时是远程还是近战模式，都会有概率掉落其在当前模式手持的物品和另一模式所最后一次储存的物品。（这里说的最后一次储存的物品
是指最后一次储存的远程或近战物品，我希望的是这两个物品可以有概率同时掉落。例如：生物在远程模式时死亡时，有概率掉落的武器除了手中的弓以外也同样有
概率掉落最后一次储存的近战武器，在近战模式时死亡时，有概率掉落的武器除了手中的近战武器以外也同样有概率掉落最后一次储存的远程武器。如果近战模式或
远程模式没有物品则没有概率掉落，你总不能让他掉落一个空手出来...）请问实现这个生物的代码是什么？
*/




/*

 default:

    private ItemStack bowStack = ItemStack.EMPTY;
    private ItemStack meleeStack = ItemStack.EMPTY;
    private boolean isMeleeMode = false;
*/

    // 储存的武器（完整NBT，包括附魔、耐久、自定义名称等）
    public ItemStack bowStack = ItemStack.EMPTY;      // 当前持有的弓
    public ItemStack meleeStack = ItemStack.EMPTY;    // 当前持有的近战武器（可能为空）
    private boolean isMeleeMode = false;

    /** 切换到近战模式：保存当前弓，切换为储存的近战武器 */
    private void switchToMelee() {
        if (!this.isMeleeMode) {
            // 保存当前弓（包含附魔、耐久等所有NBT）
            this.bowStack = this.getMainHandItem().copy();
            // 切换为近战武器（可能为空）
            this.setItemSlot(EquipmentSlot.MAINHAND, this.meleeStack.copy());
            this.isMeleeMode = true;
        }
    }

    /** 切换到远程模式：保存当前近战武器，切换回储存的弓 */
    private void switchToRanged() {
        if (this.isMeleeMode) {
            // 保存当前近战武器（可能为空）
            this.meleeStack = this.getMainHandItem().copy();
            // 切换回弓
            this.setItemSlot(EquipmentSlot.MAINHAND, this.bowStack.copy());
            this.isMeleeMode = false;
        }
    }

    public void WitherZombieaiStep() {
        if (this.isAlive()) {
            LivingEntity target = this.getTarget();
            if (target != null) {
                double distSq = this.distanceToSqr(target);
                if (distSq <= this.getBbWidth() * 32) { // 距离 ≤ 2 格
                    if (!this.isMeleeMode && this.getMainHandItem().getItem() == Items.BOW) {
                        this.switchToMelee();
                    }
                } else {
                    if (this.isMeleeMode) {
                        this.switchToRanged(); // 目标远离，切回远程
                    }
                }
            } else {
                // 目标死亡或丢失，若处于近战模式则切回远程
                if (this.isMeleeMode) {
                    this.switchToRanged();
                }
            }
        }
    }

    public void WitherZombiedie(DamageSource source) {
        // 先调用父类，生成原有掉落（骨头、箭、盔甲），手持物品概率为0所以不会掉
        if (this.level instanceof ServerLevel) {
            if (this.shouldDropLoot() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                Entity killer = source.getEntity();
                if (killer instanceof Player) {
                    this.handleExtraDrops(source);
                }
            }
        }
    }


    // ========== 持久化 ==========
    public void WitherZombieaddAdditionalSaveData(CompoundTag tag) {

        if (!this.bowStack.isEmpty()) {
            tag.put("BowStack", this.bowStack.save(new CompoundTag()));
        }
        if (!this.meleeStack.isEmpty()) {
            tag.put("MeleeStack", this.meleeStack.save(new CompoundTag()));
        }
        tag.putBoolean("IsMeleeMode", this.isMeleeMode);
    }

    public void WitherZombiereadAdditionalSaveData(CompoundTag tag) {
        this.bowStack = tag.contains("BowStack") ? ItemStack.of(tag.getCompound("BowStack")) : ItemStack.EMPTY;
        this.meleeStack = tag.contains("MeleeStack") ? ItemStack.of(tag.getCompound("MeleeStack")) : ItemStack.EMPTY;
        this.isMeleeMode = tag.getBoolean("IsMeleeMode");
    }

    /** 处理额外掉落：当前手持武器 + 另一模式储存的武器，各自独立概率 */
    private void handleExtraDrops(DamageSource source) {
        int looting = ForgeHooks.getLootingLevel(this, source.getEntity(), source);

        // 另一模式储存的物品
        ItemStack otherWeapon = isMeleeMode ? this.bowStack.copy() : this.meleeStack.copy();

        // 独立判断掉落概率（基础8.5%，每级抢夺+1%）
        boolean dropOther = shouldDrop(looting, 0.085f) && !otherWeapon.isEmpty() && !EnchantmentHelper.hasVanishingCurse(otherWeapon);

        if (dropOther) {
            this.applyRandomDamage(otherWeapon);
            this.spawnAtLocation(otherWeapon, 0.0f);
        }
    }

    /** 概率计算：基础概率 + 抢夺等级 * 1%，最大100% */
    private boolean shouldDrop(int looting, float baseChance) {
        float chance = Math.min(baseChance + looting * 0.01f, 1.0f);
        return this.random.nextFloat() < chance;
    }

    private void applyRandomDamage(ItemStack stack) {
        if (!stack.isDamageableItem()) return; // 不可损坏的物品（如弓？弓可损坏）直接返回

        int maxDamage = stack.getMaxDamage();
        // 原版算法：剩余耐久 = 最大耐久 - (0 到 随机值之间的随机数)
        // 其中随机值 = 1 + random.nextInt( max( maxDamage-3, 1 ) )
        // 为简化且更均匀，也可使用更直接的随机剩余耐久（例如 1 到 maxDamage 之间随机）
        int remainingDurability = 1 + random.nextInt(Math.max(maxDamage - 1, 1)); // 剩余 1 ~ maxDamage
        // 或者完全复刻原版：
        // int remainingDurability = maxDamage - random.nextInt(1 + random.nextInt(Math.max(maxDamage - 3, 1)));
        stack.setDamageValue(maxDamage - remainingDurability);
    }
}
