#priority 100

import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipe.replacement.Replacer;
import crafttweaker.api.fluid.IFluidStack;

import mods.bxp2.CrTManager;
import mods.bxp2.CrTConfig;
import mods.bxp2.HarvestEntry;
import mods.bxp2.PlantRecipe;

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
    <recipetype:thermal:insolator>.addRecipe(recipe);
}

var caiRecipe = new PlantRecipe(<item:bxp2:cai_seed>,200,10000,[czHarvestEntry,caigengzi1,caigengzi2,caigengzi3],["dirt"]);
addPlantRecipe(caiRecipe);
addPlantRecipe(new PlantRecipe(<item:bxp2:ench_cai_seed>,200,30000,[caigengzi4,caigengzi5,caigengzi6],["dirt"]));

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

var eib = <item:extendedcrafting:ender_ingot_block>;

for i in 0 .. 4{
    addCraftRecipe([
        [eib,eib,eib],
        [eib,mbds[i+4],eib],
        [eib,eib,eib]
    ],mbds[i],"bxp");
}

transRatsRecipe(<item:jerotesvillage:meror_metal_ingot>,<item:ad_astra:calorite_ingot>);

public function upgradeRecipe(a as IIngredient,b as IIngredient,c as IIngredient,d as IIngredient,e as IItemStack) as void{
    if(difficulty() == 1){
        addCraftRecipe([
            [a,b,a],
            [c,d,c],
            [a,b,a]
        ],e,"bxp2");
    }else{
        addCraftRecipe([
            [a,b,a],
            [d,c,d],
            [a,d,a]
        ],e,"bxp2");
    }
}

public function removeAlloyRecipe(name as string) as void{
    <recipetype:tconstruct:alloying>.removeRecipeByName(name);
}

upgradeRecipe(<item:bxp2:bx_ingot>,<item:bxp2:caigengzi>,<item:mekanism:elite_control_circuit>,<item:thermalendergy:endergy_upgrade_3>,<item:bxp2:bx_upgrade>);
upgradeRecipe(<item:bxp2:oumang_ingot>,<item:tinkers_thinking:silky_jewel>,<item:mekanism:ultimate_control_circuit>,<item:bxp2:bx_upgrade>,<item:bxp2:oumang_upgrade>);
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
upgradeRecipe(<item:thermalendergy:prismalium_ingot>,<item:minecraft:ender_eye>,<item:thermal:enderium_gear>,<item:thermal:upgrade_augment_3>,<item:thermalendergy:endergy_upgrade_1>);
upgradeRecipe(<item:thermalendergy:melodium_ingot>,<item:minecraft:shulker_shell>,<item:thermalendergy:prismalium_gear>,<item:thermalendergy:endergy_upgrade_1>,<item:thermalendergy:endergy_upgrade_2>);
upgradeRecipe(<item:thermalendergy:stellarium_ingot>,<item:minecraft:clay>,<item:thermalendergy:melodium_gear>,<item:thermalendergy:endergy_upgrade_2>,<item:thermalendergy:endergy_upgrade_3>);
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