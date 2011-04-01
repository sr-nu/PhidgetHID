package Accelerometer;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class PhidgetController {

    private static PopupMenu createPopupMenu() throws HeadlessException {
        PopupMenu menu = new PopupMenu();

        menu.add(getPresenterMenuItem());
        menu.add(getAppLauncherMenuItem());
        menu.add(getSettingsMenuItem());
        menu.add(getExitMenuItem());
        return menu;

    }


    private static MenuItem getExitMenuItem() {
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exit;
    }

    private static MenuItem getPresenterMenuItem() {
        MenuItem exit = new MenuItem("Presenter");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Presenter.getInstance().execute();
            }
        });
        return exit;
    }

    private static MenuItem getAppLauncherMenuItem() {
        MenuItem exit = new MenuItem("App Launcher");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //app launcher code
            }
        });
        return exit;
    }

    private static MenuItem getSettingsMenuItem() {
        MenuItem exit = new MenuItem("Settings");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Accelerometer.execute();
            }
        });
        return exit;
    }

    public static void main(String[] args) throws Exception {
        PhidgetController app = new PhidgetController();
        app.execute();
    }

    private static Image getImage() throws HeadlessException {
        Icon defaultIcon = MetalIconFactory.getTreeComputerIcon();
        Image img = new BufferedImage(defaultIcon.getIconWidth(), defaultIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);
        return img;
    }

    public void execute() throws Exception {
        TrayIcon icon = new TrayIcon(getImage(), "Phidget Presenter", createPopupMenu());
        SystemTray.getSystemTray().add(icon);
    }
}

