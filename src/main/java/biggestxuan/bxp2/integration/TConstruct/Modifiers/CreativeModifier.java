package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.BxP2;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolDataNBT;

/**
 * @Author Biggest_Xuan
 * 2025/5/9
 */
public class CreativeModifier extends Modifier implements VolatileDataModifierHook, ModifierRemovalHook {
    public static final ResourceLocation KEY_SLOTS = BxP2.RL("creative_slot");
    private SlotType type;

    public CreativeModifier(SlotType type){
        this.type = type;
    }

    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.VOLATILE_DATA, ModifierHooks.REMOVE);
    }

    @Override
    public @Nullable Component onRemoved(IToolStackView iToolStackView, Modifier modifier) {
        iToolStackView.getPersistentData().remove(KEY_SLOTS);
        return null;
    }

    @Override
    public void addVolatileData(IToolContext iToolContext, ModifierEntry modifierEntry, ToolDataNBT toolDataNBT) {
        IModDataView persistentData = iToolContext.getPersistentData();
        toolDataNBT.addSlots(type,modifierEntry.getLevel());
    }
}
