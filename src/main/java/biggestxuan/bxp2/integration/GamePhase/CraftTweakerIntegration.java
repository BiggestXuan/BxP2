package biggestxuan.bxp2.integration.GamePhase;

import biggestxuan.bxp2.integration.CraftTweaker.Utils;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.cpearl.gamephase.GamePhaseHelper;
import com.cpearl.gamephase.functions.dimension.PhaseDimensions;
import com.cpearl.gamephase.functions.item.PhaseItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/4/5
 */

@ZenRegister
@ZenCodeType.Name("bxp2.GamePhase")
@SuppressWarnings("unused")
public class CraftTweakerIntegration {
    @ZenCodeType.Method
    public static void addPlayerPhase(ServerPlayer player,String phase){
        GamePhaseHelper.addPhase(player, phase);
    }

    @ZenCodeType.Method
    public static void removePlayerPhase(ServerPlayer player,String phase){
        GamePhaseHelper.removePhase(player, phase);
    }

    @ZenCodeType.Method
    public static void addItemPhase(String phase, IItemStack ...stack) {
        PhaseItems.addItemRestriction(phase, Utils.IItemStackArray2ItemStackArray(stack));
    }

    @ZenCodeType.Method
    public static void addModPhase(String phase, String ...modid) {
        PhaseItems.addItemModRestriction(phase, modid);
    }

    @ZenCodeType.Method
    public static void removeItemPhase(String phase, IItemStack ...stack) {
        PhaseItems.removeItemRestriction(phase, Utils.IItemStackArray2ItemStackArray(stack));
    }

    @ZenCodeType.Method
    public static void addDimensionPhase(String phase, String rl){
        PhaseDimensions.addDimensionRestriction(phase,ResourceLocation.tryParse(rl));
    }

    @ZenCodeType.Method
    public static void removeDimensionPhase(String phase, String rl){
        PhaseDimensions.removeDimensionRestriction(phase,ResourceLocation.tryParse(rl));
    }
}
