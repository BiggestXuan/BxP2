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
public class ToughnessModifier extends LimitLevelModifier implements ToolStatsModifierHook {
    public ToughnessModifier(){
        super(3);
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder builder) {
        int level = modifierEntry.getLevel();
        ToolStats.ARMOR_TOUGHNESS.add(builder,0.75 * level);
    }
}
