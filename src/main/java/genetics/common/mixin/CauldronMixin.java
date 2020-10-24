package genetics.common.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(CauldronBlock.class)
public class CauldronMixin {

    private CauldronBlock e = (CauldronBlock) (Object) this;
    private ChickenEntity chicken;

    @Inject(at = @At("HEAD"), method = "onUse", cancellable = true)
    public void onUse(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1, CallbackInfoReturnable cir) {
        ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
        if (itemStack_1.isEmpty()) {
        } else {
            Item item_1 = itemStack_1.getItem();
            if (item_1 == Items.MILK_BUCKET) {
                if (!world_1.isClient) {

                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onEntityCollision", cancellable = true)
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1, CallbackInfo ci) {
        if (!world_1.isClient) {
            if (entity_1 instanceof ChickenEntity) {
                ChickenEntity theChicken = ((ChickenEntity) entity_1);
                chicken = theChicken;
                theChicken.setAiDisabled(true);
                theChicken.setOnGround(true);
                theChicken.setSwimming(true);
            }
        }
    }
}
