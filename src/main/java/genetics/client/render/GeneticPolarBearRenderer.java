package genetics.client.render;


import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.ChickenColorLogic;
import genetics.client.geneticRenderLogic.PolarBearColorLogic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.PolarBearEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PolarBearEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GeneticPolarBearRenderer extends MobEntityRenderer<MobEntity, EntityModel<MobEntity>> {
    public static final Identifier ENTITY_TEXTURE = new Identifier("minecraft:textures/entity/bear/polarbear.png");
    //public static final Identifier ENTITY_COAT_TEXTURE = new Identifier("genetics:textures/entity/genetics/polarbear_coat.png");


    public GeneticPolarBearRenderer(EntityRenderDispatcher renderManagerIn, EntityRendererRegistry.Context context) {
        super(renderManagerIn, new PolarBearEntityModel(), 0.3f);
        this.addFeature(new LayerDyeableFeatureRenderer(this, ENTITY_TEXTURE, new PolarBearColorLogic()));
        //this.bindTexture(ENTITY_TEXTURE);
    }

    @Override
    public void render(MobEntity ent, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(ent,f,g,matrixStack,vertexConsumerProvider,i);
    }

    @Override
    public Identifier getTexture(MobEntity polarBearEntity) {
        return ENTITY_TEXTURE;
    }
}