package biggestxuan.bxp2.integration.JEI;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.recipes.ICycleRecipe;
import biggestxuan.bxp2.integration.JEI.catalyst.BxPCatalystCategory;
import biggestxuan.bxp2.integration.JEI.cycle.BxPCombineCycleCategory;
import biggestxuan.bxp2.items.BxPCatalyst;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.recipes.CombineCycleRecipe;
import biggestxuan.bxp2.recipes.RecipeUtils;
import mekanism.common.registries.MekanismBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/7
 */

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return BxP2.RL("jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new BxPCatalystCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new BxPCombineCycleCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(BxPItems.BX_INGOT.get());
        registration.useNbtForSubtypes(MekanismBlocks.COMBINER.asItem());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration){
        BxPCatalyst.getAllCatalyst(BxPCatalyst.ADAPT.ALL).forEach(c -> {
            registration.addRecipeCatalyst(c.stack,BxPCatalystCategory.TYPE);
        });
        registration.addRecipeCatalyst(MekanismBlocks.COMBINER.asItem(),BxPCombineCycleCategory.type);
        Arrays.asList(CombineCycleRecipe.values()).forEach(recipe -> {
            registration.addRecipeCatalyst(recipe.getInput(),BxPCombineCycleCategory.type);
            RecipeUtils.removeRepeatIngredient(recipe.getCala()).forEach(item -> {
                registration.addRecipeCatalyst(item,BxPCombineCycleCategory.type);
            });
        });
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registry){
        List<BxPCatalyst> catalysts = BxPCatalyst.getAllCatalyst(BxPCatalyst.ADAPT.ALL);
        registry.addRecipes(BxPCatalystCategory.TYPE,catalysts);
        List<ICycleRecipe> recipes = Arrays.asList(CombineCycleRecipe.values());
        registry.addRecipes(BxPCombineCycleCategory.type,recipes);
    }
}
