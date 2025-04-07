package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.ItemStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/5
 */

public final class Utils {
    public static ItemStack[] IItemStackArray2ItemStackArray(IItemStack[] items) {
        ItemStack[] array = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            array[i] = items[i].getInternal();
        }
        return array;
    }

    public static IItemStack[] ItemStackArray2IItemStackArray(ItemStack[] items) {
        IItemStack[] array = new IItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            array[i] = IItemStack.of(items[i]);
        }
        return array;
    }
}
