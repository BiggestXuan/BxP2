import BxP2.CrTManager;

import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.fluid.IFluidStack;

import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.chemical.GasStack;
import mods.mekanism.api.chemical.InfusionStack;

public function enrichRecipe(input as IIngredient,output as IItemStack) as void{
    <recipetype:mekanism:enriching>.addRecipe(CrTManager.getRecipeName(output,"enriched"),ItemStackIngredient.from(input),output);
}

public function removeEnrichRecipe(name as string) as void{
    <recipetype:mekanism:enriching>.removeByName("mekanism:enriching/"+name);
}

public function infusionConversion(input as IIngredient,output as InfusionStack) as void{
    <recipetype:mekanism:infusion_conversion>.addRecipe(CrTManager.addRecipeName(input.items,"infusion"),ItemStackIngredient.from(input),output);
}

public function removeInfusionConversion(name as string) as void{
    <recipetype:mekanism:infusion_conversion>.removeByName("mekanism:infusion_conversion/"+name);
}

public function infusionRecipe(input as IIngredient,type as InfusionStack,output as IItemStack) as void {
    <recipetype:mekanism:metallurgic_infusing>.addRecipe(CrTManager.getRecipeName(output,"metallurgic"),ItemStackIngredient.from(input),type,output);
}

public function combineRecipe(input as IIngredient,input1 as IIngredient,output as IItemStack) as void{
    <recipetype:mekanism:combining>.addRecipe(CrTManager.getRecipeName(output,"combine"),ItemStackIngredient.from(input),ItemStackIngredient.from(input1),output);
}

public function removeInfusionRecipe(name as string) as void{
    <recipetype:mekanism:metallurgic_infusing>.removeByName(name);
}

public function reactionRecipe(fluid as IFluidStack,gas as GasStack,item as IIngredient,output as IItemStack,outGas as GasStack,time as int) as void {
    <recipetype:mekanism:reaction>.addRecipe(CrTManager.getRecipeName(output,"mek_reaction"),ItemStackIngredient.from(item),fluid,gas,time,output,outGas);
}

public function removeReactionRecipe(name as string) as void{
    <recipetype:mekanism:reaction>.removeByName("mekanism:reaction/"+name);
}

public function chemicalInfusionRecipe(input1 as GasStack,input2 as GasStack,output as GasStack) as void{
    <recipetype:mekanism:chemical_infusing>.addRecipe(getGasRecipeName(output,"chemical"),input1,input2,output);
}

public function removeChemicalInfusionRecipe(name as string) as void{
    <recipetype:mekanism:chemical_infusing>.removeByName(name);
}

public function separatingRecipe(fluid as IFluidStack,output1 as GasStack,output2 as GasStack) as void{
    <recipetype:mekanism:separating>.addRecipe(getGasRecipeName(output1,"separating"),fluid,output1,output2);
}

public function evaporatingRecipe(fluid as IFluidStack,output as IFluidStack,name as string) as void{
    <recipetype:mekanism:evaporating>.addRecipe("evaporating/"+name,fluid,output);
}

public function dissolutionRecipe(input as IIngredient,gas as GasStack,output as GasStack) as void{
    <recipetype:mekanism:dissolution>.addRecipe(getGasRecipeName(output,"dissolution"),ItemStackIngredient.from(input),gas,output);
}

public function purifyingRecipe(item as IIngredient,gas as GasStack,output as IItemStack) as void{
    <recipetype:mekanism:purifying>.addRecipe(CrTManager.getRecipeName(output,"purifying"),ItemStackIngredient.from(item),gas,output);
}

public function crystallizingRecipe(gas as GasStack,output as IItemStack) as void{
    <recipetype:mekanism:crystallizing>.addRecipe(CrTManager.getRecipeName(output,"crystallizing"),gas,output);
}

public function activatingRecipe(gas as GasStack,output as GasStack) as void{
    <recipetype:mekanism:activating>.addRecipe(getGasRecipeName(output,"activating"),gas,output);
}

public function removeActivatingRecipe(name as string) as void{
    <recipetype:mekanism:activating>.removeByName(name);
}

public function centrifugingRecipe(gas as GasStack,output as GasStack) as void{
    <recipetype:mekanism:centrifuging>.addRecipe(getGasRecipeName(output,"centrifuging"),gas,output);
}

public function removeCentrifugingRecipe(name as string) as void{
    <recipetype:mekanism:centrifuging>.removeByName(name);
}

