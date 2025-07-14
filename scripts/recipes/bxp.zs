#priority 100

import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipe.replacement.Replacer;
import crafttweaker.api.fluid.IFluidStack;

import mods.bxp2.CrTManager;
import mods.bxp2.CrTConfig;
import mods.bxp2.HarvestEntry;
import mods.bxp2.PlantRecipe;
import mods.bxp2.CrTWeight;
import mods.bxp2.CrTWeightItem;

var a = <item:minecraft:air>;
var base_chance as float = CrTConfig.getOuhuangChance();

var czHarvestEntry as HarvestEntry = new HarvestEntry(1f,<item:bxp2:cai_seed>);
var caigengzi3 as HarvestEntry = new HarvestEntry(base_chance,<item:bxp2:cai_seed>);
var caigengzi1 as HarvestEntry = new HarvestEntry(1f,<item:bxp2:caigengzi>);
var caigengzi2 as HarvestEntry = new HarvestEntry(0.4f,<item:bxp2:caigengzi>);
var caigengzi4 as HarvestEntry = new HarvestEntry(0.9f,<item:bxp2:ench_cai_seed>);
var caigengzi5 as HarvestEntry = new HarvestEntry(base_chance,<item:bxp2:ouhuang_ingot>);
var caigengzi6 as HarvestEntry = new HarvestEntry(1f,<item:bxp2:caigengzi> * 32);

public function addRatsRecipe(input as IIngredient,output as IItemStack) as void{
    <recipetype:rats:archeologist>.addRecipe(input,output);
}

public function transRatsRecipe(input as IItemStack,output as IItemStack) as void{
    addRatsRecipe(input,output);
    addRatsRecipe(output,input);
}

public function alloyRecipe(input as IFluidStack[],output as IFluidStack,temp as int) as void{
    <recipetype:tconstruct:alloying>.addRecipe(input,output,temp);
}

public function addPlantRecipe(recipe as PlantRecipe) as void{
    <recipetype:botanypots:crop>.addRecipe(recipe);
    addInsolatorRecipe(recipe);
}

public function addInsolatorRecipe(recipe as PlantRecipe) as void{
    <recipetype:thermal:insolator>.addRecipe(recipe);
}

var caiRecipe = new PlantRecipe(<item:bxp2:cai_seed>,200,10000,[czHarvestEntry,caigengzi1,caigengzi2,caigengzi3],["dirt"]);
addPlantRecipe(caiRecipe);
addInsolatorRecipe(new PlantRecipe(<item:bxp2:ench_cai_seed>,200,30000,[caigengzi4,caigengzi5,caigengzi6],["dirt"]));

var eyes = [
    <item:endrem:black_eye>,
    <item:endrem:cold_eye>,
    <item:endrem:lost_eye>,
    <item:endrem:corrupted_eye>,
    <item:endrem:nether_eye>,
    <item:endrem:rogue_eye>,
    <item:endrem:old_eye>,
    <item:endrem:cursed_eye>,
    <item:endrem:evil_eye>,
    <item:endrem:guardian_eye>,
    <item:endrem:magical_eye>,
    <item:endrem:wither_eye>,
    <item:endrem:witch_eye>,
    <item:endrem:exotic_eye>,
    <item:endrem:undead_eye>,
    <item:endrem:cryptic_eye>
];

for i in 0 .. eyes.length{
    <tag:items:bxp2:ender_eyes>.add(eyes[i]);
}

var steel = <item:mekanism:ingot_steel>;

addCraftRecipe([
    [steel,steel,steel],
    [steel,<item:thermal:machine_frame>,steel],
    [steel,steel,steel]
],<item:mbd2:bx_simple_power>,"bxp2");

addRatsRecipe(<item:bxp2:bx_unstable_ingot>,<item:bxp2:sx_ingot>);

