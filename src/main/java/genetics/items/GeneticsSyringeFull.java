package genetics.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static genetics.util.Logger.log;

public class GeneticsSyringeFull extends GeneticsBaseItem {
    public GeneticsSyringeFull(Settings item$Settings_1) {
        super(item$Settings_1);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        ItemStack held = playerEntity_1.getMainHandStack();

        log("GeneticsSyringeFull use function. Genes: " + Arrays.toString(held.getTag().getIntArray("genetics:genes")));
        return super.use(world_1, playerEntity_1, hand_1);
    }

    @Override
    public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
        if (world_1 != null) {
            if (itemStack_1.hasTag()) {
                list_1.add(new TranslatableText(("Animal Type: " + itemStack_1.getTag().getString("genetics:entitytype"))));
                list_1.add(new TranslatableText(("Genes: " + Arrays.toString(itemStack_1.getTag().getIntArray("genetics:genes")))));
            } else
                list_1.add(new TranslatableText("Empty Syringe"));
        }
    }

}
