package biggestxuan.bxp2.integration.Thermal;

import biggestxuan.bxp2.items.BxPItem;
import cofh.core.common.item.IAugmentItem;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.lib.util.constants.NBTTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 *  @Author Biggest_Xuan
 *  2025/5/4
 */
public class UpgradeItem extends BxPItem implements IAugmentItem {
    private final float upgradeRate;

    public UpgradeItem(float rate){
        upgradeRate = rate;
    }

    @Override
    public @Nullable CompoundTag getAugmentData(ItemStack itemStack) {
        return AugmentDataHelper.builder().type(NBTTags.TAG_AUGMENT_TYPE_UPGRADE).mod(NBTTags.TAG_AUGMENT_BASE_MOD,upgradeRate).build();
    }
}
