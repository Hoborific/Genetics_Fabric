package genetics.common.genetics;


import genetics.common.net.PacketHandling;
import genetics.util.Logger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static genetics.util.Logger.debugLog;
import static genetics.util.Logger.log;

public class BaseGenetics implements IGeneticBase {

    public Boolean hasGenetics = false;
    public Boolean hasParents = false;
    protected int[] GENETIC_TRACKER;
    private Entity myself;
    int max_Random = 20;

    public BaseGenetics(Entity en) {
        this.myself = en;
        GENETIC_TRACKER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    @Override
    public int[] getGenetics() {
        if (this.GENETIC_TRACKER.length < 2) {
            log("ERROR: ENTITY HAS NO GENETICS: " + this.getEntityID() + " " + this.getWorld().getEntityById(this.getEntityID()));
            initializeGenetics();
            if (this.GENETIC_TRACKER.length < 2) {
                log("ERROR: ENTITY STILL HAS NO GENETICS: " + this.getEntityID() + " " + this.getWorld().getEntityById(this.getEntityID()));
                return new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            }
        }
        return this.GENETIC_TRACKER;
    }

    public void setGenetics(int[] genes) {
        GENETIC_TRACKER = genes;
        if (myself instanceof PlayerEntity) return;
        if (myself.getEntityWorld().isClient) {
            debugLog("CALLED setGenetics on CLIENT");
            if(Logger.debugMode){
                myself.setCustomName(new TranslatableText(Arrays.toString(genes)));
                myself.setCustomNameVisible(true);
            }
        } else {
            debugLog("sending packet");
            Packet pacman = PacketHandling.craftGeneticPacket(myself.getEntityId(), GENETIC_TRACKER);
            PacketHandling.sendPacketToPlayer(pacman, myself.world, myself.getBlockPos());
        }
    }

    public int getGeneticByIndex(int in) {
        if (GENETIC_TRACKER.length >= in) {
            return GENETIC_TRACKER[in];
        } else {
            return -1;
        }
    }

    public World getWorld() {
        return this.myself.getEntityWorld();
    }

    @Override
    public void initializeGenetics() {
        if (!this.getWorld().isClient) {
            this.setGenetics(generateGenetics());
            this.hasGenetics = true;
            debugLog("Initialized Genetics: " + Arrays.toString(this.getGenetics()));
        }
    }

    @Override
    public void initializeGenetics(int[] mum, int[] dad) {
        if (!this.getWorld().isClient) {
            this.setGenetics(generateGenetics(mum, dad));
            this.hasGenetics = true;
            debugLog("Initialized Genetics: " + Arrays.toString(this.getGenetics()));
        }
    }

    @Override
    public int[] generateGenetics(int[] parent1, int[] parent2) {
        int[] newGenetics = new int[genomeSize];

        if ((parent1.length < genomeSize) || (parent2.length < genomeSize)) {
            log("generateGenetics(int[], int[]) was passed arrays shorter than genomeSize, generating new random values.");
            return generateGenetics();
        }

        for (int i = 0; i < genomeSize; i++) {
            Random randy = new Random();

            if (randy.nextBoolean())
                newGenetics[i] = parent1[i];
            else
                newGenetics[i] = parent2[i];
        }

        return newGenetics;
    }

    @Override
    public int[] generateGenetics() {
        Random randy = new Random();
        int[] newGenetics = new int[genomeSize];

        for (int i = 0; i < genomeSize; i++) {
            newGenetics[i] = randy.nextInt(max_Random);
        }

        return newGenetics;
    }

    @Override
    public void setGeneticsInherited(int[] arr) {
        this.setGenetics(arr);
    }

    @Override
    public void setGeneticsFromPacket(int[] geneticarray) {
        debugLog("got genetics from packet ID: " + this.getEntityID() + " " + Arrays.toString(geneticarray));
        this.setGenetics(geneticarray);
    }

    public int getEntityID() {
        return myself.getEntityId();
    }
}
