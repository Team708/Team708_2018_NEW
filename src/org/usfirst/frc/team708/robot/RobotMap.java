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
//	public static final int  				= 1;
//	public static final int 			 	= 2;
//	public static final int  				= 3;
	public static final int  	hoodAngle	= 4;
//	public static final int  				= 5;
//	public static final int  				= 6;
//	public static final int  				= 7;
//	public static final int  				= 8;
//	public static final int  				= 9;
	
	// Drivetrain CAN Device IDs
	public static final int drivetrainLeftMotorMaster	= 11;
	public static final int drivetrainLeftMotorSlave	= 12;
	public static final int drivetrainRightMotorMaster	= 13;
	public static final int drivetrainRightMotorSlave	= 14;
	
	// Climber CANDevice ID
	public static final int climberMotor				= 21;
	
	// Intake CAN Device IDs
	public static final int intakeMotorBall				= 31;
	public static final int intakeMotorGear				= 32;
	public static final int pivotGearMotor				= 33;

	// Grappler Grabber CAN Device IDs
	public static final int shooterMotorMaster			= 41;
	public static final int shooterMotorSlave			= 42;
	
	// Shooter CAN Device ID
	public static final int feederMotor					= 51;
		
//	public static final int loaderMotor					= 61;

	// Digital IO
	public static final int drivetrainEncoderARt		= 0;
	public static final int drivetrainEncoderBRt		= 1;
	public static final int drivetrainEncoderALeft		= 2;
	public static final int drivetrainEncoderBLeft		= 3;
//	public static final int shooterEncoderA				= 4;
//	public static final int shooterEncoderB				= 5;
	public static final int gearSensorSwitch			= 5;
//	public static final int 							= 7;
//	public static final int 					 		= 8;
//	public static final int 							= 9;
	
	// RELAY
//	public static final int 			 	= 1;
//	public static final int 			 	= 2;
//	public static final int 			 	= 3;
	
	//Analog sesnor IDs
	public static final int dtSonar				= 0;
	public static final int gearIRSensor		= 1;
//	public static final int 					= 2;
//	public static final int 					= 3;
	
	// PCM Ports
	public static final int PWR0				= 0;
	public static final int PWR1  				= 1;
	public static final int PWR2 				= 2;
	public static final int PWR3				= 3;
	public static final int GEARLIGHT			= 4;
	public static final int BOILERLIGHT			= 5;
//	public static final int	PWR6				= 6;
//	public static final int PWR7				= 7;

	
//  PDP Board Mappings
//	Climber		0
//	40A		Climber			0
//	40A		Shooter M		1
//	40A		Shooter S		2
//	40A						3
//	20/30A	Intake Ball		4
//	20/30A	Intake Gear		5
//	20/30A	Feeder			6
//	20/30A	Hopper			7
//	20/30A					8
//	20/30A					9
//	20/30A					10
//	20/30A					11
//	40A						12
//	40A						13
//	40A						14
//	40A						15

}
