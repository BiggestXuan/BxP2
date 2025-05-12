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
    furnace.addRecipe(CrTManager.getRecipeName(output,"furnace"),output,input,1,200);
}

public function removeFurnaceRecipe(output as IIngredient) as void{
    furnace.remove(output);
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
    <item:electrodynamics:raworeblocktin>,
    <item:electrodynamics:raworeblocksilver>,
    <item:mekanism:block_raw_osmium>,
    <item:mekanism:block_raw_lead>,
    <item:mekanism:block_raw_uranium>,
    <item:thermal:raw_nickel_block>,
    <item:minecraft:iron_block>,
    <item:minecraft:copper_block>,
    <item:minecraft:gold_block>,
    <item:mekanism:block_tin>,
    <item:thermal:silver_block>,
    <item:mekanism:block_osmium>,
    <item:mekanism:block_lead>,
    <item:mekanism:block_uranium>,
    <item:thermal:nickel_block>
];
var c = blocks.length / 2;
for i in 0 .. c{
    furnaceRecipe(blocks[i],blocks[i+c]);
}

removeFurnaceRecipe(<item:biggerreactors:graphite_ingot>);
addShaplessRecipe([<tag:items:forge:storage_blocks/quartz>],<item:minecraft:quartz>*4,"bxp");

<recipetype:botanypots:crop>.removeBxPRequireRecipe();
removeCraftRecipe(<item:electrodynamics:raworeblockuranium>);

var d = <item:minecraft:diamond>;
modifyCraftRecipe([
    [d,d,d],
    [d,<item:minecraft:netherrack>,d],
    [d,d,d]
],<item:minecraft:netherite_upgrade_smithing_template>,"mc");


removeCraftRecipeByName("mekanism_extras:naquadah_reactor/port");
removeCraftRecipeByName("mekanism_extras:naquadah_reactor/controller");
removeFurnaceRecipe(<item:biggerreactors:graphite_ingot>);
removeCraftRecipe(<item:aether:golden_ring>);
removeCraftRecipe(<item:extendedcrafting:elite_component>);
addShaplessRecipe([<item:extendedcrafting:black_iron_slate>,<item:extendedcrafting:black_iron_slate>,<item:extendedcrafting:black_iron_slate>,<item:extendedcrafting:black_iron_slate>,<item:bxp2:oumang_ingot>,<item:bxp2:bx_unstable_ingot>,<item:bxp2:bx_unstable_ingot>],<item:extendedcrafting:elite_component>*4,"bxp2");