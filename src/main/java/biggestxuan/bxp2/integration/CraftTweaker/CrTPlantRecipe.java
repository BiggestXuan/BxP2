package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.integration.BotanyPots.CrTHarvestEntry;
import biggestxuan.bxp2.recipes.RecipeUtils;
import cofh.lib.common.fluid.FluidIngredient;
import cofh.thermal.core.util.recipes.machine.InsolatorRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.displaystate.SimpleDisplayState;
import net.darkhax.botanypots.data.recipes.crop.BasicCrop;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author Biggest_Xuan
 * 2025/5/5
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.PlantRecipe")
public class CrTPlantRecipe {
    private final IIngredient input;
    private final int tick;
    private final int energy;
    private final CrTHarvestEntry[] entries;
    private final String[] dirt;

    @ZenCodeType.Constructor
    public CrTPlantRecipe(IIngredient input,int tick,int energy,CrTHarvestEntry[] entries,String[] dirt){
        this.input = input;
        this.tick = tick;
        this.energy = energy;
        this.entries = entries;
        this.dirt = dirt;
    }

    public InsolatorRecipe toInsolatorRecipe(){
        return new InsolatorRecipe(
                RecipeUtils.getRecipeNameByRL(getStackArray(entries),"te"),
                energy,
                1f,
                getIngredientList(input),
                water(),
                getOutputList(),
                getOutputChance(),
                getOutWater()
        );
    }

    private List<FluidStack> getOutWater(){
        List<FluidStack> list = new ArrayList<>();
        list.add(new FluidStack(Fluids.WATER,1000));
        return list;
    }

    public BasicCrop toBotanyRecipe(){
        return new BasicCrop(
                RecipeUtils.getRecipeNameByRL(getStackArray(entries),"botany"),
                input.asVanillaIngredient(),
                Set.of(dirt),
                tick,
                getResultList(entries),
                getStateList(Blocks.WHEAT.defaultBlockState()),15
        );
    }

    private List<Float> getOutputChance(){
        List<Float> list = new ArrayList<>();
        for(var entry : entries){
            list.add(entry.getChance());
        }
        return list;
    }

    private List<ItemStack> getOutputList(){
        List<ItemStack> list = new ArrayList<>();
        for(var entry : entries){
            list.add(entry.getItem().getImmutableInternal());
        }
        return list;
    }

    private static List<FluidIngredient> water(){
        List<FluidIngredient> list = new ArrayList<>();
        list.add(FluidIngredient.of(new FluidStack(Fluids.WATER,1000)));
        return list;
    }

    private static List<Ingredient> getIngredientList(IIngredient input){
        List<Ingredient> list = new ArrayList<>();
        list.add(input.asVanillaIngredient());
        return list;
    }

    private static List<DisplayState> getStateList(BlockState state){
        List<DisplayState> list = new ArrayList<>();
        list.add(new SimpleDisplayState(Blocks.WHEAT.defaultBlockState()));
        return list;
    }

    private static List<HarvestEntry> getResultList(CrTHarvestEntry[] entries){
        List<HarvestEntry> list = new ArrayList<>();
        for(CrTHarvestEntry ct : entries){
            list.add(ct.build());
        }
        return list;
    }

    private static ItemStack[] getStackArray(CrTHarvestEntry[] entries){
        ItemStack[] arr = new ItemStack[entries.length];
        for (int i = 0; i < entries.length; i++) {
            arr[i] = entries[i].build().getItem();
        }
        return arr;
    }
}
