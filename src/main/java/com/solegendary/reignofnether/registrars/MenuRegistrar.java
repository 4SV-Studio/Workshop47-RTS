package com.solegendary.reignofnether.registrars;

import com.solegendary.reignofnether.ReignOfNether;
import com.solegendary.reignofnether.gui.ControllerMenu;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MenuRegistrar {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ReignOfNether.MOD_ID);

    public static final RegistryObject<MenuType<ControllerMenu>> CONTROLLER_MENU =
            MENUS.register("controller_menu", () -> new MenuType<>(ControllerMenu::new, FeatureFlagSet.of()));

    public static void register() {
        MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}