var eib = <item:extendedcrafting:ender_ingot_block>;
/*
var mbds = [
    <item:mbd2:item_input>,
    <item:mbd2:item_output>,
    <item:mbd2:energy_input>,
    <item:mbd2:energy_output>,
    <item:minecraft:emerald_block>,
    <item:minecraft:redstone_block>,
    <item:tinkers_thinking:chlorophyte_block>,
    <item:thermal:ruby_block>
];


for i in 0 .. 4{
    addCraftRecipe([
        [eib,eib,eib],
        [eib,mbds[i+4],eib],
        [eib,eib,eib]
    ],mbds[i],"bxp");
}
*/
transRatsRecipe(<item:jerotesvillage:meror_metal_ingot>,<item:ad_astra:calorite_ingot>);

public function euRecipe(a as IIngredient,b as IIngredient,c as IIngredient,d as IIngredient,e as IItemStack) as void{
    addCraftRecipe([
        [a,b,a],
        [c,d,c],
        [a,b,a]
    ],e,"bxp2");
}

public function nuRecipe(a as IIngredient,b as IIngredient,c as IIngredient,d as IIngredient,e as IItemStack) as void{
    addCraftRecipe([
        [a,b,a],
        [d,c,d],
        [a,d,a]
    ],e,"bxp2");
}

public function upgradeRecipe(a as IIngredient,b as IIngredient,c as IIngredient,d as IIngredient,e as IItemStack) as void{
    if(difficulty() == 1){
        euRecipe(a,b,c,d,e);
    }else{
        nuRecipe(a,b,c,d,e);
    }
}

public function removeAlloyRecipe(name as string) as void{
    <recipetype:tconstruct:alloying>.removeRecipeByName(name);
}

