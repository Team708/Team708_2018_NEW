package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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

public class IntakeCube extends Subsystem {
	
	public Spark intakeMotor;
	public DigitalInput cubeSensor;
    /**
      * Constructor
      */
	public IntakeCube() {
//	intakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
		intakeMotor = new Spark(RobotMap.intakeMotor);
		cubeSensor 	= new DigitalInput(RobotMap.cubeSensor);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
    }
	
	public void moveMotor(double speed) {
		intakeMotor.set(speed);
    	SmartDashboard.putNumber("In Move Motor speed=", speed);
	}
	
	public void stopMotor() {
		intakeMotor.set(0.0);
    	SmartDashboard.putNumber("In Move Motor speed=", 0.0);
	}
	
    public boolean hasCube() {
		
		if (!cubeSensor.get()) {
			return (true);
	    }
		else {
			return (false);
		}
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
		SmartDashboard.putBoolean("Has cube:", hasCube());
    }
    
    
}