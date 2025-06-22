import crafttweaker.api.ingredient.IIngredient;

import mods.extendedcrafting.TableCrafting;

var c = [
    <item:draconicevolution:creative_op_capacitor>,
    <item:electrodynamics:creativefluidsource>,
    <item:enderio:creative_power>,
    <item:immersiveengineering:capacitor_creative>,
    <item:ifeu:creative_capacitor>,
    <item:draconicevolution:creative_capacitor>,
    <item:appbot:creative_mana_cell>,
    <item:ae2:creative_item_cell>,
    <item:ae2:creative_fluid_cell>,
    <item:ae2:creative_energy_cell>,
    <item:appmek:creative_chemical_cell>,
    <item:ars_nouveau:creative_spell_book>,
    <item:ars_nouveau:creative_source_jar>,
    <item:arseng:creative_source_cell>,
    <item:crossroads:heat_reservoir_creative>,
    <item:crossroads:master_axis_creative>,
    <item:crossroads:beam_extractor_creative>,
    <item:electrodynamics:creativepowersource>,
    <item:rats:creative_cheese>,
    <item:rats:rat_upgrade_creative>,
    <item:rats:rat_upgrade_combined_creative>,
    <item:prefab:item_creative_bulldozer>,
    <item:powah:energy_cell_creative>,
    <item:wormhole:creative_energy_cell>,
    <item:pneumaticcraft:creative_compressed_iron_block>,
    <item:pneumaticcraft:creative_compressor>,
    <item:pneumaticcraft:creative_upgrade>,
    <item:thermal:rf_coil_creative_augment>,
    <item:thermal:fluid_tank_creative_augment>,
    <item:thermal:machine_efficiency_creative_augment>,
    <item:thermal:machine_catalyst_creative_augment>,
    <item:mysticalagradditions:creative_essence>,
    <item:modularrouters:creative_module>,
    <item:mekanism:creative_energy_cube>.withTag({mekData: {EnergyContainers: [{Container: 0, stored: "18446744073709551615.9999"}], componentConfig: {config0: {side4: 4, side3: 4, side5: 4, side0: 4, side2: 4, side1: 4}}}})
];

var si = <item:bxp2:bx_super_ingot>;
var a = <item:minecraft:air>;

val positions as int[][] = [
    [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [0,7], [0,8],
    [1,0], [1,2], [1,3], [1,4], [1,5], [1,6], [1,7], [1,8],
    [2,0], [2,1], [2,3], [2,4], [2,5], [2,6], [2,7], [2,8],
    [3,0], [3,1], [3,2], [3,4], [3,5], [3,6], [3,7], [3,8],
    [4,0], [4,1], [4,2], [4,3], [4,5], [4,6], [4,7], [4,8],
    [5,0], [5,1], [5,2], [5,3], [5,4], [5,6], [5,7], [5,8],
    [6,0], [6,1], [6,2], [6,3], [6,4], [6,5], [6,7], [6,8],
    [7,0], [7,1], [7,2], [7,3], [7,4], [7,5], [7,6], [7,8],
    [8,0], [8,1], [8,2], [8,3], [8,4], [8,5], [8,6], [8,7]
];

for i in 0 .. c.length {    
    val pos1 as int = positions[i][0];
    val pos2 as int = positions[i][1];
    val recipe as IIngredient[][] = [
        [a,a,a],
        [a,a,a],
        [a,a,a]
    ];

    recipe[pos1 / 3][pos1 % 3] = si.reuse();
    recipe[pos2 / 3][pos2 % 3] = <item:bxp2:bx_ench_ingot>;

    addCraftRecipe(recipe,c[i],"creative");
}

var il = [
    <item:enderio:vibrant_alloy_ingot>,
<item:enderio:redstone_alloy_ingot>,
<item:enderio:conductive_alloy_ingot>,
<item:enderio:pulsating_alloy_ingot>,
<item:enderio:dark_steel_ingot>,
<item:enderio:soularium_ingot>,
<item:enderio:end_steel_ingot>,
<item:extendedcrafting:black_iron_ingot>,
<item:extendedcrafting:redstone_ingot>,
<item:minecraft:iron_ingot>,
<item:minecraft:copper_ingot>,
<item:minecraft:gold_ingot>,
<item:minecraft:netherite_ingot>,
<item:bxp2:bx_unstable_ingot>,
<item:bxp2:bx_ingot>,
<item:bxp2:bx_ench_ingot>,
<item:tconstruct:cobalt_ingot>,
<item:tconstruct:slimesteel_ingot>,
<item:tconstruct:amethyst_bronze_ingot>,
<item:tconstruct:rose_gold_ingot>,
<item:tconstruct:pig_iron_ingot>,
<item:tconstruct:manyullyn_ingot>,
<item:tconstruct:queens_slime_ingot>,
<item:tconstruct:hepatizon_ingot>,
<item:draconicevolution:awakened_draconium_ingot>,
<item:draconicevolution:draconium_ingot>,
<item:allthemodium:allthemodium_ingot>,
<item:allthemodium:vibranium_ingot>,
<item:botania:manasteel_ingot>,
<item:botania:terrasteel_ingot>,
<item:botania:elementium_ingot>,
<item:botania:gaia_ingot>,
<item:electrodynamics:ingottitanium>,
<item:electrodynamics:ingotmolybdenum>,
<item:enderio:copper_alloy_ingot>,
<item:enderio:energetic_alloy_ingot>,
<item:thermal:electrum_ingot>,
<item:thermal:invar_ingot>,
<item:thermal:constantan_ingot>,
<item:thermal:signalum_ingot>,
<item:thermal:lumium_ingot>,
<item:thermal:enderium_ingot>,
<item:mekanism:ingot_bronze>,
<item:mekanism:ingot_steel>,
<item:mekanism:ingot_refined_obsidian>,
<item:mekanism:ingot_refined_glowstone>,
<item:mekanism:ingot_osmium>,
<item:mekanism:ingot_tin>,
<item:mekanism:ingot_lead>,
<item:mekanism:ingot_uranium>,
<item:pneumaticcraft:ingot_iron_compressed>,
<item:jerotesvillage:meror_metal_ingot>,
<item:jerotesvillage:refine_meror_metal_ingot>,
<item:immersiveengineering:ingot_hop_graphite>,
<item:immersiveengineering:ingot_aluminum>,
<item:extendedcrafting:enhanced_redstone_ingot>,
<item:extendedcrafting:ender_ingot>,
<item:extendedcrafting:enhanced_ender_ingot>,
<item:extendedcrafting:crystaltine_ingot>,
<item:thermal:silver_ingot>,
<item:thermal:nickel_ingot>,
<item:thermal:rose_gold_ingot>,
<item:twilightforest:ironwood_ingot>,
<item:twilightforest:knightmetal_ingot>,
<item:twilightforest:fiery_ingot>,
<item:thermalendergy:prismalium_ingot>,
<item:thermalendergy:melodium_ingot>,
<item:thermalendergy:stellarium_ingot>,
<item:megacells:sky_steel_ingot>
] as IIngredient[];

TableCrafting.addShapeless("final_ingot", 0, <item:extendedcrafting:the_ultimate_ingot>, il);