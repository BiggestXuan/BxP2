package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Leveling.LevelBufferModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Armor.KnockbackModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.MekaArmorModifiers.MekaArmorModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Armor.ToughnessModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Botania.ManaRepairModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Botania.TerraModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.DE.*;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.DefenseBreaker;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.FistOfAbsoluteJustice;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.GuiSuoReckoning;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.RadiationAddModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.RadiationDamageModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.RadiationProtectModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mysticalagriculture.SoulModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.ProjectE.EMCKillerModifier;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Vampirism.DrinkBloodModifier;
import com.brandon3055.brandonscore.api.TechLevel;
import de.teamlapen.vampirism.core.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.SlotType;

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
    public static StaticModifier<DEDamageModifier> ChaoticDamageModifier = REGISTER.register("chaotic_damage",() -> new DEDamageModifier.ChaoticDamageModifier(108f,TechLevel.CHAOTIC));
    public static StaticModifier<BlackDeathModifier> BlackDeath = REGISTER.register("black_death",BlackDeathModifier::new);
    public static StaticModifier<BaseMeleeDamageModifier> PLAGUEModifier = REGISTER.register("plague_damage",PlagueDamageModifier::new);
    public static StaticModifier<CreativeModifier> CREATIVE_ABILITY = REGISTER.register("creative_ability",() -> new CreativeModifier(SlotType.ABILITY));
    public static StaticModifier<CreativeModifier> CREATIVE_UPGRADE = REGISTER.register("creative_upgrade",() -> new CreativeModifier(SlotType.UPGRADE));
    public static StaticModifier<BaseMeleeDamageModifier> FpsKiller = REGISTER.register("fps_killer",FpsKillerModifier::new);
    public static StaticModifier<BaseMeleeDamageModifier> PxKiller = REGISTER.register("px_killer",PxKiller::new);
    public static StaticModifier<BaseMeleeDamageModifier> ServerSaveKiller = REGISTER.register("server_save_kill",ServerSaveKiller::new);
    public static StaticModifier<BaseMeleeDamageModifier> LUCKY_ATTACK = REGISTER.register("lucky_attack",LuckyAttackModifier::new);
    public static StaticModifier<BaseMeleeDamageModifier> Radiation_add = REGISTER.register("radiation_add", RadiationAddModifier::new);
    public static StaticModifier<BaseMeleeDamageModifier> Radiation_damage = REGISTER.register("radiation_damage", RadiationDamageModifier::new);
    public static StaticModifier<RadiationProtectModifier> Radiation_protect = REGISTER.register("radiation_protect", RadiationProtectModifier::new);
    public static StaticModifier<DrinkBloodModifier> DrinkBlood = REGISTER.register("drink_blood",DrinkBloodModifier::new);
    public static StaticModifier<FlyModifier> Fly = REGISTER.register("fly",FlyModifier::new);
    public static StaticModifier<OmiteModifier> omite = REGISTER.register("omite",OmiteModifier::new);
    public static StaticModifier<BaseEffectModifier> SunShineProtect = REGISTER.register("sunshine_protect",() -> new BaseEffectModifier(new MobEffectInstance(ModEffects.SUNSCREEN.get(),1000,5,true,true)));
    public static StaticModifier<HalfDamageModifier> HalfDamage = REGISTER.register("half_damage",HalfDamageModifier::new);
    public static StaticModifier<LevelBufferModifier> WeaponLevelModifier = REGISTER.register("weapon_level", LevelBufferModifier.WeaponLevelModifier::new);
    public static StaticModifier<LevelBufferModifier> ToolLevelModifier = REGISTER.register("tool_level", LevelBufferModifier.ToolLevelModifier::new);
    public static StaticModifier<LevelBufferModifier> ArmorLevelModifier = REGISTER.register("armor_level", LevelBufferModifier.ArmorLevelModifier::new);
    public static StaticModifier<LimitLevelModifier> Tool_XP = REGISTER.register("tool_xp",LimitLevelModifier::new);
    public static StaticModifier<LimitLevelModifier> Tool_LIMIT_XP = REGISTER.register("tool_limit_xp",LimitLevelModifier::new);
    public static StaticModifier<RatsSoulModifier> RatSoul = REGISTER.register("rats_soul",RatsSoulModifier::new);
    public static StaticModifier<LimitLevelModifier> Toughness = REGISTER.register("toughness", ToughnessModifier::new);
    public static StaticModifier<LimitLevelModifier> Knockback_resistance = REGISTER.register("knockback_resistance", KnockbackModifier::new);
    public static StaticModifier<BaseMeleeDamageModifier> EMCKiller = REGISTER.register("emc_killer", EMCKillerModifier::new);
    public static StaticModifier<MekaArmorModifier> MekaArmor = REGISTER.register("meka_armor",MekaArmorModifier::new);
    public static StaticModifier<Modifier> SOUL = REGISTER.register("soul", SoulModifier::new);
}
