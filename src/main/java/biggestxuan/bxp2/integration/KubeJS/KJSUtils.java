package biggestxuan.bxp2.integration.KubeJS;

import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.items.BxPItems;
import dev.architectury.fluid.FluidStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */
@SuppressWarnings("unused")
public class KJSUtils {
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
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item.getItem());
        return "bxp2:recipes/" + rl.toString().split(":")[0] + "_" +rl.toString().split(":")[1] + "_" + add + item.getCount();
    }

    public static String getRecipeName(FluidStack fluidStack, String add){
        ResourceLocation rl = BuiltInRegistries.FLUID.getKey(fluidStack.getFluid());
        return "bxp2:recipes/" + rl.toString().split(":")[1] + "_" + add + fluidStack.getAmount();
    }
}
