package biggestxuan.bxp2.recipes;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.items.BxPCatalyst;
import biggestxuan.bxp2.utils.Utils;
import dev.architectury.fluid.FluidStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.*;

/**
 * @Author Biggest_Xuan
 * 2025/4/9
 */
public final class RecipeUtils {
    public static Map<String, Map<Map<ItemStack[],int[]>,ItemStack[]>> getBXFurnaceRecipe(BXFurnaceRecipe recipe){
        Map<String, Map<Map<ItemStack[],int[]>,ItemStack[]>> map = new HashMap<>();
        var utils = Utils.generateSubsets(BxPCatalyst.getAllCatalyst(recipe.catalyst));
        int priority = utils.size();
        for(List<BxPCatalyst> list : utils){
            List<ItemStack> input = new ArrayList<>(Arrays.stream(recipe.input).toList());
            list.forEach(e -> input.add(e.stack));
            ItemStack[] inputs = Utils.getItemStackArray(input);
            StringBuilder name = new StringBuilder();
            name.append("bxp2:recipes/"+recipe.namespace);
            for(ItemStack stack : input){
                name.append(getItemName(stack));
            }
            double recipeTime = 1;
            double energy = 1;
            double fluid = 1;
            for(BxPCatalyst c : list){
                recipeTime *= c.speedRate;
                energy *= c.energyRate;
                fluid *= c.inputFluidRate;
                name.append(getItemName(c.stack));
            }
            if(Config.difficulty == 1){
                energy /= 2;
                fluid /= 2;
            }
            if(Config.difficulty == 3){
                energy *= 1.33;
                fluid *= 1.33;
            }
            int time = (int) (recipeTime * recipe.time);
            double fe = (int) (energy * recipe.energy / time);
            int flt = (int) (fluid * recipe.fluid);
            int[] data = new int[]{time,(int) fe,priority,flt};
            priority--;
            Map<ItemStack[],int[]> m1 = new HashMap<>();
            m1.put(inputs,data);
            Map<Map<ItemStack[],int[]>,ItemStack[]> m2 = new HashMap<>();
            ItemStack[] output = new ItemStack[recipe.output.length];
            for (int i = 0; i < recipe.output.length; i++) {
                ItemStack stack = recipe.output[i].copy();
                stack.setCount(Math.min(64,stack.getCount() * getDifficultyMulti()));
                output[i] = stack;
            }
            m2.put(m1,output);
            map.put(name.toString(),m2);
        }
        return map;
    }

    private static int getDifficultyMulti(){
        switch(Config.difficulty){
            case 1 -> {
                return 3;
            }
            case 2 -> {
                return 2;
            }
            default -> {
                return 1;
            }
        }
    }

    public static String getRecipeName(ItemStack item, String add){
        return "bxp2:recipes/" + getItemName(item) + "_" + add + item.getCount();
    }

    public static String getRecipeName(FluidStack fluidStack, String add){
        ResourceLocation rl = BuiltInRegistries.FLUID.getKey(fluidStack.getFluid());
        return "bxp2:recipes/" + rl.toString().split(":")[1] + "_" + add + fluidStack.getAmount();
    }

    public static String getItemName(ItemStack item){
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item.getItem());
        return rl.toString().split(":")[0] + "_" +rl.toString().split(":")[1];
    }
}
