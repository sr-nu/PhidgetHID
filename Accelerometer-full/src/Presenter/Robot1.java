package Presenter;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Robot1{

    static int keyInput[] =
    {
      KeyEvent.VK_J, KeyEvent.VK_A, KeyEvent.VK_V, KeyEvent.VK_A, KeyEvent.VK_SPACE,
      KeyEvent.VK_P, KeyEvent.VK_R, KeyEvent.VK_O, KeyEvent.VK_G, KeyEvent.VK_R,
      KeyEvent.VK_A, KeyEvent.VK_M, KeyEvent.VK_M, KeyEvent.VK_I, KeyEvent.VK_N,
      KeyEvent.VK_G, KeyEvent.VK_SPACE, KeyEvent.VK_F, KeyEvent.VK_O, KeyEvent.VK_R,
      KeyEvent.VK_U, KeyEvent.VK_M, KeyEvent.VK_S, KeyEvent.VK_SPACE, KeyEvent.VK_PERIOD,
      KeyEvent.VK_C, KeyEvent.VK_O, KeyEvent.VK_M
    };


    public static void main(String[] args) throws AWTException,IOException {

    Runtime.getRuntime().exec("notepad");

    Robot robot = new Robot();

    for (int i = 0; i < keyInput.length; i++)
    {

      robot.keyPress(keyInput[i]);
      robot.delay(100);

    }
}
}
