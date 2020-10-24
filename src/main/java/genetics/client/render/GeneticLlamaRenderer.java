package genetics.client.render;


import genetics.client.geneticRenderLogic.BaseColorLogic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.LlamaDecorFeatureRenderer;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GeneticLlamaRenderer extends MobEntityRenderer<LlamaEntity, LlamaEntityModel<LlamaEntity>> {
    private static final Identifier[] TEXTURES = new Identifier[]{new Identifier("textures/entity/llama/creamy.png"), new Identifier("textures/entity/llama/white.png"), new Identifier("textures/entity/llama/brown.png"), new Identifier("textures/entity/llama/gray.png")};
    private static final Identifier LLAMA_BASE = new Identifier("minecraft:textures/entity/llama/brown.png");
    private static final Identifier LLAMA_COAT = new Identifier("genetics:textures/entity/genetics/llama_brown_coat.png");

    public GeneticLlamaRenderer(EntityRenderDispatcher renderManagerIn, EntityRendererRegistry.Context context) {
        super(renderManagerIn,  new LlamaEntityModel(0.0F), 0.7F);
        this.addFeature(new LlamaDecorFeatureRenderer(this));
        this.addFeature(new LlamaDyeableFeatureRenderer(this, LLAMA_COAT, new BaseColorLogic(5)));
        //this.bindTexture(LLAMA_BASE);

    }
    @Override
    public void render(LlamaEntity ent, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(ent,f,g,matrixStack,vertexConsumerProvider,i);
    }
    public Identifier getTexture(LlamaEntity entity) {
        return LLAMA_BASE;
    }
}