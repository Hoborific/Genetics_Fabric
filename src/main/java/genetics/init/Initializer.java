package genetics.init;

import genetics.Main;
import genetics.common.BlockEntity.JarBlockEntity;
import genetics.common.blocks.BlockJar;
import genetics.items.GeneticsSyringeEmpty;
import genetics.items.GeneticsSyringeFull;
import genetics.items.JarBlockItem;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Initializer {
    public static final Item SYRINGE_FULL = new GeneticsSyringeFull(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    public static final Item SYRINGE_EMPTY = new GeneticsSyringeEmpty(new Item.Settings().group(ItemGroup.TOOLS).maxCount(64));
    public static final Block BLOCK_JAR = new BlockJar(FabricBlockSettings.of(Material.METAL).build());
    public static BlockEntityType<JarBlockEntity> JAR_BLOCK_ENTITY;

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "geneticssyringe_empty"), SYRINGE_EMPTY);
        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "geneticssyringe_full"), SYRINGE_FULL);

        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "blockjar"), new JarBlockItem(BLOCK_JAR, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(Main.MODID, "blockjar"), BLOCK_JAR);

        JAR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Main.MODID, "jar"), BlockEntityType.Builder.create(JarBlockEntity::new, BLOCK_JAR).build(null));

    }
}
