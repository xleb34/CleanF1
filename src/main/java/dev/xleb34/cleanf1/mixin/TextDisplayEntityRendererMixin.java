package dev.xleb34.cleanf1.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.DisplayEntityRenderer;
import net.minecraft.client.render.entity.state.TextDisplayEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisplayEntityRenderer.TextDisplayEntityRenderer.class)
public abstract class TextDisplayEntityRendererMixin {

    @Inject(
            method = "render",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cleanf1$render(
            TextDisplayEntityRenderState state,
            MatrixStack matrices,
            OrderedRenderCommandQueue queue,
            int light,
            float tickDelta,
            CallbackInfo ci
    ) {
        if (MinecraftClient.getInstance().options.hudHidden) {
            ci.cancel();
        }
    }
}