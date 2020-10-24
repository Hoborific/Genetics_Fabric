package genetics.common.mixin;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.util.Optional;
import java.util.Random;


@Mixin(ChickenEntity.class)
public class EntityChickenEvents {

    @Shadow
    public int eggLayTime;
    private ChickenEntity e = (ChickenEntity) (Object) this;
    private Random randy = new Random();

    @Inject(at = @At("HEAD"), method = "tickMovement", cancellable = true)
    public void tickMovement(CallbackInfo ci) {
        if(!this.e.world.isClient && e.isAlive() && !e.isBaby() && !e.hasJockey() && --this.eggLayTime <= 0){
            int index = ((IGeneticBase)e).getGeneticByIndex(0);
            if(index != 0){
                e.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (randy.nextFloat() - randy.nextFloat()) * 0.2F + 1.0F);
                e.dropItem(DyeItem.byColor(DyeColor.byId(index)));
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "createChild", cancellable = true) // I was too busy seeing if I could, I never stopped to ask myself if I should, this class is an abomination and a sin against code.
    public void createChild(ServerWorld serverWorld,PassiveEntity passiveEntity_1, CallbackInfoReturnable cir) {

        ChickenEntity myChicken = EntityType.CHICKEN.create(passiveEntity_1.world);
        if (!passiveEntity_1.world.isClient) {
            {
                int parent1 = ((IGeneticBase) this).getGeneticByIndex(0);
                int parent2 = ((IGeneticBase) passiveEntity_1).getGeneticByIndex(0);

                ((IGeneticBase) myChicken).initializeGenetics(((IGeneticBase) this).getGenetics(),((IGeneticBase) passiveEntity_1).getGenetics());
                int[] childGenes = ((IGeneticBase) myChicken).getGenetics();

                DyeColor dyeColor_1 = DyeColor.byId(parent1);
                DyeColor dyeColor_2 = DyeColor.byId(parent2);

                CraftingInventory DyeInventory = new CraftingInventory(new ScreenHandler( null, -1) {
                    public boolean canUse(PlayerEntity playerEntity_1) {
                        return false;
                    }
                }, 2, 1);

                DyeInventory.setStack(0, new ItemStack(DyeItem.byColor(dyeColor_1)));
                DyeInventory.setStack(1, new ItemStack(DyeItem.byColor(dyeColor_2)));

                Optional var10000 = passiveEntity_1.world.getRecipeManager().getFirstMatch(RecipeType.CRAFTING, DyeInventory, passiveEntity_1.world).map((craftingRecipe_1) -> {
                    return craftingRecipe_1.craft(DyeInventory);
                }).map(ItemStack::getItem);

                DyeItem.class.getClass();
                var10000 = var10000.filter(DyeItem.class::isInstance);
                DyeItem.class.getClass();
                DyeItem myDyeItem = (DyeItem) var10000.map(DyeItem.class::cast).orElseGet(() -> passiveEntity_1.world.random.nextBoolean() ? DyeItem.byColor(dyeColor_1) : DyeItem.byColor(dyeColor_2));

                childGenes[0] = myDyeItem.getColor().getId();
                ((IGeneticBase) myChicken).setGeneticsInherited(childGenes);
                cir.setReturnValue(myChicken);
            }
        }
    }
}


