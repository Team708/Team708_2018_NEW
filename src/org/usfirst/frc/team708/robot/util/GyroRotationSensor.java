/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team708.robot.util;

//import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
/**
 * Wrapper class for the gyro allowing interchanging of
 * encoders gyro for measuring rotation.
 * @author 708
 */
public class GyroRotationSensor extends RotationSensor{

    private ADXRS450_Gyro gyro;

    public GyroRotationSensor(ADXRS450_Gyro gyro){
        this.gyro = gyro;
    }

    public double getAngle(){
        return gyro.getAngle();
    }

    public void reset(){
        gyro.reset();
    }
}
