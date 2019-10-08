package genetics.client.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import genetics.common.BlockEntity.JarBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.Box;

import java.util.Random;

public class JarBlockEntityRenderer extends BlockEntityRenderer<JarBlockEntity> {
    private MobEntityWithAi ent;

    Random randy = new Random();
    @Override
    public void render(JarBlockEntity blockEntity_1, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(x + 0.5, y + 0.1, z + 0.5);
        GlStateManager.scalef(0.33f, 0.33f, 0.33f);
        //GlStateManager.rotated(0,x,y,z);

        int light = blockEntity_1.getWorld().getLightmapIndex(blockEntity_1.getPos().up(), 0);
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) (light & 0xFFFF), (float) ((light >> 16) & 0xFFFF));
        ent = (MobEntityWithAi)blockEntity_1.getEntity();
        if (ent != null) {
            MinecraftClient.getInstance().getEntityRenderManager().render(ent, 0.0f, 0.0f, 0.0f, 0f, 0f, true);
            MinecraftClient.getInstance().getEntityRenderManager().renderSecondPass(ent,0f);
        }
        GlStateManager.popMatrix();
        super.render(blockEntity_1, x, y, z, partialTicks, destroyStage);

    }
}
