package biggestxuan.bxp2.integration.Thinker.Modifiers.LOL;

import net.minecraft.server.level.ServerLevel;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/4/13
 */
public class FistOfAbsoluteJustice extends BaseLOLModifier{
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var attacker = toolAttackContext.getAttacker();
        if(attacker.level() instanceof ServerLevel sl){
            if(attacker.getHealth() > attacker.getMaxHealth() / 2){
                return v1 * (1 + (0.15F * modifierEntry.getLevel()));
            }else{
                attacker.heal(v1 * (0.2F * modifierEntry.getLevel()));
                return v1;
            }
        }
        return v1;
    }
}
