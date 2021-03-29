package cn.loryu.client.modules;

import cn.loryu.client.modules.movement.Sprint;
import cn.loryu.client.modules.render.Hud;

import java.util.ArrayList;

public class ModuleManager {

    static ArrayList<Module> list = new ArrayList<Module>();

    public ArrayList<Module> getModules() {
        return list;
    }

    public ModuleManager() {

    }

    public Module getModule(String name) {
        for (Module m : list) {
            if (m.getName().equalsIgnoreCase(name))
                return m;
        }
        return null;
    }

    static {
        list.add(new Sprint());
        list.add(new Hud());
    }
}
