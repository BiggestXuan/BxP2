package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import com.github.alexthe666.rats.registry.RatsEntityRegistry;
import com.github.alexthe666.rats.registry.RatsItemRegistry;
import com.github.alexthe666.rats.registry.RatsSoundRegistry;
import com.github.alexthe666.rats.server.entity.projectile.PlagueShot;
import com.github.alexthe666.rats.server.events.ForgeEvents;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

/**
 * @Author Biggest_Xuan
 * 2025/5/8
 */

@Mixin(value = ForgeEvents.class,remap = false)
public class ForgeEventsMixin {
    /**
     * @author Biggest_Xuan
     * @reason Support TConstruct
     */
    @Overwrite
    public static void handleArmSwing(ItemStack stack, Player player) {
        if (BxP2$isPlague(stack) && player.swingTime == 0 && !player.isSpectator()) {
            Multimap<Attribute, AttributeModifier> dmg = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
            double totalDmg = 0.0F;
            for(AttributeModifier modifier : dmg.get(Attributes.ATTACK_DAMAGE)) {
                totalDmg += modifier.getAmount();
            }
            player.playSound(RatsSoundRegistry.PLAGUE_CLOUD_SHOOT.get(), 1.0F, 1.0F);
            PlagueShot shot = new PlagueShot(RatsEntityRegistry.PLAGUE_SHOT.get(), player.level(), player, totalDmg * (double)0.5F);
            Vec3 vec3 = player.getViewVector(1.0F);
            shot.shoot(vec3.x(), vec3.y(), vec3.z(), 1.0F, 0.5F);
            player.level().addFreshEntity(shot);
        }
    }

    @Unique
    private static boolean BxP2$isPlague(ItemStack stack){
        return stack.is(RatsItemRegistry.PLAGUE_SCYTHE.get()) || ModifierUtil.getModifierLevel(stack, BxPModifiers.BlackDeath.getId()) > 0;
    }
}
