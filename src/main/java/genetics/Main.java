package genetics;

import genetics.client.render.GeneticChickenRenderer;
import genetics.client.render.GeneticCowRenderer;
import genetics.init.Initializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import static genetics.common.net.PacketHandling.*;

public class Main implements ModInitializer {

	public static final String MODID = "genetics";



	@Override
	public void onInitialize() {

		EntityRendererRegistry.INSTANCE.register(ChickenEntity.class, GeneticChickenRenderer::new);
		EntityRendererRegistry.INSTANCE.register(CowEntity.class, GeneticCowRenderer::new);

		ClientSidePacketRegistry.INSTANCE.register(GENETIC_SYNC_PACKET, SYNC_PACKET_CONSUMER); // SERVER 2 CLIENT CONSUMER
		ServerSidePacketRegistry.INSTANCE.register(GENETIC_REQUEST_PACKET, REQUEST_PACKET_CONSUMER); // CLIENT TO SERVER CONSUMER

        Initializer.init();
	}


}

