package biggestxuan.bxp2.api.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @Author Biggest_Xuan
 * 2025/4/21
 */
public interface ICycleRecipe {
    String getName();

    int getCycle();

    ItemStack getInput();

    ItemStack getOutput();

    Ingredient[] getCala();
}
