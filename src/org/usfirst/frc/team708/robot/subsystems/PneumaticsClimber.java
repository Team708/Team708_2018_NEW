package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.pneumatics.ShiftClimber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	
public class PneumaticsClimber extends Subsystem {
	
	private DoubleSolenoid climberDoubleSolenoid;
	private boolean high;

	
	/**
	 * Constructor
	 */
	public PneumaticsClimber() {
		climberDoubleSolenoid = new DoubleSolenoid(RobotMap.telescopeLow, RobotMap.telescopeHigh); //initializes
		
		climberDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void initDefaultCommand() {
//		 setDefaultCommand(new ShiftClimber());
    }
	
	public void setPiston(Value value) {
		climberDoubleSolenoid.set(value);	
	}	
	
	public void reverse(){
		climberDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
		high = true;
	}
	
	public void forward() {
		climberDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
		high = false;
	}
	
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Climber High", high);
	}
}