package genetics.client.render;


import com.mojang.blaze3d.platform.GlStateManager;
import genetics.client.geneticRenderLogic.ChickenColorLogic;
import genetics.client.geneticRenderLogic.PolarBearColorLogic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.PolarBearEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PolarBearEntityModel;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
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
        this.bindTexture(ENTITY_TEXTURE);
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
    protected Identifier getTexture(MobEntity polarBearEntity) {
        return ENTITY_TEXTURE;
    }
}