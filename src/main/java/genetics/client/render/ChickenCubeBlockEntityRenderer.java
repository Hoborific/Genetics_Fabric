package genetics.client.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import genetics.common.BlockEntity.ChickenCubeBlockEntity;
import genetics.common.BlockEntity.JarBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.entity.mob.MobEntityWithAi;

import java.util.Random;

public class ChickenCubeBlockEntityRenderer extends BlockEntityRenderer<ChickenCubeBlockEntity> {
    private MobEntityWithAi ent;

    Random randy = new Random();
    @Override
    public void render(ChickenCubeBlockEntity blockEntity_1, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(x + 0.5, y , z + 0.5);
        GlStateManager.scalef(0.8f, 0.8f, 0.8f);
        int light = blockEntity_1.getWorld().getLightmapIndex(blockEntity_1.getPos().south(), 0);
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) (light & 0xFFFF), (float) ((light >> 16) & 0xFFFF));
        ent = (MobEntityWithAi)blockEntity_1.getEntity();
        if (ent != null) {

            //ent.setPositionAndAngles(x,y,z,180,0);
            MinecraftClient.getInstance().getEntityRenderManager().render(ent, 0.0f, 0.0f, 0.0f, 0f, 0f, false);
        }
        GlStateManager.popMatrix();
        super.render(blockEntity_1, x, y, z, partialTicks, destroyStage);

    }
}
