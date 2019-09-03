package genetics.common.genetics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;

import java.util.Random;

import static genetics.util.Logger.log;

public class PolarBearGenetics extends BaseGenetics {
    public PolarBearGenetics(Entity en) {
        super(en);
    }
    public void initializeGenetics(LlamaEntity en) {
        if (!this.getWorld().isClient) {
            Random randy = new Random();
            int[] newGenetics = new int[genomeSize];
            for (int i = 0; i < genomeSize; i++) {
                if(i == 5) {
                        newGenetics[i] = 19;
                    }
                else{
                    newGenetics[i] = randy.nextInt(max_Random);
                }
            }
            this.setGenetics(newGenetics);
            this.hasGenetics = true;
            log("Initialized PolarBear Genetics: " + (this.getGenetics()[5]));
        }
    }

}
