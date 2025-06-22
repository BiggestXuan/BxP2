package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.BxP2;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/28
 */
public class LimitLevelModifier extends Modifier implements ValidateModifierHook {
    private final int maxLevel;

    public LimitLevelModifier(){
        this(4);
    }

    public LimitLevelModifier(int l){
        this.maxLevel = l;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.VALIDATE);
    }

    @Override
    public @Nullable Component validate(IToolStackView iToolStackView, ModifierEntry modifierEntry) {
        if(modifierEntry.getLevel()-1>= maxLevel){
            return BxP2.tr("tooltip.tconstruct.max_level");
        }
        return null;
    }
}
