package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

	
public class PneumaticsTest extends Subsystem {
	
	private static DoubleSolenoid ExampleDoubleSolenoid = new DoubleSolenoid(RobotMap.Test1, RobotMap.Test2);
	/**
	 * Constructor
	 */
	public PneumaticsTest() {
	DoubleSolenoid	ExampleDoubleSolenoid = new DoubleSolenoid(RobotMap.Test1, RobotMap.Test2); //initializes
	}
	
	public void initDefaultCommand() {
    }
	
	
	public static void stop(){
		ExampleDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
	}
	
	public void sendToDashboard() {
//		SmartDashboard.putNumber("Loader Motor Speed", feedMotor.getSpeed());

		if (Constants.DEBUG) {
		}
	}

	public static void set(Value kforward) {
		ExampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
		// TODO Auto-generated method stub
		
	}
}