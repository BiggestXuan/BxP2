package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.recipes.RecipeUtils;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.Item;
import org.openzen.zencode.java.ZenCodeType;
import owmii.powah.api.PowahAPI;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */

@ZenRegister
@ZenCodeType.Name("BxP2.CrTManager")
@SuppressWarnings("unused")
public class CrTUtils {
    @ZenCodeType.Method
    public static boolean isDevMode(){
        return BxP2.devMode;
    }

    @ZenCodeType.Method
    public static void registerCoolant(String name, int cool){
        PowahAPI.registerCoolant(BxP2.MODRL(name),cool);
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

    @ZenCodeType.Method
    public static int getDifficulty(){
        return Config.difficulty;
    }

    @ZenCodeType.Method
    public static String getRecipeName(IItemStack stack,String name){
        String n = RecipeUtils.getRecipeName(stack.getInternal(),name);
        return n.replace(':','_');
    }

    @ZenCodeType.Method
    public static String addRecipeName(IItemStack[] stacks,String name){
        String s = RecipeUtils.getRecipeName(Utils.IItemStackArray2ItemStackArray(stacks),name);
        return s.replace(':','_');
    }
}
