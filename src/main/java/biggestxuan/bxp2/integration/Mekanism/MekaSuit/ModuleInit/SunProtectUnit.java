package biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleInit;

import de.teamlapen.vampirism.core.ModEffects;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * @Author Biggest_Xuan
 * 2025/4/19
 */
public class SunProtectUnit implements ICustomModule<SunProtectUnit> {
    public void tickServer(IModule<SunProtectUnit> module, Player player) {
        Level level = player.level();
        if(player.tickCount % 100 == 0){
            player.addEffect(new MobEffectInstance(ModEffects.SUNSCREEN.get(),200,6,false,false));
        }
    }
}
