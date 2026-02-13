package com.solegendary.reignofnether.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.gui.components.Button;

import static com.solegendary.reignofnether.ReignOfNether.MOD_ID;

public class ControllerScreen extends Screen {
    private static final int screenTextureWidth = 256; //TODO: put texture dimensions here
    private static final int screenTextureHeight = 256;

    private int screenWidthStart;
    private int screenHeightStart;

    protected ControllerScreen() {
        super(Component.literal("World Controller"));
    }

    @Override
    protected void init() {
        screenWidthStart = this.width / 2 - screenTextureWidth / 2;
        screenHeightStart = this.height / 2 - screenTextureHeight / 2;

        this.addRenderableWidget(
            Button.builder(Component.empty(), btn -> {

                })
                .bounds(
                        screenWidthStart + 50,
                        screenHeightStart + 50,
                        font.width(">"),
                        font.lineHeight
                )
                .build()
        );
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        pGuiGraphics.blit(ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/gui/controller_bg.png"),
                this.width / 2 - screenTextureWidth / 2,
                this.height / 2 - screenTextureHeight / 2,
                0,
                0,
                screenTextureWidth,
                screenTextureHeight,
                screenTextureWidth,
                screenTextureHeight
        );
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
