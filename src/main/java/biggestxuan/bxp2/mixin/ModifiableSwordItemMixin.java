package biggestxuan.bxp2.mixin;

import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.brandonscore.capability.MultiCapabilityProvider;
import com.brandon3055.draconicevolution.api.modules.ModuleCategory;
import com.brandon3055.draconicevolution.api.modules.lib.ModularOPStorage;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleHostImpl;
import com.brandon3055.draconicevolution.init.EquipCfg;
import com.brandon3055.draconicevolution.init.ModuleCfg;
import com.brandon3055.draconicevolution.items.equipment.DETier;
import com.brandon3055.draconicevolution.items.equipment.IModularMelee;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */

//想让匠魂材料支持龙之研究的模块化，但是失败了，如果你有想法请联系我～

//@Mixin(ModifiableSwordItem.class)
public class ModifiableSwordItemMixin implements IModularMelee {
    @Unique
    DETier tier = new DETier(TechLevel.WYVERN);

    @Unique
    TechLevel level = TechLevel.WYVERN;

    @Override
    public DETier getItemTier() {
        return new DETier(TechLevel.WYVERN);
    }

    @Override
    public double getSwingSpeedMultiplier() {
        return 0;
    }

    @Override
    public double getDamageMultiplier() {
        return 0;
    }

    @Override
    public TechLevel getTechLevel() {
        return TechLevel.WYVERN;
    }

    @Override
    public ModuleHostImpl createHost(ItemStack itemStack) {
        ModuleHostImpl host = new ModuleHostImpl(level, ModuleCfg.toolWidth(level), ModuleCfg.toolHeight(level), "sword", ModuleCfg.removeInvalidModules, new ModuleCategory[0]);
        return host;
    }

    @Nullable
    @Override
    public ModularOPStorage createOPStorage(ItemStack itemStack, ModuleHostImpl moduleHost) {
        return new ModularOPStorage(moduleHost, EquipCfg.getBaseToolEnergy(this.level), EquipCfg.getBaseToolTransfer(this.level));
    }

    @Override
    public boolean isRepairable(ItemStack itemStack) {
        return true;
    }

    public void initCapabilities(ItemStack stack, ModuleHostImpl host, MultiCapabilityProvider provider) {
    }

}
