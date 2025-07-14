package biggestxuan.bxp2.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

/**
 * @Author Biggest_Xuan
 * 2025/7/8
 */

@Mixin(ModifiableItem.class)
public abstract class ModifiableItemMixin extends Item {
    public ModifiableItemMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean canBeHurtBy(DamageSource p_41387_) {
        return false;
    }
}
