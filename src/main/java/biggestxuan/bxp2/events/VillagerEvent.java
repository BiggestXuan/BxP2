package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.items.BxPItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/5/5
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class VillagerEvent {
    @SubscribeEvent
    public static void villagerTrade(VillagerTradesEvent event){
        var trade = event.getTrades();
        if(event.getType() == VillagerProfession.FARMER){
            if(trade.containsKey(2)){
                trade.get(1).add((entity,rand) -> new MerchantOffer(
                        BxPItems.BX_UNSTABLE_INGOT.get().getDefaultInstance(),
                        BxPItems.CAI_SEED.get().getDefaultInstance(),
                        16,2,0.05f
                ));
            }
        }
    }
}
