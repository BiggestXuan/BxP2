package biggestxuan.bxp2.client.screen;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.client.event.ClientScreenEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

/**
 * @Author Biggest_Xuan
 * 2025/4/19
 */
public class PCLWarningScreen extends Screen {
    private Screen screen;
    public PCLWarningScreen(Screen screen) {
        super(BxP2.tr("screen.pcl.title"));
        this.screen = screen;
    }

    protected void init() {
        super.init();
        ClientScreenEvent.isShowPCL = true;
        Button button = new Button.Builder(BxP2.tr("screen.pcl.ik"), new Button.OnPress() {
            @Override
            public void onPress(Button button) {
                Minecraft.getInstance().setScreen(screen);
                ClientScreenEvent.isShowPCL = true;
            }
        }).pos(this.width / 2 - 75, this.height * 3 / 4).size(150,20).build();
    }

    public void render(GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {
        super.render(p_281549_, p_281550_, p_282878_, p_282465_);
        this.renderBackground(p_281549_);
        p_281549_.drawString(font, title, width / 2, 30, 16777215);
        for (int i = 1; i < 6; i++) {
            p_281549_.drawString(font, BxP2.tr("screen.pcl.line"+i), width / 12, 60+(i-1)*25, 16777215);
        }
    }

}
