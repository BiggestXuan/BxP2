package biggestxuan.bxp2.integration.Thinker.Modifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.Thinker.Modifiers.Botania.ManaRepairModifier;
import biggestxuan.bxp2.integration.Thinker.Modifiers.Botania.TerraModifier;
import biggestxuan.bxp2.integration.Thinker.Modifiers.DE.*;
import biggestxuan.bxp2.integration.Thinker.Modifiers.LOL.DefenseBreaker;
import biggestxuan.bxp2.integration.Thinker.Modifiers.LOL.FistOfAbsoluteJustice;
import biggestxuan.bxp2.integration.Thinker.Modifiers.LOL.GuiSuoReckoning;
import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.draconicevolution.items.equipment.DETier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

@SuppressWarnings({"unused"})
public class BxPModifiers {
    public static ModifierDeferredRegister REGISTER = ModifierDeferredRegister.create(BxP2.MODID);

    public static StaticModifier<BXModifier> BX = REGISTER.register("bx", BXModifier::new);
    public static StaticModifier<BXModifier.BXModifier1> BX1 = REGISTER.register("bx1", BXModifier.BXModifier1::new);
    public static StaticModifier<BXModifier.BXModifier2> BX2 = REGISTER.register("bx2", BXModifier.BXModifier2::new);
    public static StaticModifier<ColdnessModifier> COLDNESS = REGISTER.register("coldness", ColdnessModifier::new);
    public static StaticModifier<GiantKillerModifier> GIANT_KILLER = REGISTER.register("giant_killer", GiantKillerModifier::new);
    public static StaticModifier<KillAllModifiers> KILLALL = REGISTER.register("kill_all", KillAllModifiers::new);
    public static StaticModifier<SorrowModifier> SORROW = REGISTER.register("sorrow", SorrowModifier::new);
    public static StaticModifier<EternalModifier> ETERNAL = REGISTER.register("eternal", EternalModifier::new);
    public static StaticModifier<ScaleboundFuryModifier> ScaleboundFuryModifier = REGISTER.register("scalebound_fury", ScaleboundFuryModifier::new);
    public static StaticModifier<ChronodragonFuryModifier> ChronodragonFuryModifier = REGISTER.register("chronodragon_fury", ChronodragonFuryModifier::new);
    public static StaticModifier<GuiSuoReckoning> GuiSuoReckoning = REGISTER.register("guisuo_reckoning", GuiSuoReckoning::new);
    public static StaticModifier<DefenseBreaker> DefenseBreaker = REGISTER.register("defense_breaker", DefenseBreaker::new);
    public static StaticModifier<FistOfAbsoluteJustice> FistOfAbsoluteJustice = REGISTER.register("fist_of_absolute_justice", FistOfAbsoluteJustice::new);
    public static StaticModifier<MoneyDevourerModifier> MoneyDevourer = REGISTER.register("money_devourer",MoneyDevourerModifier::new);
    public static StaticModifier<ManaRepairModifier> ManaRepair = REGISTER.register("mana_repair",ManaRepairModifier::new);
    public static StaticModifier<EvolutionModifier> Evolution = REGISTER.register("evolution",EvolutionModifier::new);
    public static StaticModifier<TerraModifier> TerraModifier = REGISTER.register("terra",TerraModifier::new);
    public static StaticModifier<DEDamageModifier> DragonDamageModifier = REGISTER.register("dragon_damage",() -> new DEDamageModifier(4f, TechLevel.DRACONIC));
    public static StaticModifier<DEDamageModifier> WyvernDamageModifier = REGISTER.register("wyvern_damage",() -> new DEDamageModifier(12f,TechLevel.WYVERN));
    public static StaticModifier<DEDamageModifier> AwakenedDamageModifier = REGISTER.register("awakened_damage",() -> new DEDamageModifier(36f,TechLevel.DRACONIUM));
    public static StaticModifier<DEDamageModifier> ChaoticDamageModifier = REGISTER.register("chaotic_damage",() -> new DEDamageModifier(108f,TechLevel.CHAOTIC));
}
