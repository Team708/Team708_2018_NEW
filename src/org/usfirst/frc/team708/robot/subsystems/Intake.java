package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
//import org.usfirst.frc.team708.robot.RobotMap;
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Value;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Subsystem that intakes balls
 * @author James_Makovics
 * @author Michael_Steinberg
 * @author Thomas Zhao
 * @author Alex Tysak
 */

public class Intake extends Subsystem {
	
    /**
      * Constructor
      */
	public Intake() {
	
	}
	
	public void initDefaultCommand() {
        
    }
	
	public void moveMotor(double speed) {
		
	}
	
	public void stop(){
		
	}
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
    
    
}

