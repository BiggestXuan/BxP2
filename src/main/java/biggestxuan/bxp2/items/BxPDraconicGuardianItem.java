package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.Utils;
import biggestxuan.bxp2.utils.WorldUtils;
import com.brandon3055.draconicevolution.entity.guardian.DraconicGuardianEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/29
 */
public abstract class BxPDraconicGuardianItem extends BxPItem{
    @Nullable
    protected DraconicGuardianEntity getDraconicGuardian(Player player){
        List<LivingEntity> list = WorldUtils.getNearLiving(player,500,false);
        for(LivingEntity living : list){
            if(living instanceof DraconicGuardianEntity dracon){
                return dracon;
            }
        }
        return null;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        super.use(level, player, hand);
        ItemStack stack = player.getItemInHand(hand);
        if(level.isClientSide){
            return InteractionResultHolder.pass(stack);
        }
        DraconicGuardianEntity dragon = getDraconicGuardian(player);
        if(dragon == null){
            Utils.sendMessage(player, BxP2.tr("message.not_found"));
            return InteractionResultHolder.fail(stack);
        }
        if(doSomething(player,level,dragon)){
            if(!BxP2.devMode){
                player.getCooldowns().addCooldown(this,1200);
            }
            stack.shrink(1);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    protected abstract boolean doSomething(Player player, Level level,@Nonnull DraconicGuardianEntity dragon);
}
