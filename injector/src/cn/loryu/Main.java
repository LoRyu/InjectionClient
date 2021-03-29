package cn.loryu;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static String path = System.getProperty("user.dir");

    public static void main(String[] args) {
        File dll = new File(path + "\\attach.dll");
        File clientJar = new File(path + "\\client.jar");

        if (!dll.exists()) {
            error("没找到attach.dll");
            return;
        }
        System.loadLibrary("attach");
        VirtualMachineDescriptor vm = VirtualMachine.list().stream().filter(m -> m.displayName().startsWith("net.minecraft.launchwrapper.Launch")).findFirst().orElse(null);
        if (vm == null) {
            error("没找到minecraft");
            return;
        }
        try {
            VirtualMachine attach = VirtualMachine.attach(vm);
            if (!clientJar.exists()) {
                error("没找到client.jar");
                return;
            }
            try {
                attach.loadAgent(clientJar.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            attach.detach();
            error("注入成功");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void error(String message) {
        Font messageFont = new Font("微软雅黑", Font.PLAIN, 12);
        Font dialogButtonFont = new Font("微软雅黑", Font.PLAIN, 12);
        UIManager.put("OptionPane.messageFont", messageFont);
        UIManager.put("OptionPane.buttonFont", dialogButtonFont);
        JOptionPane.showMessageDialog(null, message, "info", 1);
    }
}