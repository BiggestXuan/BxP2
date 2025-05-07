package biggestxuan.bxp2.integration.CraftTweaker;

import cofh.thermal.core.init.registries.TCoreRecipeTypes;
import cofh.thermal.core.util.recipes.machine.InsolatorRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/5/5
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.InsolatorRecipe")
public class CrTInsolatorRecipe implements IRecipeManager<InsolatorRecipe> {
    @ZenCodeType.Method
    public void addRecipe(CrTPlantRecipe recipe){
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe.toInsolatorRecipe()));
    }

    @Override
    public RecipeType<InsolatorRecipe> getRecipeType() {
        return TCoreRecipeTypes.INSOLATOR_RECIPE.get();
    }
}
