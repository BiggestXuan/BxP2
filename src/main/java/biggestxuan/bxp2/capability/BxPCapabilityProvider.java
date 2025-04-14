package biggestxuan.bxp2.capability;

import biggestxuan.bxp2.BxP2;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @Author Biggest_Xuan
 * 2025/4/12
 */

public class BxPCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<IBxPCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<IBxPCapability>() {});
    private IBxPCapability bxp = null;
    private final LazyOptional<IBxPCapability> capabilityLazyOptional = LazyOptional.of(this::getOrCreate);

    private IBxPCapability getOrCreate() {
        if(bxp == null) {
            bxp = new BxPCapability();
        }
        return bxp;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return capability == CAPABILITY ? this.capabilityLazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return getOrCreate().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        getOrCreate().deserializeNBT(compoundTag);
    }
}
