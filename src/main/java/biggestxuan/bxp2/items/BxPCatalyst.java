package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/7
 */
public class BxPCatalyst{
    public final ItemStack stack;
    public double speedRate;
    public double energyRate;
    public double inputFluidRate;
    public double outputFluidRate;
    public double inputManaRate;
    public double outputManaRate;
    public final ADAPT[] adapt;

    public BxPCatalyst(ItemStack stack, double speedRate, double energyRate,double inputFluidRate,double outputFluidRate,double inputManaRate,double outputManaRate,ADAPT[] adapt){
        this.stack = stack;
        this.speedRate = speedRate;
        this.energyRate = energyRate;
        this.inputFluidRate = inputFluidRate;
        this.outputFluidRate = outputFluidRate;
        this.inputManaRate = inputManaRate;
        this.outputManaRate = outputManaRate;
        this.adapt = adapt;
    }

    public BxPCatalyst(ItemStack stack, double speedRate, double energyRate){
        this(stack,speedRate,energyRate,1,1,1,1,new ADAPT[]{ADAPT.ALL});
    }

    public BxPCatalyst(ItemStack stack, double speedRate, double energyRate,boolean isBXBase){
        this(stack,speedRate,energyRate,0,0,0,0,new ADAPT[]{ADAPT.BX_FURNACE});
    }

    public BxPCatalyst copy(){
        return new BxPCatalyst(stack,speedRate,energyRate,inputFluidRate,outputFluidRate,inputManaRate,outputManaRate,adapt);
    }

    public static String getAdaptNames(ADAPT[] adapt){
        if(adapt.length == 1){
            return getAdaptName(adapt[0]);
        }
        StringBuilder sb = new StringBuilder();
        for(ADAPT d : adapt){
            sb.append(BxP2.tr(getAdaptName(d)).getString());
            sb.append(" ");
        }
        return BxP2.tr(sb.toString()).getString();
    }

    public static String getAdaptName(ADAPT adapt){
        String s;
        switch(adapt){
            case BX_UNSTABLE_FURNACE -> s = "mbd2.bx_unstable_furnace";
            case BX_FURNACE -> s = "block.mbd2.bx_furnace";
            case BX_ENCH_FURNACE -> s = "bx_ench_furnace";
            case ALL -> s = "bxp2.catalyst.adapt_all";
            default -> s = "bxp2.catalyst.adapt_unknown";
        }
        return BxP2.tr(s).getString();
    }

    public enum ADAPT{
        BX_UNSTABLE_FURNACE,BX_FURNACE,BX_ENCH_FURNACE,ALL,NONE
    }

    public static List<BxPCatalyst> getAllCatalyst(ADAPT adapt){
        List<BxPCatalyst> catalysts = new ArrayList<>();
        for(Catalysts c : Catalysts.values()){
            if(c.c.stack == null || c.c.stack.equals(ItemStack.EMPTY)){
                continue;
            }
            if(c.c().adaptRecipe(adapt)){
                BxPCatalyst a = c.c.copy();
                if(Config.difficulty == 1){
                    a.speedRate *= a.speedRate;
                    a.energyRate *= a.energyRate;
                    a.inputFluidRate *= a.inputFluidRate;
                    a.outputFluidRate *= a.outputFluidRate;
                    a.inputManaRate *= a.inputManaRate;
                    a.outputManaRate *= a.outputManaRate;
                }
                if(Config.difficulty == 3){
                    a.speedRate = Math.sqrt(a.speedRate);
                    a.energyRate = Math.sqrt(a.energyRate);
                    a.inputFluidRate = Math.sqrt(a.inputFluidRate);
                    a.outputFluidRate = Math.sqrt(a.outputFluidRate);
                    a.inputManaRate = Math.sqrt(a.inputManaRate);
                    a.outputManaRate = Math.sqrt(a.outputManaRate);
                }
                catalysts.add(a);
            }
        }
        return catalysts;
    }

    public boolean adaptRecipe(ADAPT adapt){
        for(ADAPT a : this.adapt){
            if(adapt == ADAPT.ALL || a == adapt){
                return true;
            }
        }
        return false;
    }

    public enum Catalysts{
        //NONE(new BxPCatalyst(ItemStack.EMPTY,0,0,0,0,0,0,new ADAPT[]{ADAPT.NONE})),
        HELL_INGOT(new BxPCatalyst(BxP2.getStack("bloodmagic:ingot_hellforged"),0.75,0.8,true)),
        AE_CELL_64(new BxPCatalyst(BxP2.getStack("ae2:cell_component_64k"),0.64,0.66,true)),
        ESS_5(new BxPCatalyst(BxP2.getStack("mysticalagriculture:supremium_essence"),0.69,0.71,true)),
        ESS_4(new BxPCatalyst(BxP2.getStack("mysticalagriculture:imperium_essence"),0.52,0.57,true)),
        ENCH_GOLDEN_APPLE(new BxPCatalyst(BxP2.getStack("minecraft:enchanted_golden_apple"),0.45,0.6,true)),
        //REFINED_OBSIDIAN(new BxPCatalyst(BxP2.getStack("mekanism:ingot_refined_obsidian"),0.75,0.82,true)),
        DIAMOND(new BxPCatalyst(BxP2.getStack("minecraft:diamond"),0.75,0,0.8,0,0,0,new ADAPT[]{ADAPT.BX_UNSTABLE_FURNACE})),
        EMERALD(new BxPCatalyst(BxP2.getStack("minecraft:emerald"),0.75,0,0.8,0,0,0,new ADAPT[]{ADAPT.BX_UNSTABLE_FURNACE})),
        SLATE(new BxPCatalyst(BxP2.getStack("bloodmagic:infusedslate"),0.58,0,0.55,0,0,0,new ADAPT[]{ADAPT.BX_UNSTABLE_FURNACE})),
        HOT_BLOOD(new BxPCatalyst(BxP2.getStack("twilightforest:fiery_blood"),0.65,0,0.65,0,0,0,new ADAPT[]{ADAPT.BX_UNSTABLE_FURNACE})),
        HOT_TEAR(new BxPCatalyst(BxP2.getStack("twilightforest:fiery_tears"),0.65,0,0.65,0,0,0,new ADAPT[]{ADAPT.BX_UNSTABLE_FURNACE})),
        PLAGUE_SCYTHE(new BxPCatalyst(BxP2.getStack("rats:plague_scythe"),0.45,0.49,true)),
        SPIRITED(new BxPCatalyst(BxP2.getStack("powah:capacitor_spirited"),0.62,0.7,true))
        ;
        private final BxPCatalyst c;

        Catalysts(BxPCatalyst c){
            this.c = c;
        }

        public BxPCatalyst c() {
            return c;
        }
    }
}
