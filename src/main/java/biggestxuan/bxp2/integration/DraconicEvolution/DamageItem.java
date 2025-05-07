package biggestxuan.bxp2.integration.DraconicEvolution;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.items.BxPDraconicGuardianItem;
import biggestxuan.bxp2.utils.Utils;
import com.brandon3055.draconicevolution.entity.guardian.DraconicGuardianEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/29
 */
public class DamageItem extends BxPDraconicGuardianItem {
    @Override
    protected boolean doSomething(Player player, Level level, @Nonnull DraconicGuardianEntity dragon) {
        if(dragon.getShieldPower() > 0.1f){
            Utils.sendMessage(player,BxP2.tr("message.has_shield"));
            return false;
        }
        if(dragon.isDeadOrDying()) return false;
        dragon.attackEntityPartFrom(dragon.dragonPartHead,level.damageSources().mobAttack(player),dragon.getHealth() / 3f);
        return true;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(BxP2.tr("tooltip.draconic.health"));
    }
}
