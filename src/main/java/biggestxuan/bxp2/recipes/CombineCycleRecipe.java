package biggestxuan.bxp2.recipes;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.recipes.ICycleRecipe;
import biggestxuan.bxp2.integration.CraftTweaker.CrTCombineCycleRecipe;
import biggestxuan.bxp2.items.BxPItems;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @Author Biggest_Xuan
 * 2025/4/21
 */
public enum CombineCycleRecipe implements ICycleRecipe {
    WYVERN_CORE(
            BxPItems.BX_INGOT.get().getDefaultInstance(),
            new Ingredient[]{
                    BxP2.getIngredient("draconicevolution:draconium_core"),
                    BxP2.getIngredient("draconicevolution:draconium_core"),
                    BxP2.getIngredient("draconicevolution:draconium_ingot"),
                    BxP2.getIngredient("draconicevolution:draconium_ingot"),
                    BxP2.getIngredient("mysticalagradditions:nether_star_shard"),
            },
            BxP2.getStack("draconicevolution:wyvern_core"),
            5,"wyvern_core")
    ;
    private final ItemStack input;
    private final Ingredient[] cala;
    private final ItemStack output;
    private final int cycle;
    private final String name;

    CombineCycleRecipe(ItemStack input,Ingredient[] cala,ItemStack output,int cycle,String name){
        this.cycle = cycle;
        this.cala = cala;
        this.input = input;
        this.output = output;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCycle() {
        return cycle;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public Ingredient[] getCala() {
        return cala;
    }

    public CrTCombineCycleRecipe getCrTRecipe(){
        IIngredient[] array = new IIngredient[cala.length];
        for (int i = 0; i < cala.length; i++) {
            array[i] = IIngredient.fromIngredient(cala[i]);
        }
        return new CrTCombineCycleRecipe(
                IItemStack.of(input),array,IItemStack.of(output),cycle,name
        );
    }
}
