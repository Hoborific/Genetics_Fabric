package genetics.client.geneticRenderLogic;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.Entity;

public class CowHideColorLogic extends BaseColorLogic {
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

    public float[] geneticsToRGB(Entity en) {
        int primary = getValueOfPrimaryGene(en);
        float[] test2 = new float[]{(primary ) / 255.0f, (primary - 20) / 255.0f, (primary - 30) / 255.0f};
        return test2;//System.out.println(Arrays.toString(test2));
    }
}
