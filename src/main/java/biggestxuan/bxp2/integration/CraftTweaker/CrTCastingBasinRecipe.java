package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.casting.ICastingRecipe;

/**
 * @Author Biggest_Xuan
 * 2025/6/28
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTCastingBasinRecipe")
public class CrTCastingBasinRecipe implements IRecipeManager<ICastingRecipe> {
    @ZenCodeType.Method
    public void addRecipe(){

    }

    @Override
    public RecipeType<ICastingRecipe> getRecipeType() {
        return TinkerRecipeTypes.CASTING_BASIN.get();
    }
}
