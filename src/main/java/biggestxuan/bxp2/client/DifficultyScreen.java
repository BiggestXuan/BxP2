package biggestxuan.bxp2.client;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.network.toServer.ChangeDifficultyPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
public class DifficultyScreen extends Screen {
    static String name = "bxp2.screen.difficulty";
    protected DifficultyScreen() {
        super(BxP2.tr(name));
    }

    ResourceLocation bg = BxP2.RL("textures/gui/difficulty_screen.png");

    private final List<Button> buttons = new ArrayList<>();

    @Override
    public void render(GuiGraphics g, int p_281550_, int p_282878_, float p_282465_) {
        super.render(g, p_281550_, p_282878_, p_282465_);
        int w = this.width;
        int h = this.height;
        g.drawString(font,BxP2.tr(name+".title").getString(),a(width*0.45),a(height*0.03),111111);
        this.minecraft.getTextureManager().bindForSetup(bg);
        renderDirtBackground(g);
        render(g,"easy",a(w * 0.02));
        render(g,"normal",a(w * 0.38));
        render(g,"hard",a(w * 0.68));
        for(Button button : buttons){
            button.render(g,p_281550_,p_282878_,p_282465_);
        }
    }

    public void renderDirtBackground(GuiGraphics p_281950_) {
        super.renderDirtBackground(p_281950_);
        p_281950_.blit(bg, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
    }

    private int a(double p){
        return (int) p;
    }

    private void render(GuiGraphics g, String diff, int w){
        int color = 0;
        switch (diff){
            case "easy" -> color = 111111;
            case "normal" -> color = 0xFF9933;
            case "hard" -> color = 0xD52121;
        }
        int h = a(height * 0.25);
        for (int i = 0; i <= 10; i++) {
            g.drawString(font,BxP2.tr(name+diff+"_"+i),w,h,color);
            h += a(height * 0.04);
        }
    }

    @Override
    protected void init() {
        super.init();
        int w = this.width;
        int h = this.height;
        Button easy = new ChooseDifficultyButton("easy",a(w * 0.04),a(h * 0.8),1);
        Button normal = new ChooseDifficultyButton("normal",a(w * 0.38),a(h * 0.8),2);
        Button hard = new ChooseDifficultyButton("hard",a(w * 0.73),a(h * 0.8),3);
        buttons.add(easy);
        buttons.add(normal);
        buttons.add(hard);
        addWidget(easy);
        addWidget(normal);
        addWidget(hard);
    }

    static class ChooseDifficultyButton extends Button{
        private int difficulty;

        public ChooseDifficultyButton(String name,int x,int y,int difficulty) {
            super(new Builder(BxP2.tr("bxp2.button."+name), button -> {
                PacketHandler.sendToServer(new ChangeDifficultyPacket(difficulty));
                Minecraft.getInstance().setScreen(null);
            }).pos(x,y).size(75,20));
        }
    }

    public static void open(){
        Minecraft.getInstance().setScreen(new DifficultyScreen());
    }
}
