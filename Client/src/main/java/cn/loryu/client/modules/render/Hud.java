package cn.loryu.client.modules.render;

import cn.loryu.client.Category;
import cn.loryu.client.Client;
import cn.loryu.client.modules.Module;
import cn.loryu.client.modules.ModuleManager;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Comparator;

public class Hud extends Module {
    public Hud() {
        super("HUD", Keyboard.KEY_H, Category.Render);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        ScaledResolution s = new ScaledResolution(mc);
        int width = new ScaledResolution(mc).getScaledWidth();
        int height = new ScaledResolution(mc).getScaledHeight();
        int y = 1;
        if (mc.currentScreen != null && !(mc.currentScreen instanceof GuiMainMenu)) return;
        ArrayList<Module> enabledModules = new ArrayList<>();
        for (Module m : Client.instance.moduleManager.getModules()) {
            if (m.state) {
                enabledModules.add(m);
            }
        }
        enabledModules.sort(new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {
                return mc.fontRendererObj.getStringWidth(o2.getName()) - mc.fontRendererObj.getStringWidth(o1.getName());
            }
        });
        for (Module m : enabledModules) {
            if (m != null && m.getState()) {
                int moduleWidth = mc.fontRendererObj.getStringWidth(m.name);
                mc.fontRendererObj.drawString(m.name, width - moduleWidth - 1, y, -1);
                y += mc.fontRendererObj.FONT_HEIGHT;
            }
        }
    }

}
