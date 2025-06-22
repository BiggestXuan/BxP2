package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Item> getAllProjectEItems(){
        List<Item> list = new ArrayList<>();
        for(Item item : ForgeRegistries.ITEMS.getValues()){
            if(ForgeRegistries.ITEMS.getKey(item) != null && ForgeRegistries.ITEMS.getKey(item).getNamespace().contains("projecte")){
                list.add(item);
            }
        }
        return list;
    }
}
