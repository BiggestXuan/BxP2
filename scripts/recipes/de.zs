import BxP2.CrTManager;

import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;

public function DEFusionRecipe(main as IIngredient,inputs as IIngredient[],output as IItemStack,level as int,energy as long) as void{
    <recipetype:draconicevolution:fusion_crafting>.addRecipe(main,inputs,output,level,energy);
}

var di = <item:draconicevolution:draconium_ingot>;
var gi = <item:minecraft:gold_ingot>;
var ue = <item:endrem:undead_eye>;

modifyCraftRecipe([
    [di,gi,di],
    [gi,<item:bxp2:bx_ingot>,gi],
    [di,gi,di]
],<item:draconicevolution:draconium_core>,"de");

removeCraftRecipe(<item:draconicevolution:wyvern_core>);
removeCraftRecipe(ue);

var stack = <item:bxp2:bx_ingot>.withTag({bxp_cycle: {l: 0, f: 0}});

DEFusionRecipe(stack * 4,[<item:draconicevolution:wyvern_core>,<item:mekanism_extras:supreme_control_circuit>,<item:thermalendergy:stellarium_ingot>,<item:ae2:cell_component_256k>],<item:bxp2:bx_ench_ingot>*4,1,10000000000);
DEFusionRecipe(<item:minecraft:ender_eye>,[<item:bxp2:bx_ench_ingot>,<item:minecraft:phantom_membrane>,<item:minecraft:bone>,<item:minecraft:ghast_tear>,<item:minecraft:rotten_flesh>],<item:endrem:undead_eye>,1,20000000);
DEFusionRecipe(<item:bxp2:bx_ench_ingot>,[<item:draconicevolution:chaotic_core>],<item:bxp2:bx_ench_ingot>*16,3,1000000000);
DEFusionRecipe(<item:mekanism_extras:enriched_shining> * 16,[<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>,<item:draconicevolution:chaotic_core>],<item:mekanism_extras:enriched_spectrum>*16,3,1000000000000);
DEFusionRecipe(<item:mekanism:module_base>,[<item:draconicevolution:awakened_core>,<item:draconicevolution:awakened_core>,<item:bxp2:bx_ench_ingot>],<item:bxp2:chaos_protect_module>,2,1000000000);