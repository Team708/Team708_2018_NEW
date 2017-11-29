package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.shooter.ManualShoot;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.visionProcessor.SonarOverride;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	// Put methods for controlling this subsystem here. Call these
	// from Commands.
	
	private CANTalon 	shooter, shooterSlave;	// Motor Controllers
	private Servo	 	hood;
	private int	 		hoodLocation;
	private int			spinSpeed;

	/**
	 * Constructor
	 */
	public Shooter() {
		// Initializes the encoder
        
		
		// Initializes the motor

		shooter      = new CANTalon(RobotMap.shooterMotorMaster);
        shooterSlave = new CANTalon(RobotMap.shooterMotorSlave);

        shooterSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        shooterSlave.set(shooter.getDeviceID());

//      shooter.reset();
		shooter.enable();

		shooter.reverseSensor(true);
    	shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);    
//    	shooter.changeControlMode(TalonControlMode.PercentVbus);
    	shooter.changeControlMode(TalonControlMode.Speed);

    	shooter.configNominalOutputVoltage(Constants.NOMINAL_POS, Constants.NOMINAL_NEG);
    	shooter.configPeakOutputVoltage(Constants.SHOOTER_PEAK_POS, Constants.SHOOTER_PEAK_NEG);
    	shooter.configEncoderCodesPerRev(Constants.SHOOTER_ENCODER_PULSES);
    	shooter.setPID(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, Constants.SHOOTER_F, Constants.SHOOTER_IZONE, Constants.SHOOTER_RAMPRATE, Constants.SHOOTER_PROFILE);
    	
    	hood = new Servo(RobotMap.hoodAngle);
    	hoodLocation = Constants.HOOD_MIN;
    	spinSpeed = 0;
	}

	public void initDefaultCommand() {

    }
	
	public void manualSpeed(double speed) {
		shooter.changeControlMode(TalonControlMode.PercentVbus);
		shooter.set(speed);
}
	
	public void manualRPM(double rpm) {
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(rpm);
}
	
//	public void setFgain(double F){
//		shooter.setF(F);
//	}
	
	public void setSpinSpeed(int speed)
	{
		spinSpeed = speed;
	}
	
	public int getSpinSpeed()
	{
		return(spinSpeed);
	}
	
	public void stop(){
		shooter.set(Constants.MOTOR_OFF);
}
	
	public void moveHood(int angle) {
		hoodLocation = angle;
        hood.setRaw(angle);		
		SmartDashboard.putNumber("Move Hood", hoodLocation);
	}
	
	public void hoodAdjust(double angle) {

		if ((angle > 0.0) && (hoodLocation<Constants.HOOD_MAX)) hoodLocation+=Constants.HOOD_CALIBRATION;
		else if ((angle < 0.0) && (hoodLocation>Constants.HOOD_MIN)) hoodLocation-=Constants.HOOD_CALIBRATION;
			
		SmartDashboard.putNumber("Servo Location", 		hoodLocation);
		moveHood(hoodLocation);
	}
	/**
	 * Sends data to the Smart Dashboard
	 */
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
//		SmartDashboard.putNumber("Encoder Position", 	shooter.getEncPosition());
		SmartDashboard.putNumber("Encoder Speed", 		shooter.getSpeed());
//		SmartDashboard.putNumber("Encoder Velocity", 	shooter.getEncVelocity());

	}
}

