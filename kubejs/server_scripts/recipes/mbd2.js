// priority: 0

function addSimplePowerRecipe(event,item,value,name){
    event.recipes.mbd2.bx_simple_power()
    .id(name)
    .perTick(builder => builder.outputFE(value))
    .duration(600)
    .inputItems(item)
}

function addSimpleManaRecipe(event,item,value,name){
    event.recipes.mbd2.bx_simple_mana()
    .id(name)
    .duration(1200)
    .inputItems(item)
    .perTick(builder => builder.outputMana(value))
}

function addCommonFoodToBloodRecipe(event,item,value,name){
    event.recipes.mbd2.common_food_to_blood()
    .id(name)
    .perTick(builder => builder.inputFE(Math.round(120 / BxP2.getSimpleMachineDifficultyRate())))
    .duration(200)
    .inputItems(item)
    .outputFluids('vampirism:blood '+value)
}

function getFoodBlood(a,b){
    var value = Math.round(a * 30 * (1 + b * 0.33) * BxP2.getSimpleMachineDifficultyRate())
    return value == 0 ? 1 : value
}

function addBXFuranceRecipe(event,inputs,outputs,data,name){
    event.recipes.mbd2.bx_furnace()
    .id(name)
    .perTick(builder => builder.inputFE(data[1]))
    .duration(data[0])
    .priority(parseInt((data[2])))
    .inputItems(inputs)
    .outputItems(outputs)
}

function addCuriumRecipe(event,data){
    event.recipes.mbd2.curium_recipe()
    .id("curium_recipe_"+data[0]+"_"+data[1])
    .priority(data[4])
    .perTick(builder => builder.inputFE(data[2]))
    .duration(data[3])
    .inputItems(data[0].toString()+"x mekanism:upgrade_energy",data[1].toString()+"x mekanism:upgrade_speed")
    .inputGases("300x bxp2:activated_americium")
    .outputGases("300x bxp2:activated_curium")
}

function addBXUnstableFurnaceRecipe(event,inputs,outputs,data,name){
    event.recipes.mbd2.bx_unstable_furnace()
    .id(name)
    .duration(data[0])
    .priority(parseInt((data[2])))
    .inputItems(inputs)
    .inputFluids('minecraft:lava '+data[3])
    .outputItems(outputs)
}

function addEasyFissionRecipe(event,data){
    event.recipes.mbd2.easy_fission()
    .id("easy_fission_"+data[0]+"_"+data[1])
    .duration(data[1])
    .inputGases(data[0]+"x mekanism:uranium_oxide")
    .outputGases(data[0]+"x mekanism:fissile_fuel")
    .perTick(builder => builder.inputFE(1000000))
}

BxP2.getBXIngotRecipe().forEach((a,b) => {
    b.forEach((k,v) => {
        k.forEach(i => console.log(i))
    })
})

ServerEvents.recipes((event) => {
    BxP2.getAllBXItems().forEach((k,v) => {
        addSimplePowerRecipe(event,k,Math.round(v * 450 * BxP2.getSimpleMachineDifficultyRate()),BxP2.getRecipeName(k,"bx_simple_power"))
        addSimpleManaRecipe(event,k,Math.round(v * 36 * BxP2.getSimpleMachineDifficultyRate()),BxP2.getRecipeName(k,"bx_simple_mana"))
    })
    BxP2.getAllFoods().forEach((k,v) => {
        addCommonFoodToBloodRecipe(event,k,getFoodBlood(v[0],v[1]),BxP2.getRecipeName(k,"common_food_to_blood"))
    })
    BxP2.getBXUnstableIngotRecipe().forEach((a,b) => {
        b.forEach((k,v) => {
            k.forEach((q,z) => {
                addBXUnstableFurnaceRecipe(event,q,v,z,a)
            })
        })
    })
    addEasyFissionRecipe(event,BxP2.getEasyFissionData())
    /*
    BxP2.getBXIngotRecipe().forEach((a,b) => {
        b.forEach((k,v) => {
            k.forEach((q,z) => {
                addBXFuranceRecipe(event,q,v,z,a)
            })
        })
    })
    BxP2.getCuriumRecipe().forEach(data => {
        //console.log(""+data[0]+"_"+data[1]+"_"+data[2]+"_"+data[3]+"_"+data[4])
        addCuriumRecipe(event,data)
    })
    BxP2.getEnchSeedRecipe().forEach((a,b) => {
        b.forEach((k,v) => {
            k.forEach((q,z) => {
                addBXFuranceRecipe(event,q,v,z,a)
            })
        })
    })*/
    addBXUnstableFurnaceRecipe(event,['thermal:gold_gear', 'thermal:copper_gear', 'thermal:tin_gear', 'thermal:lead_gear', 'thermal:silver_gear', 'thermal:nickel_gear', 'thermal:steel_gear', 'thermal:rose_gold_gear', 'thermal:bronze_gear'],'bxp2:meaning_less',[6666666,1024,1,1000],"bxp2_test")
})