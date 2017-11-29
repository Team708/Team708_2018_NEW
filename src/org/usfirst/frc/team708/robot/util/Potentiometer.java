/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team708.robot.util;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 *
 * @author Nam Tran
 */
public class Potentiometer {
    
    private AnalogInput potentiometer;
    
    // Stuff based on potentiometer for finding the scaling factor in getAngle()
    private final double MIN_POTENTIOMETER_VOLTAGE = 0.0;
    private final double MAX_POTENTIOMETER_VOLTAGE = 5.0;
    private final double MIN_POTENTIOMETER_ANGLE = 0.0;
    private 	  double maxPotentiometerAngle;
    
    public Potentiometer(int pwmPort, double numberOfRotations) {
        potentiometer = new AnalogInput(pwmPort);
        maxPotentiometerAngle = (numberOfRotations * 360);
    }
    
    public double getAngle() {
        // Finds the scaling factor for the voltage
        double scalingFactor = (maxPotentiometerAngle - MIN_POTENTIOMETER_ANGLE) / (MAX_POTENTIOMETER_VOLTAGE - MIN_POTENTIOMETER_VOLTAGE);
        
        double voltage 	= potentiometer.getVoltage();
        double offset 	= 0.0;
        
        return (scalingFactor * voltage) + offset;
    }
    
    public double getVoltage() {
    	return(potentiometer.getVoltage());
    }
}
