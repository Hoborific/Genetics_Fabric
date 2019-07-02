package genetics.client.geneticRenderLogic;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.Entity;

public class CowSpotColorLogic extends BaseColorLogic {
    public CowSpotColorLogic() {

    }

    public CowSpotColorLogic(int primaryGeneticIndex) {
        super(primaryGeneticIndex);
    }

    public CowSpotColorLogic(int primaryGeneticIndex, int secondaryGeneticIndex) {
        super(primaryGeneticIndex, secondaryGeneticIndex);
    }
    @Override
    public int getValueOfPrimaryGene(Entity en) {
        return ((IGeneticBase) en).getGeneticByIndex(primaryGeneticIndex);
    }
    @Override
    public int getValueofSecondaryGene(Entity en) {
        return ((IGeneticBase) en).getGeneticByIndex(primaryGeneticIndex);
    }
    @Override
    public float[] geneticsToRGB(Entity en) {
        int primary = getValueOfPrimaryGene(en);
        float[] test2 = new float[]{(primary) / 255.0f, (primary - 20) / 255.0f, (primary - 30) / 255.0f};
        return test2;
    }
}
