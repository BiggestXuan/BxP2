package biggestxuan.bxp2.integration.JEI;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.JEI.catalyst.BxPCatalystCategory;
import biggestxuan.bxp2.items.BxPCatalyst;
import biggestxuan.bxp2.items.BxPItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
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
    }

    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(BxPItems.BX_INGOT.get());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration){
        BxPCatalyst.getAllCatalyst(BxPCatalyst.ADAPT.ALL).forEach(c -> {
            registration.addRecipeCatalyst(c.stack,BxPCatalystCategory.TYPE);
        });
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registry){
        List<BxPCatalyst> catalysts = BxPCatalyst.getAllCatalyst(BxPCatalyst.ADAPT.ALL);
        registry.addRecipes(BxPCatalystCategory.TYPE,catalysts);
    }
}
