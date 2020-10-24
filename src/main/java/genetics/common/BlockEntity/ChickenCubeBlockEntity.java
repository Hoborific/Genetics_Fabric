package genetics.common.BlockEntity;

import genetics.init.Initializer;
import genetics.util.Logger;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.registry.Registry;

public class ChickenCubeBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable {


    private PathAwareEntity entity;
    private Identifier myEntityType;
    private CompoundTag entityData;
    private int tickCount = 0;

    public ChickenCubeBlockEntity() {
        super(Initializer.CHICKENCUBE_BLOCK_ENTITY);
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        if (myEntityType != null) {
            CompoundTag ent = new CompoundTag();
            tag.put("entityData", entityData);
            tag.putString("entity_id", myEntityType.toString());
        }
        Logger.log("TO TAG: " + tag.toString());
        return tag;
    }

    public void fromTag(BlockState bs, CompoundTag tag) {
        if (tag != null) {
            super.fromTag(bs,tag);
            Logger.log("FROM TAG: " + tag.asString());
            this.myEntityType = new Identifier(tag.getString("entity_id"));
            this.entityData = tag.getCompound("entityData");

        }
    }

    public LivingEntity getEntity() {
        if (entity == null) {
            if (myEntityType != null) {
                Logger.log("created entity");
                entity = (PathAwareEntity) Registry.ENTITY_TYPE.get(myEntityType).create(world);
                assert entity != null;
                entity.fromTag(entityData);
                initializeTasks(entity);
            }
        }
        return entity;
    }

    public void initializeTasks(PathAwareEntity entity) {

    }

    public Identifier getMyEntityType(){
        return myEntityType;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        Logger.log(tag.toString());
        if (tag.contains("entity_id")) {
            if (tag.contains("entityData")) {
                entityData = tag.getCompound("entityData");
                myEntityType = new Identifier(tag.getString("entity_id"));
                Logger.log("Loaded on client");
            }
        }
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        if (myEntityType != null) {
            tag.putString("entity_id", myEntityType.toString());
            tag.put("entityData", entityData);
            Logger.log("Sent to client");
        }
        return tag;
    }

    @Override
    public void tick() {
        tickCount++;
        if (tickCount >= 20) {
            if (entity != null) {

            }
            tickCount = 0;
        }

    }
}
