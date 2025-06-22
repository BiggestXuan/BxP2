package biggestxuan.bxp2;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = BxP2.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder().push("General");

    public static final ForgeConfigSpec.IntValue DIFFICULTY = builder.comment("1 -> relax mode, 2 -> advanced mode, 3 -> expert mode").defineInRange("difficulty", 2, 1, 3);
    public static final ForgeConfigSpec.BooleanValue Tinkers_Survival = builder.comment("If true,you must use tconstruct's weapons,tools or armors").define("TinkersSurvival",true);

    public static final ForgeConfigSpec.DoubleValue EASY_HEALTH = builder.pop().push("Difficulty Setting").push("Relax").defineInRange("RegenerationRate",2.0d,0,Float.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue EASY_INTEREST = builder.comment("Interest you will receive if your balance is negative").defineInRange("InterestRate",1d,1,100);
    public static final ForgeConfigSpec.DoubleValue EASY_ENHANCEMENT = builder.comment("Mobs enhancement rate,1 -> 1%,10 -> 10%").defineInRange("EnhancementRate",0d,0,Double.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue EASY_TEAM_MONEY = builder.comment("Amount reduction rate caused by each person in your team, 0.8 -> -20%").defineInRange("MoneyLossRate",1d,0,1);
    public static final ForgeConfigSpec.IntValue EASY_RITUAL_FLIGHT = builder.comment("Duration of flight ritual(tick)").defineInRange("FlightTime",120000,0,Integer.MAX_VALUE);
    public static final ForgeConfigSpec.BooleanValue EASY_TIB_NO_LIMIT = builder.comment("Can use time in bottle in any machine").define("TIBNoLimit",true);
    public static final ForgeConfigSpec.DoubleValue EASY_DEATH_MONEY_BASE = builder.comment("Drop money base when you death. Total = base + (phase * addition)").defineInRange("DeathMoneyBase",0d,0,1);
    public static final ForgeConfigSpec.DoubleValue EASY_DEATH_MONEY_ADDITION = builder.defineInRange("DeathMoneyAddition",0d,0,1);
    public static final ForgeConfigSpec.DoubleValue EASY_OUHUANG_CHANCE = builder.comment("The production probability of ouhuang ingot also affects the reuse rate of vegetable seeds").defineInRange("OuhuangChance",0.75d,0.01d,1d);
    public static final ForgeConfigSpec.DoubleValue EASY_BUY_CREATIVE = builder.comment("Additional price after each purchase of creative items").defineInRange("CreativeRate",0d,0d,Double.MAX_VALUE);

    public static final ForgeConfigSpec.DoubleValue NORMAL_HEALTH = builder.pop().push("Advanced").defineInRange("RegenerationRate",1.0d,0,Float.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue NORMAL_INTEREST = builder.comment("Interest you will receive if your balance is negative").defineInRange("InterestRate",1.01d,1,100);
    public static final ForgeConfigSpec.DoubleValue NORMAL_ENHANCEMENT = builder.comment("Mobs enhancement rate,1 -> 1%,10 -> 10%").defineInRange("EnhancementRate",1d,0,Double.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue NORMAL_TEAM_MONEY = builder.comment("Amount reduction rate caused by each person in your team, 0.8 -> -20%").defineInRange("MoneyLossRate",0.85d,0,1);
    public static final ForgeConfigSpec.IntValue NORMAL_RITUAL_FLIGHT = builder.comment("Duration of flight ritual(tick)").defineInRange("FlightTime",12000,0,Integer.MAX_VALUE);
    public static final ForgeConfigSpec.BooleanValue NORMAL_TIB_NO_LIMIT = builder.comment("Can use time in bottle in any machine").define("TIBNoLimit",false);
    public static final ForgeConfigSpec.DoubleValue NORMAL_DEATH_MONEY_BASE = builder.comment("Drop money base when you death. Total = base + (phase * addition)").defineInRange("DeathMoneyBase",0.1d,0,1);
    public static final ForgeConfigSpec.DoubleValue NORMAL_DEATH_MONEY_ADDITION = builder.defineInRange("DeathMoneyAddition",0.015d,0,1);
    public static final ForgeConfigSpec.DoubleValue NORMAL_OUHUANG_CHANCE = builder.comment("The production probability of ouhuang ingot also affects the reuse rate of vegetable seeds").defineInRange("OuhuangChance",0.03d,0.01d,1d);
    public static final ForgeConfigSpec.DoubleValue NORMAL_BUY_CREATIVE = builder.comment("Additional price after each purchase of creative items").defineInRange("CreativeRate",0.2d,0d,Double.MAX_VALUE);

    public static final ForgeConfigSpec.DoubleValue HARD_HEALTH = builder.pop().push("Expert").defineInRange("RegenerationRate",0.5d,0,Float.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue HARD_INTEREST = builder.comment("Interest you will receive if your balance is negative").defineInRange("InterestRate",1.02d,1,100);
    public static final ForgeConfigSpec.DoubleValue HARD_ENHANCEMENT = builder.comment("Mobs daily enhancement rate,1 -> 1%,10 -> 10%").defineInRange("EnhancementRate",2.5d,0,Double.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue HARD_TEAM_MONEY= builder.comment("Amount reduction rate caused by each person in your team, 0.8 -> -20%").defineInRange("MoneyLossRate",0.8d,0,1);
    public static final ForgeConfigSpec.BooleanValue DROP_ITEM_WHEN_DEATH = builder.comment("Drop your inventory items when you death").define("DeathDrop",true);
    public static final ForgeConfigSpec.IntValue HARD_RITUAL_FLIGHT = builder.comment("Duration of flight ritual(tick)").defineInRange("FlightTime",1200,0,Integer.MAX_VALUE);
    public static final ForgeConfigSpec.BooleanValue HARD_TIB_NO_LIMIT = builder.comment("Can use time in bottle in any machine").define("TIBNoLimit",false);
    public static final ForgeConfigSpec.DoubleValue HARD_DEATH_MONEY_BASE = builder.comment("Drop money base when you death. Total = base + (phase * addition)").defineInRange("DeathMoneyBase",0.2d,0,1);
    public static final ForgeConfigSpec.DoubleValue HARD_DEATH_MONEY_ADDITION = builder.defineInRange("DeathMoneyAddition",0.022d,0,1);
    public static final ForgeConfigSpec.DoubleValue HARD_OUHUANG_CHANCE = builder.comment("The production probability of ouhuang ingot also affects the reuse rate of vegetable seeds").defineInRange("OuhuangChance",0.01d,0.01d,1d);
    public static final ForgeConfigSpec.DoubleValue HARD_BUY_CREATIVE = builder.comment("Additional price after each purchase of creative items").defineInRange("CreativeRate",0.35d,0d,Double.MAX_VALUE);

    static final ForgeConfigSpec SPEC = builder.pop().build();

    public static int difficulty;
    public static boolean TinkersSurvival;
    public static double EASY_HEALTH_RATE;
    public static double EASY_INTEREST_RATE;
    public static double EASY_ENHANCEMENT_RATE;
    public static double EASY_TEAM_MONEY_RATE;
    public static int EASY_FLY_TIME;
    public static boolean EASY_TIB_NOLIMIT;
    public static double EASY_DEATH_DROP_MONEY_BASE;
    public static double EASY_DEATH_DROP_MONEY_ADDITION;
    public static double EASY_OUHUANG_INGOT_CHANCE;
    public static double EASY_BUY_CREATIVE_RATE;

    public static double NORMAL_HEALTH_RATE;
    public static double NORMAL_INTEREST_RATE;
    public static double NORMAL_ENHANCEMENT_RATE;
    public static double NORMAL_TEAM_MONEY_RATE;
    public static int NORMAL_FLY_TIME;
    public static boolean NORMAL_TIB_NOLIMIT;
    public static double NORMAL_DEATH_DROP_MONEY_BASE;
    public static double NORMAL_DEATH_DROP_MONEY_ADDITION;
    public static double NORMAL_OUHUANG_INGOT_CHANCE;
    public static double NORMAL_BUY_CREATIVE_RATE;

    public static double HARD_HEALTH_RATE;
    public static double HARD_INTEREST_RATE;
    public static double HARD_ENHANCEMENT_RATE;
    public static double HARD_TEAM_MONEY_RATE;
    public static int HARD_FLY_TIME;
    public static boolean HARD_TIB_NOLIMIT;
    public static double HARD_DEATH_DROP_MONEY_BASE;
    public static double HARD_DEATH_DROP_MONEY_ADDITION;
    public static double HARD_OUHUANG_INGOT_CHANCE;
    public static double HARD_BUY_CREATIVE_RATE;
    public static boolean DEATH_DROP;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        difficulty = DIFFICULTY.get();
        TinkersSurvival = Tinkers_Survival.get();
        EASY_HEALTH_RATE = EASY_HEALTH.get();
        EASY_INTEREST_RATE = EASY_INTEREST.get();
        EASY_ENHANCEMENT_RATE = EASY_ENHANCEMENT.get();
        EASY_TEAM_MONEY_RATE = EASY_TEAM_MONEY.get();
        EASY_FLY_TIME = EASY_RITUAL_FLIGHT.get();
        EASY_TIB_NOLIMIT = EASY_TIB_NO_LIMIT.get();
        EASY_DEATH_DROP_MONEY_BASE = EASY_DEATH_MONEY_BASE.get();
        EASY_DEATH_DROP_MONEY_ADDITION = EASY_DEATH_MONEY_ADDITION.get();
        EASY_OUHUANG_INGOT_CHANCE = EASY_OUHUANG_CHANCE.get();
        EASY_BUY_CREATIVE_RATE = EASY_BUY_CREATIVE.get();
        
        NORMAL_HEALTH_RATE = NORMAL_HEALTH.get();
        NORMAL_INTEREST_RATE = NORMAL_INTEREST.get();
        NORMAL_ENHANCEMENT_RATE = NORMAL_ENHANCEMENT.get();
        NORMAL_TEAM_MONEY_RATE = NORMAL_TEAM_MONEY.get();
        NORMAL_FLY_TIME = NORMAL_RITUAL_FLIGHT.get();
        NORMAL_TIB_NOLIMIT = NORMAL_TIB_NO_LIMIT.get();
        NORMAL_DEATH_DROP_MONEY_BASE = NORMAL_DEATH_MONEY_BASE.get();
        NORMAL_DEATH_DROP_MONEY_ADDITION = NORMAL_DEATH_MONEY_ADDITION.get();
        NORMAL_OUHUANG_INGOT_CHANCE = NORMAL_OUHUANG_CHANCE.get();
        NORMAL_BUY_CREATIVE_RATE = NORMAL_BUY_CREATIVE.get();

        HARD_HEALTH_RATE = HARD_HEALTH.get();
        HARD_INTEREST_RATE = HARD_INTEREST.get();
        HARD_ENHANCEMENT_RATE = HARD_ENHANCEMENT.get();
        HARD_TEAM_MONEY_RATE = HARD_TEAM_MONEY.get();
        HARD_FLY_TIME = HARD_RITUAL_FLIGHT.get();
        HARD_TIB_NOLIMIT = HARD_TIB_NO_LIMIT.get();
        HARD_DEATH_DROP_MONEY_BASE = HARD_DEATH_MONEY_BASE.get();
        HARD_DEATH_DROP_MONEY_ADDITION = HARD_DEATH_MONEY_ADDITION.get();
        HARD_OUHUANG_INGOT_CHANCE = HARD_OUHUANG_CHANCE.get();
        HARD_BUY_CREATIVE_RATE = HARD_BUY_CREATIVE.get();
        DEATH_DROP = DROP_ITEM_WHEN_DEATH.get();
    }
}
