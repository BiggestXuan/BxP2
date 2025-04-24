package biggestxuan.bxp2.integration.JEI.cycle;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.recipes.ICycleRecipe;
import biggestxuan.bxp2.recipes.CombineCycleRecipe;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

/**
 * @Author Biggest_Xuan
 * 2025/4/21
 */
public class BxPCombineCycleCategory implements IRecipeCategory<ICycleRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    public static final RecipeType<ICycleRecipe> type = RecipeType.create(BxP2.MODID,"combine_cycle", CombineCycleRecipe.class);

    public BxPCombineCycleCategory(IGuiHelper helper){
        this.background = helper.createDrawable(BxP2.RL("textures/gui/jei/combine_cycle.png"),0,0,186,100);
        this.icon = helper.createDrawableItemLike(MekanismBlocks.COMBINER);
    }

    @Override
    public RecipeType<ICycleRecipe> getRecipeType() {
        return type;
    }

    @Override
    public Component getTitle() {
        return BxP2.tr("bxp.recipe.combine_cycle");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    @SuppressWarnings("all")
    public @Nullable IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ICycleRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,13,16).addIngredients(Ingredient.of(recipe.getInput()));
        int x = 31;
        int y = 62;
        for (int i = 0; i < 5; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT,x,y).addIngredients(recipe.getCala()[i]);
            x += 27;
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT,158,16).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(ICycleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;
        guiGraphics.drawString(font,"×"+recipe.getCycle(),84,12,0xFCFCFC);
    }
}
