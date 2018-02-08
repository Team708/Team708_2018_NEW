package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
//import org.team708.robot.commands.arm.JoystickMoveArm;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code for the pivot motors of the two arms.
 * @author Nick Iannarone
 * @author Michael Steinberg
 * @author Jorsh Smith
 */

public class Telescope extends Subsystem {
	
	public Talon telescopeMotor;
	
	
    /**
      * Constructor
      */
	public Telescope() {
	telescopeMotor = new Talon(RobotMap.intakeMotor);
	 
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
    }
	
	public void moveMotor(double speed) {
		telescopeMotor.set(speed);
	}
	
	public void stop(){
		telescopeMotor.set(Constants.INTAKE_OFF);
	}
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
    
    
}

