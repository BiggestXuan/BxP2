package biggestxuan.bxp2.client.screen;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.UpdateLog;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractScrollWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @Author Biggest_Xuan & Deepseek
 * 2025/6/28
 */
public class UpdateLogScreen extends Screen {
    private String version;
    private final List<UpdateLog> allLogs = UpdateLog.getAllLogs();
    private UpdateLog selectedLog;
    private VersionListWidget versionList;
    private ScrollingTextBox logDisplay;
    private final Component title;

    public UpdateLogScreen(String version) {
        super(BxP2.tr("bxp2.screen.update_log"));
        title = BxP2.tr("bxp2.screen.update_log");
        this.version = version;
        if (!allLogs.isEmpty()) {
            selectedLog = allLogs.get(0);
        }
        UpdateLog ul = UpdateLog.getLog(version);
        if(ul != null){
            this.selectedLog = ul;
        }
    }

    @Override
    protected void init() {
        super.init();
        int listWidth = this.width / 4;
        this.versionList = new VersionListWidget(this.minecraft, listWidth, this.height, 30, this.height - 30);
        this.addRenderableWidget(versionList);
        int rightPanelX = listWidth + 10;
        int buttonWidth = 60;
        int buttonHeight = 20;
        int buttonSpacing = 5;
        createCategoryButton(rightPanelX, 30, buttonWidth, buttonHeight, "添加", UpdateLog::add);
        createCategoryButton(rightPanelX + buttonWidth + buttonSpacing, 30, buttonWidth, buttonHeight, "修改", UpdateLog::modify);
        createCategoryButton(rightPanelX + (buttonWidth + buttonSpacing) * 2, 30, buttonWidth, buttonHeight, "修复", UpdateLog::fix);
        createCategoryButton(rightPanelX + (buttonWidth + buttonSpacing) * 3, 30, buttonWidth, buttonHeight, "其他", UpdateLog::other);
        int logDisplayHeight = this.height - 90;
        logDisplay = new ScrollingTextBox(
                this.font,
                rightPanelX,
                60,
                this.width - listWidth - 20,
                logDisplayHeight,
                Component.empty()
        );
        logDisplay.setEditable(false);
        logDisplay.setValue(selectedLog != null ?
                "选择左侧版本和上方分类查看详细更新日志" : "请先选择左侧版本");
        this.addRenderableWidget(logDisplay);
    }

    private void createCategoryButton(int x, int y, int width, int height, String text, Function<UpdateLog, Integer> countGetter) {
        Button button = Button.builder(Component.literal(text), btn -> {
            if (selectedLog != null) {
                String t = switch (text){
                    case "添加" -> "add";
                    case "修改" -> "modify";
                    case "修复" -> "fix";
                    case "其他" -> "other";
                    default -> throw new IllegalStateException("Unexpected value: " + text);
                };
                int count = countGetter.apply(selectedLog);
                if(count == 0){
                    logDisplay.setValue("无");
                }else{
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < count; i++) {
                        sb.append("").append(i + 1).append(".").append(BxP2.tr("bxp2.log_"+selectedLog.version().replace('.','_')+"_"+t+"_"+(i+1)).getString()).append("\n");
                    }
                    logDisplay.setValue(sb.toString());
                }

            }
        }).bounds(x, y, width, height).build();
        this.addRenderableWidget(button);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(this.font, title, this.width / 2, 10, 0xFFFFFF);
        if (selectedLog != null) {
            String viewingText = "你正在查看 " + selectedLog.version() + " 版本的更新日志，欢迎来到QQ群和我们交流："+BxP2.QQGroup;
            guiGraphics.drawString(this.font, viewingText,
                    this.width / 3 + 10, this.height - 25, 0xFFFF00);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    public static class ScrollingTextBox extends AbstractScrollWidget {
        private final Font font;
        private String value = "";
        private boolean editable = true;
        private int frame;
        public int max;
        public int added;

        public ScrollingTextBox(Font font, int x, int y, int width, int height, Component message) {
            super(x, y, width, height, message);
            this.font = font;
            max = 100;
            added = 0;
        }

        public void setValue(String value) {
            this.value = value;
            this.setScrollAmount(0);
            formatValue();
        }

        public String getValue() {
            return value;
        }

        public String copyValue(){
            return String.valueOf(value);
        }

        public void setEditable(boolean editable) {
            this.editable = editable;
        }

        private void formatValue(){
            String[] ls = copyValue().split("\n");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ls.length; i++) {
                String s = ls[i];
                if(Utils.getTextLength(s) >= max){
                    added ++;
                }
                s = Utils.insertNewLines(s,max);
                sb.append(s).append("\n");
            }
            value = sb.toString();
        }

        @Override
        protected void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            int textColor = this.editable ? 0xFFFFFF : 0xA0A0A0;
            int lineHeight = font.lineHeight + 2;
            int scrollOffset = (int) this.scrollAmount();
            int startLine = scrollOffset / lineHeight;
            //int totalLines = ns.split("\n").length;
            int visibleLines = (int) Math.ceil((double) this.height / lineHeight) + 1;
            int endLine = value.split("\n").length + (added > 0 ? added - 1 : 0);
            //BxP2.LOGGER.info("{}",startLine+","+totalLines+","+visibleLines+","+endLine);
            String[] lines = value.split("\n");
            int baseY = this.getY() + 4; // 文本框内顶部内边距

            for (int i = startLine; i < endLine; i++) {
                int lineY = baseY + (i * lineHeight) - scrollOffset;
                if(i >= lines.length){
                    break;
                }
                //if (lineY >= this.getY() && lineY < this.getY() + this.height - font.lineHeight) {
                    guiGraphics.drawString(
                            font,
                            lines[i],
                            this.getX() + 2,
                            lineY,
                            textColor,
                            false
                    );
                //}
            }
        }

