package biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.MekaArmorModifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Leveling.LevelUtils;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

/**
 * @Author Biggest_Xuan
 * 2025/6/2
 */

public final class MekaTinkerUtils {
    public static boolean canPreventDamage(Player player, DamageSource source,float damage){
        if(!isAllMekaArmor(player)) return false;
        for(ItemStack stack : player.getInventory().armor){
            ToolStack tool = ToolStack.from(stack);
            float bx = tool.getPersistentData().getFloat(BxP2.RL("bx_value"));
            if(bx >= damage){
                tool.getPersistentData().putFloat(BxP2.RL("bx_value"),bx - damage);
                //Utils.sendDevMessage(player,BxP2.tr(""+bx));
                //Utils.sendDevMessage(player,BxP2.tr(""+damage));
                LevelUtils.addToolXp(stack,damage,player);
                return true;
            }
        }
        return false;
    }

    public static boolean addBXEnergy(Player player,float amount){
        float a = amount;
        if(!isAllMekaArmor(player)) return false;
        for(ItemStack stack : player.getInventory().armor){
            ToolStack tool = ToolStack.from(stack);
            float bx = tool.getPersistentData().getFloat(BxP2.RL("bx_value"));
            float max = tool.getPersistentData().getFloat(BxP2.RL("bx_max_value"));
            if(bx != max){
                float need = max - bx;
                tool.getPersistentData().putFloat(BxP2.RL("bx_value"),Math.min(max,bx + amount));
                if(amount > need){
                    amount -= need;
                }else{
                    amount = 0F;
                }
            }
        }
        return a != amount;
    }

    public static boolean isAllMekaArmor(Player player){
        NonNullList<ItemStack> list = player.getInventory().armor;
        for(ItemStack stack : list){
            if(stack.getItem() instanceof ModifiableArmorItem){
                if(ModifierUtil.getModifierLevel(stack, BxPModifiers.MekaArmor.getId()) < 1){
                    return false;
                }else{
                    ToolStack tool = ToolStack.from(stack);
                    if(tool.getPersistentData().getFloat(BxP2.RL("bx_max_value")) == 0){
                        tool.getPersistentData().putFloat(BxP2.RL("bx_max_value"),1000F);
                        tool.getPersistentData().putFloat(BxP2.RL("bx_value"),0F);
                    }
                }
            }else{
                return false;
            }
        }
        return true;
    }
}
