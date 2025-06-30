package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.mojang.datafixers.util.Pair;
import cy.jdkdigital.productivebees.common.recipe.CentrifugeRecipe;
import cy.jdkdigital.productivebees.init.ModRecipeTypes;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;

/**
 * @Author Biggest_Xuan
 * 2025/6/28
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTCentrifugeRecipe")
public class CrTCentrifugeRecipe implements IRecipeManager<CentrifugeRecipe> {
    @ZenCodeType.Method
    public void addCommonRecipe(String name,CrTWeightItem output,int time){
        ItemStack comb = Utils.getHoneyComb(name);
        HashMap<Ingredient, IntArrayTag> map = new HashMap<>();
        var entry = output.toEntry();
        map.put(entry.getKey(),entry.getValue());
        CentrifugeRecipe recipe = new CentrifugeRecipe(BxP2.MODRL(name+"_bee_centrifuge"), Ingredient.of(comb),map, Pair.of("productivebees:honey",50),time);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe));
    }

    @Override
    public RecipeType<CentrifugeRecipe> getRecipeType() {
        return ModRecipeTypes.CENTRIFUGE_TYPE.get();
    }
}
