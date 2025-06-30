package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;
import slimeknights.mantle.recipe.helper.FluidOutput;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.melting.IMeltingRecipe;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipe;

import java.util.ArrayList;

/**
 * @Author Biggest_Xuan
 * 2025/6/28
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTMeltingRecipe")
public class CrTMeltingRecipe implements IRecipeManager<IMeltingRecipe> {
    @ZenCodeType.Method
    public void addRecipe(IIngredient ingredient, IFluidStack output, int time, int temp){
        MeltingRecipe recipe = new MeltingRecipe(BxP2.RL(CrTUtils.addRecipeName(ingredient.getItems(),"melting")),"",ingredient.asVanillaIngredient(), FluidOutput.fromStack(output.getInternal()),temp,time,new ArrayList<>());
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe));
    }

    @Override
    public RecipeType<IMeltingRecipe> getRecipeType() {
        return TinkerRecipeTypes.MELTING.get();
    }
}
