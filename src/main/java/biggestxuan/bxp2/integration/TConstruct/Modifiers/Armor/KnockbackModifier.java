package biggestxuan.bxp2.integration.TConstruct.Modifiers.Armor;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.LimitLevelModifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */
public class KnockbackModifier extends LimitLevelModifier implements ToolStatsModifierHook {
    public KnockbackModifier(){
        super(3);
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder builder) {
        int level = modifierEntry.getLevel();
        ToolStats.KNOCKBACK_RESISTANCE.add(builder,0.05 * level);
    }
}
