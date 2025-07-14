package biggestxuan.bxp2.blocks;

import biggestxuan.bxp2.BxP2;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/16
 */
public class BxPBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,BxP2.MODID);

    public static RegistryObject<Block> UNSTABLE_BX_BLOCK = register("unstable_bx_block");
    public static RegistryObject<Block> BX_BLOCK = register("bx_block");
    public static RegistryObject<Block> OUMANG_BLOCK = register("oumang_block");
    public static RegistryObject<Block> OU_GOLD_BLOCK = register("ou_gold_block");
    public static RegistryObject<Block> SX_BLOCK = register("sx_block");
    public static RegistryObject<Block> POLY_BLOCK = register("poly_block");
    public static RegistryObject<Block> ENCH_BX_BLOCK = register("ench_bx_block");
    public static RegistryObject<Block> ATM = BLOCKS.register("atm",ATMBlock::new);

    public static RegistryObject<Block> YUNXI = BLOCKS.register("yunxi",() -> new PlayerModelBlock(){
        @Override
        public InteractionResult use(BlockState p_60503_, Level level, BlockPos p_60505_, Player player, InteractionHand hand, BlockHitResult p_60508_) {
            ItemStack stack = player.getItemInHand(hand);
            if(stack.getItem().equals(Items.BUCKET) && level instanceof ServerLevel){
                stack.shrink(1);
                player.addItem(Items.MILK_BUCKET.getDefaultInstance());
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
    });
    public static RegistryObject<Block> BIGGEST_XUAN = BLOCKS.register("biggest_xuan",PlayerModelBlock::new);
    public static RegistryObject<Block> DCTOR_0415 = BLOCKS.register("dctor_0415",PlayerModelBlock::new);
    public static RegistryObject<Block> SDXHOP = BLOCKS.register("sdxhop",PlayerModelBlock::new);
    public static RegistryObject<Block> TULYE = BLOCKS.register("tulye",PlayerModelBlock::new);
    public static RegistryObject<Block> JAOXAONO = BLOCKS.register("jaoxaono",PlayerModelBlock::new);
    public static RegistryObject<Block> LAMB_KISARA = BLOCKS.register("lamb_kisara",PlayerModelBlock::new);
    public static RegistryObject<Block> CAIGENGZI = BLOCKS.register("mcaigengzi",PlayerModelBlock::new);
    public static RegistryObject<Block> YULUO_1 = BLOCKS.register("yuluo_1",PlayerModelBlock::new);
    public static RegistryObject<Block> ABUNANA = BLOCKS.register("abunana",PlayerModelBlock::new);
    public static RegistryObject<Block> KLPZM = BLOCKS.register("klpzm",PlayerModelBlock::new);
    //public static RegistryObject<Block> CLSHERLOCK = BLOCKS.register("clsherlock",PlayerModelBlock::new);

    public static RegistryObject<Block> register(String name){
        return BLOCKS.register(name,() -> new BxPBlock(name));
    }
}
