package biggestxuan.bxp2.integration.thinker.Modifiers;

import biggestxuan.bxp2.utils.BXUtils;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */
public class BXModifier extends Modifier implements MeleeDamageModifierHook{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        double d = 0.9 + 0.1 * modifierEntry.getLevel();
        Player player = toolAttackContext.getPlayerAttacker();
        if(player == null) return v;
        int value = BXUtils.getPlayerBXValue(player,getMaxValue());
        return (float) (v1 * (1 + value * perValueDamage() * d));
    }

    protected int getMaxValue(){
        return 300;
    }

    protected double perValueDamage(){
        return 0.0005D;
    }

    public static class BXModifier1 extends BXModifier{
        @Override
        protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
            super.registerHooks(hookBuilder);
        }

        protected int getMaxValue(){
            return 1500;
        }

        protected double perValueDamage(){
            return 0.00055D;
        }
    }

    public static class BXModifier2 extends BXModifier{
        @Override
        protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
            super.registerHooks(hookBuilder);
        }

        protected int getMaxValue(){
            return 4500;
        }

        protected double perValueDamage(){
            return 0.0006D;
        }
    }
}
