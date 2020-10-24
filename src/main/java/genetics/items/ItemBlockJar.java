package genetics.items;

import genetics.common.BlockEntity.JarBlockEntity;
import genetics.util.Logger;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemBlockJar extends BlockItem {
    private Block block;
    public boolean isEmpty = true;

    public ItemBlockJar(Block block_1, Settings item$Settings_1) {
        super(block_1, item$Settings_1);
        block = block_1;
    }

    public static boolean writeTagToBlockEntity(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, ItemStack itemStack_1) {
        Logger.log("called");
        MinecraftServer minecraftServer_1 = world_1.getServer();
        if (minecraftServer_1 == null) {
            return false;
        } else {
            CompoundTag compoundTag_1 = itemStack_1.getSubTag("BlockEntityTag");
            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
            if (blockEntity_1 != null) {
                if (!world_1.isClient && blockEntity_1.copyItemDataRequiresOperator() && (playerEntity_1 == null || !playerEntity_1.isCreativeLevelTwoOp())) {
                    System.out.println("FAILED BE CONDITIONS, RETURN FALSE");
                    return false;
                }
                CompoundTag compoundTag_2 = blockEntity_1.toTag(new CompoundTag());
                compoundTag_2.putInt("x", blockPos_1.getX());
                compoundTag_2.putInt("y", blockPos_1.getY());
                compoundTag_2.putInt("z", blockPos_1.getZ());
                if (itemStack_1.hasTag()) {
                    if (itemStack_1.getTag().contains("entity_id")) {
                        compoundTag_2.putString("entity_id", itemStack_1.getTag().getString("entity_id"));
                        compoundTag_2.put("entityData", itemStack_1.getTag().getCompound("entityData"));
                        Logger.log("wrote item data to blockentity");
                        Logger.log(compoundTag_2.asString());
                    }
                    blockEntity_1.fromTag(null,compoundTag_2);
                }
            }

            return false;
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
        if (world_1 != null) {
            if (itemStack_1.hasTag()) {
                assert itemStack_1.getTag() != null;
                list_1.add(new TranslatableText(("Animal Type: " + itemStack_1.getTag().getString("genetics:entitytype"))));
                list_1.add(new TranslatableText("entity_id:" + itemStack_1.getTag().getString("entity_id")));
                list_1.add(new TranslatableText("data:" + itemStack_1.getTag().getCompound("entityData").toString()));
                isEmpty = false;
            } else {
                list_1.add(new TranslatableText("Empty Jar"));
                isEmpty = true;
            }
        }
    }

    @Override
    public ActionResult place(ItemPlacementContext itemPlacementContext_1) {
        if (!itemPlacementContext_1.canPlace()) {
            return ActionResult.FAIL;
        } else {
            ItemPlacementContext itemPlacementContext_2 = this.getPlacementContext(itemPlacementContext_1);
            if (itemPlacementContext_2 == null) {
                return ActionResult.FAIL;
            } else {
                BlockState blockState_1 = this.getPlacementState(itemPlacementContext_2);
                if (blockState_1 == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(itemPlacementContext_2, blockState_1)) {
                    return ActionResult.FAIL;
                } else {
                    BlockPos blockPos_1 = itemPlacementContext_2.getBlockPos();
                    World world_1 = itemPlacementContext_2.getWorld();
                    PlayerEntity playerEntity_1 = itemPlacementContext_2.getPlayer();
                    ItemStack itemStack_1 = itemPlacementContext_2.getStack();
                    BlockState blockState_2 = world_1.getBlockState(blockPos_1);
                    Block block_1 = blockState_2.getBlock();
                    if (block_1 == blockState_1.getBlock()) {
                        this.postPlacement(blockPos_1, world_1, playerEntity_1, itemStack_1, blockState_2);
                        block_1.onPlaced(world_1, blockPos_1, blockState_2, playerEntity_1, itemStack_1);

                        if (playerEntity_1 instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) playerEntity_1, blockPos_1, itemStack_1);
                        }
                    }

                    BlockSoundGroup blockSoundGroup_1 = blockState_2.getSoundGroup();
                    world_1.playSound(playerEntity_1, blockPos_1, this.getPlaceSound(blockState_2), SoundCategory.BLOCKS, (blockSoundGroup_1.getVolume() + 1.0F) / 2.0F, blockSoundGroup_1.getPitch() * 0.8F);
                    itemStack_1.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }
        }
    }

    @Override
    protected boolean postPlacement(BlockPos blockPos_1, World world_1, PlayerEntity playerEntity_1, ItemStack itemStack_1, BlockState blockState_1) {
        return writeTagToBlockEntity(world_1, playerEntity_1, blockPos_1, itemStack_1);
    }
}

