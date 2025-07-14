package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.handler.MobSoulHandler;
import com.blakebr0.mysticalagriculture.init.ModEnchantments;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/7/8
 */

@Mixin(value = MobSoulHandler.class,remap = false)
public abstract class MobSoulHandlerMixin {
    @Shadow
    private static List<ItemStack> getValidSoulJars(Player player, MobSoulType type) {
        return null;
    }

    @Inject(method = "onLivingDeath",at = @At("HEAD"))
    public void __inject(LivingDeathEvent event, CallbackInfo ci){
        Entity source = event.getSource().getEntity();
        if (source instanceof Player player) {
            ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);
            if(held.getItem() instanceof ModifiableItem item){
                int level = ModifierUtil.getModifierLevel(held, BxPModifiers.SOUL.getId());
                if(level >= 1){
                    LivingEntity entity = event.getEntity();
                    MobSoulType type = MobSoulTypeRegistry.getInstance().getMobSoulTypeByEntity(entity);
                    if (type == null || !type.isEnabled()) {
                        return;
                    }

                    List<ItemStack> jars = getValidSoulJars(player, type);
                    if (!jars.isEmpty()) {
                        double remaining = 2 * level;

                        for(ItemStack jar : jars) {
                            remaining = MobSoulUtils.addSoulsToJar(jar, type, remaining);
                            if (remaining <= (double)0.0F) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
