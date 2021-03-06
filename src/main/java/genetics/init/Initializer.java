package genetics.init;

import genetics.Main;
import genetics.client.render.*;
import genetics.common.BlockEntity.ChickenCubeBlockEntity;
import genetics.common.BlockEntity.JarBlockEntity;
import genetics.common.blocks.BlockChickenCube;
import genetics.common.blocks.BlockJar;
import genetics.items.GeneticsSyringeEmpty;
import genetics.items.GeneticsSyringeFull;
import genetics.items.ItemBlockChickenCube;
import genetics.items.ItemBlockJar;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Initializer {
    public static final Item SYRINGE_FULL = new GeneticsSyringeFull(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    public static final Item SYRINGE_EMPTY = new GeneticsSyringeEmpty(new Item.Settings().group(ItemGroup.TOOLS).maxCount(64));
    public static final Block BLOCK_JAR = new BlockJar(FabricBlockSettings.of(Material.METAL).build());
    public static final Block BLOCK_CHICKENCUBE = new BlockChickenCube(FabricBlockSettings.of(Material.WOOD).build());

    public static BlockEntityType<JarBlockEntity> JAR_BLOCK_ENTITY;
    public static BlockEntityType<ChickenCubeBlockEntity> CHICKENCUBE_BLOCK_ENTITY;

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "geneticssyringe_empty"), SYRINGE_EMPTY);
        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "geneticssyringe_full"), SYRINGE_FULL);

        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "blockjar"), new ItemBlockJar(BLOCK_JAR, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(Main.MODID, "blockjar"), BLOCK_JAR);

        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "chickencube"), new ItemBlockChickenCube(BLOCK_CHICKENCUBE, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(Main.MODID, "blockchickencube"), BLOCK_CHICKENCUBE);

        JAR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Main.MODID, "entityjar"), BlockEntityType.Builder.create(JarBlockEntity::new, BLOCK_JAR).build(null));
        CHICKENCUBE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Main.MODID, "entitychickencube"), BlockEntityType.Builder.create(ChickenCubeBlockEntity::new, BLOCK_CHICKENCUBE).build(null));

    }
}
