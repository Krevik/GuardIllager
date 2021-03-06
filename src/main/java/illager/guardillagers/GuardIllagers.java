package illager.guardillagers;

import illager.guardillagers.client.IllagerEntityRender;
import illager.guardillagers.event.EntityEventHandler;
import illager.guardillagers.init.IllagerEntityRegistry;
import illager.guardillagers.init.IllagerItems;
import illager.guardillagers.init.IllagerSoundsRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("guardillagers")
public class GuardIllagers {
    public static final String MODID = "guardillagers";

    public static final ResourceLocation LOOT_TABLE = LootTableList.register(new ResourceLocation(GuardIllagers.MODID, "entity/guard_illager"));

    public GuardIllagers() {
        // Register the setup method for modloading
        FMLModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server, registry and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code

        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        IllagerEntityRender.entityRender();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        EntityEventHandler.addSpawn();
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onSoundRegistry(final RegistryEvent.Register<SoundEvent> soundEvent) {
        IForgeRegistry<SoundEvent> registry = soundEvent.getRegistry();

        IllagerSoundsRegister.registerSounds(registry);
    }

    @SubscribeEvent
    public void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        // register a new block here

        IForgeRegistry<Item> registry = event.getRegistry();

        IllagerItems.registerItems(registry);
    }


    @SubscribeEvent
    public void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();


        IllagerEntityRegistry.registerEntity(registry);
    }


}