public function getGasRecipeName(gas as GasStack,add as string) as string{
    return "bxp2_recipes_"+gas.getRegistryName().path+"_"+add+"_"+gas.getAmount();
}

public function nuclearRecipeModify() as void{
    var late = "mekanism:processing/lategame/";
    removeChemicalInfusionRecipe("mekanism:processing/uranium/sulfuric_acid");
    chemicalInfusionRecipe(<gas:mekanism:uranium_oxide>,<gas:mekanismscience:nitric_acid> * 8,<gas:bxp2:uns> * 3);
    evaporatingRecipe(<fluid:bxp2:uns>,<fluid:bxp2:condense_uns>,"uns");
    crystallizingRecipe(<gas:bxp2:condense_uns> * 200,<item:bxp2:condense_uranium>);
    evaporatingRecipe(<fluid:mekanismscience:potassium_hydroxide>,<fluid:bxp2:aph>,"aph");
    separatingRecipe(<fluid:bxp2:aph> * 4,<gas:bxp2:potassium> * 4,<gas:mekanism:oxygen>);
    crystallizingRecipe(<gas:bxp2:potassium> * 200,<item:bxp2:potassium>);
    chemicalInfusionRecipe(<gas:bxp2:potassium>,<gas:mekanism:hydrofluoric_acid>,<gas:bxp2:potassium_fluoride>);
    separatingRecipe(<fluid:mekanism:hydrofluoric_acid>*2,<gas:mekanism:hydrogen>,<gas:bxp2:fluorine>);
    dissolutionRecipe(<item:bxp2:condense_uranium>,<gas:bxp2:fluorine>,<gas:bxp2:uranium_tetrafluoride> * 100);
    dissolutionRecipe(<item:bxp2:condense_uranium>,<gas:bxp2:potassium_fluoride>,<gas:bxp2:uranium_tetrafluoride> * 1200);
    chemicalInfusionRecipe(<gas:bxp2:uranium_tetrafluoride>,<gas:bxp2:fluorine>,<gas:mekanism:uranium_hexafluoride>);

    removeActivatingRecipe(late+"polonium");
    removeCentrifugingRecipe(late+"plutonium");
    chemicalInfusionRecipe(<gas:mekanism:nuclear_waste>,<gas:mekanismscience:nitric_acid>,<gas:bxp2:nws> * 2);
    chemicalInfusionRecipe(<gas:bxp2:nws>,<gas:mekanism:hydrofluoric_acid> * 2,<gas:bxp2:plutonium_tetrafluoride>);
    chemicalInfusionRecipe(<gas:bxp2:nws>,<gas:mekanismscience:potassium_hydroxide> * 2,<gas:bxp2:polonium_dioxide>);
    centrifugingRecipe(<gas:bxp2:polonium_dioxide>,<gas:mekanism:polonium>);
    activatingRecipe(<gas:bxp2:plutonium_tetrafluoride>,<gas:mekanism:plutonium>);
}

public function mekBasicEnergy() as void{
    removeReactionRecipe("substrate/water_hydrogen");
    reactionRecipe(<fluid:minecraft:water> * 1000,<gas:mekanism:oxygen> * 1000,<item:minecraft:paper>,<item:mekanism:substrate>,<gas:bxp2:carbon_dioxide> * 500,10);
    chemicalInfusionRecipe(<gas:bxp2:carbon_dioxide>,<gas:mekanism:hydrogen> * 4,<gas:bxp2:methane>);
    reactionRecipe(<fluid:mekanism:oxygen> * 100,<gas:bxp2:methane> * 200,<item:mekanism:bio_fuel>*2,<item:mekanism:substrate>*2,<gas:mekanism:ethene> * 200,15);
    separatingRecipe(<fluid:mekanism:ethene>,<gas:bxp2:acetylene>,<gas:mekanism:hydrogen>);
    chemicalInfusionRecipe(<gas:bxp2:acetylene>,<gas:mekanismscience:nitrogen>,<gas:bxp2:hydrogen_cyanide> * 2);
    reactionRecipe(<fluid:mekanism:ethene> * 500,<gas:bxp2:hydrogen_cyanide> * 500,<item:bxp2:copper_chloride>,<item:minecraft:raw_copper>,<gas:bxp2:acrylonitrile>*500,20);
    purifyingRecipe(<item:minecraft:raw_copper>,<gas:mekanism:hydrogen_chloride>*5,<item:bxp2:copper_chloride>);
    chemicalInfusionRecipe(<gas:bxp2:acrylonitrile>,<gas:mekanism:hydrogen> * 4,<gas:bxp2:propylamine>);
}

