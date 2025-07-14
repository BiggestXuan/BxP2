import mods.bxp2.CrTManager;

import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;

public function DEFusionRecipe(main as IIngredient,inputs as IIngredient[],output as IItemStack,level as int,energy as long) as void{
    <recipetype:draconicevolution:fusion_crafting>.addRecipe(main,inputs,output,level,energy);
}

var di = <item:draconicevolution:draconium_ingot>;
var gi = <item:minecraft:gold_ingot>;
var ue = <item:endrem:undead_eye>;
var ds = <item:draconicevolution:item_draconic_damage>;
var dc = <item:draconicevolution:draconium_core>;

modifyCraftRecipe([
    [di,gi,di],
    [gi,<item:bxp2:bx_ingot>,gi],
    [di,gi,di]
],<item:draconicevolution:draconium_core>,"de");

removeCraftRecipe(<item:draconicevolution:wyvern_core>);
removeCraftRecipe(ue);

var stack = <item:bxp2:bx_ingot>;

DEFusionRecipe(<item:bxp2:ouhuang_ingot>,[stack,stack,stack,stack,<item:draconicevolution:wyvern_core>,<item:mekanism_extras:supreme_control_circuit>,<item:thermalendergy:stellarium_ingot>,<item:ae2:cell_component_256k>],<item:bxp2:bx_ench_ingot>*4,1,10000000000);
DEFusionRecipe(<item:minecraft:ender_eye>,[<item:bxp2:bx_ench_ingot>,<item:minecraft:phantom_membrane>,<item:minecraft:bone>,<item:minecraft:ghast_tear>,<item:minecraft:rotten_flesh>],<item:endrem:undead_eye>,1,20000000);
DEFusionRecipe(<item:bxp2:bx_ench_ingot>,[<item:draconicevolution:chaotic_core>],<item:bxp2:bx_ench_ingot>*16,3,1000000000);
DEFusionRecipe(<item:mekanism_extras:enriched_shining>,[<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>],<item:mekanism_extras:enriched_spectrum>*16,3,1000000000);
DEFusionRecipe(<item:mekanism:module_base>,[<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:bxp2:bx_ench_ingot>],<item:bxp2:chaos_protect_module>,2,1000000000);
DEFusionRecipe(<item:draconicevolution:draconic_sword>,[ds,ds,ds,ds],<item:bxp2:chaos_health_scroll>,2,100000000);
DEFusionRecipe(<item:mekanism_extras:enriched_shining>,[ds,ds,ds,ds],<item:bxp2:chaos_shield_scroll>,2,100000000);
DEFusionRecipe(<item:bxp2:bx_ench_ingot>,[ds,ds,ds,ds],<item:bxp2:chaos_crystal_scroll>,2,100000000);
DEFusionRecipe(<item:mekanism_extras:supreme_control_circuit>,[dc,dc,dc,dc,dc,dc,dc,dc],<item:draconicevolution:wyvern_core>*8,0,1000000);
var adi = <item:draconicevolution:awakened_draconium_ingot>;
var ac = <item:draconicevolution:awakened_core>;
var lcf = <item:draconicevolution:large_chaos_frag>;
DEFusionRecipe(<item:draconicevolution:awakened_draconium_block>,[ac,ac,ac,ac,lcf,lcf,lcf,lcf],<item:draconicevolution:chaotic_core>,2,100000000);
var bsi = <item:bxp2:ouhuang_ingot>;
DEFusionRecipe(bsi,[<item:draconicevolution:wyvern_core>,<item:draconicevolution:wyvern_core>,<item:draconicevolution:wyvern_core>,<item:draconicevolution:wyvern_core>,<item:mekanism_extras:alloy_radiance>,<item:mekanism_extras:alloy_radiance>],<item:bxp2:wyvern_ingot>,1,100000000);

public function difficultyRecipe(input as IIngredient,input1 as IIngredient) as void{
    DEFusionRecipe(input,[<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:mekanism_extras:alloy_thermonuclear>,<item:mekanism_extras:alloy_thermonuclear>],<item:bxp2:draconium_ingot>,2,10000000000);
    DEFusionRecipe(input1,[<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:mekanism_extras:alloy_spectrum>,<item:mekanism_extras:alloy_spectrum>],<item:bxp2:chaotic_ingot>,3,10000000000);
}

if(difficulty() == 1){
    difficultyRecipe(bsi,bsi);
}else{
    difficultyRecipe(<item:bxp2:wyvern_ingot>,<item:bxp2:draconium_ingot>);
}