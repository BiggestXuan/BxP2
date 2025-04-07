package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/7
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class LivingHurtEvent {
    @SubscribeEvent
    public static void onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        Level world = entity.level();
        DamageSource sources = event.getSource();
        if(!world.isClientSide()) {
            Entity attacker = sources.getDirectEntity();
            if(entity instanceof Warden){
                if(!(attacker instanceof Player)){
                    event.setAmount(event.getAmount() * 0.6F);
                }
            }
            if(attacker instanceof Warden warden){
                warden.heal(event.getAmount() * 0.4F);
            }
        }
    }
}