public function diamondInfusionModify() as void{
    removeInfusionConversion("diamond/from_dust");
    removeEnrichRecipe("enriched/diamond");
    var pc = <item:enderio:pulsating_crystal>;
    infusionConversion(pc,<infuse_type:mekanism:diamond> * 10);
    enrichRecipe(pc,<item:mekanism:enriched_diamond>);
}

public function mekCount(base as int) as int{
    return base / difficulty();
}

var a = <item:minecraft:air>;
var bsi = <item:bxp2:bx_unstable_ingot>;
var iron = <item:minecraft:iron_ingot>;
var rs = <item:minecraft:redstone>;
var al1 = <item:mekanism:alloy_infused>;
var al2 = <item:mekanism:alloy_reinforced>;
var al3 = <item:mekanism:alloy_atomic>;
var cc1 = <item:mekanism:basic_control_circuit>;
var cc2 = <item:mekanism:advanced_control_circuit>;
var cc3 = <item:mekanism:elite_control_circuit>;
var cc4 = <item:mekanism:ultimate_control_circuit>;

if(difficulty() != 1){
    removeCraftRecipe(cc1);
    removeCraftRecipe(<item:mekanism:thermal_evaporation_block>*4);
    diamondInfusionModify();
    combineRecipe(cc1,al1*2,cc2);
    modifyCraftRecipe([
        [al1,al1,al1],
        [al1,cc1,al1],
        [al1,al1,al1]
    ],cc2,"mek");
    modifyCraftRecipe([
        [a,al2,a],
        [al2,cc2,al2],
        [a,al2,a]
    ],cc3,"mek");
    modifyCraftRecipe([
        [a,al3,a],
        [al3,cc3,al3],
        [a,al3,a]
    ],cc4,"mek");
    mekBasicEnergy();
    nuclearRecipeModify();
    addShaplessRecipe([cc3,<item:bxp2:bx_ingot>],cc3*mekCount(24),"mek");
}

if(difficulty() == 1){
    furnaceRecipe(<item:biggerreactors:blutonium_ingot>,<item:mekanism:pellet_plutonium>);
    furnaceRecipe(<item:biggerreactors:cyanite_ingot>,<item:mekanism:pellet_polonium>);
}

enrichRecipe(bsi,<item:bxp2:enriched_bx>);
enrichRecipe(<item:minecraft:ender_pearl>,<item:enderio:pulsating_alloy_nugget> * mekCount(6));

infusionConversion(bsi,<infuse_type:bxp2:bx> * 10);
infusionConversion(<item:bxp2:enriched_bx>,<infuse_type:bxp2:bx> * 80);

removeInfusionRecipe("mekanism:control_circuit/basic");
removeInfusionRecipe("mekanism:metallurgic_infusing/alloy/infused");
removeInfusionRecipe("mekanism:metallurgic_infusing/alloy/atomic");
infusionRecipe(<item:mekanism:ingot_steel>,<infuse_type:bxp2:bx> * 10,bsi);
infusionRecipe(<item:mekanism:ingot_osmium>,<infuse_type:bxp2:bx> * 10,cc1);
infusionRecipe(<item:mekanism:ingot_steel>,<infuse_type:mekanism:redstone> * 10,al1);
combineRecipe(al2 * mekCount(24),<item:bxp2:bx_ingot>,al3 * mekCount(24));

modifyCraftRecipe([
    [iron,rs,iron],
    [rs,bsi,rs],
    [iron,rs,iron]
],<item:mekanism:metallurgic_infuser>,"mek");

modifyCraftRecipe([
    [a,bsi,a],
    [bsi,cc2,bsi],
    [a,bsi,a]
],<item:mekanism:thermal_evaporation_block> * mekCount(64),"mek");

addCraftRecipe([
    [a,bsi,a],
    [bsi,cc1,bsi],
    [a,bsi,a]
],<item:mekanism:upgrade_speed>*64,"mek");

addCraftRecipe([
    [bsi,a,bsi],
    [a,cc1,a],
    [bsi,a,bsi]
],<item:mekanism:upgrade_energy> * 64,"mek");

addCraftRecipe([
    [a,bsi,a],
    [a,cc1,a],
    [a,bsi,a]
],<item:mekanism:upgrade_gas> * 32,"mek");

var et = <item:mekanism:energy_tablet>;
addCraftRecipe([
    [bsi,et,bsi],
    [et,<item:mekanism:elite_combining_factory>,et],
    [bsi,et,bsi]
],<item:mbd2:bx_furnace>,"mek");
