package biggestxuan.bxp2.integration.AsteorBar;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.MekaArmorModifiers.MekaTinkerUtils;
import com.afoxxvi.asteorbar.AsteorBar;
import com.afoxxvi.asteorbar.overlay.parts.SimpleBarOverlay;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

/**
 * @Author Biggest_Xuan
 * 2025/6/10
 */
public class BXValueOverlay extends SimpleBarOverlay {
    private static final int BX_FILL_COLOR = 0xFF8000FF;
    private static final int BX_EMPTY_BASE = 0xFF2A003C;

    @Override
    protected Parameters getParameters(Player player) {
        float totalMax = 0.0f;
        float totalCurrent = 0.0f;
        if(!MekaTinkerUtils.isAllMekaArmor(player)){
            return null;
        }
        for (ItemStack armorStack : player.getArmorSlots()) {
            if(armorStack.getItem() instanceof ModifiableArmorItem item){
                ToolStack tool = ToolStack.from(armorStack);
                var data = tool.getPersistentData();
                float max = data.getFloat(BxP2.RL("bx_max_value"));
                float bx = data.getFloat(BxP2.RL("bx_value"));
                totalMax += max;
                totalCurrent += bx;
            }
        }
        final var params = new Parameters();
        params.capacity = totalMax;
        params.value = totalCurrent;
        params.fillColor = BX_FILL_COLOR;
        params.boundColor = AsteorBar.config.armorBoundColor();
        params.emptyColor = BX_EMPTY_BASE;
        params.centerText = String.format("%.0f/%.0f", totalCurrent, totalMax);
        params.centerColor = 0xFFFCFCFC;
        return params;
    }

    @Override
    protected boolean isLeftSide() {
        return true;
    }

    @Override
    protected boolean shouldRender(Player player) {
        return true;
    }
}
