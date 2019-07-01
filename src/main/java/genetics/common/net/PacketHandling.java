package genetics.common.net;

import genetics.Main;
import genetics.common.genetics.IGeneticBase;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.minecraft.client.network.packet.CustomPayloadS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static genetics.util.Logger.debugLog;

public class PacketHandling {

    public static Identifier GENETIC_SYNC_PACKET = new Identifier(Main.MODID + "genetic_sync");
    public static Identifier GENETIC_REQUEST_PACKET = new Identifier(Main.MODID + "genetic_request");

    public static PacketConsumer SYNC_PACKET_CONSUMER = (PacketContext, PacketByteBuf) -> {
        Runnable runnable = () -> {
            int id = PacketByteBuf.readInt();
            int[] arr = PacketByteBuf.readIntArray();
            PlayerEntity player = PacketContext.getPlayer();
            if (player == null) return;
            World world = player.getEntityWorld();
            if (world == null) return;
            Entity entity = world.getEntityById(id);
            if (entity == null) {
                debugLog("Packet received but no reference of entityID in world: " + id);
            } else {
                debugLog("packet being read: " + id + " arr: " + Arrays.toString(arr));
                ((IGeneticBase) entity).setGeneticsFromPacket(arr);
            }
        };
        PacketByteBuf.retain();
        PacketContext.getTaskQueue().execute(runnable);

    };

    public static PacketConsumer REQUEST_PACKET_CONSUMER = (PacketContext, PacketByteBuf) -> {
        int id = PacketByteBuf.readInt();
        PlayerEntity player = PacketContext.getPlayer();
        Supplier supplier = () -> {
            World wrld = player.world.getWorld();
            Entity en = wrld.getEntityById(id);
            assert en != null;
            int[] genes = ((IGeneticBase) en).getGenetics();
            debugLog("ID: " + id + " genes:" + Arrays.toString(genes));
            sendPacketToPlayer(craftGeneticPacket(id, genes), player.world, en.getBlockPos());
            return 1;
        };
        PacketContext.getTaskQueue().executeFuture(supplier);
    };

    public static Packet craftGeneticPacket(int id, int[] genes) {
        PacketByteBuf myBuf = new PacketByteBuf(Unpooled.buffer());
        myBuf.writeInt(id);
        myBuf.writeIntArray(genes);
        return new CustomPayloadS2CPacket(GENETIC_SYNC_PACKET, myBuf);
    }

    public static Packet craftGeneticRequestPacket(int id) {
        PacketByteBuf myBuf = new PacketByteBuf(Unpooled.buffer());
        myBuf.writeInt(id);
        return new CustomPayloadC2SPacket(GENETIC_REQUEST_PACKET, myBuf);
    }

    public static void sendPacketToPlayer(Packet pak, World world, BlockPos pos) {
        debugLog("begin sendPacketToPlayer");
        if (!world.isClient) {
            debugLog("getting player list");
            List<ServerPlayerEntity> players = Objects.requireNonNull(world.getServer()).getPlayerManager().getPlayerList();
            debugLog(("reached"));
            if (players.size() > 0) {
                for (ServerPlayerEntity player : players
                ) {
                    if (new Box(pos).expand(160).intersects(player.getBoundingBox())) {
                        player.networkHandler.sendPacket(pak);
                        debugLog(("Proximity Packet Sent"));
                    }
                    debugLog("out of loop");
                }
                //debugLog("for each player");
                // for (ServerPlayerEntity player : players) {
                //    debugLog("sending packet to player");
                //    player.networkHandler.sendPacket(pak);
                // }

            }
            debugLog("end sendPacketToPlayer");
        }
    }
}
