package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;


/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class CapabilityEvent {
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
    }
}
