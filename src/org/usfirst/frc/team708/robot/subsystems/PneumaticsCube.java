package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.pneumatics.PneumaticsManual;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	
public class PneumaticsCube extends Subsystem {
	
	private DoubleSolenoid exampleDoubleSolenoid;
//	private Solenoid singleSolenoid;
	
	/**
	 * Constructor
	 */
	public PneumaticsCube() {
		exampleDoubleSolenoid = new DoubleSolenoid(RobotMap.squeezeGrabber, RobotMap.releaseGrabber); //initializes
//		singleSolenoid = new Solenoid(3);
		
		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void initDefaultCommand() {
		 setDefaultCommand(new PneumaticsManual());
    }
	
	public void setPiston(Value value) {
		exampleDoubleSolenoid.set(value);	
	}	
	
	public void reverse(){
		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);

	}
	
	public void forward() {
		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void sendToDashboard() {

//		SmartDashboard.putBoolean("In Soleniod", true);

		if (Constants.DEBUG) {
		}
	}
}