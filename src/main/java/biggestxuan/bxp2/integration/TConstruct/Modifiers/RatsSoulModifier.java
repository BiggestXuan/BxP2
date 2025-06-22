package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import com.github.alexthe666.rats.registry.RatlantisEntityRegistry;
import com.github.alexthe666.rats.server.entity.misc.RatProtector;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */
public class RatsSoulModifier extends BaseMeleeDamageModifier{
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        Entity entity = toolAttackContext.getTarget();
        LivingEntity l = toolAttackContext.getAttacker();
        if (!l.level().isClientSide && entity instanceof LivingEntity living && l instanceof Player player) {
            if (living.isAlive() && !(living instanceof RatProtector) && player.swingTime == 0) {
                RatProtector protector = new RatProtector(RatlantisEntityRegistry.RAT_PROTECTOR.get(), entity.level());
                protector.moveTo(player.getX(), player.getY() + 1.0F, player.getZ(), player.getYRot(), player.getXRot());
                protector.setTarget(living);
                entity.level().addFreshEntity(protector);
            }
        }
        return v1;
    }
}
