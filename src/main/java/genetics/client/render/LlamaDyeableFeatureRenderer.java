package genetics.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.IColorLogic;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL13;


public class LlamaDyeableFeatureRenderer extends FeatureRenderer<LlamaEntity, LlamaEntityModel<LlamaEntity>> {
    private final LlamaEntityModel<LlamaEntity> myModel;
    private final Identifier LAYER_TEXTURE;
    private IColorLogic myColorLogic;

    LlamaDyeableFeatureRenderer(FeatureRendererContext<LlamaEntity, LlamaEntityModel<LlamaEntity>> var1, Identifier texture, IColorLogic myColorLogic) {
        super(var1);
        myModel = var1.getModel();
        LAYER_TEXTURE = texture;
        this.myColorLogic = myColorLogic;
    }

    //@Override
    public void render(LlamaEntity en, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    }

    //@Override
    public boolean hasHurtOverlay() {
        return true;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LlamaEntity entity, float f, float g, float h, float j, float k, float l) {
        //this.bindTexture(LAYER_TEXTURE);
        //this.bindTexture(LAYER_TEXTURES);
        float[] afloat = myColorLogic.geneticsToRGB(entity);
        GlStateManager.enableColorMaterial();
        GL13.glColor3f(afloat[0], afloat[1], afloat[2]);
        this.getContextModel().copyStateTo(myModel);
        render(this.getContextModel(), this.myModel, LAYER_TEXTURE, matrixStack, vertexConsumerProvider, i, entity, f, g, j, k, l, h, afloat[0], afloat[1], afloat[2]);
    }
}