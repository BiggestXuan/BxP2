package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import cy.jdkdigital.productivebees.common.recipe.AdvancedBeehiveRecipe;
import cy.jdkdigital.productivebees.compat.jei.ingredients.BeeIngredient;
import cy.jdkdigital.productivebees.compat.jei.ingredients.BeeIngredientFactory;
import cy.jdkdigital.productivebees.init.ModRecipeTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.Lazy;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/6/25
 */
@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTBeeProduce")
public class CrTBeeProduce implements IRecipeManager<AdvancedBeehiveRecipe> {
    @ZenCodeType.Method
    public void addCommonRecipe(String name, CrTWeightItem.CrTWeight weight){
        List<CrTWeightItem> list = new ArrayList<>();
        list.add(new CrTWeightItem(IIngredient.fromIngredient(Ingredient.of(Utils.getHoneyComb(name))),weight));
        addRecipe(name,list);
    }

    @ZenCodeType.Method
    public void addRecipe(String name,List<CrTWeightItem> list){
        Lazy<BeeIngredient> beeIngredient = Lazy.of(BeeIngredientFactory.getIngredient(name));
        HashMap<Ingredient, IntArrayTag> map = new HashMap<>();
        list.forEach(e -> {
            map.put(e.toEntry().getKey(),e.toEntry().getValue());
        });
        AdvancedBeehiveRecipe recipe = new AdvancedBeehiveRecipe(BxP2.MODRL(name+"_bee"),beeIngredient,map);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe));
    }

    @Override
    public RecipeType<AdvancedBeehiveRecipe> getRecipeType() {
        return ModRecipeTypes.ADVANCED_BEEHIVE_TYPE.get();
    }
}
