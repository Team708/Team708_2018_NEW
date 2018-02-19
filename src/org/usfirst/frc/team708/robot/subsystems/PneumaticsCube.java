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
	
//	private DoubleSolenoid exampleDoubleSolenoid;
	private Solenoid intakeSolenoid;
	private boolean intake_on = false;
	
	/**
	 * Constructor
	 */
	public PneumaticsCube() {
//		exampleDoubleSolenoid = new DoubleSolenoid(RobotMap.squeezeGrabber, RobotMap.releaseGrabber); //initializes
		intakeSolenoid = new Solenoid(RobotMap.intake);
		
		intakeSolenoid.set(false);
		intakeSolenoid.setPulseDuration(Constants.INTAKE_PULSE_TIME);		
//		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void initDefaultCommand() {
//		 setDefaultCommand(new PneumaticsManual());
    }
	  public void toggleIntake()
	    {
	    	if(intakeSolenoid.get() == true)
	    		intakeSolenoid.set(false);
	    	else
	    		intakeSolenoid.set(true);
	    	switch_intake();
	    }
	    
	    //Activate Butterfly Solenoid for a set duration
	    public void pulseIntake() {
	    	intakeSolenoid.startPulse();
	    }
	    public void switch_intake()
	    {
	    	intake_on = !intake_on;
	    }
	
//	public void setPiston(Value value) {
//		exampleDoubleSolenoid.set(value);	
//	}	
//	
//	public void reverse(){
//		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
//
//	}
//	
//	public void forward() {
//		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
//	}
	
	public void sendToDashboard() {

//		SmartDashboard.putBoolean("In Soleniod", true);

		if (Constants.DEBUG) {
		}
	}
}