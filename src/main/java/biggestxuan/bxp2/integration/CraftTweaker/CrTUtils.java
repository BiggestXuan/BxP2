package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.items.IBXItem;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.Item;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */

@ZenRegister
@ZenCodeType.Name("BxP2")
@SuppressWarnings("unused")
public class CrTUtils {
    @ZenCodeType.Method
    public static boolean isDevMode(){
        return BxP2.devMode;
    }

    @ZenCodeType.Method
    public static boolean isSkyBlock(){
        return BxP2.isSkyBlock;
    }

    @ZenCodeType.Method
    public static int getBXValue(IItemStack stack){
        Item item = stack.getDefinition();
        if(item instanceof IBXItem bx){
            return bx.getBXValue(stack.getInternal());
        }
        return 0;
    }

}
