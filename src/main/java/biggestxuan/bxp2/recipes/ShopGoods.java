package biggestxuan.bxp2.recipes;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public enum ShopGoods {
    d(BxP2.getStack("minecraft:iron_ingot",32),1),
    a(BxP2.getStack("minecraft:copper_ingot",32),1),
    Ia(BxP2.getStack("minecraft:gold_ingot",16),1),
    s(BxP2.getStack("immersiveengineering:ingot_aluminum",32),1),
    da(BxP2.getStack("minecraft:diamond",6),1.5f),
    I(BxP2.getStack("minecraft:ender_pearl",4),1),
    sI(BxP2.getStack("mekanism:ingot_steel",8),1),
    Is(BxP2.getStack("minecraft:redstone_block",6),1),
    Id(BxP2.getStack("thermal:silver_ingot",16),1),
    ad(BxP2.getStack("mekanism:ingot_osmium",16),1),
    af(BxP2.getStack("mekanism:ingot_uranium",16),2,2),
    ada(BxP2.getStack("mekanism:ingot_lead",16),2,2),
    adta(BxP2.getStack("ae2wtlib:wireless_universal_terminal"),2,30f),
    adhta(BxP2.getStack("ae2:cell_component_64k"),2,21),
    ahdta(BxP2.getStack("ae2:fluix_glass_cable",64),2,6),
    ahta(BxP2.getStack("ae2:fluix_smart_cable",48),2,8),
    hdta(BxP2.getStack("ae2:fluix_smart_dense_cable",16),2,7),
    aht(BxP2.getStack("appflux:core_64k"),2,21),
    h(BxP2.getStack("mekanism:basic_control_circuit"),1,5),
    atd(BxP2.getStack("mekanism:advanced_control_circuit"),2,12),
    adrtta(BxP2.getStack("mekanism:elite_control_circuit"),2,25),
    agrsda(BxP2.getStack("mekanism:alloy_infused"),1,4),
    advra(BxP2.getStack("mekanism:alloy_reinforced"),2,8),
    htsa(BxP2.getStack("ae2:logic_processor",64),1,16),
    atrsa(BxP2.getStack("ae2:calculation_processor",64),1,16),
    jisa(BxP2.getStack("ae2:engineering_processor",64),1,21),
    adaattta(BxP2.getStack("mekanism:hdpe_pellet"),2,10),
    addtata(BxP2.getStack("mekanism:dust_lithium",16),3,8),
    adtata(BxP2.getStack("bloodmagic:strong_tau"),3,18),
    atata(BxP2.getStack("bloodmagic:blankslate"),3,2),
    grtta(BxP2.getStack("bloodmagic:reinforcedslate"),3,3),
    grutta(BxP2.getStack("bloodmagic:infusedslate"),3,6),
    gruta(BxP2.getStack("minecraft:wither_skeleton_skull",3),3,4.5f),
    grttta(BxP2.getStack("bloodmagic:demonslate"),3,9),
    grrtta(BxP2.getStack("bloodmagic:etherealslate"),3,15),
    gra(BxP2.getStack("botania:terrasteel_ingot"),3,15.5f),
    grta(BxP2.getStack("botania:life_essence",4),3,18.5f),
    gua(BxP2.getStack("mekanism:ultimate_control_circuit"),4,50),
    guka(BxP2.getStack("aeinfinitybooster:infinity_card"),4,25),
    g1ua(BxP2.getStack("aeinfinitybooster:dimension_card"),4,35),
    gyua(BxP2.getStack("mekanism:alloy_atomic"),4,16),
    gyta(BxP2.getStack("mekanism:ingot_refined_obsidian"),4,3),
    grtao(BxP2.getStack("irons_spellbooks:common_ink"),4,1),
    gtao(BxP2.getStack("irons_spellbooks:uncommon_ink"),4,4),
    tao(BxP2.getStack("irons_spellbooks:rare_ink"),4,12),
    ga(BxP2.getStack("mekanism:reprocessed_fissile_fragment"),5,8),
    ia(BxP2.getStack("mekanism:pellet_polonium"),5,12),
    grfa(BxP2.getStack("mekanism:pellet_plutonium"),5,12),
    fgra(BxP2.getStack("mekanismscience:pellet_neutron_source"),6,9),
    gfra(BxP2.getStack("mekanism_extras:alloy_radiance"),5,10),
    graf(BxP2.getStack("mekanism_extras:enriched_radiance"),5,11),
    ffgra(BxP2.getStack("mekanism_extras:absolute_control_circuit"),5,26),
    fra(BxP2.getStack("mekanism_extras:alloy_thermonuclear"),6,18),
    fga(BxP2.getStack("mekanism_extras:supreme_control_circuit"),6,40),
    fa(BxP2.getStack("mekanism:pellet_antimatter"),6,150),
    ijda(BxP2.getStack("bxp2:bx_unstable_ingot"),3,0.7f),
    faqw(BxP2.getStack("bxp2:bx_ingot"),5,4),
    aqw(BxP2.getStack("enderio:energetic_alloy_ingot"),6.5f),
    aw(BxP2.getStack("draconicevolution:draconium_ingot",32),7,8),
    aw1(BxP2.getStack("draconicevolution:draconium_ingot",64),7,15),
    aaw(BxP2.getStack("minecraft:netherite_ingot"),7,16),
    qw(BxP2.getStack("minecraft:nether_star"),7,24),
    afqw(BxP2.getStack("draconicevolution:draconium_core"),7,3),
    aqfw(BxP2.getStack("draconicevolution:wyvern_core"),7,22f),
    aaqfw(BxP2.getStack("draconicevolution:wyvern_core",2),8,15),
    aqhfw(BxP2.getStack("draconicevolution:wyvern_core",3),9,8),
    afqfw(BxP2.getStack("draconicevolution:wyvern_core",4),10,5),
    oqufw(BxP2.getStack("bxp2:bx_ench_ingot"),8,66.6f),
    oqfw1(BxP2.getStack("endrem:black_eye"),8,35),
    oqfw2(BxP2.getStack("endrem:cold_eye"),8,35),
    oqfw3(BxP2.getStack("endrem:corrupted_eye"),8,35),
    oqfw4(BxP2.getStack("endrem:lost_eye"),8,35),
    oqfw5(BxP2.getStack("endrem:nether_eye"),8,35),
    oqfw6(BxP2.getStack("endrem:old_eye"),8,35),
    oqfw11(BxP2.getStack("endrem:rogue_eye"),8,35),
    oqfw112(BxP2.getStack("endrem:cursed_eye"),8,35),
    oqfw214(BxP2.getStack("endrem:evil_eye"),8,35),
    oqfw46(BxP2.getStack("endrem:guardian_eye"),8,35),
    oqfw765(BxP2.getStack("endrem:magical_eye"),8,35),
    oqfw867(BxP2.getStack("endrem:wither_eye"),8,35),
    oqfw90(BxP2.getStack("endrem:witch_eye"),8,35),
    oqfw65(BxP2.getStack("endrem:undead_eye"),8,35),
    oqfw45(BxP2.getStack("endrem:exotic_eye"),8,35),
    oqfw55(BxP2.getStack("endrem:cryptic_eye"),8,50),
    oqfw515(BxP2.getStack("minecraft:echo_shard"),9,16),
    oq11fw55(BxP2.getStack("minecraft:dragon_egg"),9,33),
    o1qfw55(BxP2.getStack("draconicevolution:dragon_heart"),9,33),
    oqf55(BxP2.getStack("bxp2:sun_protect_module"),5,50),
    oqf5(BxP2.getStack("bxp2:condense_uranium",16),4,10),
    f55(BxP2.getStack("bxp2:potassium",16),4,10),
    f155(BxP2.getStack("draconicevolution:awakened_core"),10,70),
    f5(BxP2.getStack("draconicevolution:large_chaos_frag"),11,10),
    qf55(BxP2.getStack("draconicevolution:chaotic_core"),11,65),
    fo55(BxP2.getStack("draconicevolution:awakened_core",2),11,33),
    qf155(BxP2.getStack("vampirism:pure_blood_0"),3,35),
    qf255(BxP2.getStack("vampirism:pure_blood_1"),4,35),
    q3f55(BxP2.getStack("vampirism:pure_blood_2"),5,35),
    qf455(BxP2.getStack("vampirism:pure_blood_3"),6,35),
    qf655(BxP2.getStack("vampirism:pure_blood_4"),7,35),
    qf5(BxP2.getStack("thermal:upgrade_augment_1"),2,2.25f),
    q55(BxP2.getStack("thermal:upgrade_augment_2"),2,4.5f),
    f655(BxP2.getStack("thermal:upgrade_augment_3"),2,6.25f),
    f65(BxP2.getStack("thermalendergy:endergy_upgrade_1"),2,8),
    qfg55(BxP2.getStack("thermalendergy:endergy_upgrade_2"),2,10f),
    q655(BxP2.getStack("thermalendergy:endergy_upgrade_3"),3,13f),
    ahw(BxP2.getStack("botania:blacker_lotus"),2,6f),
    ah2w(BxP2.getStack("mekanismgenerators:wind_generator"),2,4.5f),
    ikahf(BxP2.getStack("jerotesvillage:meror_metal_ingot",3),2,7.5f),
    ikqahf(BxP2.getStack("ae2things:disk_drive_64k"),2,40f),
    ikqaf(BxP2.getStack("ae2:wireless_access_point"),1,2.5f),
    kqahf(BxP2.getStack("allthemodium:teleport_pad"),9.5f),
    kqaf(BxP2.getStack("ironfurnaces:million_furnace"),2,30f),
    kq1af(BxP2.getStack("fluxnetworks:flux_dust",32),1,7f),
    ejuni(BxP2.getStack("minecraft:ghast_tear"),3,5f),
    aowii(BxP2.getStack("bxp2:chaos_shield_scroll"),10,35f),
    fa3(BxP2.getStack("bxp2:chaos_health_scroll"),10,35f),
    qqb(BxP2.getStack("bxp2:chaos_crystal_scroll"),10,25f),
    iahf(BxP2.getStack("bxp2:oumang_upgrade"),5,15f),
    ioaw(BxP2.getStack("bxp2:ou_gold_upgrade"),5,18f),
    iawur(BxP2.getStack("bxp2:ouhuang_upgrade"),6,25f),
    //ihhy(BxP2.getTconstructCreative("upgrades"),0,10f,true),
    //ihhay(BxP2.getTconstructCreative("defense"),0,10f,true),
   // iahhy(BxP2.getTconstructCreative("abilities"),0,15f,true),
   // aihhay(BxP2.getTconstructCreative("souls"),0,12.5f,true),
    ;

    private final ItemStack stack;
    private final int phase;
    private final float price;
    private final boolean isCreative;

    ShopGoods(ItemStack stack, int phase, float price,boolean isCreative){
        this.stack = stack;
        this.phase = phase;
        this.price = price;
        this.isCreative = isCreative;
    }

    ShopGoods(ItemStack stack, int phase, float price){
        this(stack,phase,price,false);
    }

    ShopGoods(ItemStack stack, int count,int phase, float price){
        this(stack,phase,price,false);
    }

    ShopGoods(Item item, int phase, float price){
        this(item.getDefaultInstance(),phase,price,false);
    }

    ShopGoods(Item item, float price){
        this(item,-1,price);
    }

    ShopGoods(ItemStack item, float price){
        this(item,-1,price,false);
    }

    ShopGoods(String rl, int phase, float price){
        this(BxP2.getStack(rl),phase,price,false);
    }

    /*
    * 阶段说明
    * -1：未初始化阶段。
    * 0：进入游戏后，如果为-1将变为该值。
    * 1：获取不稳定大轩锭后为该值。
    * 2：制作精英控制电路后为该值。
    * 3：制作大轩锭后为该值。
    * 4：制作终极控制电路后为该值。
    * 5：制作钋靶丸、钚靶丸、中子源靶丸其一为该值。
    * 6：制作至尊控制电路后为该值。
    * 7：获取飞龙核心后为该值。
    * 8：获取附魔大轩锭后为该值。
    * 9：获得龙心后为该值。
    * 10：获得神龙核心后为该值。
    * 11：获得混沌核心后为该值。
    * 12：获得超级大轩锭后为该值。
    *
    * */
    public int getPhase() {
        return phase;
    }

    public float getPrice() {
        return price;
    }

    public ItemStack getStack() {
        return stack.copy();
    }

    public static List<ShopGoods> getAllItems() {
        List<ShopGoods> list = new ArrayList<>();
        list.addAll(Arrays.asList(values()));
        list.sort(Comparator.comparingInt(ShopGoods::getPhase).thenComparingDouble(ShopGoods::getPrice));
        return list;
    }

    public boolean isCreative(){
        return isCreative;
    }

    @Override
    public String toString() {
        return stack.toString()+"_"+price;
    }
}
