package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.emc.EMCMappingHandler;
import moze_intel.projecte.integration.crafttweaker.actions.CustomEMCAction;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/5/31
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.ProjectE")
@SuppressWarnings("unused")
public class CrTProjectE {
    @ZenCodeType.Method
    public static void removeAllItemEMC(){
        EMCMappingHandler.clearEmcMap();
        for(Item item : ForgeRegistries.ITEMS.getValues()){
            if(EMCHelper.getEmcValue(item) > 0){
                CraftTweakerAPI.apply(new CustomEMCAction(NSSItem.createItem(item),0));
            }
        }
    }
}
