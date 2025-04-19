import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;

import BxP2.CrTManager;

public function addPowahRecipe(inputs as IIngredient[],output as IItemStack,energy as long) as void{
    var d = difficulty();
    if(d == 1){
        energy /= 2;
    }
    if(d == 3){
        energy = (energy * 1.2F) as int;
    }
    <recipetype:powah:energizing>.addRecipe(inputs,output,energy);
}

public function removePowahRecipe(output as IItemStack) as void{
    <recipetype:powah:energizing>.removeRecipe(output);
}

public function modifyPowahRecipe(inputs as IIngredient[],output as IItemStack,energy as long) as void{
    removePowahRecipe(output);
    addPowahRecipe(inputs as IIngredient[],output as IItemStack,energy as long);
}


if(difficulty() != 3){
    modifyPowahRecipe([<item:bxp2:bx_unstable_ingot>],<item:powah:crystal_niotic>,300000);
    modifyPowahRecipe([<item:tinkers_thinking:chlorophyte_ingot>,<item:minecraft:emerald>],<item:powah:crystal_spirited>,1000000);
}else{
    modifyPowahRecipe([<item:powah:steel_energized>,<item:minecraft:blaze_powder>,<item:minecraft:blaze_powder>,<item:minecraft:blaze_powder>],<item:powah:crystal_blazing>,50000);
    modifyPowahRecipe([<item:powah:crystal_blazing>,<item:bxp2:bx_unstable_ingot>],<item:powah:crystal_niotic>,300000);
    modifyPowahRecipe([<item:powah:crystal_niotic>,<item:tinkers_thinking:chlorophyte_ingot>,<item:minecraft:emerald>],<item:powah:crystal_spirited>,1000000);
    modifyPowahRecipe([<item:minecraft:nether_star>,<item:minecraft:redstone_block>,<item:minecraft:redstone_block>,<item:powah:spirited_crystal_block>],<item:powah:crystal_nitro>,20000000);
}

var cap = [
    <item:powah:capacitor_hardened>,
    <item:powah:capacitor_blazing>,
    <item:powah:capacitor_niotic>,
    <item:powah:capacitor_spirited>,
    <item:powah:capacitor_nitro>
];

var dp = <item:powah:dielectric_paste>;

var cry = [
    <item:powah:crystal_blazing>,
    <item:powah:crystal_niotic>,
    <item:powah:crystal_spirited>,
    <item:powah:crystal_nitro>
];

if(difficulty() >= 2){
    for i in 1 .. 5{
        modifyCraftRecipe([
            [dp,cry[i-1],dp],
            [cry[i-1],cap[i-1],cry[i-1]],
            [dp,cry[i-1],dp],
        ],cap[i],"powah_diff");
    }
}

var ms = <item:ad_astra:moon_stone>;
var ur = <tag:items:forge:ingots/uranium>;

addPowahRecipe([ms,ur],<item:powah:uraninite_ore_poor>,10000);
addPowahRecipe([ms,ur,ur],<item:powah:uraninite_ore>,30000);
addPowahRecipe([ms,ur,ur,ur,ur],<item:powah:uraninite_ore_dense>,60000);

CrTManager.registerCoolant("tinkers_advanced:molten_blizz_enderium",10);