package genetics;

import genetics.client.render.*;
import genetics.common.BlockEntity.ChickenCubeBlockEntity;
import genetics.common.BlockEntity.JarBlockEntity;
import genetics.init.Initializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PolarBearEntity;

import static genetics.common.net.PacketHandling.*;

public class Main implements ModInitializer {

    public static final String MODID = "genetics";

    @Override
    public void onInitialize() {


        ClientSidePacketRegistry.INSTANCE.register(GENETIC_SYNC_PACKET, SYNC_PACKET_CONSUMER); // SERVER 2 CLIENT CONSUMER
        ServerSidePacketRegistry.INSTANCE.register(GENETIC_REQUEST_PACKET, REQUEST_PACKET_CONSUMER); // CLIENT TO SERVER CONSUMER

        Initializer.init();
    }


}

