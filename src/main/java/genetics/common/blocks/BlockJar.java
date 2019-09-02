//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package genetics.common.blocks;


import genetics.common.BlockEntity.JarBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class BlockJar extends Block implements BlockEntityProvider {

    public BlockJar(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Environment(EnvType.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        JarBlockEntity BE = new JarBlockEntity();
        return BE;
    }
}
