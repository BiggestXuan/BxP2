package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.recipes.RecipeUtils;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.github.alexthe666.rats.registry.RatsRecipeRegistry;
import com.github.alexthe666.rats.server.recipes.ArcheologistRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

/**
 *  @Author Biggest_Xuan
 *  2025/5/5
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.ArcheologistRecipe")
public class CrTArcheologistRecipe implements IRecipeManager<ArcheologistRecipe> {
    @Override
    public RecipeType<ArcheologistRecipe> getRecipeType() {
        return RatsRecipeRegistry.ARCHEOLOGIST.get();
    }

    @ZenCodeType.Method
    public void addRecipe(IIngredient input, IItemStack stack){
        ArcheologistRecipe recipe = new ArcheologistRecipe(RecipeUtils.getRecipeNameByRL(stack.getInternal(),"rats"),"",input.asVanillaIngredient(),stack.getInternal());
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe));
    }
}