euRecipe(<item:bxp2:bx_ingot>,<item:bxp2:caigengzi>,<item:mekanism:elite_control_circuit>,<item:thermalendergy:endergy_upgrade_3>,<item:bxp2:bx_upgrade>);
euRecipe(<item:bxp2:oumang_ingot>,<item:tinkers_thinking:silky_jewel>,<item:mekanism:ultimate_control_circuit>,<item:bxp2:bx_upgrade>,<item:bxp2:oumang_upgrade>);
upgradeRecipe(<item:bxp2:ou_gold_ingot>,<item:mekanismscience:pellet_neutron_source>,<item:mekanism_extras:absolute_control_circuit>,<item:bxp2:oumang_upgrade>,<item:bxp2:ou_gold_upgrade>);
upgradeRecipe(<item:bxp2:ouhuang_ingot>,<item:mekanism:pellet_antimatter>,<item:mekanism_extras:supreme_control_circuit>,<item:bxp2:ou_gold_upgrade>,<item:bxp2:ouhuang_upgrade>);
alloyRecipe([<fluid:tinkers_thinking:molten_electrical_steel> * 30,<fluid:tinkers_thinking:molten_tinkers_bronze> * 20,<fluid:tinkers_thinking:molten_obsidian_bronze> * 20,<fluid:tconstruct:molten_amethyst_bronze> * 20],<fluid:bxp2:molten_umbra_amethyst_brass> * 90,900);
alloyRecipe([<fluid:tconstruct:molten_rose_gold> * 10,<fluid:tconstruct:molten_slimesteel> * 30,<fluid:tconstruct:molten_invar> * 20,<fluid:tconstruct:molten_pewter> * 30],<fluid:bxp2:molten_stainless_steel> * 90,900);
alloyRecipe([<fluid:tconstruct:molten_enderium> * 30,   <fluid:tconstruct:molten_signalum> * 30,<fluid:tconstruct:molten_lumium> * 30],<fluid:bxp2:molten_seismite> * 90,900);
alloyRecipe([<fluid:tconstruct:molten_silver> * 45,<fluid:tconstruct:molten_nickel> * 45,<fluid:tconstruct:molten_osmium> * 45,<fluid:tconstruct:molten_uranium> * 45],<fluid:bxp2:molten_necrosilver> * 180,900);
alloyRecipe([<fluid:bxp2:molten_umbra_amethyst_brass>*45,<fluid:bxp2:molten_stainless_steel>*45,<fluid:bxp2:molten_seismite> *45,<fluid:bxp2:molten_necrosilver>*45],<fluid:bxp2:molten_poly>*180,1000);
removeAlloyRecipe("tconstruct:smeltery/alloys/molten_lumium");
removeAlloyRecipe("tconstruct:smeltery/alloys/molten_enderium");
alloyRecipe([<fluid:tconstruct:molten_tin> * 270,<fluid:tconstruct:molten_silver> * 90,<fluid:thermal:glowstone> * 500],<fluid:tconstruct:molten_lumium> * 360,999);
alloyRecipe([<fluid:tconstruct:molten_lead> * 270,<fluid:tconstruct:molten_diamond> * 100,<fluid:tconstruct:molten_ender> * 500],<fluid:tconstruct:molten_enderium> * 180,999);
alloyRecipe([<fluid:bxp2:plague_essence> * 10,<fluid:bxp2:molten_bx> * 30],<fluid:bxp2:molten_plague_metal> * 30,1200);
upgradeRecipe(<item:bxp2:wyvern_ingot>,<item:minecraft:nether_star>,<item:mekanism_extras:supreme_control_circuit>,<item:bxp2:ouhuang_upgrade>,<item:bxp2:wyvern_upgrade>);
upgradeRecipe(<item:bxp2:bx_ench_ingot>,<item:mekanism:pellet_antimatter>,<item:mekanism_extras:supreme_control_circuit>,<item:bxp2:wyvern_upgrade>,<item:bxp2:ench_bx_upgrade>);
upgradeRecipe(<item:bxp2:draconium_ingot>,<item:draconicevolution:dragon_heart>,<item:mekanism_extras:cosmic_control_circuit>,<item:bxp2:ench_bx_upgrade>,<item:bxp2:draconium_upgrade>);
upgradeRecipe(<item:bxp2:chaotic_ingot>,<item:draconicevolution:chaos_shard>,<item:mekanism_extras:infinite_control_circuit>,<item:bxp2:draconium_upgrade>,<item:bxp2:chaotic_upgrade>);
upgradeRecipe(<item:extendedcrafting:the_ultimate_ingot>,<item:ae2_mega_things:item_disk_drive_256m>,<item:mekanism_extras:infinite_control_circuit>,<item:bxp2:chaotic_upgrade>,<item:bxp2:final_upgrade>);
var ups = [<item:thermalendergy:endergy_upgrade_1>,<item:thermalendergy:endergy_upgrade_2>,<item:thermalendergy:endergy_upgrade_3>];
var ups1 = [<item:thermal:upgrade_augment_2>,<item:thermal:upgrade_augment_3>,<item:minecraft:debug_stick>];
for i in 0 .. ups.length{
    removeCraftRecipe(ups[i]);
    if(difficulty() == 3){
        removeCraftRecipe(ups1[i]);
    }
}
euRecipe(<item:thermalendergy:prismalium_ingot>,<item:minecraft:ender_eye>,<item:thermal:enderium_gear>,<item:thermal:upgrade_augment_3>,<item:thermalendergy:endergy_upgrade_1>);
euRecipe(<item:thermalendergy:melodium_ingot>,<item:minecraft:shulker_shell>,<item:thermalendergy:prismalium_gear>,<item:thermalendergy:endergy_upgrade_1>,<item:thermalendergy:endergy_upgrade_2>);
euRecipe(<item:thermalendergy:stellarium_ingot>,<item:minecraft:clay>,<item:thermalendergy:melodium_gear>,<item:thermalendergy:endergy_upgrade_2>,<item:thermalendergy:endergy_upgrade_3>);
addShaplessRecipe([<item:vampirism:blood_sieve>],<item:mbd2:common_food_to_blood>,"bxp2");
alloyRecipe([<fluid:vampirism:blood> * 600,<fluid:tconstruct:molten_iron> * 90],<fluid:bxp2:molten_blood_iron> * 90,700);
alloyRecipe([<fluid:tconstruct:molten_iron> * 90,<fluid:minecraft:water> * 2000],<fluid:bxp2:molten_omite> * 90,30);
if(difficulty() == 3){
    upgradeRecipe(<item:thermal:electrum_ingot>,<item:minecraft:quartz>,<item:thermal:signalum_gear>,<item:thermal:upgrade_augment_1>,<item:thermal:upgrade_augment_2>);
    upgradeRecipe(<item:thermal:enderium_ingot>,<item:thermal:obsidian_glass>,<item:thermal:lumium_gear>,<item:thermal:upgrade_augment_2>,<item:thermal:upgrade_augment_3>);
}
alloyRecipe([<fluid:bxp2:molten_polonium> * 10,<fluid:bxp2:molten_plutonium> * 10,<fluid:bxp2:molten_neutron> * 10,<fluid:bxp2:molten_bx> * 10],<fluid:bxp2:molten_radiation_metal> * 10,3500);

