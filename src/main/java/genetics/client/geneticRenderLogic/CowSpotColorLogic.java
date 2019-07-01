package genetics.client.geneticRenderLogic;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.Entity;

public class CowSpotColorLogic extends BaseColorLogic {
    private int primaryGeneticIndex = 3;
    private int secondaryGeneticIndex = 7;
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
        return ((IGeneticBase) en).getGeneticByIndex(secondaryGeneticIndex);
    }
    @Override
    public float[] geneticsToRGB(Entity en) {
        int primary = getValueofSecondaryGene(en);
        float[] test2 = new float[]{(primary) / 255.0f, (primary - 20) / 255.0f, (primary - 30) / 255.0f};
        return test2;//System.out.println(Arrays.toString(test2));
    }/*
        float greyscale = 13 + (13 * getValueOfPrimaryGene(en));
        int secondary = getValueofSecondaryGene(en);
        float brown = (float) (2 * secondary);
        return new float[]{(greyscale + brown) / 255.0f, greyscale / 255.0f, greyscale / 255.0f};
    }*/
}
