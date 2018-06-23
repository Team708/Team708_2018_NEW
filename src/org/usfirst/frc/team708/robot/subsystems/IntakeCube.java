package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
	
	private WPI_TalonSRX 	intakeMaster;
	private WPI_VictorSPX   intakeSlave;
	
	public DigitalInput 	cubeSensor;

	public IntakeCube() {
		intakeMaster = new WPI_TalonSRX(RobotMap.intakeMotorMaster);
		intakeSlave  = new WPI_VictorSPX(RobotMap.intakeMotorSlave);
		
		/* Peak Current and Duration must be exceeded before current limit is activated.
		 * When activated, current will be limited to Continuous Current.
		 * Set Peak Current params to 0 if desired behavior is to immediately current-limit. 
		 * (10 ms timeout)*/
		intakeMaster.configPeakCurrentLimit(25, 10); /* 45 A */
		intakeMaster.configPeakCurrentDuration(200, 10); /* 200ms */
		intakeMaster.configContinuousCurrentLimit(20, 10); /* 40A */
		intakeMaster.enableCurrentLimit(true); /* turn it on */
		
		cubeSensor 	= new DigitalInput(RobotMap.cubeSensor);
		
		intakeSlave.follow(intakeMaster);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
    }
	
	public void moveMotor(double speed) {
		intakeMaster.set(speed);
    	SmartDashboard.putNumber("In Move Motor speed=", speed);
	}
	
	public void stopMotor() {
		intakeMaster.set(0.0);
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
		intakeMaster.set(Constants.INTAKE_OFF);
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