package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.items.BxPItems;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.common.recipe.ingredient.creator.ItemStackIngredientCreator;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/4/26
 */

@Mixin(value = ItemStackIngredientCreator.class,remap = false)
public class ItemStackIngredientCreatorMixin {

    @Inject(method = "from(Lnet/minecraft/world/item/crafting/Ingredient;I)Lmekanism/api/recipes/ingredients/ItemStackIngredient;",at = @At("HEAD"),cancellable = true)
    public void __inject(Ingredient ingredient, int amount, CallbackInfoReturnable<ItemStackIngredient> cir){
        if(ingredient == Ingredient.EMPTY){
            ingredient = Ingredient.of(BxPItems.BX_UNSTABLE_INGOT.get());
        }
        if(amount < 0){
            amount = 1;
        }
    }
}
