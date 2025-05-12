package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.utils.Utils;
import dev.shadowsoffire.hostilenetworks.data.CachedModel;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import dev.shadowsoffire.hostilenetworks.util.Color;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

import static dev.shadowsoffire.hostilenetworks.item.DataModelItem.getData;

/**
 * @Author Biggest_Xuan
 * 2025/5/9
 */

@Mixin(DataModelItem.class)
public class DataModelItemMixin<E> {
    /**
     * @author Biggest_Xuan
     * @reason Format Energy
     */
    @Overwrite
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> list, TooltipFlag pFlag) {
        if (Screen.hasShiftDown()) {
            CachedModel cModel = new CachedModel(pStack, 0);
            if (!cModel.isValid()) {
                list.add(Component.translatable("Error: %s", Component.literal("Broke_AF").withStyle(ChatFormatting.OBFUSCATED, ChatFormatting.GRAY)));
                return;
            }

            int data = getData(pStack);
            ModelTier tier = ModelTier.getByData(cModel.getModel(), data);
            list.add(Component.translatable("hostilenetworks.info.tier", tier.getComponent()));
            int dProg = data - cModel.getTierData();
            int dMax = cModel.getNextTierData() - cModel.getTierData();
            if (tier != ModelTier.SELF_AWARE) {
                list.add(Component.translatable("hostilenetworks.info.data", Component.translatable("hostilenetworks.info.dprog", new Object[]{dProg, dMax}).withStyle(ChatFormatting.GRAY)));
                int dataPerKill = cModel.getDataPerKill();
                if (dataPerKill == 0) {
                    Component c1 = Component.literal("000 ").withStyle(ChatFormatting.GRAY, ChatFormatting.OBFUSCATED);
                    list.add(Component.translatable("hostilenetworks.info.dpk", new Object[]{c1}).append(Component.translatable("hostilenetworks.info.disabled").withStyle(ChatFormatting.RED)));
                } else {
                    list.add(Component.translatable("hostilenetworks.info.dpk", Component.literal("" + cModel.getDataPerKill()).withStyle(ChatFormatting.GRAY)));
                }
            }

            list.add(Component.translatable("hostilenetworks.info.sim_cost", Component.translatable("hostilenetworks.info.rft", Utils.KMT(String.valueOf(cModel.getModel().simCost()))).withStyle(ChatFormatting.GRAY)));
            List<EntityType<? extends LivingEntity>> subtypes = cModel.getModel().subtypes();
            if (!subtypes.isEmpty()) {
                list.add(Component.translatable("hostilenetworks.info.subtypes"));

                for(EntityType<?> t : subtypes) {
                    list.add(Component.translatable("hostilenetworks.info.sub_list", new Object[]{t.getDescription()}).withStyle(Style.EMPTY.withColor(65472)));
                }
            }
        } else {
            list.add(Component.translatable("hostilenetworks.info.hold_shift", new Object[]{Color.withColor("hostilenetworks.color_text.shift", ChatFormatting.WHITE.getColor())}).withStyle(ChatFormatting.GRAY));
        }

    }
}
