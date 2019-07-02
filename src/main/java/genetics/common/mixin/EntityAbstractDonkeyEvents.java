package genetics.common.mixin;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AbstractDonkeyEntity.class)
public class EntityAbstractDonkeyEvents {

    private AbstractDonkeyEntity e = (AbstractDonkeyEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true) // I was too busy seeing if I could, I never stopped to ask myself if I should, this class is an abomination and a sin against code.
    public void interactMob(PlayerEntity playerEntity_1, Hand hand_1, CallbackInfoReturnable cir) {
        if(!e.world.isClient()) {
            ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
            if (e instanceof LlamaEntity) {
                if (itemStack_1.getItem() instanceof DyeItem) {
                    int[] tempGenes = ((IGeneticBase) e).getGenetics();
                    tempGenes[5] = ((DyeItem) itemStack_1.getItem()).getColor().getId();
                    ((IGeneticBase) e).setGeneticsInherited(tempGenes);
                    if (!playerEntity_1.abilities.creativeMode) {
                        itemStack_1.setCount(itemStack_1.getCount() - 1);
                    }
                }
            }
        }
    }
}


