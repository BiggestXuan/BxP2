package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.effects.BxPEffects;
import biggestxuan.bxp2.integration.Mekanism.MekUtils;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.utils.Utils;
import biggestxuan.bxp2.utils.WorldUtils;
import com.brandon3055.draconicevolution.init.DEDamage;
import com.jerotes.jerotesvillage.entity.Animal.DeepBatEntity;
import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.system.MathUtil;

import java.util.Collection;
import java.util.UUID;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class LivingCombatEvent {
    @SubscribeEvent
    public static void PlayerAttackEvent(LivingHurtEvent event){
        var source = event.getEntity();
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        Level world = entity.level();
        DamageSource sources = event.getSource();
        float amount = event.getAmount();
        if(!world.isClientSide()) {
            MobEffectInstance instance = entity.getEffect(BxPEffects.Vulnerability.get());
            Entity attacker = sources.getDirectEntity();
            if(entity instanceof Warden){
                if(!(attacker instanceof Player)){
                    double rate = Config.difficulty == 3 ? 0.36F : 0.6F;
                    amount *= (float) rate;
                }
            }
            if(attacker instanceof Warden warden){
                warden.heal(amount * 0.4F);
                if(Config.difficulty == 3){
                    warden.heal(amount * 0.4F);
                }
            }
            double r = BxP2.devMode ? 1 : 0.03;
            if(attacker instanceof Player){
                if(Config.difficulty == 1){
                    if(Utils.isRandom(r)){
                        entity.addEffect(new MobEffectInstance(BxPEffects.Vulnerability.get()));
                    }
                }
            }
            if(entity instanceof Player player){
                if(Config.difficulty == 3){
                    if(Utils.isRandom(r)){
                        entity.addEffect(new MobEffectInstance(BxPEffects.Vulnerability.get()));
                        entity.addEffect(new MobEffectInstance(BxPEffects.Debilitated.get()));
                    }
                }
                if(sources.is(DEDamage.CHAOTIC_ARROW) || sources.is(DEDamage.CHAOTIC_ARROW_SPOOF) || sources.is(DEDamage.CHAOS_IMPLOSION) || sources.is(DEDamage.GUARDIAN) || sources.is(DEDamage.GUARDIAN_LASER) || sources.is(DEDamage.GUARDIAN_PROJECTILE)){
                    int count = MekUtils.getPlayerChaosProtectAmount(player);
                    if(count >= 1){
                        float rate = 1;
                        switch (count){
                            case 1 -> rate = 0.75F;
                            case 2 -> rate = 0.45F;
                            case 3 -> rate = 0.3F;
                            case 4 -> rate = 0.05F;
                        }
                        amount *= rate;
                    }
                }
            }
            if(instance != null){
                for (int i = 0; i <= instance.getAmplifier(); i++) {
                    amount *= 1.2F;
                }
            }
            event.setAmount(amount);
        }
    }

    @SubscribeEvent
    public static void healEvent(LivingHealEvent event){
        LivingEntity entity = event.getEntity();
        if(entity.level().isClientSide) return;
        float amount = event.getAmount();
        if(entity instanceof Player player){
            if(Config.difficulty == 1){
                amount *= (float) Config.EASY_HEALTH_RATE;
            }
            if(Config.difficulty == 2){
                amount *= (float) Config.NORMAL_HEALTH_RATE;
            }
            if(Config.difficulty == 3){
                amount *= (float) Config.HARD_HEALTH_RATE;
            }
        }
        MobEffectInstance instance = entity.getEffect(BxPEffects.Debilitated.get());
        if(instance != null){
            int level = instance.getAmplifier();
            for (int i = 0; i <= level; i++) {
                amount *= 0.67F;
            }
        }
        event.setAmount(amount);
    }
    private static final UUID MODIFY_HEALTH_ID = UUID.fromString("a80fcb74-82f3-4e06-8df7-7d8031e8976e");
    private static final UUID MODIFY_ATTACK_ID = UUID.fromString("ec77a354-5dab-4ec4-8bde-496ba2b72860");
    private static final String MODIFY_HEALTH_NAME = "BxP2.HealthModifier";
    private static final String MODIFY_ATTACK_NAME = "BxP2.AttackModifier";
    @SubscribeEvent
    public static void spawnEvent(MobSpawnEvent.FinalizeSpawn event){
        Mob mob = event.getEntity();
        if(event.getLevel().isClientSide()) return;
        if(mob.level() instanceof ServerLevel sl){
            if(Config.difficulty == 1){return;}
            if(mob instanceof WitherBoss) return;
            if(mob instanceof DeepBatEntity bat){
                bat.setNoAi(true);
            }
            ServerLevel world = sl.getServer().overworld();
            double rate = 0;
            switch (Config.difficulty){
                case 1 -> rate = Config.EASY_ENHANCEMENT_RATE;
                case 2 -> rate = Config.NORMAL_ENHANCEMENT_RATE;
                case 3 -> rate = Config.HARD_ENHANCEMENT_RATE;
            }
            double day = 1d * world.getDayTime() / 24000 * rate;
            AttributeInstance health = mob.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance attack = mob.getAttribute(Attributes.ATTACK_DAMAGE);
            if(health != null){
                AttributeModifier modifier = health.getModifier(MODIFY_HEALTH_ID);
                if(modifier != null){
                    health.removeModifier(MODIFY_HEALTH_ID);
                }
                health.addPermanentModifier(new AttributeModifier(MODIFY_HEALTH_ID,MODIFY_HEALTH_NAME,day*0.01, AttributeModifier.Operation.MULTIPLY_BASE));
            }
            if(attack != null){
                AttributeModifier modifier = attack.getModifier(MODIFY_ATTACK_ID);
                if(modifier != null){
                    attack.removeModifier(MODIFY_ATTACK_ID);
                }
                attack.addPermanentModifier(new AttributeModifier(MODIFY_ATTACK_ID,MODIFY_ATTACK_NAME,day*0.01, AttributeModifier.Operation.MULTIPLY_BASE));
            }
            mob.heal(mob.getMaxHealth());
        }
    }

    @SubscribeEvent
    public static void deathDrop(LivingDropsEvent event){
        LivingEntity livingEntity = event.getEntity();
        if(!livingEntity.level().isClientSide){
            ItemStack bloodSac = BxPItems.BLOOD_SAC.get().getDefaultInstance();
            Collection<ItemEntity> drops = event.getDrops();
            if(livingEntity instanceof Animal){
                if(Config.difficulty == 1){
                    bloodSac.setCount(2);
                }
                if(Config.difficulty == 2 && Utils.isRandom(0.25)){
                    bloodSac.setCount(2);
                }
                drops.add(WorldUtils.getItemEntity(livingEntity,bloodSac));
                if (Config.difficulty != 3 && Utils.isRandom(0.5)) {
                    drops.add(WorldUtils.getItemEntity(livingEntity,bloodSac));
                }
            }
            if(livingEntity instanceof Monster && Config.difficulty != 3 && event.getSource().getDirectEntity() instanceof Player player){
                if(VampirismAPI.getVampirePlayer(player).isPresent()){
                    drops.add(WorldUtils.getItemEntity(livingEntity,bloodSac));
                }
            }
        }

    }
}
