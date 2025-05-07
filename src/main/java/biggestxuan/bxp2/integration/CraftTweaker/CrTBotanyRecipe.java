package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.darkhax.botanypots.BotanyPotHelper;
import net.darkhax.botanypots.data.recipes.crop.Crop;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/26
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.BotanyPot")
@SuppressWarnings("unused")
public class CrTBotanyRecipe implements IRecipeManager<Crop> {
    @Override
    public RecipeType<Crop> getRecipeType() {
        return BotanyPotHelper.CROP_TYPE.get();
    }

    @ZenCodeType.Method
    public void addRecipe(CrTPlantRecipe recipe){
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe.toBotanyRecipe()));
    }

    @ZenCodeType.Method
    public void removeBxPRequireRecipe(){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByName<>(this){
            @Override
            public void apply(){
                List<ResourceLocation> remove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()){
                    if(location.getPath().contains("mysticala")){
                        remove.add(location);
                    }
                }
                remove.forEach(getManager().getRecipes()::remove);
            }
        });
    }


}