for i in CrTManager.getProjecteItems(){
    removeCraftRecipe(i);
}

combineRecipe(<item:minecraft:copper_ingot>*16,<tag:items:forge:cheese>,<item:rats:oratchalcum_ingot>);
addShaplessRecipe([<item:bxp2:bx_unstable_ingot>,<item:bxp2:bx_gold_ingot>],<item:bxp2:bx_shop>,"");
addCraftRecipe([
    [a,<item:mekanism:mekasuit_helmet>,a],
    [<item:mekanism:mekasuit_bodyarmor>,<item:bxp2:bx_ingot>,<item:mekanism:mekasuit_pants>],
    [a,<item:mekanism:mekasuit_boots>,a]
],<item:bxp2:meka_cube> * 4,"");

var bui = <item:bxp2:bx_unstable_ingot>;
var be = <item:mysticalagriculture:unstable_bx_essence>;
var pe = <item:mysticalagriculture:poly_essence>;
var bxe = <item:mysticalagriculture:bx_essence>;
var ebxe = <item:mysticalagriculture:ench_bx_essence>;

rrecipe(be,<item:minecraft:iron_ingot>,bui * dm(6));
rrecipe(pe,bui,<item:bxp2:poly_ingot>*dm(3));
rrecipe(bxe,<item:bxp2:bx_unstable_ingot>,<item:bxp2:bx_ingot>*dm(2));
rrecipe(ebxe,<item:bxp2:bx_ingot>,<item:bxp2:bx_ench_ingot>);
rrecipe(<item:mysticalagriculture:oumang_essence>,<item:bxp2:bx_unstable_ingot>,<item:bxp2:oumang_ingot>);
rrecipe(<item:mysticalagriculture:ou_gold_essence>,<item:bxp2:oumang_ingot>,<item:bxp2:ou_gold_ingot>);
rrecipe(<item:mysticalagriculture:sx_essence>,<item:bxp2:bx_unstable_ingot>,<item:bxp2:sx_ingot>*dm(4));

public function dm(amt as int) as int{
    return amt / difficulty();
}

public function rrecipe(i as IIngredient,i1 as IIngredient,output as IItemStack) as void{
    addCraftRecipe([
        [i,i,i],
        [i,i1,i],
        [i,i,i]
    ],output,"");
}

public function blockRecipe(ingot as IItemStack,block as IItemStack) as void{
    addCraftRecipe([
        [ingot,ingot,ingot],
        [ingot,ingot,ingot],
        [ingot,ingot,ingot]
    ],block,"");
    addShaplessRecipe([block],ingot*9,"");
}

public function eachRecipe(i as IItemStack,i1 as IItemStack) as void{
    addShaplessRecipe([i],i1,"");
    addShaplessRecipe([i1],i,"");
}

public function projectECommonRecipe() as void{
    addShaplessRecipe([<item:minecraft:coal>,<item:minecraft:blaze_powder>],<item:projecte:mobius_fuel>,"");
    var list = [
        <item:projecte:mobius_fuel>,
        <item:projecte:aeternalis_fuel>,
        <item:projecte:dark_matter>,
        <item:projecte:red_matter>,
        <item:projecte:mobius_fuel_block>,
        <item:projecte:aeternalis_fuel_block>,
        <item:projecte:dark_matter_block>,
        <item:projecte:red_matter_block>
    ] as IItemStack[];
    for i in 0 .. 4{
        blockRecipe(list[i],list[i+4]);
    }
    infusionRecipe(list[0],<infuse_type:bxp2:bx> * 40,list[1]);
    var m = difficulty() == 2 ? <item:draconicevolution:chaotic_core> : <item:bxp2:bx_ingot>;
    var r = <item:projecte:red_matter_block>;
    DEFusionRecipe(m,[r,r,r,r],<item:projecte:transmutation_table>,difficulty(),200000000);
    eachRecipe(<item:projecte:transmutation_table>,<item:projecte:transmutation_tablet>);
}

