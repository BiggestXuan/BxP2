#priority 20

import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipe.replacement.Replacer;

import mods.bxp2.CrTManager;

var a = <item:minecraft:air>;
var cstone = <tag:items:minecraft:stone_crafting_materials>;

public function removeCraftRecipe(output as IIngredient) as void{
    craftingTable.remove(output);
}

public function removeCraftRecipeByName(name as string) as void{
    craftingTable.removeByName([name]);
}

public function addCraftRecipe(inputs as IIngredient[][],output as IItemStack, addon as string) as void{
   craftingTable.addShaped(CrTManager.getRecipeName(output,addon),output,inputs);
}

public function addShaplessRecipe(inputs as IIngredient[],output as IItemStack,addon as string) as void{
    craftingTable.addShapeless(CrTManager.getRecipeName(output,addon),output,inputs);
}

public function modifyCraftRecipe(inputs as IIngredient[][],output as IItemStack, addon as string) as void{
    removeCraftRecipe(output);
    addCraftRecipe(inputs,output,addon);
}

public function furnaceRecipe(input as IIngredient,output as IItemStack) as void{
    furnace.addRecipe(CrTManager.getRecipeName(output,"furnace"),output,input,0.2,200);
}

public function removeFurnaceRecipe(output as IIngredient) as void{
    furnace.remove(output);
}

public function blastRecipe(input as IIngredient,output as IItemStack) as void{
    blastFurnace.addRecipe(CrTManager.getRecipeName(output,"blast"),output,input,0.2,100);
}

for i in [
    <item:alltheores:copper_ore_hammer>,
    <item:alltheores:iron_ore_hammer>,
    <item:alltheores:bronze_ore_hammer>,
    <item:alltheores:invar_ore_hammer>,
    <item:alltheores:platinum_ore_hammer>
]{
    removeCraftRecipe(i);
}

removeCraftRecipe(<item:ifeu:rule_controller>);

addCraftRecipe([
    [a,<item:minecraft:blaze_powder>,a],
    [a,<item:minecraft:blaze_powder>,a],
    [cstone,cstone,cstone]
],<item:minecraft:brewing_stand>,"mc");

Replacer.create()
    .replace<IIngredient>(<recipecomponent:crafttweaker:input/ingredients>,<item:pneumaticcraft:plastic>,<tag:items:forge:plastic>)
    .execute();

furnaceRecipe(<item:ad_astra:desh_ingot>,<item:enderio:energetic_alloy_ingot>);

var blocks = [
    <item:minecraft:raw_iron_block>,
    <item:minecraft:raw_copper_block>,
    <item:minecraft:raw_gold_block>,
    <item:mekanism:block_raw_osmium>,
    <item:mekanism:block_raw_lead>,
    <item:mekanism:block_raw_uranium>,
    <item:thermal:raw_nickel_block>,
    <item:minecraft:iron_block>,
    <item:minecraft:copper_block>,
    <item:minecraft:gold_block>,
    <item:mekanism:block_osmium>,
    <item:mekanism:block_lead>,
    <item:mekanism:block_uranium>,
    <item:thermal:nickel_block>
];
var c = blocks.length / 2;
for i in 0 .. c{
    furnaceRecipe(blocks[i],blocks[i+c]);
    blastRecipe(blocks[i],blocks[i+c]);
}

blastRecipe(<item:minecraft:iron_block>,<item:mekanism:block_steel>);
blastRecipe(<item:minecraft:iron_ingot>,<item:mekanism:ingot_steel>);

removeFurnaceRecipe(<item:biggerreactors:graphite_ingot>);
addShaplessRecipe([<tag:items:forge:storage_blocks/quartz>],<item:minecraft:quartz>*4,"bxp");

<recipetype:botanypots:crop>.removeBxPRequireRecipe();

var d = <item:minecraft:diamond>;
modifyCraftRecipe([
    [d,d,d],
    [d,<item:minecraft:netherrack>,d],
    [d,d,d]
],<item:minecraft:netherite_upgrade_smithing_template>,"mc");


//removeCraftRecipeByName("mekanism_extras:naquadah_reactor/port");
//removeCraftRecipeByName("mekanism_extras:naquadah_reactor/controller");
removeFurnaceRecipe(<item:biggerreactors:graphite_ingot>);
removeCraftRecipe(<item:aether:golden_ring>);
removeCraftRecipe(<item:extendedcrafting:elite_component>);
addShaplessRecipe([<item:extendedcrafting:black_iron_slate>,<item:extendedcrafting:black_iron_slate>,<item:extendedcrafting:black_iron_slate>,<item:extendedcrafting:black_iron_slate>,<item:bxp2:poly_ingot>,<item:bxp2:bx_unstable_ingot>,<item:bxp2:bx_unstable_ingot>],<item:extendedcrafting:elite_component>*4,"bxp2");

var meat as IItemStack[] = [
    <item:farmersdelight:steak_and_potatoes>,
    <item:minecraft:cooked_rabbit>,
    <item:minecraft:rabbit_stew>,
    <item:minecraft:rotten_flesh>,
    <item:tconstruct:meat_soup>,
    <item:jerotesvillage:meganose_snow_lizard_meat>,
    <item:jerotesvillage:cooked_meganose_snow_lizard_meat>,
    <item:jerotesvillage:solish_meat>,
    <item:jerotesvillage:cooked_solish_meat>,
    <item:jerotesvillage:rambler_meat>,
    <item:jerotesvillage:cooked_rambler_meat>,
    <item:rats:rat_burger>,
    <item:thermal:stuffed_pepper>,
    <item:tinkers_thinking:beef_jerky>,
    <item:tinkers_thinking:chicken_jerky>,
    <item:tinkers_thinking:pork_jerky>,
    <item:tinkers_thinking:mutton_jerky>,
    <item:tinkers_thinking:rabbit_jerky>,
    <item:tinkers_thinking:rotten_flesh_jerky>,
    <item:brewinandchewin:jerky>,
    <item:farmersdelight:chicken_sandwich>,
    <item:farmersdelight:mutton_wrap>,
    <item:farmersdelight:beef_stew>,
    <item:farmersdelight:chicken_soup>,
    <item:farmersdelight:fish_stew>,
    <item:farmersdelight:pasta_with_meatballs>,
    <item:farmersdelight:pasta_with_mutton_chop>,
    <item:farmersdelight:roasted_mutton_chops>
];

for i in 0 .. meat.length{
    <tag:items:werewolves:werewolf_food>.add(meat[i]);
    <tag:items:werewolves:meat>.add(meat[i]);
}