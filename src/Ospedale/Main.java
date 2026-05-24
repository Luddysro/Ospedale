/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale;

import Data.JSONLoader;
import Data.Storage;
import Ospedale.View.BaseView;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        System.setProperty("flatlaf.useNativeLibrary", "false");
        System.setProperty("flatlaf.uiScale", "1x");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        JSONLoader.loadUsers();
        AppContext context = new AppContext(Storage.getInstance());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaseView(context).setVisible(true);
            }
        });
    }

}
