function removeRecipe(event,out){
    event.remove({ output: out })
}

function addRecipe(event,a,b,c){
    event.shaped(a,b,c)
}

ServerEvents.recipes(event => {
    removeRecipe(event,'minecraft:beacon')
    addRecipe(event,
        Item.of('minecraft:beacon',1),
        [
            'AAA',
            'ABA',
            'CCC'
        ],
        {
            A:'#forge:glass',
            B:'bxp2:bx_unstable_ingot',
            C:'minecraft:obsidian'
        }
    )
})