package biggestxuan.bxp2.container;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class BxPContainers {
    public static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BxP2.MODID);

    public static RegistryObject<MenuType<ATMContainer>> ATM_CONTAINER = CONTAINERS.register("atm_container",() -> IForgeMenuType.create(ATMContainer::new));
}
