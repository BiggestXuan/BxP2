package biggestxuan.bxp2.integration.JEI.catalyst;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.items.BxPCatalyst;
import biggestxuan.bxp2.items.BxPItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
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
import org.jetbrains.annotations.Nullable;

/**
 * @Author Biggest_Xuan
 * 2025/4/9
 */
public class BxPCatalystCategory implements IRecipeCategory<BxPCatalyst> {
    private final IGuiHelper helper;
    private final IDrawable background;
    private final IDrawable icon;

    public static RecipeType<BxPCatalyst> TYPE = RecipeType.create(BxP2.MODID,"catalyst",BxPCatalyst.class);

    public BxPCatalystCategory(IGuiHelper helper) {
        this.helper = helper;
        this.background = helper.createDrawable(BxP2.RL("textures/gui/jei/jei_catalyst.png"),0,0,140,50);
        this.icon = helper.createDrawableItemLike(BxPItems.BX_INGOT.get());
    }
    @Override
    public RecipeType<BxPCatalyst> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return BxP2.tr("jei.bxp2.catalyst");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth(){
        return 140;
    }

    @Override
    public int getHeight(){
        return 90;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BxPCatalyst recipe, IFocusGroup iFocusGroup) {
        IRecipeSlotBuilder inputSlotBuilder = builder.addSlot(RecipeIngredientRole.INPUT, 16, 38);
        inputSlotBuilder.addItemStack(recipe.stack);
    }

    @Override
    public void draw(BxPCatalyst r, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;
        BxPCatalyst recipe = r.copy();
        if(BxP2.devMode) {
            //BxP2.LOGGER.info("{}",recipe.speedRate);
            //BxP2.LOGGER.info("{}",f(recipe.speedRate));
        }
        String speed = BxP2.tr("jei.bxp2.catalyst_speed").getString()+f(recipe.speedRate);
        String energy = BxP2.tr("jei.bxp2.catalyst_energy").getString()+f(recipe.energyRate);
        String inputFluid = BxP2.tr("jei.bxp2.catalyst_input_fluid").getString()+f(recipe.inputFluidRate);
        String outputFluid = BxP2.tr("jei.bxp2.catalyst_output_fluid").getString()+f(recipe.outputFluidRate);
        String inputMana = BxP2.tr("jei.bxp2.catalyst_inputMana").getString()+f(recipe.inputManaRate);
        String outputMana = BxP2.tr("jei.bxp2.catalyst_outputMana").getString()+f(recipe.outputManaRate);
        String[] arr = new String[]{speed,energy,inputFluid,outputFluid,inputMana,outputMana};
        int h = 5;
        for (String s : arr) {
            guiGraphics.drawString(font, s, 45, h, 0x808080);
            h += 12;
        }
        guiGraphics.drawString(font, BxP2.tr("jei.bxp2.catalyst_can_adapt").getString()+BxPCatalyst.getAdaptNames(recipe.adapt), 45, h, 0x808080);
    }

    private static String f(double d){
        if(d == 0D){
            return "-";
        }
        if(d < 1){
            return "-"+String.format("%.0f",(1-d)*100)+"%";
        }
        return "+"+String.format("%.0f",(d-1)*100)+"%";
    }
}
