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
	public static final double INTAKE_FORWARD 		= 1.0;
	public static final double INTAKE_REVERSE 		= -1.0;
	public static final double ARM_FORWARD 		= 1.0;
	public static final double ARM_REVERSE 		= -1.0;
	public static final double INTAKE_OFF 			= 0.0;
	
	public static final double DRIVE_MOTOR_MAX_SPEED 		= 1.0;
	public static final double ROTATE_MOTOR_MAX_SPEED 		= 1.0;
	public static final double ARMPIVOT_MOTOR_MAX_SPEED 	= 1.0;
	public static final double VISION_ROTATE_MOTOR_SPEED	= 0.8;
	public static final double DRIVE_MOTOR_OFF		 		= 0.0;
	
	public static final double VISION_LED_ON				= 0.0;
	public static final double VISION_LED_OFF				= 1.0;
	public static final double VISION_LED_BLINK				= 2.0;
	public static final double VISION_PROCESSING_ON			= 0.0;

	public static final double VISION_PROCESSING_OFF	= 1.0;
	public static final double VISION_TARGET_NOT_FOUND	= 0.0;
	public static final double VISION_TARGET_FOUND		= 1.0;
	public static final boolean VISION_CODER_HAPPY		= false;
	public static final boolean VISION_CODER_ANNOYED	= true;

	/*
	 * Smart Dashboard
	 */
	public static final double SEND_STATS_INTERVAL	= .5;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= false;
	

	/*
	 * Sensors
	 */

	public static final double GRAYHILL_ENCODER_PULSES_PER_REVOLUTION 	= 128.0;
	public static final double ENCODER_BOTTOM_POSITION 					= 0.0;
	/*
	 * Game Elements
	 */	
    public static final double SCALE_HEIGHT = 0.0;	
    public static final double SWITCH_HEIGHT = 0.0;
    public static final double FEEDER_STATION_HEIGHT = 0.0;
    public static final double GROUND_HEIGHT = 0.0;
    
    
	/*
	 * PIVOT ARM -- moves arm up and down
	 */
	public static final double PIVOT_ARM_SPROCKET_DIAMETER 	= 1.8;
	public static final double PIVOT_UP 					= 1.0;
	public static final double PIVOT_DOWN 					= 0.25;
	
	
	/*
	 * telescoping
	 */
	public static final double TELESCOPING_MOTOR_MIN_SPEED 	= .8;
	public static final double TELESCOPING_MOTOR_MAX_SPEED 	= 1.0;

	
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
