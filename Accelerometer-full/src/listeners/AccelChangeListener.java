/* - AccelChangeListener -
 * Display the current acceleration value for the corresponding axis.
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package listeners;

import graphics.MotionGraphPanel;

import com.phidgets.AccelerometerPhidget;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AccelerationChangeListener;
import com.phidgets.event.AccelerationChangeEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.KeyEvent;


public class AccelChangeListener implements AccelerationChangeListener{
    
    private JFrame appFrame;
    private MotionGraphPanel graphPanel;
    private JTextField accel1Txt;
    private JTextField accel2Txt;
    private JTextField accel3Txt;
    private double[] xFilt, yFilt, zFilt;
    private double xOut, yOut, zOut;

    Robot robot;
    private boolean forwardLock;
    private boolean backwardLock;


    /** Creates a new instance of AccelChangeListener */
    public AccelChangeListener(JFrame appFrame, MotionGraphPanel graphPanel, JTextField accel1Txt, JTextField accel2Txt, JTextField accel3Txt)
    {
        this.appFrame = appFrame;
        this.graphPanel = graphPanel;
        this.accel1Txt = accel1Txt;
        this.accel2Txt = accel2Txt;
        this.accel3Txt = accel3Txt;
        this.xFilt = new double[7];
        this.yFilt = new double[7];
        this.zFilt = new double[7];
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void accelerationChanged(AccelerationChangeEvent ce)
    {
        //AccelerometerPhidget sender = (AccelerometerPhidget)ce.getSource();

        int i = 0;
        switch(ce.getIndex())
        {
            case 0:
                xOut = 0;
                xFilt[6] = ce.getValue();
                for(i = 0; i < 6; i++)
                {
                    xFilt[i] = xFilt[i+1];
                    xOut = xOut + xFilt[i];
                }
                xOut = xOut / 6;
                double xChange = ce.getValue();
                accel1Txt.setText(Double.toString(xChange));
                graphPanel.setXOut(xOut);

                generateKeyPress(xChange);
                break;
            case 1:
                yOut = 0;
                yFilt[6] = ce.getValue();
                for(i = 0; i < 6; i++)
                {
                    yFilt[i] = yFilt[i+1];
                    yOut = yOut + yFilt[i];
                }
                yOut = yOut / 6;
                accel2Txt.setText(Double.toString(ce.getValue()));
                graphPanel.setYOut(yOut);
                break;
            case 2:
                zOut = 0;
                zFilt[6] = ce.getValue();
                for(i = 0; i < 6; i++)
                {
                    zFilt[i] = zFilt[i+1];
                    zOut = zOut + zFilt[i];
                }
                zOut = zOut / 6;
                accel3Txt.setText(Double.toString(ce.getValue()));
                graphPanel.setZOut(zOut);
                break;
            default:
                JOptionPane.showMessageDialog(appFrame, "Index " + ce.getIndex() + " is not a valid axis!", "Axis Index Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        
        graphPanel.repaint();
    }

    private void generateKeyPress(double xChange) {

        if(xChange <= 0){
            forwardLock = false;
        }

        if(xChange >=0){
            backwardLock = false;
        }

        if(!forwardLock && xChange >= 0.5){
            robot.keyPress(KeyEvent.VK_RIGHT);
            forwardLock = true;
        }else if(!backwardLock && xChange <= -0.5){
            robot.keyPress(KeyEvent.VK_LEFT);
            backwardLock = true;
        }
    }

}
