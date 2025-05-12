package biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/4/13
 */
public class DefenseBreaker extends BaseLOLModifier{
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        Entity entity = toolAttackContext.getTarget();
        if(entity.level() instanceof ServerLevel sl && entity instanceof LivingEntity living) {
            if(living.getArmorValue() != 0){
                return v1 * (1 + 0.1F * modifierEntry.getLevel());
            }
        }
        return v1;
    }
}
