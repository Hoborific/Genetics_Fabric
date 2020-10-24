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
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockJar extends Block implements BlockEntityProvider {

    public BlockJar(Settings block$Settings_1) {
        super(block$Settings_1.of(Material.GLASS).nonOpaque());
    }


    @Override
    public boolean isTranslucent(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return true;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new JarBlockEntity();
    }
    @Override
    public void onBreak(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
        //world_1.playLevelEvent(playerEntity_1, 2001, blockPos_1, getRawIdFromState(blockState_1));
        if (!world_1.isClient()) {
            JarBlockEntity BE =  (JarBlockEntity)world_1.getBlockEntity(blockPos_1);
            if (BE != null) {
                if (BE.getMyEntityType() != null) {
                    Entity myEntity = Registry.ENTITY_TYPE.get(BE.getMyEntityType()).create(world_1);
                    assert myEntity != null;
                    myEntity.fromTag(BE.getMyEntityData());
                    myEntity.setPos(blockPos_1.getX(), blockPos_1.getY(), blockPos_1.getZ());
                    world_1.spawnEntity(myEntity);
                }
            }
        }
    }
}
