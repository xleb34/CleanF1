package dev.xleb34.cleanf1.config;

import dev.xleb34.cleanf1.Cleanf1;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {

    private static final int BUTTON_WIDTH  = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_GAP    = 4;

    private final Screen parent;

    public ConfigScreen(Screen parent) {
        super(Text.translatable("cleanf1.config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width  / 2 - BUTTON_WIDTH / 2;
        int centerY = this.height / 2 - BUTTON_HEIGHT;

        ButtonWidget toggleButton = ButtonWidget.builder(getToggleText(), btn -> {
            Cleanf1.modEnabled = !Cleanf1.modEnabled;
            btn.setMessage(getToggleText());
        }).dimensions(centerX, centerY, BUTTON_WIDTH, BUTTON_HEIGHT).build();

        ButtonWidget doneButton = ButtonWidget.builder(
                Text.translatable("gui.done"),
                btn -> this.client.setScreen(parent)
        ).dimensions(centerX, centerY + BUTTON_HEIGHT + BUTTON_GAP, BUTTON_WIDTH, BUTTON_HEIGHT).build();

        this.addDrawableChild(toggleButton);
        this.addDrawableChild(doneButton);
    }

    private Text getToggleText() {
        String key = Cleanf1.modEnabled ? "cleanf1.config.on" : "cleanf1.config.off";
        return Text.translatable("cleanf1.config.toggle", Text.translatable(key));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(
                this.textRenderer, this.title,
                this.width / 2, this.height / 2 - 40,
                0xFFFFFF
        );
    }
}