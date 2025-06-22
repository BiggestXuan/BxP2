package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.utils.Utils;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.EMCHelper;
import moze_intel.projecte.utils.text.ILangEntry;
import moze_intel.projecte.utils.text.PELang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.math.BigInteger;

/**
 * @Author Biggest_Xuan
 * 2025/5/31
 */

@Mixin(value = EMCHelper.class,remap = false)
public abstract class EMCHelperMixin {

    /**
     * @author Biggest_Xuan
     * @reason Format value
     */
    @Overwrite
    public static Component getEmcTextComponent(long emc, int stackSize) {
        if (ProjectEConfig.server.difficulty.covalenceLoss.get() == (double)1.0F) {
            String value;
            ILangEntry prefix;
            if (stackSize > 1) {
                prefix = PELang.EMC_STACK_TOOLTIP;
                value = Utils.KMT(BigInteger.valueOf(emc).multiply(BigInteger.valueOf(stackSize)));
            } else {
                prefix = PELang.EMC_TOOLTIP;
                value = Utils.KMT(emc);
            }

            return prefix.translateColored(ChatFormatting.YELLOW, ChatFormatting.WHITE, value);
        } else {
            long emcSellValue = EMCHelper.getEmcSellValue(emc);
            ILangEntry prefix;
            String value;
            String sell;
            if (stackSize > 1) {
                prefix = PELang.EMC_STACK_TOOLTIP_WITH_SELL;
                BigInteger bigIntStack = BigInteger.valueOf(stackSize);
                value = Constants.EMC_FORMATTER.format(BigInteger.valueOf(emc).multiply(bigIntStack));
                sell = Constants.EMC_FORMATTER.format(BigInteger.valueOf(emcSellValue).multiply(bigIntStack));
            } else {
                prefix = PELang.EMC_TOOLTIP_WITH_SELL;
                value = Constants.EMC_FORMATTER.format(emc);
                sell = Constants.EMC_FORMATTER.format(emcSellValue);
            }

            return prefix.translateColored(ChatFormatting.YELLOW, ChatFormatting.WHITE, value, ChatFormatting.BLUE, sell);
        }
    }
}
