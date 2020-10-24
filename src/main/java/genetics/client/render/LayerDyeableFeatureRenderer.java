package genetics.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.IColorLogic;
import genetics.common.mixin.EntityChickenEvents;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL13;


public class LayerDyeableFeatureRenderer extends FeatureRenderer<MobEntity, EntityModel<MobEntity>> {
    private final EntityModel<MobEntity> myModel;
    private final Identifier LAYER_TEXTURES;
    private IColorLogic myColorLogic;

    LayerDyeableFeatureRenderer(FeatureRendererContext<MobEntity, EntityModel<MobEntity>> var1, Identifier texture, IColorLogic myColorLogic) {
        super(var1);
        myModel = var1.getModel();
        LAYER_TEXTURES = texture;
        this.myColorLogic = myColorLogic;


    }

    @Override
    protected Identifier getTexture(MobEntity entity) {
        return this.LAYER_TEXTURES;
    }

    //@Override
    public boolean hasHurtOverlay() {
        return true;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, MobEntity entity, float f, float g, float h, float j, float k, float l) {


        float[] afloat = myColorLogic.geneticsToRGB(entity);
        GL13.glColor3f(afloat[0], afloat[1], afloat[2]);
        this.getContextModel().copyStateTo(myModel);
        render(this.getContextModel(), this.myModel, LAYER_TEXTURES, matrixStack, vertexConsumerProvider, i, entity, f, g, j, k, l, h, afloat[0], afloat[1], afloat[2]);
    }
}