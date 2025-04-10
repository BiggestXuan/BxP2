package biggestxuan.bxp2.recipes;

import biggestxuan.bxp2.items.BxPCatalyst;
import net.minecraft.world.item.ItemStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/9
 */
public class BXFurnaceRecipe {
    public BXFurnaceRecipe(String namespace,ItemStack[] input, ItemStack[] output,BxPCatalyst.ADAPT catalyst,int time,long energy,int fluid) {
        this.namespace = namespace;
        this.input = input;
        this.output = output;
        this.catalyst = catalyst;
        this.time = time;
        this.energy = energy;
        this.fluid = fluid;
    }

    public final String namespace;
    public final ItemStack[] input;
    public final ItemStack[] output;
    public final BxPCatalyst.ADAPT catalyst;
    public final int time;
    public final long energy;
    public final int fluid;

    public BXFurnaceRecipe copy(){
        return new BXFurnaceRecipe(namespace,input,output,catalyst,time,energy,fluid);
    }
}
