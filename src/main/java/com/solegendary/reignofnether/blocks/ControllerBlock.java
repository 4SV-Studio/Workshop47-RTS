package com.solegendary.reignofnether.blocks;

import com.solegendary.reignofnether.gui.ControllerMenu;
import com.solegendary.reignofnether.registrars.BlockRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ControllerBlock extends BaseEntityBlock {
    public ControllerBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pPlayer.openMenu(new SimpleMenuProvider(
                (id, inv, buf) -> new ControllerMenu(id, inv),
                Component.literal("World Controller")
        ));

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        BlockPos[] subBlocksPoses = getSubBlocks(pPos);
        for (BlockPos subBlockPos : subBlocksPoses) {
            if (!pLevel.getBlockState(subBlockPos).isAir()) {
                pLevel.removeBlock(pPos, false);
                return;
            }

            pLevel.setBlock(subBlockPos, BlockRegistrar.SUB_BLOCK.get().defaultBlockState(), 3);
            if (pLevel.getBlockEntity(subBlockPos) instanceof SubBlockEntity subBlockEntity) {
                subBlockEntity.setMainPos(pPos);
            }
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        BlockPos[] subBlocksPoses = getSubBlocks(pPos);
        for (BlockPos subBlockPos : subBlocksPoses) {
            if (!pLevel.getBlockState(subBlockPos).is(BlockRegistrar.SUB_BLOCK.get())) continue;

            pLevel.removeBlock(subBlockPos, false);
        }
    }

    private static BlockPos[] getSubBlocks(BlockPos pos) {
        BlockPos[] out = new BlockPos[26];
        int i = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) continue;
                out[i++] = pos.offset(dx, 0, dz);
            }
        }

        for (int dy = 1; dy <= 2; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    out[i++] = pos.offset(dx, dy, dz);
                }
            }
        }

        return out;
    }
}
