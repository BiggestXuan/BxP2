package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
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
        if(!world.isClientSide){
            if(living instanceof Warden && world.getDayTime() % 100 == 0){
                living.heal(0.05F * (living.getMaxHealth() - living.getHealth()));
            }
        }
    }
}
