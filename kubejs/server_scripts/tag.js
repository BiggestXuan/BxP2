function addBXMultiBlockCommonTag(event,item){
    event.add('bxp2:multiblock_common',item)
}

ServerEvents.tags('block',event => {
    var a = ['extendedcrafting:ender_ingot_block','mbd2:energy_input','mbd2:energy_output','mbd2:item_input','mbd2:item_output']
    var b = ['mbd2:energy_input','mbd2:energy_output','mbd2:item_input','mbd2:item_output','mbd2:gas_input', 'mbd2:gas_output', 'mekanismscience:high_quality_concrete', 'mekanismscience:aqua_high_quality_concrete', 'mekanismscience:black_high_quality_concrete', 'mekanismscience:blue_high_quality_concrete', 'mekanismscience:green_high_quality_concrete', 'mekanismscience:cyan_high_quality_concrete', 'mekanismscience:dark_red_high_quality_concrete', 'mekanismscience:purple_high_quality_concrete', 'mekanismscience:orange_high_quality_concrete', 'mekanismscience:light_gray_high_quality_concrete', 'mekanismscience:gray_high_quality_concrete', 'mekanismscience:light_blue_high_quality_concrete', 'mekanismscience:lime_high_quality_concrete', 'mekanismscience:red_high_quality_concrete', 'mekanismscience:magenta_high_quality_concrete', 'mekanismscience:yellow_high_quality_concrete', 'mekanismscience:white_high_quality_concrete', 'mekanismscience:brown_high_quality_concrete', 'mekanismscience:pink_high_quality_concrete']
    a.forEach(item => addBXMultiBlockCommonTag(event,item))
    b.forEach(item => event.add("bxp2:multiblock_advanced",item))
})

ServerEvents.tags('item',event => {
    var a = ['ars_nouveau:purple_archwood_log','ars_nouveau:blue_archwood_log','ars_nouveau:red_archwood_log','ars_nouveau:green_archwood_log']
    a.forEach(item => event.add('bxp2:ars_mission_log',item))
    var b = ['ars_nouveau:agronomic_sourcelink','ars_nouveau:volcanic_sourcelink','ars_nouveau:alchemical_sourcelink','ars_nouveau:vitalic_sourcelink','ars_nouveau:mycelial_sourcelink']
    b.forEach(item => event.add('bxp2:ars_mission_sourcelink',item))
    event.add('forge:plastic','pneumaticcraft:plastic')
    event.add('forge:plastic','rats:raw_plastic')
    var c = ['minecraft:stone', 'minecraft:cobblestone', 'minecraft:mossy_cobblestone', 'minecraft:granite', 'minecraft:diorite', 'minecraft:andesite', 'minecraft:deepslate', 'minecraft:cobbled_deepslate']
    c.forEach(item => event.add('minecraft:base_stone_overworld',item))
    //var d = ['mekanism:pellet_polonium', 'mekanism:pellet_plutonium', 'mekanismscience:pellet_neutron_source']
    //d.forEach(item => event.add('bxp2:radiation_pellet',item))
    var e = ['brewery:dried_wheat','brewery:dried_barley','brewery:dried_corn'];
    e.forEach(item => event.add("bxp2:wine_dried",item)) 
    var f = ['mekanismscience:high_quality_concrete', 'mekanismscience:aqua_high_quality_concrete', 'mekanismscience:black_high_quality_concrete', 'mekanismscience:blue_high_quality_concrete', 'mekanismscience:green_high_quality_concrete', 'mekanismscience:cyan_high_quality_concrete', 'mekanismscience:dark_red_high_quality_concrete', 'mekanismscience:purple_high_quality_concrete', 'mekanismscience:orange_high_quality_concrete', 'mekanismscience:light_gray_high_quality_concrete', 'mekanismscience:gray_high_quality_concrete', 'mekanismscience:light_blue_high_quality_concrete', 'mekanismscience:lime_high_quality_concrete', 'mekanismscience:red_high_quality_concrete', 'mekanismscience:magenta_high_quality_concrete', 'mekanismscience:yellow_high_quality_concrete', 'mekanismscience:white_high_quality_concrete', 'mekanismscience:brown_high_quality_concrete', 'mekanismscience:pink_high_quality_concrete']
    f.forEach(item => event.add("bxp2:high_quality_concrete",item)) 
})

ServerEvents.tags('fluid',event => {
    event.add('forge:crude_oil','ad_astra:oil')
})