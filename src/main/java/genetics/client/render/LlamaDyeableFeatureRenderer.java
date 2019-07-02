package genetics.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.IColorLogic;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.Identifier;


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

    @Override
    public void render(LlamaEntity en, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.bindTexture(LAYER_TEXTURE);
        float[] afloat = myColorLogic.geneticsToRGB(en);
        GlStateManager.enableColorMaterial();
        GlStateManager.color3f(afloat[0], afloat[1], afloat[2]);
        this.getModel().copyStateTo(myModel);
        this.myModel.render(en, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean hasHurtOverlay() {
        return true;
    }
}