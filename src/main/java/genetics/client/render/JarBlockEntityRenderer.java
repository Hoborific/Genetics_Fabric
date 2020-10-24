package genetics.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import genetics.common.BlockEntity.JarBlockEntity;
import genetics.util.Logger;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import org.lwjgl.opengl.GL13;

import java.util.Random;
import java.util.function.Function;

public class JarBlockEntityRenderer extends BlockEntityRenderer<JarBlockEntity> {
    private PathAwareEntity ent;

    Random randy = new Random();

    public JarBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
    }


    @Override
    public void render(JarBlockEntity mobJar, float f, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        ent = mobJar.getEntity();
        if (ent != null) {
        matrices.push();
        RenderSystem.glMultiTexCoord2f(GL13.GL_TEXTURE1, (float) (i & 0xFFFF), (float) ((i >> 16) & 0xFFFF));
        matrices.translate(0.5,0.1,0.5);
        matrices.scale(0.33f, 0.33f, 0.33f);
        MinecraftClient.getInstance().getEntityRenderDispatcher().render(ent, 0, 0, 0, f, f, matrices, vertexConsumerProvider, i);
        matrices.pop();
        }
    }


}
