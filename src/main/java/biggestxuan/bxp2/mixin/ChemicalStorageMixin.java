package biggestxuan.bxp2.mixin;

import com.lowdragmc.mbd2.integration.mekanism.trait.chemical.ChemicalStorage;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.chemical.gas.GasStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

/**
 * @Author Biggest_Xuan
 * 2025/5/7
 */

@Mixin(value = ChemicalStorage.Gas.class,remap = false)
public abstract class ChemicalStorageMixin extends ChemicalStorage<mekanism.api.chemical.gas.Gas, GasStack>{
    protected ChemicalStorageMixin(long capacity, Predicate<mekanism.api.chemical.gas.@NotNull Gas> validator, @Nullable ChemicalAttributeValidator attributeValidator) {
        super(capacity, validator, ChemicalAttributeValidator.ALWAYS_ALLOW);
    }

    @ModifyArg(method = "<init>",at = @At(value = "INVOKE", target = "Lcom/lowdragmc/mbd2/integration/mekanism/trait/chemical/ChemicalStorage;<init>(JLjava/util/function/Predicate;Lmekanism/api/chemical/attribute/ChemicalAttributeValidator;)V"),index = 2)
    private static @Nullable ChemicalAttributeValidator __inject(@Nullable ChemicalAttributeValidator attributeValidator){
        return ChemicalAttributeValidator.ALWAYS_ALLOW;
    }
}
