package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.Constants;
//import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.RobotMap;
//import org.usfirst.frc.team708.robot.util.IRSensor;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Leaders
 * 
 */
public class Loader extends Subsystem {
	
	/**
	 * Constructor
	 */
	public Loader() {
		
	}
	
	public void initDefaultCommand() {
  //      setDefaultCommand(new ManualLoader());
    }
	
	
	public void sendToDashboard() {
		
		
		if (Constants.DEBUG) {
	
		}
	}
}