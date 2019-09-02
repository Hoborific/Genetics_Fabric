package genetics.common.genetics;

import net.minecraft.entity.Entity;

import java.util.Random;

public class CowGenetics extends BaseGenetics {
    public CowGenetics(Entity en) {
        super(en);
    }

    //@Override
    public void initializeGenetics() {
        if (!this.getWorld().isClient) {
            //log("cow override called");
            Random randy = new Random();
            int[] newGenetics = new int[genomeSize];

            for (int i = 0; i < genomeSize; i++) {
                int temp = 25;
                if(i == 3){
                    if(randy.nextInt(8) < 7) {
                        temp += randy.nextInt(75);
                        newGenetics[i] = temp;
                    }else{
                        temp = 40;
                        temp += randy.nextInt(211);
                        newGenetics[i] = temp;
                    }
                }else if(i == 7){
                    if(randy.nextInt(8) < 4) {
                        temp += randy.nextInt(100);
                        newGenetics[i] = temp;
                    }else{
                        temp = 40;
                        temp += randy.nextInt(211);
                        newGenetics[i] = temp;
                    }
                }
                if(i != 3 && i != 7){
                    newGenetics[i] = randy.nextInt(16);
                }
            }
            this.setGenetics(newGenetics);
            this.hasGenetics = true;
            //log("Initialized Cow Genetics: " + Arrays.toString(this.getGenetics()));
        }
    }
}
