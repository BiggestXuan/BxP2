package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.api.BxPApi;
import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.capability.IBxPCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

public final class BXUtils {
    public static int getPlayerBXValue(Player p,int max){
        int value = 0;
        if(p == null) return value;
        var items = p.getInventory().items;
        for (ItemStack item : items) {
            if (value >= max) {
                return max;
            }
            if (item.getItem() instanceof IBXItem bx) {
                value += bx.getBXValue(item) * item.getCount();
            }
        }
        return value;
    }

    public static boolean shouldDropPlayerItemsWhenDeath(Player player,ItemStack stack){
        if(Config.difficulty != 3 || !Config.DEATH_DROP){
            return false;
        }
        if(stack.hasTag()){
            if(stack.getItem() instanceof ModifiableItem || stack.getItem() instanceof ModifiableArmorItem){
                ToolStack tool = ToolStack.from(stack);
                if(ModifierUtil.getModifierLevel(stack,new ModifierId("tconstruct:soulbound")) > 0){
                    return false;
                }
            }
            CompoundTag tag = stack.getOrCreateTag();
            byte b = tag.getByte("Botania_keepIvy");
            return b != 1;
        }
        return true;
    }

    public static boolean canUseProjecte(Player player){
        if(Config.difficulty == 3){return false;}
        IBxPCapability cap = BxPApi.getPlayerCap(player);
        if(Config.difficulty == 2){
            return cap.getPhase() >= 11;
        }else{
            return cap.getPhase() >= 3;
        }
    }
}
