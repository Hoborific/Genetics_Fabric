//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package genetics.common.blocks;


import genetics.common.BlockEntity.ChickenCubeBlockEntity;
import genetics.common.BlockEntity.JarBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockChickenCube extends Block implements BlockEntityProvider {

    ChickenCubeBlockEntity chickenCubeEntityReference;
    public BlockChickenCube(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Environment(EnvType.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        ChickenCubeBlockEntity BE = new ChickenCubeBlockEntity();
        chickenCubeEntityReference = BE;
        return BE;
    }

    @Override
    public void onBreak(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
        world_1.playLevelEvent(playerEntity_1, 2001, blockPos_1, getRawIdFromState(blockState_1));
        if(!world_1.isClient()){

        }
    }
}
