package biggestxuan.bxp2.integration.TConstruct.Modifiers.DE;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */
public class ScaleboundFuryModifier extends Modifier implements MeleeDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        if(!toolAttackContext.getLevel().isClientSide){
            if(Utils.isRandom((BxP2.devMode ? 0.9 : 0.01) / modifierEntry.getLevel())){
                Player player = toolAttackContext.getPlayerAttacker();
                if(player != null){
                    player.hurt(player.level().damageSources().playerAttack(player),v1 * 0.1F);
                }
            }
        }
        return v1;
    }
}
