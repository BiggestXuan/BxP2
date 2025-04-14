package biggestxuan.bxp2.integration.thinker.Modifiers.LOL;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/4/13
 */
public class GuiSuoReckoning extends BaseLOLModifier{
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        Entity target = toolAttackContext.getLivingTarget();
        if(target != null && target.level() instanceof ServerLevel sl){
            target.getPersistentData().putInt("guisuo_count",target.getPersistentData().getInt("guisuo_count")+1);
            int count = target.getPersistentData().getInt("guisuo_count");
            int level = modifierEntry.getLevel();
            return v1 * (1 + (level * 0.05F + count * 0.01F));
        }
        return v1;
    }
}
