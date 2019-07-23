package genetics.client.render;


import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.ChickenColorLogic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
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
        this.bindTexture(CHICKEN_TEXTURE);
    }

    @Override
    protected float getAge(MobEntity en, float v) {
        float var3 = MathHelper.lerp(v, ((ChickenEntity) en).field_6736, ((ChickenEntity) en).field_6741);
        float var4 = MathHelper.lerp(v, ((ChickenEntity) en).field_6738, ((ChickenEntity) en).field_6743);
        return (MathHelper.sin(var3) + 1.0F) * var4;
    }

    @Override
    public void render(MobEntity entity, float x, float y, float z, float entityYaw, float partialTicks, float scale) {
        boolean boolean_1 = this.method_4056(entity);
        boolean boolean_2 = !boolean_1 && !entity.canSeePlayer(MinecraftClient.getInstance().player);
        if (boolean_1 || boolean_2) {
            if (!this.bindEntityTexture(entity)) {
                return;
            }

            if (boolean_2) {
                GlStateManager.setProfile(GlStateManager.RenderMode.TRANSPARENT_MODEL);
            }

            this.model.render(entity, x, y, z, entityYaw, partialTicks, scale);
            if (boolean_2) {
                GlStateManager.unsetProfile(GlStateManager.RenderMode.TRANSPARENT_MODEL);
            }
        }
        super.render(entity, x, y, z, entityYaw, partialTicks, scale);
    }

    @Override
    protected Identifier getTexture(MobEntity dyeableChickenEntity) {
        return CHICKEN_TEXTURE;
    }


}