package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.items.MoneyItem;
import net.mehvahdjukaar.dummmmmmy.common.TargetDummyEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
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
 * 2025/5/6
 */
public class MoneyDevourerModifier extends Modifier implements MeleeDamageModifierHook {
    private static final String MONEY_ID = "bxp_max_money";

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        LivingEntity target = toolAttackContext.getLivingTarget();
        if(!(target instanceof TargetDummyEntity)){
            CompoundTag tag = target.getPersistentData();
            if(!tag.contains(MONEY_ID)){
                tag.putFloat(MONEY_ID,target.getMaxHealth() * 0.002F);
            }
            if(toolAttackContext.getAttacker() instanceof Player player){
                float heal = v1 * 0.05F;
                if(player.getMaxHealth() - player.getHealth() < heal && tag.getFloat(MONEY_ID) > 0f){
                    float money = tag.getFloat(MONEY_ID) * 0.15f * (v1 / target.getMaxHealth());
                    tag.putFloat(MONEY_ID,Math.max(0,tag.getFloat(MONEY_ID) - money));
                    MoneyItem.dropMoney(target,Math.min(money,0.15f * (1 + 0.1F * modifierEntry.getLevel())),true);
                }
                player.heal(heal);
            }
        }
        return v1;
    }
}