        @Override
        protected int getInnerHeight() {
            if (value.isEmpty()) return font.lineHeight;
            return (value.split("\n").length) * (font.lineHeight + 2);
        }

        @Override
        protected boolean scrollbarVisible() {
            return this.getInnerHeight() > this.height;
        }

        @Override
        protected double scrollRate() {
            return font.lineHeight + 2;
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                this.frame++;
                this.renderBackground(guiGraphics);

                // 启用裁剪区域
                guiGraphics.enableScissor(
                        this.getX(),
                        this.getY(),
                        this.getX() + this.width,
                        this.getY() + this.height
                );
                this.renderContents(guiGraphics, mouseX, mouseY, partialTicks);
                guiGraphics.disableScissor();
            }
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

        }

        public void renderBackground(GuiGraphics guiGraphics) {
            guiGraphics.fill(this.getX(), this.getY(),
                    this.getX() + this.width,
                    this.getY() + this.height,
                    0xFF202020);
            guiGraphics.fill(this.getX(), this.getY(),
                    this.getX() + this.width,
                    this.getY() + 1,
                    0xFF606060);
            guiGraphics.fill(this.getX(), this.getY() + this.height - 1,
                    this.getX() + this.width,
                    this.getY() + this.height,
                    0xFF606060);
            guiGraphics.fill(this.getX(), this.getY(),
                    this.getX() + 1,
                    this.getY() + this.height,
                    0xFF606060);
            guiGraphics.fill(this.getX() + this.width - 1, this.getY(),
                    this.getX() + this.width,
                    this.getY() + this.height,
                    0xFF606060);
        }
    }

    class VersionListWidget extends ContainerObjectSelectionList<VersionListWidget.Entry> {
        public VersionListWidget(Minecraft minecraft, int width, int height, int y0, int y1) {
            super(minecraft, width, height, y0, y1, 20);
            this.setRenderBackground(false);
            this.setRenderTopAndBottom(false);
            for (UpdateLog log : allLogs) {
                this.addEntry(new Entry(log));
            }
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width - 6;
        }

        @Override
        public int getRowWidth() {
            return this.width - 10;
        }

        class Entry extends ContainerObjectSelectionList.Entry<Entry> {
            private final UpdateLog log;
            private final Button versionButton;
            public Entry(UpdateLog log) {
                this.log = log;
                this.versionButton = Button.builder(
                                Component.literal(log.version() + " (" + log.updateTime() + ")"),
                                btn -> {
                                    selectedLog = log;
                                    logDisplay.setValue("你正在查看 " + log.version() + " 版本的更新日志，请点击上方按钮选择对应条目！");
                                })
                        .size(getRowWidth(), 18)
                        .build();
            }

            @Override
            public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean isMouseOver, float partialTicks) {
                versionButton.setX(left);
                versionButton.setY(top);
                versionButton.render(guiGraphics, mouseX, mouseY, partialTicks);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                return versionButton.mouseClicked(mouseX, mouseY, button);
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return Collections.singletonList(versionButton);
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                return Collections.singletonList(versionButton);
            }
        }
    }
}
