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
	//These "heights" will be the angles that can reach the specified heights in the comments below
    public static final double SCALE_HEIGHT = 78.0; //78 inches (arm telescopes out to here)	
    public static final double SWITCH_HEIGHT = 21.0; //21 inches (robot throws cube up to this height, so this constant is our angular trajectory)
    public static final double FEEDER_STATION_HEIGHT = 27.0; //27 inches (trajectory)
    public static final double GROUND_HEIGHT = 0.0;  //0 inches (keep as is)
    
    
	/*
	 * PIVOT ARM -- moves arm up and down
	 */
	public static final double PIVOT_ARM_SPROCKET_DIAMETER 	= 1.8;
	public static final double PIVOT_UP 					= 1.0;
	public static final double PIVOT_DOWN 					= 0.25;
	public static final double ARM_REVS_PER_TALON_REV = 0.0; // Will be used to find pivot angle from encoder count
	
	
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
