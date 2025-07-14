package biggestxuan.bxp2.client.screen;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.container.ATMContainer;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.network.toServer.WithdrawPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class ATMScreen extends AbstractContainerScreen<ATMContainer> {

    private static final ResourceLocation TEXTURE = BxP2.RL("textures/gui/atm.png");
    private EditBox amount;
    private ATMContainer container;
    public Set<Button> buttons = new HashSet<>();

    public ATMScreen(ATMContainer p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
        this.imageWidth = 176;
        this.imageHeight = 222;
        container = p_97741_;
    }

    @Override
    protected void init() {
        super.init();
        amount = new EditBox(font, leftPos + 5, topPos + 95, 50, 12, Component.literal(""));
        amount.setValue("0");
        amount.setFilter(s -> s.matches("\\d*(\\.\\d{0,2})?"));
        addWidget(amount);
        WithdrawButton button = new WithdrawButton(leftPos + 120,topPos + 95,container.getTile().getBlockPos(),getFloat(amount.getValue()));
        addRenderableWidget(button);
        buttons.add(button);
    }

    public static class WithdrawButton extends Button{

        protected WithdrawButton(int x, int y, BlockPos pos,float amt) {
            super(new Builder(BxP2.tr("gui.bxp2.atm_withraw"),button -> {
                PacketHandler.sendToServer(new WithdrawPacket(pos,amt));
            }).size(20,10).pos(x,y));
        }
    }

    public float getFloat(String s){
        float f = 0;
        try{
            f = Float.parseFloat(amount.getValue());
            return Math.max(0,f);
        }catch (NumberFormatException ignored){

        }
        return Math.max(0,f);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        guiGraphics.drawString(this.font, BxP2.tr("gui.bxp2.atm_store"), this.leftPos + 5, this.topPos + 10, 0x404040, false);
        Level level = Minecraft.getInstance().level;
        if(level != null){
            Player player = container.getTile().getPlayer(level);
            if(player != null){
                guiGraphics.drawString(this.font, BxP2.tr("gui.bxp2.atm_player",player.getScoreboardName()), this.leftPos + 5, this.topPos + 60, 0x404040, false);
            }else{
                guiGraphics.drawString(this.font, BxP2.tr("gui.bxp2.need_player"), this.leftPos + 5, this.topPos + 60, 0x404040, false);
            }
        }
        guiGraphics.drawString(this.font, BxP2.tr("gui.bxp2.atm_withraw"), this.leftPos + 5, this.topPos + 90, 0x404040, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        amount.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        for(Button b : buttons){
            b.render(guiGraphics,mouseX,mouseY,delta);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {

    }
}
