var d = <item:minecraft:diamond>;
var o = <item:minecraft:obsidian>;

modifyCraftRecipe([
    [d,<item:bloodmagic:airscribetool>,d],
    [<item:bloodmagic:firescribetool>,<item:bxp2:bx_unstable_ingot>,<item:bloodmagic:waterscribetool>],
    [d,<item:bloodmagic:earthscribetool>,d]
],<item:bloodmagic:ritualdiviner>,"bm3");

addCraftRecipe([
    [o,o,o],
    [<item:vampirism:pure_blood_0>,<item:enderio:clear_glass>,<item:bloodmagic:life_essence_bucket>],
    [o,o,o]
],<item:mbd2:blood_transposer>,"bm3");