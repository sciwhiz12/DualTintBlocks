package sciwhiz12.dualtintblocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(DualTintBlocks.MODID)
public class DualTintBlocks {
    public static final String MODID = "dualtintblocks";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<OreBlock> BLACK_ORE = BLOCKS
            .register("black_ore", () -> new OreBlock(Block.Properties.from(Blocks.COAL_ORE)));
    public static final RegistryObject<OreBlock> RED_ORE = BLOCKS
            .register("red_ore", () -> new OreBlock(Block.Properties.from(Blocks.REDSTONE_ORE)));
    public static final RegistryObject<OreBlock> AQUA_ORE = BLOCKS
            .register("aqua_ore", () -> new OreBlock(Block.Properties.from(Blocks.DIAMOND_ORE)));

    public static final RegistryObject<BlockItem> BLACK_ORE_ITEM = ITEMS.register("black_ore",
            () -> new BlockItem(BLACK_ORE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> RED_ORE_ITEM = ITEMS
            .register("red_ore", () -> new BlockItem(RED_ORE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> AQUA_ORE_ITEM = ITEMS.register("aqua_ore",
            () -> new BlockItem(AQUA_ORE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));

    public DualTintBlocks() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
