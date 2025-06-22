package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.effects.BxPEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/7
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class LivingTickEvent {
    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingTickEvent event){
        LivingEntity living = event.getEntity();
        Level world = living.level();
        if(world instanceof ServerLevel sl){
            MinecraftServer server = sl.getServer();
            if(living instanceof Warden && server.getTickCount() % 100 == 0){
                living.heal(0.05F * (living.getMaxHealth() - living.getHealth()));
            }
            MobEffectInstance instance = living.getEffect(BxPEffects.Debilitated.get());
            if(instance != null && server.getTickCount() % 100 == 0){
                living.hurt(world.damageSources().outOfBorder(),living.getMaxHealth() * (1 + instance.getAmplifier()) * 0.02F);
            }
            if(server.getTickCount() % 40 == 0){
                int guisuo_count = living.getPersistentData().getInt("guisuo_count");
                if(guisuo_count > 0){
                    living.getPersistentData().putInt("guisuo_count", guisuo_count - 1);
                }
            }
        }
    }
}
