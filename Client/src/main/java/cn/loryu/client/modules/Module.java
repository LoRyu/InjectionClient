package cn.loryu.client.modules;

import cn.loryu.client.Category;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Module {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public boolean state = false;
    public int key;
    public String name;
    public Category category;

    public Module(String name, int key, Category category) {
        this.name = name;
        this.key = key;
        this.category = category;
    }

    public void toggle() {
        this.setState(!this.state);
    }

    public void setState(boolean state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            MinecraftForge.EVENT_BUS.register(this);
            FMLCommonHandler.instance().bus().register(this);
            enable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
            disable();
        }
    }

    public void enable() {

    }

    public void disable() {

    }

    public String getName() {
        return name;
    }

    public int getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getState() {
        return state;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
