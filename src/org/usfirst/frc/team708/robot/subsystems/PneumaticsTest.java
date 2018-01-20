package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.pneumatics.PneumaticsManual;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

	
public class PneumaticsTest extends Subsystem {
	
	private Solenoid exampleSolenoid;
	
	/**
	 * Constructor
	 */
	public PneumaticsTest() {
		exampleSolenoid = new Solenoid(2); //initializes
		
		exampleSolenoid.set(true);
	}
	
	public void initDefaultCommand() {
		 setDefaultCommand(new PneumaticsManual());
    }	
	
	
	
	public void off(){
		exampleSolenoid.set(false);
	}
	
	public void on() {
		exampleSolenoid.set(true);
	}
	
	public void sendToDashboard() {

		if (Constants.DEBUG) {
		}
	}
}