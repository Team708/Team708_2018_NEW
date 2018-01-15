package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

	
public class PneumaticsTest extends Subsystem {
	
	private DoubleSolenoid ExampleDoubleSolenoid = new DoubleSolenoid(1,2);
	/**
	 * Constructor
	 */
	public PneumaticsTest() {
		ExampleDoubleSolenoid = new DoubleSolenoid(RobotMap.Test1, RobotMap.Test2); //initializes the loading motor
	}
	
	public void initDefaultCommand() {
    }
	
	public void manualMove(double speed){
		ExampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void stop(){
		ExampleDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
	}
	
	public void sendToDashboard() {
//		SmartDashboard.putNumber("Loader Motor Speed", feedMotor.getSpeed());

		if (Constants.DEBUG) {
		}
	}
}