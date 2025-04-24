package biggestxuan.bxp2.blocks;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/16
 */
public class BxPBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,BxP2.MODID);

    public static RegistryObject<Block> YUNXI = BLOCKS.register("yunxi",PlayerModelBlock::new);
    public static RegistryObject<Block> BIGGEST_XUAN = BLOCKS.register("biggest_xuan",PlayerModelBlock::new);
    public static RegistryObject<Block> DCTOR_0415 = BLOCKS.register("dctor_0415",PlayerModelBlock::new);
    public static RegistryObject<Block> SDXHOP = BLOCKS.register("sdxhop",PlayerModelBlock::new);
    public static RegistryObject<Block> TULYE = BLOCKS.register("tulye",PlayerModelBlock::new);
    public static RegistryObject<Block> JAOXAONO = BLOCKS.register("jaoxaono",PlayerModelBlock::new);
    public static RegistryObject<Block> LAMB_KISARA = BLOCKS.register("lamb_kisara",PlayerModelBlock::new);
    public static RegistryObject<Block> CAIGENGZI = BLOCKS.register("mcaigengzi",PlayerModelBlock::new);
    public static RegistryObject<Block> YULUO_1 = BLOCKS.register("yuluo_1",PlayerModelBlock::new);
}
