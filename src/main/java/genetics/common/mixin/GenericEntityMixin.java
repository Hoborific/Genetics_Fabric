package genetics.common.mixin;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

import static genetics.util.Logger.log;


@Mixin({
        HorseEntity.class
})
public class GenericEntityMixin {

    @Inject(at = @At("RETURN"), method = "createChild", cancellable = true)
    public void createChild(ServerWorld world, PassiveEntity passiveEntity, CallbackInfoReturnable<LivingEntity> cir) {
        LivingEntity child = cir.getReturnValue();
        Entity e = (Entity) (Object) this;

        if (!e.world.isClient) {
            /* idk deliver babies or whatever */
            int[] parent1 = ((IGeneticBase) e).getGenetics();
            int[] parent2 = ((IGeneticBase) passiveEntity).getGenetics();
            ((IGeneticBase) child).initializeGenetics(parent1, parent2);
            log("Child genetics applied: mum " + Arrays.toString(parent1) + " dad: " + Arrays.toString(parent2) + " this: " + Arrays.toString(((IGeneticBase) child).getGenetics()));
        }
        cir.setReturnValue(child); // if child is a new instance
    }

}
