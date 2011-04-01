/* - AccelDetachListener -
 * Clear all the fields and disable all the controls
 *
 * Copyright 2007 Phidgets Inc.  
 * This work is licensed under the Creative Commons Attribution 2.5 Canada License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/2.5/ca/
 */

package listeners;

import graphics.MotionGraphPanel;

import com.phidgets.AccelerometerPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AccelDetachListener implements DetachListener{
    
    private JFrame appFrame;
    private MotionGraphPanel graphPanel;
    private JTextField attachedTxt;
    private JTextArea nameTxt;
    private JTextField serialTxt;
    private JTextField versionTxt;
    private JTextField numAxesTxt;
    private JTextField rangeTxt;
    private JTextField accel1Txt;
    private JTextField accel2Txt;
    private JTextField accel3Txt;
    private JSlider axis1SensitivityScrl;
    private JSlider axis2SensitivityScrl;
    private JSlider axis3SensitivityScrl;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    
    
    /** Creates a new instance of AccelDetachListener */
    public AccelDetachListener(JFrame appFrame, MotionGraphPanel graphPanel, JTextField attachedTxt, JTextArea nameTxt,
            JTextField serialTxt, JTextField versionTxt, JTextField numAxesTxt,
            JTextField rangeTxt, JTextField accel1Txt, JTextField accel2Txt,
            JTextField accel3Txt, JSlider axis1SensitivityScrl, JSlider axis2SensitivityScrl,
            JSlider axis3SensitivityScrl, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15)
    {
        this.appFrame = appFrame;
        this.graphPanel = graphPanel;
        this.attachedTxt = attachedTxt;
        this.nameTxt = nameTxt;
        this.serialTxt = serialTxt;
        this.versionTxt = versionTxt;
        this.numAxesTxt = numAxesTxt;
        this.rangeTxt = rangeTxt;
        this.accel1Txt = accel1Txt;
        this.accel2Txt = accel2Txt;
        this.accel3Txt = accel3Txt;
        this.axis1SensitivityScrl = axis1SensitivityScrl;
        this.axis2SensitivityScrl = axis2SensitivityScrl;
        this.axis3SensitivityScrl = axis3SensitivityScrl;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
    }

    public void detached(DetachEvent de)
    {
        try
         {
            AccelerometerPhidget detached = (AccelerometerPhidget)de.getSource();
            attachedTxt.setText(Boolean.toString(detached.isAttached()));
            nameTxt.setText("");
            serialTxt.setText("");
            versionTxt.setText("");
            numAxesTxt.setText("");
            rangeTxt.setText("");
            accel1Txt.setText("");
            accel2Txt.setText("");
            accel3Txt.setText("");
            
            this.axis1SensitivityScrl.setEnabled(false);
            this.axis2SensitivityScrl.setEnabled(false);
            this.axis3SensitivityScrl.setEnabled(false);
            
            graphPanel.setZAxisExist(false);
        }
        catch (PhidgetException ex)
        {
            JOptionPane.showMessageDialog(appFrame, ex.getDescription(), "Phidget error " + ex.getErrorNumber(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
