package org.usfirst.frc.team708.robot;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Relay;

/**
 * Class containing all the code-related constants, so wiring and
 * gamepad controls are not included
 * @author omn0mn0m
 */
public final class Constants {

	/*
	 * Motor Controllers
	 */
	public static final double INTAKE_FORWARD 		= -1.0;
	public static final double INTAKE_REVERSE 		= 1.0;
	public static final double INTAKE_OFF 			= 0.0;
	
	public static final double MOTOR_FORWARD 		= 1.0;
	public static final double MOTOR_REVERSE 		= -1.0;
	public static final double MOTOR_OFF 			= 0.0;

	public static final double DRIVE_MOTOR_MAX_SPEED 		= 1.0;
	public static final double ROTATE_MOTOR_MAX_SPEED 		= 1.0;
	public static final double ARMPIVOT_MOTOR_MAX_SPEED 	= 1.0;
	public static final double VISION_ROTATE_MOTOR_SPEED	= 0.8;
	
	public static final double LOADER_MOTOR_FORWARD 		=  1.0;
	public static final double LOADER_MOTOR_REVERSE 		=  -1.0;
	public static final double LOADER_OFF 					= 0.0;
	
	public static final double SHOOTER_MOTOR_SPEED_LOW 		=  5200;
	public static final double SHOOTER_MOTOR_SPEED_HIGH 	=  5200;
	public static final double SHOOTER_F_HIGH 		=  .17126;
	public static final double SHOOTER_F_LOW 		=  .2398;
	
	public static final double SHOOTER_MOTOR_POWER_FORWARD_HIGH 	=  0.8;
	public static final double SHOOTER_MOTOR_POWER_FORWARD_LOW 		=  0.8;
	
	public static final double SHOOTER_MOTOR_FORWARD 		=  	0.8;
	public static final double SHOOTER_MOTOR_BACKWARD 		=  -1.0;

	
	/*
	 * Smart Dashboard
	 */
	public static final double SEND_STATS_INTERVAL	= .5;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= false;
	

	/*
	 * Sensors
	 */
	public static final double SONAR_CLOSE 								= 30.0;
	public static final double SONAR_FAR 								= 80.0;
	public static final double IR_HAS_BALL_DISTANCE 					= 4.0;
	public static final double ENCODER_BOTTOM_POSITION 					= 0.0;
	public static final double GRAYHILL_ENCODER_PULSES_PER_REVOLUTION 	= 128.0;
	
	/*
	 * Game Elements
	 */	
//	public static final double PORTCULLIS_HEIGHT = 10.5;
	
	
	/*
	 * PIVOT ARM -- moves arm up and down
	 */
	public static final double PIVOT_ARM_SPROCKET_DIAMETER 	= 1.8;
	public static final double PIVOT_UP 					= 1.0;
	public static final double PIVOT_DOWN 					= 0.25;
	
	
	/*
	 * GRAPPLER -- extends arm (telescoping)
	 */
	public static final double GRAPPLER_MOTOR_MINIMUM 		= 1.0;
	public static final double GRAPPLER_SPROCKET_DIAMETER 	= 1.4;

	
	public static final double[] GRAPPLER_STOPS_DEGREES = {
		0.0,
		60.0,
		95.0
	};
	
	
	/*
	 * Drivetrain
	 */
	public static final double 	TANK_STICK_TOLERANCE 				= .30;
	public static final double 	DRIVETRAIN_WHEEL_DIAMETER 			= 4.0;
	public static final double 	DRIVETRAIN_ENCODER_PULSES_PER_REV 	= 128.0;
	public static final boolean DRIVE_USE_SQUARED_INPUT 			= false;
	public static final boolean DRIVETRAIN_USE_LEFT_ENCODER			= true; // variable to determine which side encoder is on
	
	
	// PID Tuning parameters
	public static final double Kp = 0.0;		// Proportional gain
	public static final double Ki = 0.0;		// Integral gain
	public static final double Kd = 0.0;		// Derivative gain

	public static final double KpForward = 0.1;
	public static final double KiForward = 0.02;
	public static final double KdForward = 0.005;

	public static final double KpBackward = 0.1;
	public static final double KiBackward = 0.02;
	public static final double KdBackward = 0.005;
	
	public static final double pid_tolerance = 1;
	
	
	/*
	 * Vision Processor
	 */
	// public static final double ;
	
}
