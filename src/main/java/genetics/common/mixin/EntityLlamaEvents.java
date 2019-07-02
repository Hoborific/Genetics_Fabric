package genetics.common.mixin;

import genetics.common.genetics.IGeneticBase;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(LlamaEntity.class)
public class EntityLlamaEvents {

    private LlamaEntity e = (LlamaEntity) (Object) this;

    @Inject(at = @At("RETURN"), method = "createChild", cancellable = true)
    public void createChild(PassiveEntity passiveEntity_1, CallbackInfoReturnable cir) {

        LlamaEntity myLlama = EntityType.LLAMA.create(passiveEntity_1.world);
        if (!passiveEntity_1.world.isClient) {
            {
                int parent1 = ((IGeneticBase) this).getGeneticByIndex(0);
                int parent2 = ((IGeneticBase) passiveEntity_1).getGeneticByIndex(0);

                ((IGeneticBase) myLlama).initializeGenetics(((IGeneticBase) this).getGenetics(),((IGeneticBase) passiveEntity_1).getGenetics());
                int[] childGenes = ((IGeneticBase) myLlama).getGenetics();

                DyeColor dyeColor_1 = DyeColor.byId(parent1);
                DyeColor dyeColor_2 = DyeColor.byId(parent2);

                CraftingInventory DyeInventory = new CraftingInventory(new Container((ContainerType) null, -1) {
                    public boolean canUse(PlayerEntity playerEntity_1) {
                        return false;
                    }
                }, 2, 1);

                DyeInventory.setInvStack(0, new ItemStack(DyeItem.byColor(dyeColor_1)));
                DyeInventory.setInvStack(1, new ItemStack(DyeItem.byColor(dyeColor_2)));

                Optional var10000 = passiveEntity_1.world.getRecipeManager().getFirstMatch(RecipeType.CRAFTING, DyeInventory, passiveEntity_1.world).map((craftingRecipe_1) -> {
                    return craftingRecipe_1.craft(DyeInventory);
                }).map(ItemStack::getItem);

                DyeItem.class.getClass();
                var10000 = var10000.filter(DyeItem.class::isInstance);
                DyeItem.class.getClass();
                DyeItem myDyeItem = (DyeItem) var10000.map(DyeItem.class::cast).orElseGet(() -> passiveEntity_1.world.random.nextBoolean() ? DyeItem.byColor(dyeColor_1) : DyeItem.byColor(dyeColor_2));

                childGenes[0] = myDyeItem.getColor().getId();
                ((IGeneticBase) myLlama).setGeneticsInherited(childGenes);
                cir.setReturnValue(myLlama);
            }
        }
    }
}


