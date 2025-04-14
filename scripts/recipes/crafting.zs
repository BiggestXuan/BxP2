import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;

import BxP2.CrTManager;

var a = <item:minecraft:air>;
var cstone = <tag:items:minecraft:stone_crafting_materials>;

public function removeCraftRecipe(output as IIngredient) as void{
    craftingTable.remove(output);
}

public function addCraftRecipe(inputs as IIngredient[][],output as IItemStack, addon as string) as void{
   craftingTable.addShaped(CrTManager.getRecipeName(output,addon),output,inputs);
}

public function modifyCraftRecipe(inputs as IIngredient[][],output as IItemStack, addon as string) as void{
    removeCraftRecipe(output);
    addCraftRecipe(inputs,output,addon);
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