public function projectEEasyRecipe() as void{
    combineRecipe(<item:projecte:aeternalis_fuel>,<item:bxp2:bx_ingot>,<item:projecte:dark_matter>);
    combineRecipe(<item:projecte:dark_matter_block>,<item:bxp2:bx_ingot>,<item:projecte:red_matter>);
}

public function projectENormalRecipe() as void{
    var bx = <item:bxp2:bx_ingot>;
    var r = <tag:items:bxp2:radiation_pellet>;
    DEFusionRecipe(<item:projecte:aeternalis_fuel>,[bx,bx,bx,bx],<item:projecte:dark_matter>,1,1000000);
    DEFusionRecipe(<item:projecte:dark_matter_block>,[r,r,r,r],<item:projecte:red_matter>,2,100000000);
}

var ie = <item:mysticalagradditions:insanium_essence>;
var ee = <item:bxp2:epic_essence>;

if(difficulty() == 3){
    DEFusionRecipe(<item:minecraft:nether_star>,[ie,ie,ie,ie],<item:bxp2:epic_essence>*4,1,1000000);
    DEFusionRecipe(<item:draconicevolution:medium_chaos_frag>,[ee,ee,ee,ee],<item:bxp2:final_essence>*4,2,2000000000);
}else{
    projectECommonRecipe();
    DEFusionRecipe(<item:minecraft:nether_star>,[ie,ie,ie,ie,ie,ie,ie,ie],<item:bxp2:epic_essence>*8,1,500000);
    DEFusionRecipe(<item:draconicevolution:medium_chaos_frag>,[ee,ee,ee,ee,ee,ee,ee,ee],<item:bxp2:final_essence>*8,2,1000000000);
    if(difficulty() == 2){
        projectENormalRecipe();
    }
    if(difficulty() == 1){
        projectEEasyRecipe();
    }
}

public function combRecipe(name as string,weight as CrTWeight) as void{
    <recipetype:productivebees:advanced_beehive>.addCommonRecipe(name,weight);
}

public function combUsing(name as string,item as CrTWeightItem,time as int) as void{
    <recipetype:productivebees:centrifuge>.addCommonRecipe(name,item,time);
}

public function addBeeRecipe(name as string,weight as CrTWeight,output as CrTWeightItem,time as int) as void{
    combRecipe(name,weight);
    combUsing(name,output,time);
}

var l1 as CrTWeight = new CrTWeight(1,3,70);
var l2 as CrTWeight = new CrTWeight(1,3,30);
var l3 as CrTWeight = new CrTWeight(1,2,40);
var l4 as CrTWeight = new CrTWeight(1,2,20);
var l5 as CrTWeight = new CrTWeight(1,1,30);
var l6 as CrTWeight = new CrTWeight(1,1,10);

addBeeRecipe("bxp2:unstable_bx",l1,new CrTWeightItem(<item:mysticalagriculture:unstable_bx_essence>,l2),20);
addBeeRecipe("bxp2:sx",l2,new CrTWeightItem(<item:mysticalagriculture:sx_essence>,l3),20);
addBeeRecipe("bxp2:oumang",l3,new CrTWeightItem(<item:mysticalagriculture:oumang_essence>,l4),20);
addBeeRecipe("bxp2:poly",l3,new CrTWeightItem(<item:mysticalagriculture:poly_essence>,l3),20);
addBeeRecipe("bxp2:bx",l4,new CrTWeightItem(<item:mysticalagriculture:bx_essence>,l4),20);
addBeeRecipe("bxp2:ou_gold",l5,new CrTWeightItem(<item:mysticalagriculture:ou_gold_essence>,l5),20);
addBeeRecipe("bxp2:ench_bx",l6,new CrTWeightItem(<item:mysticalagriculture:ench_bx_essence>,l6),20);

