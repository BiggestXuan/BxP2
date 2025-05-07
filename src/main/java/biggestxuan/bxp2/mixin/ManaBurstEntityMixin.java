package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.utils.WorldUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.api.mana.LensEffectItem;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

/**
 * @Author Biggest_Xuan
 * 2025/5/6
 */

@Mixin(value = ManaBurstEntity.class,remap = false)
public abstract class ManaBurstEntityMixin {
    @Shadow public abstract ItemStack getSourceLens();

    /**
     * @author Biggest_Xuan
     * @reason Support TConstruct
     */
    @Overwrite
    private LensEffectItem getLensInstance() {
        ItemStack lens = this.getSourceLens();
        if (!lens.isEmpty()) {
            Item var3 = lens.getItem();
            if (var3 instanceof LensEffectItem) {
                LensEffectItem effect = (LensEffectItem)var3;
                return effect;
            }
            if(WorldUtils.isTerraWeapon(lens)){
                LensEffectItem terra = (TerraBladeItem) BotaniaItems.terraSword;
                return terra;
            }
        }

        return null;
    }
}
