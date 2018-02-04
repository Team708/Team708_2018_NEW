package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telescope extends Subsystem {
	
	private WPI_TalonSRX 	teleMotorMaster, teleMotorSlave1, teleMotorSlave2, teleMotorSlave3;
	public 	DigitalInput 	teleSensor;

	public double teleDistancePerPulse;
    
	public Telescope() {
		teleMotorMaster  = new WPI_TalonSRX(RobotMap.telescopingMotorMaster);
		teleMotorSlave1  = new WPI_TalonSRX(RobotMap.telescopingMotorSlave1);
		teleMotorSlave2  = new WPI_TalonSRX(RobotMap.telescopingMotorSlave2);
		teleMotorSlave3  = new WPI_TalonSRX(RobotMap.telescopingMotorSlave3);

		teleSensor 	= new DigitalInput(RobotMap.TelescopeSensor);

		teleMotorSlave1.follow(teleMotorMaster);
		teleMotorSlave2.follow(teleMotorMaster);
		teleMotorSlave3.follow(teleMotorMaster);

		teleMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		teleMotorMaster.setSelectedSensorPosition(Constants.TELE_ENC_STARTING_POSITION, 0, 0);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new JoystickMoveArm());
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
       return teleMotorMaster.getSensorCollection().getQuadraturePosition();
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
    	SmartDashboard.putNumber("Tele Angle", getAngle());	// Encoder reading
    }
    
    
}

