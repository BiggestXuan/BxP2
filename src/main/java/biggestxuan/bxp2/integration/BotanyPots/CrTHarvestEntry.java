package biggestxuan.bxp2.integration.BotanyPots;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.darkhax.botanypots.data.recipes.crop.HarvestEntry;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/5/4
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.HarvestEntry")
public class CrTHarvestEntry {
    private final float chance;
    private final IItemStack item;

    @ZenCodeType.Constructor
    public CrTHarvestEntry(float chance, IItemStack item) {
        this.chance = chance;
        this.item = item;
    }

    public HarvestEntry build(){
        return new HarvestEntry(chance,item.getInternal(),1,1);
    }

    public float getChance() {
        return this.chance;
    }

    public IItemStack getItem() {
        return this.item;
    }
}
