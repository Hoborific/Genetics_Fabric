package genetics.client.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import genetics.common.BlockEntity.JarBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntityWithAi;

public class JarBlockEntityRenderer extends BlockEntityRenderer<JarBlockEntity> {
    private MobEntityWithAi ent;

    @Override
    public void render(JarBlockEntity blockEntity_1, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(x + 0.5, y + 0.1, z + 0.5);
        GlStateManager.scalef(0.25f, 0.25f, 0.25f);

        int light = blockEntity_1.getWorld().getLightmapIndex(blockEntity_1.getPos().up(), 0);
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) (light & 0xFFFF), (float) ((light >> 16) & 0xFFFF));
        ent = (MobEntityWithAi)blockEntity_1.getEntity();
        if (ent != null) {
            ent.setPositionAnglesAndUpdate(x, y, z, partialTicks, partialTicks);
            MinecraftClient.getInstance().getEntityRenderManager().render(ent, 0.0f, 0.0f, 0.0f, 1f, 1f, false);
        }
        GlStateManager.popMatrix();
        super.render(blockEntity_1, x, y, z, partialTicks, destroyStage);

    }
}
