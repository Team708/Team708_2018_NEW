package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.telescope.JoystickMoveTele;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telescope extends Subsystem {
	
	private WPI_TalonSRX 	teleMotorMaster;
	private WPI_VictorSPX	teleMotorSlave1, teleMotorSlave2;
	public 	DigitalInput 	teleSensor;

	public double teleDistancePerPulse;
    
	public Telescope() {
		teleMotorMaster  = new WPI_TalonSRX(RobotMap.telescopingMotorMaster);
		teleMotorSlave1  = new WPI_VictorSPX(RobotMap.telescopingMotorSlave1);
		teleMotorSlave2  = new WPI_VictorSPX(RobotMap.telescopingMotorSlave2);
		
		/* Peak Current and Duration must be exceeded before current limit is activated.
		 * When activated, current will be limited to Continuous Current.
		 * Set Peak Current params to 0 if desired behavior is to immediately current-limit. 
		 * (10 ms timeout)*/
//		teleMotorMaster.configPeakCurrentLimit(45, 10); /* 45 A */
//		teleMotorMaster.configPeakCurrentDuration(200, 10); /* 200ms */
//		teleMotorMaster.configContinuousCurrentLimit(40, 10); /* 40A */
//		teleMotorMaster.enableCurrentLimit(true); /* turn it on */

		teleSensor 	= new DigitalInput(RobotMap.TelescopeSensor);

		teleMotorSlave1.follow(teleMotorMaster);
		teleMotorSlave2.follow(teleMotorMaster);

		teleMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		teleMotorMaster.setSelectedSensorPosition(Constants.TELE_ENC_STARTING_POSITION, 0, 0);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickMoveTele());
    }
	
	public void manualMove(double speed) {
		teleMotorMaster.set(speed);
	}
	
	public void moveMotor(double speed) {
		teleMotorMaster.set(speed);
	}
	
	public void stop(){
		teleMotorMaster.stopMotor();
	}
    
   public boolean telescopeDown() {
		if (!teleSensor.get()) {
			teleMotorMaster.setSelectedSensorPosition(0, 0, 0);
			return (true);
	    }
		else {
			return (false);
		}
	}
   
	public double getAngle() {		
		return getEncoderDistance();
	}
	
   public void setEncoderReading(int telelocation) {
	   teleMotorMaster.setSelectedSensorPosition(telelocation, 0, 10);
   }
   
   public double getEncoderDistance() {
       return -teleMotorMaster.getSensorCollection().getQuadraturePosition();
   }
   
   public void resetTeleEncoder() {
	   teleMotorMaster.setSelectedSensorPosition(0, 0, 0);
   }
   
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Tele Down", telescopeDown());
    	SmartDashboard.putNumber("Tele length", getAngle());	// Encoder reading
    }
    
    
}

