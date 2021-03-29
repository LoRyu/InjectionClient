package cn.loryu.client;

import cn.loryu.client.modules.Module;
import cn.loryu.client.modules.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class Client {
    public static Client instance;
    public static boolean state = false;

    public static ModuleManager moduleManager = new ModuleManager();

    public Client() {
        if (state) return;
        state = true;
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        instance = this;
    }

    @SubscribeEvent
    public void keyInput(InputEvent.KeyInputEvent event) {
        for(Module m : moduleManager.getModules()) {
            if(Keyboard.isKeyDown(m.key)) {
                m.toggle();
            }
        }
    }
}