var blist = [
    <item:bxp2:bx_ingot>,
    <item:bxp2:poly_ingot>,
    <item:bxp2:bx_unstable_ingot>,
    <item:bxp2:oumang_ingot>,
    <item:bxp2:ou_gold_ingot>,
    <item:bxp2:umbra_amethyst_brass_ingot>,
    <item:bxp2:bx_ench_ingot>,
    <item:bxp2:bx_block>,
    <item:bxp2:poly_block>,
    <item:bxp2:unstable_bx_block>,
    <item:bxp2:oumang_block>,
    <item:bxp2:ou_gold_block>,
    <item:bxp2:sx_block>,
    <item:bxp2:ench_bx_block>
];

for i in 0 .. 7{
    blockRecipe(blist[i],blist[i+7]);
}

public function centrifugeRecipe(old as IItemStack,c1 as IItemStack,mainI as IItemStack,c2 as IItemStack,output as IItemStack) as void{
    addCraftRecipe([
        [c1,c1,c1],
        [c2,mainI,c2],
        [c2,old,c2]
    ],output,"");
}

public function beeEgg(name as string) as IItemStack{
    return <item:productivebees:spawn_egg_configurable_bee>.withTag({EntityTag: {type: "bxp2:"+name}});
}

var egg = <item:minecraft:egg>;
var atmt = <item:mekanism:pellet_antimatter>;
var ali as IIngredient[]= difficulty() == 3 ? [<item:bxp2:ou_gold_block>,<item:bxp2:ou_gold_block>,atmt,atmt,atmt,atmt] : [<item:bxp2:ou_gold_block>,<item:bxp2:ou_gold_block>,atmt,atmt];
combineRecipe(egg,<item:bxp2:unstable_bx_block>,beeEgg("unstable_bx"));
combineRecipe(<item:bxp2:bx_unstable_ingot>,<item:bxp2:umbra_amethyst_brass_ingot>|<item:bxp2:seismite_ingot>|<item:bxp2:stainless_steel_ingot>,<item:bxp2:sx_ingot>);
DEFusionRecipe(beeEgg("bx"),ali,beeEgg("ou_gold"),1,200000000);

centrifugeRecipe(<item:productivebees:heated_centrifuge>,<item:productivebees:upgrade_productivity_2>,<item:bxp2:oumang_upgrade>,<item:mekanism:ultimate_control_circuit>,<item:centrifugetiersreproduced:high_end_centrifuge>);
centrifugeRecipe(<item:centrifugetiersreproduced:high_end_centrifuge>,<item:productivebees:upgrade_productivity_3>,<item:bxp2:wyvern_upgrade>,<item:mekanism_extras:supreme_control_circuit>,<item:centrifugetiersreproduced:nuclear_centrifuge>);
centrifugeRecipe(<item:centrifugetiersreproduced:nuclear_centrifuge>,<item:productivebees:upgrade_productivity_4>,<item:bxp2:draconium_upgrade>,<item:mekanism_extras:cosmic_control_circuit>,<item:centrifugetiersreproduced:cosmic_centrifuge>);

var omb1 = new HarvestEntry(0.33f,<item:bxp2:oumang_ingot> * 5);
var omb2 = new HarvestEntry(0.16f,<item:bxp2:oumang_ingot> * 3);
var omb3 = new HarvestEntry(0.03f,<item:bxp2:oumang_ingot> * 1);
var omb4 = new HarvestEntry(0.01f,beeEgg("oumang"));
var obr = new PlantRecipe(<item:bxp2:oumang_block>,600,300000,[omb1,omb2,omb3,omb4],["dirt"]);
addInsolatorRecipe(obr);

var oge as IIngredient = beeEgg("ou_gold");
centrifugeRecipe(egg,<item:bxp2:poly_block>,beeEgg("bx"),<item:mekanism:hdpe_sheet>,beeEgg("poly"));
DEFusionRecipe(oge,[<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:bxp2:ench_bx_block>,<item:bxp2:ench_bx_block>],beeEgg("ench_bx"),2,10000000000);