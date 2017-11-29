package org.usfirst.frc.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.shooter.*;
import org.usfirst.frc.team708.robot.commands.feeder.*;
import org.usfirst.frc.team708.robot.commands.led_out.*;
import org.usfirst.frc.team708.robot.commands.intake_ball.*;
import org.usfirst.frc.team708.robot.commands.intake_gear.*;
import org.usfirst.frc.team708.robot.commands.Climber.*;

import org.usfirst.frc.team708.robot.commands.visionProcessor.*;

import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	// Gamepads
	public final static Gamepad driverGamepad 	= new Gamepad(RobotMap.driverGamepad);	// Driver gamepad
	public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);// Operator gamepad
	
	// look in Gamepad.java for button constants
	
	/*
	 * Driver Button Assignment
	 */
	
	private static final int INTAKE_BALL_IN				= Gamepad.button_R_Shoulder;
	private static final int INTAKE_BALL_OUT			= Gamepad.shoulderAxisRight;
	
	public static final int LED_BUTTON 					= Gamepad.button_X;
	public static final int BRAKE_BUTTON 				= Gamepad.button_B;
	public static final int RELEASE_GEAR_BUTTON 		= Gamepad.button_A;

	
	/*
	 * Operator Button Assignment
	 */
	private static final int SPIN_SHOOTER_BUTTON		= Gamepad.button_L_Shoulder;
	private static final int SPIN_SHOOTER_BACK_BUTTON	= Gamepad.shoulderAxisLeft;
	private static final int SPIN_FEEDER_BUTTON			= Gamepad.button_R_Shoulder;
	private static final int SPIN_FEEDER_BACK_BUTTON	= Gamepad.shoulderAxisRight;
	
//	private static final int OPERATE_HOOD				= Gamepad.rightStick_Y;
	private static final int OPERATE_GEAR_PIVOT			= Gamepad.leftStick_Y;
	private static final int OPERATE_GEAR_INTAKE		= Gamepad.leftStick_X;

	public static final int HOOD_HIGH		 			= Gamepad.button_A;
	public static final int HOOD_LOW	 				= Gamepad.button_B;

	private static final int CLIMB_DOWN					= Gamepad.button_X;	
	private static final int CLIMB_UP					= Gamepad.button_Y;	
	
	private static final int VISION_TRIGGER				= Gamepad.button_Start;

	
// Driver Button Commands
	
	public static final Button  intakeBallIn 	= new JoystickButton(driverGamepad, INTAKE_BALL_IN);
	public static final Trigger intakeBallOut	= new AxisUp(driverGamepad, INTAKE_BALL_OUT);
	public static final Button 	led				= new JoystickButton(driverGamepad, LED_BUTTON);
	public static final Button 	brake			= new JoystickButton(driverGamepad, BRAKE_BUTTON);
	public static final Button 	releaseGear		= new JoystickButton(driverGamepad, RELEASE_GEAR_BUTTON);

// Operator Button Commands
	
	public static final Button  spinShooter		= new JoystickButton(operatorGamepad, SPIN_SHOOTER_BUTTON);
	public static final Trigger spinShooterBack	= new AxisUp(operatorGamepad, SPIN_SHOOTER_BACK_BUTTON);
	public static final Button  spinFeeder		= new JoystickButton(operatorGamepad, SPIN_FEEDER_BUTTON);
	public static final Trigger spinFeederBack	= new AxisUp(operatorGamepad, SPIN_FEEDER_BACK_BUTTON);
//	public static final Button  loaderSpin		= new JoystickButton(operatorGamepad, LOADER_SPIN);
//	public static final Button  loaderOff		= new JoystickButton(operatorGamepad, LOADER_STOP);
	public static final Button  climbUp 		= new JoystickButton(operatorGamepad, CLIMB_UP);
	public static final Button  climbDown 		= new JoystickButton(operatorGamepad, CLIMB_DOWN);

	public static final Button  hoodHigh		= new JoystickButton(operatorGamepad, HOOD_HIGH);
	public static final Button  hoodLow			= new JoystickButton(operatorGamepad, HOOD_LOW);
//	public static final Trigger hoodAdjust		= new AxisUp(operatorGamepad, OPERATE_HOOD);
//	public static final Trigger hoodAdjustDown	= new AxisDown(operatorGamepad, OPERATE_HOOD);
	public static final Trigger gearUp			= new AxisUp(operatorGamepad, OPERATE_GEAR_PIVOT);
	public static final Trigger gearDown		= new AxisDown(operatorGamepad, OPERATE_GEAR_PIVOT);
	public static final Trigger gearIn			= new AxisUp(operatorGamepad, OPERATE_GEAR_INTAKE);
	public static final Trigger gearOut			= new AxisDown(operatorGamepad, OPERATE_GEAR_INTAKE);

	public static final Button  visionTrigger	= new JoystickButton(operatorGamepad, VISION_TRIGGER);

	/**
	 * Constructor
	 * Assigns commands to be called when each button is pressed.
	 */
	
	public OI() {

//	Driver
//		intakeBallIn.whileHeld(new Intake_Ball_In());
//		intakeBallOut.whileActive(new Intake_Ball_Out());
		intakeBallIn.whileHeld(new ManualIntake_Ball());
		intakeBallOut.whileActive(new ManualIntake_Ball());
		releaseGear.whenPressed(new ReleaseGear());

		
//	Operator		
		spinShooter.whileHeld(new ManualShoot());
		spinShooter.whenReleased(new StopShooter());
		spinShooterBack.whileActive(new SpinShooterBack());

		spinFeeder.whileHeld(new ManualFeeder());
		spinFeederBack.whileActive(new SpinFeederBack());
		
		hoodHigh.whenPressed(new MoveHoodHigh());
		hoodLow.whenPressed(new MoveHoodLow());
//		hoodAdjust.whileActive(new HoodAdjust());
//		hoodAdjustDown.whileActive(new HoodAdjust());
		
		led.whenPressed(new LED_out());
		brake.whenPressed(new ToggleBrakeMode());

		gearUp.whileActive(new GearAdjust());
		gearDown.whileActive(new GearAdjust());
//		gearIn.whileActive(new GearAdjust());
//		gearOut.whileActive(new GearAdjust());
		gearIn.whileActive(new GearIntake());
		gearOut.whileActive(new GearIntake());

		climbUp.whileActive(new ClimbUp());
		climbDown.whileActive(new ClimbDown());
		}
}

