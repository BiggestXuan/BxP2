package biggestxuan.bxp2;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = BxP2.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue DIFFICULTY = BUILDER
            .comment("Difficulty")
            .defineInRange("difficulty", 2, 1, 3);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int difficulty;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        difficulty = DIFFICULTY.get();
    }
}
