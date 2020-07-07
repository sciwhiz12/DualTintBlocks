package sciwhiz12.dualtintblocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraft.item.DyeColor.*;

@EventBusSubscriber(value = Dist.CLIENT, modid = DualTintBlocks.MODID, bus = Bus.MOD)
public class ClientSetup {
    public static final IBlockColor BLOCK_COLOR = (state, world, pos, tintIndex) -> {
        // returns int value with 0xRRGGBB
        if (tintIndex > 0) {
            if (state.getBlock() == DualTintBlocks.BLACK_ORE.get()) {
                return 0x000000; // black
            } else if (state.getBlock() == DualTintBlocks.RED_ORE.get()) {
                return 0xFF0000; // red
            } else if (state.getBlock() == DualTintBlocks.AQUA_ORE.get()) {
                return 0x00FFFF; // aqua
            }
        }
        return 0xFFFFFF; // white
    };
    public static final IItemColor ITEM_COLOR = (stack, tintIndex) -> BLOCK_COLOR
            .getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex);

    @SubscribeEvent
    static void onItemColors(final ColorHandlerEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();
        ItemColors itemColors = event.getItemColors();

        // Block and item colors both need to be registerd
        // Block colors give different color based on the given block
        // Item colors simply pass-through to the block colors
        blockColors.register(BLOCK_COLOR, DualTintBlocks.BLACK_ORE.get(), DualTintBlocks.RED_ORE.get(),
                DualTintBlocks.AQUA_ORE.get());
        itemColors.register(ITEM_COLOR, DualTintBlocks.BLACK_ORE_ITEM.get(), DualTintBlocks.RED_ORE_ITEM.get(),
                DualTintBlocks.AQUA_ORE_ITEM.get());
    }

    @SubscribeEvent
    static void onClientSetup(final FMLClientSetupEvent event) {
        // Set the render type to CUTOUT_MIPPED, so the overlay's transparency will work
        // If not set, the overlay will render, but the transparency will become solid black
        RenderTypeLookup.setRenderLayer(DualTintBlocks.BLACK_ORE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(DualTintBlocks.RED_ORE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(DualTintBlocks.AQUA_ORE.get(), RenderType.getCutoutMipped());
    }
}
