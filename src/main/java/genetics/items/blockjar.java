package genetics.items;

import net.minecraft.block.Block;


public class blockjar extends JarBlockItem {
    private Block block;

    public blockjar(Block block_1, Settings item$Settings_1) {
        super(block_1, item$Settings_1);
        this.block = block_1;
    }

    /*@Override
    public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
        if (world_1 != null) {
            if (itemStack_1.hasTag()) {
                assert itemStack_1.getTag() != null;
                list_1.add(new TranslatableText(("Animal Type: " + itemStack_1.getTag().getString("genetics:entitytype"))));
            }else{
                list_1.add(new TranslatableText("Empty Jar"));
            }
        }
        super.appendTooltip(itemStack_1, world_1, list_1, tooltipContext_1);
    }*/
}