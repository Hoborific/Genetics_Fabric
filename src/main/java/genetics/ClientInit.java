package genetics;

import genetics.client.render.*;
import genetics.init.Initializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(EntityType.CHICKEN, GeneticChickenRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityType.COW, GeneticCowRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityType.LLAMA, GeneticLlamaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityType.POLAR_BEAR, GeneticPolarBearRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Initializer.JAR_BLOCK_ENTITY, JarBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Initializer.CHICKENCUBE_BLOCK_ENTITY, ChickenCubeBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(Initializer.BLOCK_JAR, RenderLayer.getTranslucent());
        System.out.println("client init called");
    }
}
