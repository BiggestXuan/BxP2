package biggestxuan.bxp2.integration.CraftTweaker;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

/**
 * @Author Biggest_Xuan
 * 2025/6/25
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTWeightItem")
public class CrTWeightItem {
    @ZenRegister
    @ZenCodeType.Name("mods.bxp2.CrTWeight")
    public static class CrTWeight{
        private int min;
        private int max;
        private int chance;

        @ZenCodeType.Constructor
        public CrTWeight(int min,int max,int chance){
            this.min = min;
            this.max = max;
            this.chance = chance;
        }
    }
    private IIngredient ingredient;
    private CrTWeight weight;

    @ZenCodeType.Constructor
    public CrTWeightItem(IIngredient ingredient,CrTWeight weight){
        this.ingredient = ingredient;
        this.weight = weight;
    }

    @ZenCodeType.Method
    public Map.Entry<Ingredient, IntArrayTag> toEntry(){
        return new Map.Entry<>() {
            @Override
            public Ingredient getKey() {
                return ingredient.asVanillaIngredient();
            }

            @Override
            public IntArrayTag getValue() {
                return new IntArrayTag(new int[]{weight.min,weight.max,weight.chance});
            }

            @Override
            public IntArrayTag setValue(IntArrayTag value) {
                weight.min = value.get(0).getAsInt();
                weight.max = value.get(1).getAsInt();
                weight.chance = value.get(2).getAsInt();
                return getValue();
            }
        };
    }
}
