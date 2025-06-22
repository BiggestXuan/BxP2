package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.accessor.IMekBuildAccessor;
import biggestxuan.bxp2.mixin.StructureBuilderAccessor;
import com.jerry.mekanism_extras.common.command.ExtraBuilders;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.command.builders.Builders;
import mekanism.common.command.builders.StructureBuilder;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.registries.GeneratorsBuilders;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/5/13
 */
public class AutoBuildItem extends BxPItem{
    public AutoBuildItem(){
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @org.jetbrains.annotations.Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        BuilderWrapper wrapper = getBuilder(p_41421_);
        p_41423_.add(BxP2.tr("tooltip.auto_build.warning").withStyle(ChatFormatting.RED));
        p_41423_.add(BxP2.tr("tooltip.auto_build.tips").withStyle(ChatFormatting.AQUA));
        if(wrapper != null){
            p_41423_.add(BxP2.tr("tooltip.auto_build.structure",wrapper.lang.translate()).withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        super.use(level,player,hand);
        ItemStack stack = player.getItemInHand(hand);
        if(!level.isClientSide){
            BuilderWrapper wrapper = getBuilder(stack);
            if(wrapper != null){
                StructureBuilder builder = wrapper.builder;
                StructureBuilderAccessor accessor = (StructureBuilderAccessor) builder;
                if(builder instanceof IMekBuildAccessor accessor1){
                    accessor1.publicBuild(level,player.blockPosition(),false);
                }else{
                    accessor.build(level,player.blockPosition(),false);
                }
                stack.shrink(1);
                return InteractionResultHolder.consume(stack);
            }
        }
        return InteractionResultHolder.fail(stack);
    }

    @Nullable
    private static BuilderWrapper getBuilder(ItemStack stack){
        BuilderWrapper builder = null;
        CompoundTag tag = stack.getTag();
        if(tag == null){
            return builder;
        }
        String name = tag.getString("builder");
        builder = switch (name){
            case "boiler" -> new BuilderWrapper(new Builders.BoilerBuilder(), MekanismLang.BOILER);
            case "evaporation" -> new BuilderWrapper(new Builders.EvaporationBuilder(),MekanismLang.EVAPORATION_PLANT);
            case "tank" -> new BuilderWrapper(new Builders.TankBuilder(),MekanismLang.DYNAMIC_TANK);
            case "sps" -> new BuilderWrapper(new Builders.SPSBuilder(),MekanismLang.SPS);
            case "martix" -> new BuilderWrapper(new Builders.MatrixBuilder(),MekanismLang.MATRIX);
            case "fission" -> new BuilderWrapper(new GeneratorsBuilders.FissionReactorBuilder(), GeneratorsLang.FISSION_REACTOR);
            case "turbine" -> new BuilderWrapper(new GeneratorsBuilders.TurbineBuilder(),GeneratorsLang.TURBINE);
            case "fusion" -> new BuilderWrapper(new GeneratorsBuilders.FusionReactorBuilder(),GeneratorsLang.FUSION_REACTOR);
            case "naquadah" -> new BuilderWrapper(new ExtraBuilders.NaquadahReactorBuilder(), () -> "reactor.mekanism_extras.naquadah_reactor");
            default -> null;
        };
        return builder;
    }

    public record BuilderWrapper(StructureBuilder builder, ILangEntry lang){}
}
