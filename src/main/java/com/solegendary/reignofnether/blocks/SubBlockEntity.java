package com.solegendary.reignofnether.blocks;

import com.solegendary.reignofnether.registrars.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SubBlockEntity extends BlockEntity {
    private BlockPos mainPos = BlockPos.ZERO;

    public SubBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistrar.SUB_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void setMainPos(BlockPos pos) {
        this.mainPos = pos;
        setChanged();
        sync();
    }

    public BlockPos getMainPos() {
        return mainPos;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("MainPos", mainPos.asLong());
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        mainPos = BlockPos.of(tag.getLong("MainPos"));
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void sync() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}
