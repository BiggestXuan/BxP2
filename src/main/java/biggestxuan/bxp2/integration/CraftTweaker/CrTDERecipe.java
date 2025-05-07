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
import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.draconicevolution.api.DraconicAPI;
import com.brandon3055.draconicevolution.api.crafting.FusionRecipe;
import com.brandon3055.draconicevolution.api.crafting.IFusionRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/20
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.DE")
public class CrTDERecipe implements IRecipeManager<IFusionRecipe> {
    @Override
    public RecipeType<IFusionRecipe> getRecipeType() {
        return DraconicAPI.FUSION_RECIPE_TYPE.get();
    }

    @ZenCodeType.Method
    public void addRecipe(IIngredient main, IIngredient[] other, IItemStack output, int level, long energy){
        ResourceLocation rl = BxP2.MODRL(RecipeUtils.getRecipeName(output.getInternal(),"de_recipe"));
        FusionRecipe recipe = new FusionRecipe(
                rl,output.getInternal(),main.asVanillaIngredient(),energy,getTechLevel(level),getFusionIngredient(other)
        );
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput<>(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> remove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()){
                    IFusionRecipe r = getManager().getRecipes().get(location);
                    ItemStack stack = r.getResultItem(CraftTweakerAPI.getAccessibleElementsProvider().registryAccess());
                    if(output.matches(new MCItemStackMutable(stack),true)){
                        remove.add(location);
                    }
                }
                remove.forEach(getManager().getRecipes()::remove);
            }
        });
    }

    private static NonNullList<FusionRecipe.FusionIngredient> getFusionIngredient(IIngredient[] items){
        NonNullList<FusionRecipe.FusionIngredient> list = NonNullList.create();
        for (IIngredient item : items) {
            list.add(new FusionRecipe.FusionIngredient(item.asVanillaIngredient(), true));
        }
        return list;
    }

    private static TechLevel getTechLevel(int level){
        return switch (level) {
            case 0 -> TechLevel.DRACONIUM;
            case 1 -> TechLevel.WYVERN;
            case 2 -> TechLevel.DRACONIC;
            default -> TechLevel.CHAOTIC;
        };
    }
}
