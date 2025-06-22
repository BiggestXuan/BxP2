package biggestxuan.bxp2.integration.TConstruct.Modifiers.DE;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LimitLevelModifier;
import com.brandon3055.brandonscore.api.TechLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.UUID;
import java.util.function.BiConsumer;

/**
 * @Author Biggest_Xuan
 * 2025/5/7
 */
public class DEDamageModifier extends LimitLevelModifier implements ToolStatsModifierHook, ValidateModifierHook {
    private float damageBoost = 0;
    private TechLevel tier;
    private final Lazy<UUID> uuid = Lazy.of(() -> UUID.nameUUIDFromBytes((damageBoost+this.getId().toString()).getBytes()));
    private final Lazy<String> attributeName = Lazy.of(() -> {
        ResourceLocation id = this.getId();
        String path = id.getPath();
        return path + "." + id.getNamespace() + ".attack_damage_"+damageBoost;
    });

    public DEDamageModifier(float damageBoost,TechLevel tier){
        super(4);
        this.damageBoost = damageBoost;
        this.tier = tier;
    }

    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
        hookBuilder.addHook(this,ModifierHooks.VALIDATE);
    }

    @Override
    public @Nullable Component validate(IToolStackView tool, ModifierEntry modifierEntry) {
        Component c = super.validate(tool,modifierEntry);
        if(c != null){
            return c;
        }
        int level = tool.getModifierLevel(BxPModifiers.Evolution.get());
        for(ModifierEntry modifier : tool.getModifierList()){
            if(modifier.getModifier() instanceof DEDamageModifier){
                return BxP2.tr("tooltip.tconstruct.max_level");
            }
        }
        if(level > 0){
            return null;
        }
        return BxP2.tr("tooltip.tconstruct.evolution");
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder builder) {
        double boost = damageBoost * modifierEntry.getLevel();
        ToolStats.ATTACK_DAMAGE.add(builder,boost);
    }
}
