package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.arm.JoystickMoveArm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Arm extends Subsystem {
	
	private WPI_TalonSRX 	armMotorMaster;
	private WPI_VictorSPX   armMotorSlave1;
	public 	DigitalInput 	armSensor;

	public double armDistancePerPulse;
    /**
      * Constructor
      */
	public Arm() {
		armMotorMaster = new WPI_TalonSRX(RobotMap.pivotArmMotorMaster);
		armMotorSlave1  = new WPI_VictorSPX(RobotMap.pivotArmMotorSlave1);
		armSensor 	= new DigitalInput(RobotMap.armSensor);

		armMotorSlave1.follow(armMotorMaster);

		armMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		armMotorMaster.setSelectedSensorPosition(Constants.ARM_ENC_STARTING_POSITION, 0, 0);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickMoveArm());
    }
	
	public void manualMove(double speed) {
		armMotorMaster.set(speed);
	}
	
	public void moveMotor(double speed) {
		armMotorMaster.set(speed);
	}
	
	public void stop(){
		armMotorMaster.stopMotor();
	}
    
   public boolean armDown() {
		if (!armSensor.get()) {
//			armMotorMaster.getSensorCollection().setQuadraturePosition(0, 0);	
			armMotorMaster.setSelectedSensorPosition(0, 0, 0);

			return (true);
	    }
		else {
			return (false);
		}
	}
   
	public double getAngle(){
//		return enc.get() * Constants.ARM_REVS_PER_TALON_REV; //Arm Angle = (# talon revs) * (arm revs/talon rev) 
//	   	return enc.getDistancePerPulse();
		
//		armMotorMaster.configSensorTerm
//		armMotorMaster.
		
		return getEncoderDistance();
	}
	
   public void setEncoderReading(int armlocation) {
//	   armMotorMaster.getSensorCollection().setPulseWidthPosition(armlocation, 10);
	   armMotorMaster.setSelectedSensorPosition(armlocation, 0, 10);
   }
   
   public double getEncoderDistance() {
       return armMotorMaster.getSensorCollection().getQuadraturePosition();
   }
   
   public void resetArmEncoder() {
		armMotorMaster.setSelectedSensorPosition(0, 0, 0);
   }
   
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Arm Down:", armDown());
    	SmartDashboard.putNumber("Arm Angle", getAngle());	// Encoder reading
//    	SmartDashboard.putNumber("Arm Distance", getEncoderDistance());	// Encoder reading
    }
    
    
}

