package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.integration.TConstruct.TinkersSurvival;
import biggestxuan.bxp2.recipes.CombineCycleRecipe;
import biggestxuan.bxp2.recipes.RecipeUtils;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import mekanism.api.chemical.gas.GasStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;
import owmii.powah.api.PowahAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTManager")
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
    public static String getGasRecipeName(GasStack stack,String name){
        String n = RecipeUtils.getRecipeName(stack,name);
        return n.replace(':','_');
    }

    @ZenCodeType.Method
    public static String addRecipeName(IItemStack[] stacks,String name){
        String s = RecipeUtils.getRecipeName(Utils.IItemStackArray2ItemStackArray(stacks),name);
        return s.replace(':','_');
    }

    @ZenCodeType.Method
    public static CrTCombineCycleRecipe[] getAllCycleRecipe(){
        CrTCombineCycleRecipe[] array = new CrTCombineCycleRecipe[CombineCycleRecipe.values().length];
        for (int i = 0; i < CombineCycleRecipe.values().length; i++) {
            array[i] = CombineCycleRecipe.values()[i].getCrTRecipe();
        }
        return array;
    }

    @ZenCodeType.Method
    public static List<IItemStack> getAllMekFactory(){
        List<IItemStack> stacks = new ArrayList<>();
        List<Item> list = RecipeUtils.getItemsFromModContaining("mekanism","factory");
        list.addAll(RecipeUtils.getItemsFromModContaining("mekanism_extras","factory"));
        for(Item item : list){
            stacks.add(IItemStack.of(item.getDefaultInstance()));
        }
        return stacks;
    }

    @ZenCodeType.Method
    public static IItemStack[] getMCToolsRecipe(){
        if(Config.TinkersSurvival){
            IItemStack[] arr = new IItemStack[TinkersSurvival.BLACKLISTED_ITEMS.size()];
            for (int i = 0; i < TinkersSurvival.BLACKLISTED_ITEMS.size(); i++) {
                arr[i] = IItemStack.of(TinkersSurvival.BLACKLISTED_ITEMS.get(i).getDefaultInstance());
            }
            return arr;
        }
        return new IItemStack[]{};
    }

    @ZenCodeType.Method
    public static IItemStack[] getProjecteItems(){
        IItemStack[] arr = new IItemStack[Utils.getAllProjectEItems().size()];
        for (int i = 0; i < Utils.getAllProjectEItems().size(); i++) {
            arr[i] = IItemStack.of(Utils.getAllProjectEItems().get(i).getDefaultInstance());
        }
        return arr;
    }
}
