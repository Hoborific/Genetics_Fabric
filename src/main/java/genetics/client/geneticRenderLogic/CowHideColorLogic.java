package genetics.client.geneticRenderLogic;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.Entity;

public class CowHideColorLogic extends BaseColorLogic {
    private int primaryGeneticIndex = 3;
    private int secondaryGeneticIndex = 7;
    public CowHideColorLogic() {

    }

    public CowHideColorLogic(int primaryGeneticIndex) {
        super(primaryGeneticIndex);
    }

    public CowHideColorLogic(int primaryGeneticIndex, int secondaryGeneticIndex) {
        super(primaryGeneticIndex, secondaryGeneticIndex);
    }

    @Override
    public int getValueOfPrimaryGene(Entity en) {
        return ((IGeneticBase) en).getGeneticByIndex(primaryGeneticIndex);
    }
    @Override
    public int getValueofSecondaryGene(Entity en) {
        return ((IGeneticBase) en).getGeneticByIndex(secondaryGeneticIndex);
    }
    @Override
    /*public float[] geneticsToRGB(Entity en) {
        int primary = getValueOfPrimaryGene(en);
        if (primary > 6) primary = 7;
        float test = 13 + (13 * primary);// + 23;
        int brown = (3 * primary);
        if (primary >= 7) {
            test += 98;
            brown = brown / 2;
        }
        float[] test2 = new float[]{(test + brown) / 255.0f, test / 255.0f, test / 255.0f};
        return test2;//System.out.println(Arrays.toString(test2));
    }*/
    public float[] geneticsToRGB(Entity en) {
        int primary = getValueOfPrimaryGene(en);
        float[] test2 = new float[]{(primary ) / 255.0f, (primary - 20) / 255.0f, (primary - 30) / 255.0f};
        return test2;//System.out.println(Arrays.toString(test2));
    }
}
