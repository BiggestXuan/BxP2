import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.ingredient.IIngredientWithAmount;
import crafttweaker.api.item.IItemStack;

import mods.bxp2.CrTManager;

public function reactionChamberRecipe(input as IIngredientWithAmount[],output as IItemStack[],pressure as float) as void{
    <recipetype:pneumaticcraft:pressure_chamber>.addRecipe(CrTManager.addRecipeName(output,"pne"),input,output,pressure);
}

var input = [
    <item:rats:piper_hat>,
    <item:minecraft:bone>,
    <item:minecraft:blaze_rod>,
    <item:minecraft:quartz_block>,
    <item:ae2:printed_logic_processor>,
    <item:mekanism:ultimate_control_circuit>,
    <item:pneumaticcraft:empty_pcb>,
    <item:bxp2:plague_metal>,
    <item:bxp2:sdbz>,
    <item:expatternprovider:fishbig>,
    <item:minecraft:emerald>,
    <item:minecraft:tall_grass>
];

var output = [
    <item:rats:pirat_hat>,
    <item:rats:feral_rat_claw>,
    <item:rats:ratlantean_flame>,
    <item:rats:rat_toga>,
    <item:rats:golden_rat_skull>,
    <item:rats:arcane_technology>,
    <item:rats:psionic_rat_brain>,
    <item:rats:dutchrat_wheel>,
    <item:rats:biplane_wing>,
    <item:rats:ratfish>,
    <item:rats:gem_of_ratlantis>,
    <item:rats:ratglove_petals>
];

for i in 0 .. input.length{
    reactionChamberRecipe([input[i],<item:rats:oratchalcum_ingot>],[output[i]],2f);
}
