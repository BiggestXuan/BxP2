package biggestxuan.bxp2.integration.KubeJS;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.recipes.BxPCatalyst;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.recipes.BXFurnaceRecipe;
import biggestxuan.bxp2.recipes.RecipeUtils;
import dev.architectury.fluid.FluidStack;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */
@SuppressWarnings("unused")
public class KJSUtils {
    public static int getDifficulty(){
        return Config.difficulty;
    }

    public static int[] getEasyFissionData(){
        return RecipeUtils.getEasyFissionData();
    }

    public static Map<ItemStack,Integer> getAllBXItems(){
        Map<ItemStack,Integer> map = new HashMap<>();
        for(var item :BxPItems.ITEMS.getEntries()){
            ItemStack stack = new ItemStack(item.get());
            int value = 0;
            if(item.get() instanceof IBXItem bx){
                value = bx.getBXValue(stack);
            }
            if(value > 0){
                map.put(stack,value);
            }
        }
        return map;
    }

    public static Map<ItemStack,Float[]> getAllFoods(){
        Map<ItemStack,Float[]> map = new HashMap<>();
        for(var m : ForgeRegistries.ITEMS.getEntries()){
            Item item = m.getValue();
            if(item.isEdible() && item.getFoodProperties() != null){
                FoodProperties fp = item.getFoodProperties();
                map.put(new ItemStack(item),new Float[]{1F * fp.getNutrition(),fp.getSaturationModifier()});
            }
        }
        return map;
    }

    public static String getRecipeName(ItemStack item, String add){
        return RecipeUtils.getRecipeName(item,add);
    }

    public static String getItemName(ItemStack item){
        return RecipeUtils.getItemName(item);
    }

    public static float getSimpleMachineDifficultyRate(){
        float rate = 1;
        switch (Config.difficulty){
            case 1 -> rate = 1.5f;
            case 3 -> rate = 0.7f;
        }
        return rate;
    }

    public static Map<String, Map<Map<ItemStack[],int[]>,ItemStack[]>> getBXUnstableIngotRecipe(){
        return RecipeUtils.getBXFurnaceRecipe(
                new BXFurnaceRecipe(
                        "bx_unstable_furnace",
                        new ItemStack[]{
                                BxP2.getStack("enderio:energetic_alloy_ingot"),
                                BxP2.getStack("thermal:invar_ingot"),
                                BxPItems.BX_GOLD_INGOT.get().getDefaultInstance()
                        },new ItemStack[]{BxPItems.BX_UNSTABLE_INGOT.get().getDefaultInstance()}
                        , BxPCatalyst.ADAPT.BX_UNSTABLE_FURNACE,
                        1500,0,1000
                ).copy()
        );
    }

    public static Map<String, Map<Map<ItemStack[],int[]>,ItemStack[]>> getBXIngotRecipe(){
        ItemStack stack = BxP2.getStack("bxp2:bx_ingot");
        return RecipeUtils.getBXFurnaceRecipe(
            new BXFurnaceRecipe("bx_furnace",
                   new ItemStack[]{
                           BxP2.getStack("bxp2:bx_unstable_ingot"),
                           BxP2.getStack("mekanism:hdpe_sheet"),
                           BxP2.getStack("mekanism:alloy_reinforced"),
                           BxP2.getStack("mekanism:dust_lithium"),
                           BxP2.getStack("bxp2:sx_ingot"),
                           BxP2.getStack("bxp2:poly_ingot")
                   },new ItemStack[]{stack}
                    , BxPCatalyst.ADAPT.BX_FURNACE,
                    10000,100000000,0
            ).copy()
        );
    }

    public static Map<String, Map<Map<ItemStack[],int[]>,ItemStack[]>> getEnchSeedRecipe(){
        return RecipeUtils.getBXFurnaceRecipe(
                new BXFurnaceRecipe("bx_furnace",
                        new ItemStack[]{
                                BxP2.getStack("bxp2:cai_seed"),
                                BxP2.getStack("bxp2:ou_gold_ingot"),
                                BxP2.getStack("mekanism_extras:alloy_thermonuclear")
                        },new ItemStack[]{BxP2.getStack("bxp2:ench_cai_seed")}, BxPCatalyst.ADAPT.CAISEED,
                        22500,330000000,0).copy()
        );
    }

    public static List<int[]> getCuriumRecipe(){
        return RecipeUtils.getCuriumRecipeData();
    }
}
