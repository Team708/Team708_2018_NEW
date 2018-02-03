package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.arm.JoystickMoveArm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

//import org.team708.robot.commands.arm.JoystickMoveArm;
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Value;
//import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Arm extends Subsystem {
	
	private WPI_TalonSRX armMotorMaster, armMotorSlave1;
	private SpeedControllerGroup armMotors;

	private Encoder enc; //RESET IN AUTO TO CALIBRATE BEFORE EACH MATCH

    /**
      * Constructor
      */
	public Arm() {
		armMotorMaster = new WPI_TalonSRX(RobotMap.pivotArmMotorMaster);
		armMotorSlave1  = new WPI_TalonSRX(RobotMap.pivotArmMotorSlave1);
	 
		 armMotors = new SpeedControllerGroup(armMotorMaster, armMotorSlave1);

		 enc = new Encoder(4, 5, false, Encoder.EncodingType.k4X);

	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickMoveArm());
    }
	
	public void manualMove(double speed) {
		armMotors.set(speed);
	}
	
	public void moveMotor(double speed) {
		armMotors.set(speed);
    	SmartDashboard.putNumber("In Move Motor speed=", speed);
	}
	
	public void stop(){
//		armMotors.set(Constants.INTAKE_OFF);
		armMotors.stopMotor();
    	SmartDashboard.putNumber("In Stop Motor speed=", 0.0);
	}
    
	public double getAngle(){
		return enc.get() * Constants.ARM_REVS_PER_TALON_REV; //Arm Angle = (# talon revs) * (arm revs/talon rev) 
	}

    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
    
    
}

