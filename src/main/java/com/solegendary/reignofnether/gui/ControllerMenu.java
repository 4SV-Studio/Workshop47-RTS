package com.solegendary.reignofnether.gui;

import com.solegendary.reignofnether.registrars.MenuRegistrar;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ControllerMenu extends AbstractContainerMenu {

    public ControllerMenu(int id, Inventory playerInventory) {
        super(MenuRegistrar.CONTROLLER_MENU.get(), id);
    }

    public static ControllerMenu create(int id, Inventory inv, FriendlyByteBuf buf) {
        return new ControllerMenu(id, inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
