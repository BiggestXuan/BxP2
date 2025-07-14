package biggestxuan.bxp2.integration.TConstruct.Leveling;

import biggestxuan.bxp2.BxP2;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ToolDataNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

/**
 * @Author Biggest_Xuan
 * 2025/5/28
 */
public abstract class LevelBufferModifier extends Modifier implements ToolStatsModifierHook, VolatileDataModifierHook {
    public static final ResourceLocation KEY_SLOTS = BxP2.RL("level_slot");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS,ModifierHooks.VOLATILE_DATA);
    }

    protected float getLevelRate(float[] arr,int level){
        if(level <= 5){
            return arr[0] * level;
        }
        else if(level <= 10){
            return arr[1] * level;
        }
        else if(level <= 15){
            return arr[2] * level;
        }
        else if(level <= 20){
            return arr[3] * level;
        }
        else if(level <= 25){
            return arr[4] * level;
        }
        else if(level <= 30){
            return arr[5] * level;
        }
        return arr[6] * level;
    }

    @Override
    public void addVolatileData(IToolContext iToolContext, ModifierEntry modifierEntry, ToolDataNBT toolDataNBT) {

    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder builder) {
        ToolStats.ATTACK_DAMAGE.add(builder,getDamageBuffer(builder.getStat(ToolStats.ATTACK_DAMAGE),modifierEntry.getLevel()));
        ToolStats.DURABILITY.add(builder,getDurability(builder.getStat(ToolStats.DURABILITY),modifierEntry.getLevel()));
        ToolStats.MINING_SPEED.add(builder,getPickSpeed(builder.getStat(ToolStats.MINING_SPEED),modifierEntry.getLevel()));
        ToolStats.ATTACK_SPEED.add(builder,getAttackSpeed(builder.getStat(ToolStats.ATTACK_SPEED),modifierEntry.getLevel()));
        ToolStats.ARMOR.add(builder,getArmor(builder.getStat(ToolStats.ARMOR),modifierEntry.getLevel()));
        ToolStats.ARMOR_TOUGHNESS.add(builder,getArmorToughness(builder.getStat(ToolStats.ARMOR_TOUGHNESS),modifierEntry.getLevel()));
        ToolStats.KNOCKBACK_RESISTANCE.add(builder,getKnockbackResistance(builder.getStat(ToolStats.KNOCKBACK_RESISTANCE),modifierEntry.getLevel()));
    }

    protected float getDamageBuffer(float v,int level){
        return 0;
    }

    protected float getDurability(float v,int level){
        return  v * getLevelRate(new float[]{0.02f,0.03f,0.04f,0.06f,0.08f,0.12f,0.2f},level);
    }

    protected float getAttackSpeed(float v,int level){
        return 0;
    }

    protected float getPickSpeed(float v,int level){
        return 0;
    }

    protected float getArmor(float v,int level){
        return 0;
    }

    protected float getArmorToughness(float v,int level){
        return 0;
    }

    protected float getKnockbackResistance(float v,int level){
        return 0;
    }

    public static class WeaponLevelModifier extends LevelBufferModifier{
        @Override
        protected float getDamageBuffer(float v,int level){
            return v * getLevelRate(new float[]{0.005f,0.006f,0.008f,0.01f,0.013f,0.015f,0.023f},level);
        }
    }

    public static class ToolLevelModifier extends LevelBufferModifier{
        protected float getPickSpeed(float v,int level){
            return v * getLevelRate(new float[]{0.05f,0.06f,0.08f,0.1f,0.13f,0.15f,0.23f},level);
        }

        @Override
        protected float getDurability(float v,int level){
            return  v * getLevelRate(new float[]{0.05f,0.08f,0.13f,0.2f,0.26f,0.35f,0.5f},level);
        }
    }

    public static class ArmorLevelModifier extends LevelBufferModifier{
        protected float getArmor(float v,int level){
            return v * getLevelRate(new float[]{0.013f,0.015f,0.018f,0.022f,0.025f,0.033f,0.038f},level);
        }

        protected float getArmorToughness(float v,int level){
            return v * getLevelRate(new float[]{0.003f,0.004f,0.005f,0.007f,0.01f,0.015f,0.023f},level);
        }

        protected float getKnockbackResistance(float v,int level){
            return v * getLevelRate(new float[]{0.003f,0.004f,0.005f,0.008f,0.011f,0.015f,0.023f},level);
        }
    }
}
