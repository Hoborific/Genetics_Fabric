package genetics.client.render;


import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.ChickenColorLogic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GeneticChickenRenderer extends MobEntityRenderer<MobEntity, EntityModel<MobEntity>> {//EntityMobRenderer<ChickenEntity,ChickenEntityModel<ChickenEntity>> {//EntityMobRenderer<ChickenEntity> {
    public static final Identifier CHICKEN_TEXTURE = new Identifier("genetics:textures/entity/genetics/chicken_base.png");//"genetics:textures/entity/genetics/chicken_base.png");//new ResourceLocation("textures/entity/chicken.png");
    public static final Identifier CHICKEN_LAYER_TEXTURE = new Identifier("genetics:textures/entity/genetics/chicken_coat.png");


    public GeneticChickenRenderer(EntityRenderDispatcher renderManagerIn, EntityRendererRegistry.Context context) {
        super(renderManagerIn, new ChickenEntityModel<>(), 0.3f);
        this.addFeature(new LayerDyeableFeatureRenderer(this, CHICKEN_LAYER_TEXTURE, new ChickenColorLogic()));
        //this.bindTexture(CHICKEN_TEXTURE);
    }
    @Override
    protected float getAnimationProgress(MobEntity chickenEntity, float f) {
        float g = MathHelper.lerp(f, ((ChickenEntity)chickenEntity).prevFlapProgress, ((ChickenEntity)chickenEntity).flapProgress);
        float h = MathHelper.lerp(f, ((ChickenEntity)chickenEntity).prevMaxWingDeviation, ((ChickenEntity)chickenEntity).maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;

    }
    @Override

    public void render(MobEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(entity,f,g,matrixStack,vertexConsumerProvider,i);

    }

    @Override
    public Identifier getTexture(MobEntity dyeableChickenEntity) {
        return CHICKEN_TEXTURE;
    }



}