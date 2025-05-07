package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.recipes.RecipeUtils;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.brandon3055.draconicevolution.api.crafting.IFusionRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;
import slimeknights.mantle.recipe.helper.FluidOutput;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/5/5
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.TcAlloyRecipe")
public class CrTTcAlloyRecipe implements IRecipeManager<AlloyRecipe> {
    @ZenCodeType.Method
    public void addRecipe(IFluidStack[] input,IFluidStack output,int temp){
        AlloyRecipe recipe = new AlloyRecipe(BxP2.MODRL(RecipeUtils.getRecipeName((FluidStack) output.getInternal(),"bxp_alloy")),getAlloyList(input),getOutPut(output),temp);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this,recipe));
    }

    @ZenCodeType.Method
    public void removeRecipeByName(String name){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByName<>(this,BxP2.MODRL(name)){
            @Override
            public void apply(){
                List<ResourceLocation> remove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()){
                    if(location.equals(BxP2.MODRL(name))){
                        remove.add(location);
                    }
                }
                remove.forEach(getManager().getRecipes()::remove);
            }
        });
    }

    @Override
    public RecipeType<AlloyRecipe> getRecipeType() {
        return TinkerRecipeTypes.ALLOYING.get();
    }

    private static List<AlloyRecipe.AlloyIngredient> getAlloyList(IFluidStack[] stacks){
        List<AlloyRecipe.AlloyIngredient> list = new ArrayList<>();
        for(IFluidStack stack : stacks){
            list.add(new AlloyRecipe.AlloyIngredient(FluidIngredient.of(new FluidStack(stack.getFluid(), (int) stack.getAmount())),false));
        }
        return list;
    }

    private static FluidOutput getOutPut(IFluidStack stack){
        return FluidOutput.fromStack(stack.getInternal());
    }
}
