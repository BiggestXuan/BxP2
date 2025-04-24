package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.items.BxPItem;
import biggestxuan.bxp2.items.BxPItems;
import com.brandon3055.draconicevolution.init.DEContent;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.registry.ExtraItem;
import mekanism.common.registries.MekanismItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public final class PhaseUtils {
    private static final TagKey<Item> RADIATION_PELLET_TAG = TagKey.create(BuiltInRegistries.ITEM.key(), BxP2.RL("radiation_pellet"));

    public static void setPlayerPhase(ServerPlayer player){
        player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
            int phase = cap.getPhase();
            if(phase < 0){
                cap.setPhase(0);
            }
            if(phase < 1 && getItemInInventory(player, BxPItems.BX_UNSTABLE_INGOT.get())){
                cap.setPhase(1);
            }
            if(phase < 2 && getItemInInventory(player, MekanismItems.ELITE_CONTROL_CIRCUIT.get())){
                cap.setPhase(2);
            }
            if(phase < 3 && getItemInInventory(player, BxPItems.BX_INGOT.get())){
                cap.setPhase(3);
            }
            if(phase < 4 && getItemInInventory(player, MekanismItems.ULTIMATE_CONTROL_CIRCUIT.get())){
                cap.setPhase(4);
            }
            if(phase < 5 && getItemInInventory(player, RADIATION_PELLET_TAG)){
                cap.setPhase(5);
            }
            if(phase < 6 && getItemInInventory(player, ExtraItem.SUPREME_CONTROL_CIRCUIT.get())){
                cap.setPhase(6);
            }
            if(phase < 7 && getItemInInventory(player, DEContent.CORE_WYVERN.get())){
                cap.setPhase(7);
            }
            if(phase < 8 && getItemInInventory(player, BxPItems.BX_ENCH_INGOT.get())){
                cap.setPhase(8);
            }
            if(phase < 9 && getItemInInventory(player, DEContent.DRAGON_HEART.get())){
                cap.setPhase(9);
            }
            if(phase < 10 && getItemInInventory(player, DEContent.CORE_AWAKENED.get())){
                cap.setPhase(10);
            }
            if(phase < 11 && getItemInInventory(player, DEContent.CORE_CHAOTIC.get())){
                cap.setPhase(11);
            }
            if(phase < 12 && getItemInInventory(player, BxPItems.BX_SUPER_INGOT.get())){
                cap.setPhase(12);
            }
        });
    }

    public static String getName(int phase){
        String n = "bxp2.catalyst.adapt_unknown";
        switch (phase){
            case 1 -> n = "item.bxp2.bx_unstable_ingot";
            case 2 -> n = "item.mekanism.elite_control_circuit";
            case 3 -> n = "item.bxp2.bx_ingot";
            case 4 -> n = "item.mekanism.ultimate_control_circuit";
            case 5 -> n = "item.radiation_item";
            case 6 -> n = "item.mekanism_extras.supreme_control_circuit";
            case 7 -> n = "item.draconicevolution.wyvern_core";
            case 8 -> n = "item.bxp2.bx_ench_ingot";
            case 9 -> n = "item.draconicevolution.dragon_heart";
            case 10 -> n = "item.draconicevolution.awakened_core";
            case 11 -> n = "item.draconicevolution.chaotic_core";
            case 12 -> n = "item.bxp2.bx_super_ingot";
        }
        return BxP2.tr(n).getString();
    }

    public static boolean getItemInInventory(ServerPlayer player,TagKey<Item> tag){
        for(ItemStack stack : player.getInventory().items){
            if(stack.is(tag)){
                return true;
            }
        }
        return false;
    }

    public static boolean getItemInInventory(ServerPlayer player, ItemStack item){
        for(ItemStack stack : player.getInventory().items){
            if(ItemStack.isSameItem(stack,item)){
                return true;
            }
        }
        return false;
    }

    public static boolean getItemInInventory(ServerPlayer player, Item item){
        return getItemInInventory(player,item.getDefaultInstance());
    }
}
