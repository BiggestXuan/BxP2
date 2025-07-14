package biggestxuan.bxp2.blocks.TileEntity;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.blocks.BxPBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class BxPTileEntities {
    public static DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BxP2.MODID);

    public static RegistryObject<BlockEntityType<ATMTileEntity>> ATM_MACHINE = TILES.register("atm",() -> BlockEntityType.Builder.of(ATMTileEntity::new, BxPBlocks.ATM.get()).build(null));

}
