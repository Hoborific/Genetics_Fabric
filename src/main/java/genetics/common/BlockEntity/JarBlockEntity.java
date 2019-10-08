package genetics.common.BlockEntity;

import genetics.init.Initializer;
import genetics.util.Logger;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;

public class JarBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable {


    private MobEntityWithAi entity;
    private Identifier myEntityType;
    private CompoundTag entityData;
    private int tickCount = 0;

    public JarBlockEntity() {
        super(Initializer.JAR_BLOCK_ENTITY);
    }

    public CompoundTag toTag(CompoundTag tag) {
        if (myEntityType != null) {
            super.toTag(tag);
            CompoundTag ent = new CompoundTag();
            tag.put("entityData", entityData);
            tag.putString("entity_id", myEntityType.toString());
        }
        Logger.log("TO TAG: " + tag.toString());
        return tag;
    }

    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        if (tag != null) {
            Logger.log("FROM TAG: " + tag.asString());
            this.myEntityType = new Identifier(tag.getString("entity_id"));
            this.entityData = tag.getCompound("entityData");

        }
    }


    public LivingEntity getEntity() {
        if (entity == null) {
            if (myEntityType != null) {
                Logger.log("created entity");
                entity = (AnimalEntity) Registry.ENTITY_TYPE.get(myEntityType).create(world);
                if(!world.isClient()){
                    world.getServer().getWorld(entity.dimension).spawnEntity(entity);
                }
                if(entity != null) {
                    entity.fromTag(entityData);
                    initializeTasks(entity);
                }
            }
        }
        return entity;
    }

    public void initializeTasks(MobEntityWithAi entity) {

    }

    public Identifier getMyEntityType(){
        return myEntityType;
    }
    public CompoundTag getMyEntityData(){ return entityData;}

    @Override
    public void fromClientTag(CompoundTag tag) {
        Logger.log(tag.toString());
        if (tag.containsKey("entity_id")) {
            if (tag.containsKey("entityData")) {
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

    public void tick() {
        if (entity != null) {
            entity.getMoveControl().tick();
            entity.tick();
            if(!world.isClient){
                world.getServer().getWorld(entity.dimension).tickEntity(entity);
            }
        }
    }
}
