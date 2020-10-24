package genetics.client.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import genetics.common.BlockEntity.ChickenCubeBlockEntity;
import genetics.common.BlockEntity.JarBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;

import java.util.Random;

public class ChickenCubeBlockEntityRenderer extends BlockEntityRenderer{
    private MobEntity ent;

    Random randy = new Random();

    public ChickenCubeBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
    }

    @Override
    public void render(BlockEntity blockEntity_1, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        matrixStack.push();
        matrixStack.scale(0.8f, 0.8f, 0.8f);
        int light = blockEntity_1.getWorld().getLightLevel(blockEntity_1.getPos().south(), 0);
        //GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) (light & 0xFFFF), (float) ((light >> 16) & 0xFFFF));
        ent = (MobEntity) ((ChickenCubeBlockEntity)blockEntity_1).getEntity();
        if (ent != null) {

            //ent.setPositionAndAngles(x,y,z,180,0);
            MinecraftClient.getInstance().getEntityRenderDispatcher().render(ent,0,0,0,0,0,null,null,0);
        }
        matrixStack.pop();
    }
}
