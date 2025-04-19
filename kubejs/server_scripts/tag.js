function addBXMultiBlockCommonTag(event,item){
    event.add('bxp2:multiblock_common',item)
}

ServerEvents.tags('block',event => {
    var a = ['extendedcrafting:ender_ingot_block','mbd2:energy_input','mbd2:energy_output','mbd2:item_input','mbd2:item_output']
    a.forEach(item => addBXMultiBlockCommonTag(event,item))
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
})

ServerEvents.tags('fluid',event => {
    event.add('forge:crude_oil','ad_astra:oil')
})