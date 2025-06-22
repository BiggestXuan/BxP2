package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/6/9
 */
public class WaitingItem extends BxPItem{
    private final String ModName;

    public WaitingItem(String s){
        ModName = s;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(BxP2.tr("tooltip.content.waiting",ModName).withStyle(Style.EMPTY.withColor(ChatFormatting.AQUA).applyFormat(ChatFormatting.ITALIC)));
    }

    public static class ThuamcraftItem extends WaitingItem{
        public ThuamcraftItem(){
            super("Thaumcraft 7");
        }
    }

    @Override
    public Rarity getRarity(ItemStack p_41461_) {
        return Rarity.RARE;
    }
}


