package biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.MekaArmorModifiers;

import biggestxuan.bxp2.BxP2;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/6/2
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class MekaEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void damageEvent(LivingAttackEvent event){
        if(event.getEntity() instanceof Player player && !player.level().isClientSide && MekaTinkerUtils.isAllMekaArmor(player)){
            if(byPassDamage(player.level().registryAccess(),event.getSource())){
                if(MekaTinkerUtils.canPreventDamage(player,event.getSource(),event.getAmount())){
                    event.setCanceled(true);
                }
            }
        }
    }

    //@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurtEvent(LivingHurtEvent event){
        if(event.getEntity() instanceof Player player && !player.level().isClientSide && MekaTinkerUtils.isAllMekaArmor(player)){
            if(byPassDamage(player.level().registryAccess(),event.getSource())){
                if(MekaTinkerUtils.canPreventDamage(player,event.getSource(),event.getAmount())){
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean byPassDamage(RegistryAccess access,DamageSource source){
        DamageSources sources = new DamageSources(access);
        if(source.isCreativePlayer()){
            return false;
        }
        if(sources.fellOutOfWorld().equals(source)){
            return false;
        }
        return true;
    }
}
