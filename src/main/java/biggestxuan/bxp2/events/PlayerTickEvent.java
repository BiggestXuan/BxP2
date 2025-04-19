package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerTickEvent {
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        Level world = player.level();
        if(world.isClientSide) return;
        if(!player.isCreative() && !player.isSpectator()){
            if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
                var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
                Abilities abilities = player.getAbilities();
                if(abilities.flying && !cap.canFly()){
                    player.getAbilities().flying = false;
                    player.getAbilities().mayfly = false;
                    Utils.sendMessage(player,BxP2.tr("bxp2.message.cantfly").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
                }
            }
        }
        if(world.getDayTime() % 100 == 0){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                for(ItemStack stack : player.getInventory().items){
                    if(!cap.canEnd() && stack.getItem().equals(BxPItems.BX_ENCH_INGOT.get())){
                        cap.setEnd(true);
                    }
                    if(!cap.canNether() && stack.getItem().equals(BxPItems.BX_INGOT.get())){
                        cap.setNether(true);
                    }
                    if(!cap.canFly() && stack.getItem().equals(BxP2.getItem("draconicevolution:awakened_core"))){
                        cap.setFly();
                    }
                }
            });
        }
    }
}
