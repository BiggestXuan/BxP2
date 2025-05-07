package biggestxuan.bxp2.client.shop;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.network.toServer.BuyGoodsPacket;
import biggestxuan.bxp2.recipes.RecipeUtils;
import biggestxuan.bxp2.recipes.ShopGoods;
import biggestxuan.bxp2.utils.PhaseUtils;
import biggestxuan.bxp2.utils.ShopUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan & Deepseek
 * 2025/4/23
 */
public class ShopScreen extends Screen {
    private static final int ITEMS_PER_PAGE = 16;
    private List<ItemDisplayWidget> itemWidgets = new ArrayList<>();
    private List<ShopButton> priceButtons = new ArrayList<>();
    private int currentPage = 0;
    private int totalPages = (ShopGoods.getAllItems().size() + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE;
    private boolean needFlush = false;

    public ShopScreen(int page) {
        super(BxP2.tr("screen.shop.title"));
        this.currentPage = page;
    }

    public ShopScreen() {
        super(BxP2.tr("screen.shop.title"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
        itemWidgets.clear();
        priceButtons.clear();
        int startIndex = currentPage * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, ShopGoods.getAllItems().size());
        for (int i = startIndex; i < endIndex; i++) {
            ShopGoods item = ShopGoods.getAllItems().get(i);
            int row = (i - startIndex) / 4;
            int col = (i - startIndex) % 4;

            int x = this.width / 2 - 160 + col * 80;
            int y = this.height / 2 - 80 + row * 40;

            ShopButton buy = new ShopButton(new Button.Builder(
                    BxP2.tr("screen.shop.buy").append(Component.literal(" -" + String.format("%.2f", ShopUtils.getGoodsPrice(item, getMinecraft().player)))),
                    button -> onBuyClick(item))
                    .pos(x, y)
                    .size(70, 20)
                    ,item);

            if (minecraft.player != null && getPlayerPhase() < item.getPhase()) {
                buy.active = false;
                buy.setTooltip(Tooltip.create(BxP2.tr("screen.shop.phase_limit", PhaseUtils.getName(item.getPhase()))));
            }

            this.addRenderableWidget(buy);
            priceButtons.add(buy);
            ItemDisplayWidget widget = new ItemDisplayWidget(x, y - 20, item);
            itemWidgets.add(widget);
            this.addRenderableWidget(widget);
        }
        if (currentPage > 0) {
            this.addRenderableWidget(new Button.Builder(
                    BxP2.tr("screen.shop.previous"),
                    button -> previousPage())
                    .pos(this.width / 2 - 100, this.height - 30)
                    .size(80, 20)
                    .build());
        }

        if (currentPage < totalPages - 1) {
            this.addRenderableWidget(new Button.Builder(
                    BxP2.tr("screen.shop.next"),
                    button -> nextPage())
                    .pos(this.width / 2 + 20, this.height - 30)
                    .size(80, 20)
                    .build());
        }
        this.addRenderableWidget(new Button.Builder(
                BxP2.tr("screen.shop.close"),
                button -> onClose())
                .pos(this.width / 2 - 40, this.height - 60)
                .size(80, 20)
                .build());
    }

    private void updateAllButton(){
        for (int i = 0; i < priceButtons.size(); i++) {
            ShopButton btn = priceButtons.get(i);
            btn.setMessage(BxP2.tr("screen.shop.buy").append(Component.literal(" -" + String.format("%.2f", ShopUtils.getGoodsPrice(btn.getShopGoods(), getMinecraft().player)))));
            ShopGoods item = btn.getShopGoods();
            if(!btn.playerCanBuy()){
                btn.active = false;
                btn.setTooltip(Tooltip.create(BxP2.tr("screen.shop.no_money", PhaseUtils.getName(item.getPhase()))));
            }
            if (minecraft.player != null && getPlayerPhase() < item.getPhase()) {
                btn.active = false;
                btn.setTooltip(Tooltip.create(BxP2.tr("screen.shop.phase_limit", PhaseUtils.getName(item.getPhase()))));
            }
        }
    }

    private void onBuyClick(ShopGoods item) {
        PacketHandler.sendToServer(new BuyGoodsPacket(item.getStack()));
    }

    public boolean previousPage() {
        if (currentPage > 0) {
            currentPage--;
            this.clearWidgets();
            this.init();
            return true;
        }
        return false;
    }

    public boolean nextPage() {
        if (currentPage < totalPages - 1) {
            currentPage++;
            this.clearWidgets();
            this.init();
            return true;
        }
        return false;
    }

    public void onClose() {
        this.minecraft.setScreen(null);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        Player player = Minecraft.getInstance().player;
        updateAllButton();
        if(player != null && !player.isDeadOrDying() && player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            guiGraphics.drawCenteredString(this.font, BxP2.tr("screen.shop.money",String.format("%.2f",cap.getMoney())), this.width / 4, 30, 0xFFFFFF);
            if(cap.getMoney() < 0){
                guiGraphics.drawString(this.font, BxP2.tr("screen.shop.multi"), this.width / 8, 10, 0xFF0000);
            }
        }
        guiGraphics.drawCenteredString(this.font, (currentPage + 1) + "/" + totalPages, this.width / 2, 40, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        for (ItemDisplayWidget widget : itemWidgets) {
            if (widget.isHovered(mouseX, mouseY)) {
                guiGraphics.renderTooltip(
                        Minecraft.getInstance().font,
                        widget.shopItem.getStack(),
                        mouseX + 5,
                        mouseY + 10
                );
                break;
            }
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (delta > 0) {
            previousPage();
            return true;
        } else if (delta < 0) {
            nextPage();
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    private int getPlayerPhase(){
        Player player = Minecraft.getInstance().player;
        if(player == null || player.isDeadOrDying()) return -1;
        if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            return cap.getPhase();
        }
        return -1;
    }
}

class ShopButton extends Button{
    private final ShopGoods sg;

    protected ShopButton(Builder builder,ShopGoods sg) {
        super(builder);
        this.sg = sg;
    }

    protected ShopGoods getShopGoods(){
        return sg;
    }

    boolean playerCanBuy(){
        LocalPlayer player = Minecraft.getInstance().player;
        if(player != null && player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            IBxPCapability cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            return cap.getMoney() >= ShopUtils.getGoodsPrice(sg,player);
        }
        return false;
    }
}

class ItemDisplayWidget extends AbstractWidget {
    protected final ShopGoods shopItem;

    public ItemDisplayWidget(int x, int y, ShopGoods shopItem) {
        super(x, y, 70, 20, Component.literal(shopItem.getStack().getDisplayName().getString()));
        this.shopItem = shopItem;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.renderItem(shopItem.getStack(), getX(), getY());
        guiGraphics.renderItemDecorations(
                Minecraft.getInstance().font,
                shopItem.getStack(),
                getX(),
                getY()
        );
        guiGraphics.drawString(Minecraft.getInstance().font, getItemStackName(shopItem.getStack()), getX() + 20, getY() + 5, 0xFFFFFF);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= getX() &&
                mouseX <= getX() + width &&
                mouseY >= getY() &&
                mouseY <= getY() + height;
    }

    private static String getItemStackName(ItemStack stack){
        String name = stack.getDisplayName().getString();
        if(name.length() >= 8){
            return name.substring(0,7)+"...";
        }
        return name;
    }
}
