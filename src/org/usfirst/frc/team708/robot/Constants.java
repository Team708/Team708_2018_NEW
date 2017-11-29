package org.usfirst.frc.team708.robot;


//GEAR Camera is 20.5" from the floor
//Boiler camera is 33.75"  from the floor


public final class Constants {

	/*
	 * Motor Controllers
	 */
	public static final double INTAKE_FORWARD 		=  1.0;
	public static final double INTAKE_REVERSE 		=  -1.0;
	public static final double INTAKE_OFF 			=  0.0;
	
	public static final double CLIMB_FORWARD 		=  1.0;
	public static final double CLIMB_REVERSE 		= -1.0;
	public static final double CLIMB_OFF 			=  0.0;
	
	public static final double GEAR_UP 				=  0.4;
	public static final double GEAR_DOWN	 		=  -0.4;
	public static final double GEAR_OFF 			=  0.0;
	public static final double GEAR_IN 				=  1.0;
	public static final double GEAR_OUT 			=  -1.0;

	
	public static final int PIVOT_GEAR_ENCODER_COUNT	=  1024;
	public static final int PIVOT_GEAR_ENCODER_HIGH		=  0;  //20; //35;
	public static final int PIVOT_GEAR_ENCODER_LOW		=  -35;   //-19; // 5;


	public static final double MOTOR_FORWARD 		=  1.0;
	public static final double MOTOR_REVERSE 		= -1.0;
	public static final double MOTOR_OFF 			=  0.0;

	public static final double DRIVE_MOTOR_MAX_SPEED 		= 1.0;
	public static final double ROTATE_MOTOR_MAX_SPEED 		= 1.0;
 
	public static final double VISION_ROTATE_MOTOR_SPEED		= 0.8;
	
	public static final double FEEDER_MOTOR_FORWARD 		=   0.5;    //1.0;
	public static final double FEEDER_MOTOR_REVERSE 		=  -0.5;    //-1.0;
	public static final double FEEDER_OFF 					=   0.0;	
	
//	public static final double LOADER_MOTOR_FORWARD 		=  1.0;
//	public static final double LOADER_MOTOR_REVERSE 		= -1.0;
//	public static final double LOADER_OFF 					=  0.0;	
//	
	public static final double SHOOTER_MOTOR_FORWARD 		=  1.0;
	public static final double SHOOTER_MOTOR_OFF			=  0.0;
	public static final double SHOOTER_MOTOR_BACKWARD 		=  -1.0;
	
	public static final int SHOOTER_MOTOR_SPEED_BOILER 		=  2050;
	public static final int SHOOTER_MOTOR_SPEED_LEVER 		=  2700;

//	public static final int SHOOTER_MOTOR_SPEED_MID 		=  2700;
	public static final int SHOOTER_CLOSE_SHOT				=  20; //encoder value for bumper
	

	public static final double 	SHOOTER_PEAK_POS 			=  12.0;
	public static final double 	SHOOTER_PEAK_NEG			= -12.0;
	
	public static final int 	HOOD_MIN 				=  30;
	public static final int		HOOD_MAX 				=  2050;	
	public static final int 	HOOD_BOILER 			=  400;
	public static final int 	HOOD_MID	 			=  300;
	public static final int 	HOOD_LEVER				=  30 ; //330;

	public static final int 	SHOOTER_ENCODER_PULSES	 	= 12;
	
	public static final double 	SHOOTER_P 					=  40.0;//1.5;
	public static final double	SHOOTER_I 					=  0.0;//0.005;
	public static final double 	SHOOTER_D 					=  0.0;//10.0;
	public static final double 	SHOOTER_F 					=  2.3;
	public static final int 	SHOOTER_IZONE 				=  00;
	public static final double 	SHOOTER_RAMPRATE 			=  0.0;
	public static final int		SHOOTER_PROFILE 			=  0;
	
	public static final int 	HOOD_CALIBRATION 			=  10;	

	public static final double 	AXIS_DEAD_ZONE	 			=  0.3;

	/*
	 * Smart Dashboard
	 */
	public static final double  SEND_STATS_INTERVAL			= .1;	// Interval for reporting in seconds
	public static final boolean DEBUG 						= true;
	public static final boolean LIFT_DEBUG 					= true;
	public static final boolean BOILER_DEBUG 				= true;
	public static final boolean GEAR_DEBUG 					= false;
	public static final boolean LIFT_CAMERA_OUTPUT_DEBUG 	= false;
	public static final boolean BOILER_CAMERA_OUTPUT_DEBUG	= false;

	/*
	 * Sensors
	 */
	public static final double SONAR_CLOSE 						= 30.0;
	public static final double SONAR_FAR 						= 80.0;
	public static final double IR_AT_LEVERDISTANCE	 			= 4.0;
	public static final double ENCODER_BOTTOM_POSITION 			= 0.0;

//	public static final double GRAYHILL_ENCODER_PULSES_PER_REVOLUTION 	= 1024.0;
	
	
	/*
	 * Drivetrain
	 */
	public static final double 	TANK_STICK_TOLERANCE 				= .30;
	public static final double  DRIVETRAIN_WHEEL_DIAMETER 			= 4.0;
	public static final int 	DRIVETRAIN_ENCODER_PULSES_PER_REV 	= 5704; //encoder 1024 * gear ratio 5.57
	public static final boolean DRIVE_USE_SQUARED_INPUT 			= false;
	public static final boolean DRIVETRAIN_USE_LEFT_ENCODER			= true; // variable to determine which side encoder is on
	public static final double 	PEAK_POS 							= 4.0;
	public static final double 	PEAK_NEG							= -4.0;
	public static final double 	NOMINAL_POS			 				= 0.0;
	public static final double 	NOMINAL_NEG							= -0.0;

	
// PID Tuning parameters
		public static final double Kp = 0.0;		// Proportional gain
		public static final double Ki = 0.0;		// Integral gain
		public static final double Kd = 0.0;		// Derivative gain	
		public static final double pid_tolerance = 1;
	
	
	 public static final int CLOCKWISE = 1;
	 public static final int COUNTERCLOCKWISE = -1;
	 
	// LED CONTROLS
	
	public static final byte SET_ALLIANCE_INVALID 		= 	0x00; // blinky white
	public static final byte SET_ALLIANCE_RED 	 		=	0x01; // knight rider red
	public static final byte SET_ALLIANCE_BLUE  		=	0x02; // knight rider blue
	public static final byte SET_TARGETING  		 	=	0x03; // blinky red J
	public static final byte SET_TARGET_FOUND  	 		=	0x04; // solid red
	public static final byte SET_HAS_GEAR	  	 		=	0x05; // solid green
	public static final byte SET_HAS_GEAR_TARGETING		=	0x06; //  ????  blank?? blinky green
//	public static final byte 	  	 					=	0x07;
//	public static final byte 		  					=	0x08;
	public static final byte SET_OFF		  			=	0x09; // off
	public static final byte MAX_LED_CODE				=	0x10;	
	
	public static final int ALLIANCE_RED 	 	=	1;
	public static final int ALLIANCE_BLUE 	 	=	-1;
	
}
