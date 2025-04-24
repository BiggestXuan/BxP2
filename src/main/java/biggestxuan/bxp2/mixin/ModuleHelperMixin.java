package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.BxPModules;
import mekanism.api.gear.IModuleHelper;
import mekanism.api.gear.ModuleData;
import mekanism.common.content.gear.ModuleHelper;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.registries.MekanismItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Biggest_Xuan
 * 2025/4/19
 */

@Mixin(ModuleHelper.class)
public abstract class ModuleHelperMixin implements IModuleHelper {
    @Shadow(remap = false)
    @Final
    private Map<ModuleData<?>, Set<Item>> supportedContainers;

    @Shadow (remap = false)
    @Final
    private Map<Item, Set<ModuleData<?>>> supportedModules;

    @Inject(method = "processIMC",at = @At("RETURN"),remap = false)
    public void _inject(CallbackInfo ci){
        HashSet<Item> set = new HashSet<>();
        set.add(MekanismItems.MEKASUIT_HELMET.get());
        supportedContainers.put(BxPModules.SUN_PROTECT.getModuleData(),set);
        HashSet<Item> set1 = new HashSet<>();
        set1.add(MekanismItems.MEKASUIT_HELMET.get());
        set1.add(MekanismItems.MEKASUIT_BODYARMOR.get());
        set1.add(MekanismItems.MEKASUIT_PANTS.get());
        set1.add(MekanismItems.MEKASUIT_BOOTS.get());
        supportedContainers.put(BxPModules.CHAOS_PROTECT.getModuleData(),set1);
    }

    /**
     * @author Biggest_Xuan
     * @reason Add something.
     */
    @Nonnull
    @Overwrite(remap = false)
    @Override
    public Set<ModuleData<?>> getSupported(ItemStack container) {
        Set<ModuleData<?>> set = supportedModules.getOrDefault(container.getItem(), Collections.emptySet());
        Set<ModuleData<?>> set1 = new HashSet<>(set);
        if(container.getItem().equals(MekanismItems.MEKASUIT_HELMET.get())){
            set1.add(BxPModules.SUN_PROTECT.get());
        }
        if(container.getItem() instanceof ItemMekaSuitArmor){
            set1.add(BxPModules.CHAOS_PROTECT.get());
        }
        return set1;
    }
}
