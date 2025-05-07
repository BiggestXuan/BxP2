package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.Config;
import com.hollingsworth.arsnouveau.api.ritual.RangeEffectRitual;
import com.hollingsworth.arsnouveau.common.ritual.RitualFlight;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @Author Biggest_Xuan
 * 2025/4/27
 */

@Mixin(RitualFlight.class)
public abstract class RitualFlightMixin extends RangeEffectRitual {
    public int getDuration() {
        return switch (Config.difficulty) {
            case 1 -> Config.EASY_FLY_TIME;
            case 2 -> Config.NORMAL_FLY_TIME;
            case 3 -> Config.HARD_FLY_TIME;
            default -> 1200;
        };
    }
}
