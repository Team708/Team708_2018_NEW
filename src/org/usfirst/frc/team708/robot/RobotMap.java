package org.usfirst.frc.team708.robot;

//import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author omn0mn0m
 */
public class RobotMap {
	
	// Gamepad USB ports
	public static final int driverGamepad   = 1;
	public static final int operatorGamepad = 2;
	
	// PWM Ports
//	public static final int 			 	= 0;
	public static final int INTAKE_MOTOR 	= 1;
//	public static final int SHOOTER_PWM	 	= 2;
//	public static final int  				= 3;
//	public static final int  				= 4;
//	public static final int  				= 5;
//	public static final int  				= 6;
//	public static final int  				= 7;
//	public static final int  				= 8;
//	public static final int  				= 9;
	
	// Drivetrain CAN Device IDs
	public static final int drivetrainLeftMotorMaster  = 11;
	public static final int drivetrainLeftMotorSlave   = 12;
	public static final int drivetrainRightMotorMaster = 13;
	public static final int drivetrainRightMotorSlave  = 14;
	

	public static final int loaderMotor	 	= 21;
	
	// Pivot Arm CAN Device ID
	public static final int pivotArmMotor	= 31;
	
	// Grappler Grabber CAN Device IDs
	public static final int grapplerMotorMaster	= 41;
	public static final int grapplerMotorSlave 	= 42;
	
	// Shooter CAN Device ID
	public static final int shooterMotor	= 51;
	
	// Digital IO
	public static final int drivetrainEncoderARt		= 0;
	public static final int drivetrainEncoderBRt		= 1;
	public static final int drivetrainEncoderALeft		= 2;
	public static final int drivetrainEncoderBLeft		= 3;
	public static final int shooterEncoderA				= 4;
	public static final int shooterEncoderB				= 5;
	public static final int pivotArmUpperSwitch			= 6;
	public static final int pivotArmLowerSwitch			= 7;
	public static final int grapplerUpperSwitch 		= 8;
	public static final int grapplerLowerSwitch			= 9;
	
	// RELAY
//	public static final int 			 	= 1;
//	public static final int 			 	= 2;
//	public static final int 			 	= 3;
	
	//Analog sesnor IDs
	public static final int DTSonar				= 1;
	public static final int LoaderIRSensor		= 2;
	public static final int armPotentiometer	= 0;
	
	// PCM Ports
//	public static final int 				= 0;
//	public static final int   				= 1;
//	public static final int  				= 2;
//	public static final int 				= 3;
//	public static final int 				= 4;
//	public static final int 				= 5;
//	public static final int					= 6;
//	public static final int 				= 7;
	
}
