package biggestxuan.bxp2.recipes;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.utils.Utils;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import mekanism.api.chemical.gas.GasStack;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

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

    public static ResourceLocation getRecipeNameByRL(ItemStack[] stack,String name){
        return BxP2.MODRL(getRecipeName(stack,name));
    }

    public static ResourceLocation getRecipeNameByRL(ItemStack stack,String name){
        return BxP2.MODRL(getRecipeName(stack,name));
    }

    public static String getRecipeName(GasStack gas,String add){
        return "bxp2:recipes/" + gas.getType().getName() + add + "_" + gas.getAmount();
    }

    public static String getRecipeName(ItemStack item, String add){
        return "bxp2:recipes/" + getItemName(item) + "_" + add + item.getCount();
    }

    public static String getRecipeName(FluidStack fluidStack, String add){
        ResourceLocation rl = BuiltInRegistries.FLUID.getKey(fluidStack.getFluid());
        return "bxp2:recipes/" + rl.toString().split(":")[1] + "_" + add + fluidStack.getAmount();
    }

    public static String getRecipeName(ItemStack[] items,String add){
        StringBuilder builder = new StringBuilder();
        builder.append("bxp2:recipes/");
        for(ItemStack s : items){
            builder.append(getItemName(s));
            builder.append("_");
        }
        builder.append(add);
        return builder.toString();
    }

    public static String getItemName(ItemStack item){
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item.getItem());
        return rl.toString().split(":")[0] + "_" +rl.toString().split(":")[1];
    }

    public static NonNullList<Ingredient> getIngredients(IIngredient[] input){
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for(IIngredient ingredient : input){
            ingredients.add(ingredient.asVanillaIngredient());
        }
        return ingredients;
    }

    public static List<ItemStack> removeRepeatIngredient(Ingredient[] ingredients){
        Set<ItemStack> uniqueStacks = new HashSet<>() {
            @Override
            public boolean add(ItemStack stack) {
                if (stack.isEmpty()) return false;

                ItemStack normalized = stack.copy();
                normalized.setCount(1);

                for (ItemStack existing : this) {
                    if (ItemStack.isSameItemSameTags(existing, normalized)) {
                        return false;
                    }
                }

                return super.add(normalized);
            }
        };

        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                for (ItemStack stack : ingredient.getItems()) {
                    if (!stack.isEmpty()) {
                        uniqueStacks.add(stack.copy());
                    }
                }
            }
        }

        return new ArrayList<>(uniqueStacks);
    }

    public static List<Item> getItemsFromModContaining(String modId, String keyword) {
        List<Item> result = new ArrayList<>();

        for (Item item : ForgeRegistries.ITEMS) {
            ResourceLocation registryName = ForgeRegistries.ITEMS.getKey(item);
            if (registryName != null
                    && registryName.getNamespace().equals(modId)
                    && registryName.getPath().contains(keyword)) {
                result.add(item);
            }
        }

        return result;
    }

    public static int[] getEasyFissionData(){
        switch (Config.difficulty){
            case 1 -> {
                return new int[]{477,1};
            }
            case 2 -> {
                return new int[]{1,10};
            }
            case 3 -> {
                return new int[]{1,100};
            }
        }
        throw new IllegalStateException("Difficulty must be in [1,2,3]");
    }
}
