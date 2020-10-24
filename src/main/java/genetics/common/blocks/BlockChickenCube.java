//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package genetics.common.blocks;


import genetics.common.BlockEntity.ChickenCubeBlockEntity;
import genetics.common.BlockEntity.JarBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.BlockRenderInfo;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.block.Block;

public class BlockChickenCube extends Block implements BlockEntityProvider {

    ChickenCubeBlockEntity chickenCubeEntityReference;
    public BlockChickenCube(Settings block$Settings_1) {
        super(block$Settings_1);
    }


    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        ChickenCubeBlockEntity BE = new ChickenCubeBlockEntity();
        chickenCubeEntityReference = BE;
        return BE;
    }

    @Override
    public void onBreak(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
        //world_1.playSoundFromEntity(playerEntity_1, 2001, blockPos_1, getRawIdFromState(blockState_1));
        if(!world_1.isClient()){

        }
    }
}
