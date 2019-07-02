package genetics.common.genetics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;

import java.util.Random;

import static genetics.util.Logger.log;

public class LlamaGenetics extends BaseGenetics {
    public LlamaGenetics(Entity en) {
        super(en);
    }

    public void initializeGenetics(LlamaEntity en) {
        if (!this.getWorld().isClient) {
            Random randy = new Random();
            int[] newGenetics = new int[genomeSize];
            for (int i = 0; i < genomeSize; i++) {
                if(i == 5) { // primary color gene index
                    int num = randy.nextInt(11);
                    if (num < 1) {
                        newGenetics[i] = randy.nextInt(16);
                    } else {
                        switch (randy.nextInt(4)) {
                            case 0:
                                newGenetics[i] = 0;
                                break;
                            case 1:
                                newGenetics[i] = 7;
                                break;
                            case 2:
                                newGenetics[i] = 12;
                                break;
                            case 3:
                                newGenetics[i] = 8;
                                break;
                            default:
                                newGenetics[i] = randy.nextInt(16);
                                break;
                        }
                    }
                }
                else{
                    newGenetics[i] = randy.nextInt(16);
                }
            }
            this.setGenetics(newGenetics);
            this.hasGenetics = true;
            log("Initialized llama Genetics: " + (this.getGenetics()[5]));
        }
    }

}
