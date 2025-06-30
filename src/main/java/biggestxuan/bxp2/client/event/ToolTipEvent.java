package biggestxuan.bxp2.client.event;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Leveling.LevelUtils;
import biggestxuan.bxp2.items.BxPItem;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/21
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID, value = Dist.CLIENT)
public class ToolTipEvent {
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        List<Component> list = event.getToolTip();
        Player player = Minecraft.getInstance().player;
        if(player == null || player.isDeadOrDying()) return;
        if(stack.hasTag()){
            Tag tag = stack.getTag().get("bxp_cycle");
            if(tag instanceof CompoundTag tag1){
                int l = tag1.getInt("l");
                int f = tag1.getInt("f");
                int max = tag1.getInt("max");
                String name = tag1.getString("n");
                if (name != null && !name.isEmpty()) {
                    list.add(BxP2.tr("bxp.cycle.now").append(BxP2.tr("bxp.cycle."+name)));
                    list.add(BxP2.tr("bxp.cycle.phase").append(f+"/"+max));
                    list.add(BxP2.tr("bxp.cycle.step").append(""+l));
                }
            }
        }
        if(stack.getItem() instanceof ModifiableItem || stack.getItem() instanceof ModifiableArmorItem){
            int level = LevelUtils.getToolLevel(stack);
            long xp = LevelUtils.getToolXp(stack);
            long require = LevelUtils.getRequireXp(level,stack);
            list.add(BxP2.tr("tooltip.level_level",level).append(LevelUtils.getStarToolTip(level)));
            list.add(BxP2.tr("tooltip.level_xp", Utils.KMT(xp),Utils.KMT(require),String.format("%.2f",100D*xp/require)+"%"));
        }
        if(stack.getItem() instanceof BxPItem bxp){
            int value = bxp.getBXValue(stack);
            if(value > 0){
                list.add(BxP2.tr("tooltip.tinker.bx_value",value));
            }
        }
    }
}
