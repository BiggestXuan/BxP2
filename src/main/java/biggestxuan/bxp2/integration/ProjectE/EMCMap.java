package biggestxuan.bxp2.integration.ProjectE;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.items.BxPItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

/**
 * @Author Biggest_Xuan
 * 2025/5/31
 */
public final class EMCMap {
    private static final Set<EMCInfo> emc = new HashSet<>();

    public static long getEMC(Item item){
        if(Config.difficulty == 3){
            return 0L;
        }

        init();

        for(EMCInfo entry : emc){
            ResourceLocation id1 = ForgeRegistries.ITEMS.getKey(item);
            ResourceLocation id2 = ForgeRegistries.ITEMS.getKey(entry.item);

            if(id1 != null && id1.equals(id2)){
                return entry.emc;
            }
        }
        return 0L;
    }

    public static void init(){
        emc.clear();
        addEMC();
    }

    @SuppressWarnings("all")
    public static void addEMC(){
        emc(Items.IRON_INGOT,1);
        emc(Items.COPPER_INGOT,1);
        emc(Items.GOLD_INGOT,4);
        emc(Items.DIAMOND,16);
        emc(Items.EMERALD,16);
        emc(BxPItems.BX_GOLD_INGOT,4);
        emc(BxP2.getStack("mekanism:dust_lithium"),16);
        emc(BxP2.getStack("mekanism:ingot_steel"),2);
        emc(BxP2.getStack("bxp2:bx_unstable_ingot"),4);
        emc(Items.NETHERITE_INGOT,256);
        emc(BxP2.getStack("mekanism:hdpe_sheet"),16);
        emc(BxP2.getStack("mekanism:alloy_infused"),2);
        emc(BxP2.getStack("mekanism:alloy_reinforced"),3);
        emc(BxP2.getStack("mekanism:alloy_atomic"),24);
        emc(BxP2.getStack("ae2:cell_component_1k"),4);
        emc(BxP2.getStack("ae2:cell_component_4k"),12);
        emc(BxP2.getStack("ae2:cell_component_16k"),36);
        emc(BxP2.getStack("ae2:cell_component_64k"),108);
        emc(BxP2.getStack("ae2:cell_component_256k"),324);
        emc(BxP2.getStack("botania:terrasteel_ingot"),24);
        emc(BxP2.getStack("thermalendergy:stellarium_ingot"),28);
        emc(BxP2.getStack("minecraft:nether_star"),2048);
        emc(BxP2.getStack("bxp2:oumang_ingot"),2210);
        emc(BxP2.getStack("mekanism_extras:alloy_radiance"),1179);
        emc(BxP2.getStack("mekanism:pellet_plutonium"),16384);
        emc(BxP2.getStack("mekanism:pellet_polonium"),16384);
        emc(BxP2.getStack("mekanismscience:pellet_neutron_source"),16384);
        emc(BxP2.getStack("mekanism_extras:alloy_thermonuclear"),9830);
        emc(BxP2.getStack("mekanism:pellet_antimatter"),16384000);
        emc(BxP2.getStack("draconicevolution:draconium_ingot"),128);
        emc(BxP2.getStack("draconicevolution:draconium_core"),794);
        emc(BxP2.getStack("draconicevolution:wyvern_core"),3866);
        emc(BxP2.getStack("bxp2:poly_ingot"),128);
        emc(BxP2.getStack("bxp2:cai_seed"),4);
        emc(BxP2.getStack("bxp2:ou_gold_ingot"),493730);
        emc(BxP2.getStack("bxp2:ench_cai_seed"),503564);
        emc(BxP2.getStack("bxp2:ouhuang_ingot"),566510);
        emc(BxP2.getStack("draconicevolution:dragon_heart"),2048000);
        emc(BxP2.getStack("draconicevolution:awakened_core"),80090);
        emc(BxP2.getStack("draconicevolution:chaos_shard"),49152000);
        emc(BxP2.getStack("draconicevolution:chaotic_core"),27689603);
        emc(BxP2.getStack("draconicevolution:awakened_draconium_ingot"),15644);
        emc(BxP2.getStack("projecte:dark_matter"),1024);
        emc(BxP2.getStack("projecte:red_matter"),74752);
        emc(BxP2.getStack("projecte:red_matter_block"),672768);
        emc(BxP2.getStack("projecte:dark_matter_block"),9216);
        emc(BxP2.getStack("mekanism:basic_control_circuit"),1);
        emc(BxP2.getStack("mekanism:advanced_control_circuit"),3);
        emc(BxP2.getStack("mekanism:advanced_control_circuit"),15);
        emc(BxP2.getStack("mekanism:advanced_control_circuit"),96+15);
        emc(BxPItems.BX_INGOT,256);
        emc(BxPItems.BX_ENCH_INGOT,584039);
        emc(BxPItems.SX_INGOT,5);
    }

    public static void emc(RegistryObject<Item> ro,long e){
        emc(ro.get(),e);
    }

    public static void emc(ItemStack stack,long e){
        emc(stack.getItem(),e);
    }

    public static void emc(Item item, long e){
        emc.add(new EMCInfo(item,e));
    }

    public static class EMCInfo{
        private final Item item;
        private final long emc;
        private final boolean onlyEMC;

        public EMCInfo(Item item,long emc,boolean only){
            this.item = item;
            this.emc = emc;
            this.onlyEMC = only;
        }

        public EMCInfo(Item item,long emc){
            this(item,emc,false);
        }

        public long getEmc() {
            return emc;
        }

        public Item getItem() {
            return item;
        }
    }
}
