package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

	
public class PneumaticsTest extends Subsystem {
	
	private DoubleSolenoid ExampleDoubleSolenoid;
	
	/**
	 * Constructor
	 */
	public PneumaticsTest() {
	DoubleSolenoid	ExampleDoubleSolenoid = new DoubleSolenoid(RobotMap.Test1, RobotMap.Test2); //initializes
	}
	
	public void initDefaultCommand() {
    }
	
	public void setpiston(Value value) {
		ExampleDoubleSolenoid.set(value);;	
	}	
	public void reverse(){
		ExampleDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void sendToDashboard() {
//		SmartDashboard.putNumber("Loader Motor Speed", feedMotor.getSpeed());

		if (Constants.DEBUG) {
		}
	}
}