package biggestxuan.bxp2.client.event;

import biggestxuan.bxp2.BxP2;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
    }
}
