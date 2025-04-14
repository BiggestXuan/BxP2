package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.recipes.RecipeUtils;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;
import owmii.powah.block.energizing.EnergizingRecipe;
import owmii.powah.recipe.Recipes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/11
 */

@ZenRegister
@ZenCodeType.Name("BxP2.Powah")
public class CrTEnergizingRecipe implements IRecipeManager<EnergizingRecipe> {

    @ZenCodeType.Method
    public void addRecipe(IIngredient[] inputs, IItemStack output, long energy) {
        EnergizingRecipe recipe = new EnergizingRecipe(
                BxP2.MODRL(RecipeUtils.getRecipeName(output.getInternal(),"powah_energy")),
                output.getInternal(),
                energy,
                RecipeUtils.getIngredients(inputs)
        );
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput<>(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> remove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()){
                    EnergizingRecipe r = getManager().getRecipes().get(location);
                    ItemStack stack = r.getResultItem();
                    if(output.matches(new MCItemStackMutable(stack),true)){
                        remove.add(location);
                    }
                }
                remove.forEach(getManager().getRecipes()::remove);
            }
        });
    }


    @Override
    public RecipeType<EnergizingRecipe> getRecipeType() {
        return Recipes.ENERGIZING.get();
    }

}
