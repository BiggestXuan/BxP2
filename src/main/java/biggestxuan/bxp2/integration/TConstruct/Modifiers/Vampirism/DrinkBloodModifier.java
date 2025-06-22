package biggestxuan.bxp2.integration.TConstruct.Modifiers.Vampirism;

import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import de.teamlapen.vampirism.entity.vampire.DrinkBloodContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class DrinkBloodModifier extends Modifier implements GeneralInteractionModifierHook, OnAttackedModifierHook, MeleeHitModifierHook, InventoryTickModifierHook {
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT, ModifierHooks.ON_ATTACKED,ModifierHooks.MELEE_HIT,ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onAttacked(IToolStackView iToolStackView, ModifierEntry modifierEntry, EquipmentContext equipmentContext, EquipmentSlot equipmentSlot, DamageSource damageSource, float v, boolean b) {
        if (equipmentSlot.getType() == EquipmentSlot.Type.HAND && equipmentContext.getEntity() instanceof Player player) {
            drinkBlood(iToolStackView,modifierEntry,player);
        }
    }

    @Override
    public InteractionResult onToolUse(IToolStackView iToolStackView, ModifierEntry modifierEntry, Player player, InteractionHand interactionHand, InteractionSource interactionSource) {
        drinkBlood(iToolStackView,modifierEntry,player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if(context.getAttacker() instanceof Player player){
            drinkBlood(tool,modifier,player);
        }
    }

    private static void drinkBlood(IToolStackView tool, ModifierEntry modifier, Player player){
        var v = VampirismAPI.getVampirePlayer(player);
        if(v.isPresent()){
            IVampirePlayer vp = v.orElseThrow(NullPointerException::new);
            Level world = player.level();
            if(!vp.wantsBlood()) return;
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.NEUTRAL, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
            vp.drinkBlood(2 * modifier.getLevel(),0.2F * modifier.getLevel(), DrinkBloodContext.none());
        }else{
            player.hurt(player.level().damageSources().magic(),1F);
        }
        tool.setDamage(tool.getDamage() + 6 * modifier.getLevel());
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        if(level instanceof ServerLevel sl && sl.getServer().getTickCount() % 40 == 0 && livingEntity instanceof Player player){
            drinkBlood(iToolStackView,modifierEntry,player);
        }
    }
}
