package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/4/21
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CycleRecipe")
public class CrTCombineCycleRecipe {
    private final IItemStack input;
    private final IIngredient[] cala;
    private final IItemStack output;
    private final int cycle;
    private final String name;

    @ZenCodeType.Constructor
    public CrTCombineCycleRecipe(IItemStack input, IIngredient[] cala, IItemStack output, int cycle, String name){
        this.cycle = cycle;
        this.cala = cala;
        this.input = input;
        this.output = output;
        this.name = name;
    }

    @ZenCodeType.Method
    public String getName(){
        return name;
    }

    @ZenCodeType.Method
    public int getCycle() {
        return cycle;
    }

    @ZenCodeType.Method
    public IItemStack getInput() {
        return input;
    }

    @ZenCodeType.Method
    public IItemStack getOutput() {
        return output;
    }

    @ZenCodeType.Method
    public IIngredient[] getCala() {
        return cala;
    }
}
