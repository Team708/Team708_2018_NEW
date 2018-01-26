package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import org.team708.robot.commands.arm.JoystickMoveArm;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Arm extends Subsystem {
	
	private WPI_TalonSRX armMotorMaster, armMotorSlave1;
	
    /**
      * Constructor
      */
	public Arm() {
		armMotorMaster = new WPI_TalonSRX(RobotMap.pivotArmMotorMaster);
		armMotorSlave1  = new WPI_TalonSRX(RobotMap.pivotArmMotorSlave1);
	 
		SpeedControllerGroup armMotors = new SpeedControllerGroup(armMotorMaster, armMotorSlave1);

	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
    }
	
	public void moveMotor(double speed) {
		armMotors.set(speed);
    	SmartDashboard.putNumber("In Move Motor speed=", speed);
	}
	
	public void stop(){
		armMotors.set(Constants.INTAKE_OFF);
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

