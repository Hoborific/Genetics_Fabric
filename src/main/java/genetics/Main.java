package genetics;

import genetics.client.render.*;
import genetics.common.BlockEntity.JarBlockEntity;
import genetics.init.Initializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PolarBearEntity;

import static genetics.common.net.PacketHandling.*;

public class Main implements ModInitializer {

    public static final String MODID = "genetics";

    @Override
    public void onInitialize() {

        EntityRendererRegistry.INSTANCE.register(ChickenEntity.class, GeneticChickenRenderer::new);
        EntityRendererRegistry.INSTANCE.register(CowEntity.class, GeneticCowRenderer::new);
        EntityRendererRegistry.INSTANCE.register(LlamaEntity.class, GeneticLlamaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(PolarBearEntity.class, GeneticPolarBearRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(JarBlockEntity.class, new JarBlockEntityRenderer());

        ClientSidePacketRegistry.INSTANCE.register(GENETIC_SYNC_PACKET, SYNC_PACKET_CONSUMER); // SERVER 2 CLIENT CONSUMER
        ServerSidePacketRegistry.INSTANCE.register(GENETIC_REQUEST_PACKET, REQUEST_PACKET_CONSUMER); // CLIENT TO SERVER CONSUMER

        Initializer.init();
    }


}

