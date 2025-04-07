function addBXMultiBlockCommonTag(event,item){
    event.add('bxp2:multiblock_common',item)
}

ServerEvents.tags('block',event => {
    var a = ['extendedcrafting:ender_ingot_block','mbd2:energy_input','mbd2:energy_output','mbd2:item_input','mbd2:item_output']
    a.forEach(item => addBXMultiBlockCommonTag(event,a))
})