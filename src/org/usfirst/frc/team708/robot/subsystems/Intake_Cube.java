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
 * Subsystem that intakes balls
 * @author James_Makovics
 * @author Michael_Steinberg
 * @author Thomas Zhao
 * @author Alex Tysak
 */

public class Intake_Cube extends Subsystem {
	
	public Spark intakeMotor;
	
	
    /**
      * Constructor
      */
	public Intake_Cube() {
//	intakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
		intakeMotor = new Spark(RobotMap.INTAKE_MOTOR);
	 
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
    }
	
	public void moveMotor(double speed) {
		intakeMotor.set(speed);
    	SmartDashboard.putNumber("In Move Motor speed=", speed);
	}
	
	public void stop(){
		intakeMotor.set(Constants.INTAKE_OFF);
    	SmartDashboard.putNumber("In Stop Motor speed=", 0.0);
	}
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
    
    
}

