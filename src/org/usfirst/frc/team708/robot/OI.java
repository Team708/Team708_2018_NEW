

package org.usfirst.frc.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.intakeCube.*;
import org.usfirst.frc.team708.robot.commands.arm.*;
import org.usfirst.frc.team708.robot.commands.telescope.*;
import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.commands.autonomous.*;


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

// Drivetrain Buttons
	private static final int BUTTERFLY_BUTTON 				= Gamepad.button_Y;
	private static final int HIGH_GEAR_BUTTON   		    = Gamepad.button_R_Shoulder;
	private static final int LOW_GEAR_BUTTON				= Gamepad.button_L_Shoulder;
	private static final int BRAKE_BUTTON					= Gamepad.button_B;
/*
 * Operator Button Assignment
 */
	private static final int RELEASE_CUBE_BUTTON			= Gamepad.button_R_Shoulder;
	private static final int SQUEEZE_CUBE_BUTTON			= Gamepad.button_L_Shoulder;
	private static final int INTAKE_CUBE_BUTTON				= Gamepad.rightStick_X;
	private static final int OPERATE_ARM_BUTTON				= Gamepad.leftStick_X;
	private static final int OPERATE_TELESCOPE_BUTTON		= Gamepad.leftStick_Y;

	private static final int ARM_UP_TO_GROUND_BUTTON		= Gamepad.button_A;
	private static final int ARM_UP_TO_SWITCH_BUTTON		= Gamepad.button_X;
	private static final int ARM_UP_TO_SCALE_BUTTON			= Gamepad.button_B;
	private static final int ARM_UP_TO_HUMAN_FEEDER_BUTTON	= Gamepad.button_Y;
	private static final int CLIMB_LOW_GEAR_BUTTON			= Gamepad.shoulderAxisRight;
	private static final int CLIMB_HIGH_GEAR_BUTTON			= Gamepad.shoulderAxisLeft;


	
	
/*
 * Driver Button Commands
 */
	public static final Button butterflyOn	= new JoystickButton(driverGamepad, BUTTERFLY_BUTTON);
	public static final Button highGearOn	= new JoystickButton(driverGamepad, HIGH_GEAR_BUTTON);
	public static final Button lowGearOn 	= new JoystickButton(driverGamepad, LOW_GEAR_BUTTON);
	public static final Button breakOn	 	= new JoystickButton(driverGamepad, BRAKE_BUTTON);

/*
 * Operator Button Commands
 */
	public static final Button releaseCube		= new JoystickButton(operatorGamepad, RELEASE_CUBE_BUTTON);
	public static final Button squeezeCube		= new JoystickButton(operatorGamepad, SQUEEZE_CUBE_BUTTON);
	public static final Trigger intakeCubeOut	= new AxisUp(operatorGamepad, INTAKE_CUBE_BUTTON);
	public static final Trigger intakeCubeIn	= new AxisDown(operatorGamepad, INTAKE_CUBE_BUTTON);
	
	public static final Trigger operateArmUp 	= new AxisUp(operatorGamepad, OPERATE_ARM_BUTTON);
	public static final Trigger operateArmDown	= new AxisDown(operatorGamepad, OPERATE_ARM_BUTTON);
	public static final Trigger operateTeleUp	= new AxisUp(operatorGamepad, OPERATE_TELESCOPE_BUTTON);
	public static final Trigger operateTeleDown	= new AxisDown(operatorGamepad, OPERATE_TELESCOPE_BUTTON);
	public static final Button armToGround		= new JoystickButton(operatorGamepad, ARM_UP_TO_GROUND_BUTTON);
	public static final Button armToSwitch		= new JoystickButton(operatorGamepad, ARM_UP_TO_SWITCH_BUTTON);
	public static final Button armToScale		= new JoystickButton(operatorGamepad, ARM_UP_TO_SCALE_BUTTON);
	public static final Button armToFeeder		= new JoystickButton(operatorGamepad, ARM_UP_TO_HUMAN_FEEDER_BUTTON);
	public static final Trigger climbLowGear	= new AxisUp(operatorGamepad, CLIMB_LOW_GEAR_BUTTON);
	public static final Trigger climbHighGear	= new AxisUp(operatorGamepad, CLIMB_HIGH_GEAR_BUTTON);


	public OI() {

		butterflyOn.whenPressed(new ActivateButterfly());
		
		highGearOn.whenPressed(new GearShift2());
		lowGearOn.whenPressed(new GearShift1());

		breakOn.whenPressed(new ToggleBrakeMode());
		
		releaseCube.whenPressed(new ReleaseCube());
		squeezeCube.whenPressed(new SqueezeCube());

		intakeCubeIn.whileActive(new IntakeIn());
		intakeCubeOut.whileActive(new IntakeOut());
		operateArmDown.whileActive(new JoystickMoveArm());
		operateArmUp.whileActive(new JoystickMoveArm());
		operateTeleDown.whileActive(new JoystickMoveTele());
		operateTeleUp.whileActive(new JoystickMoveTele());
		
// jame's version this works - start both when armToScale is pressed		
//		armToScale.whenPressed(new ControlArmToScale());
//		armToScale.whenPressed(new ControlTeleToScale()); //Test if armToScale works with two calls
//		armToGround.whenPressed(new ControlArmToGround());
//		armToSwitch.whenPressed(new ControlArmToSwitch());	
//		armToFeeder.whenPressed(new ControlArmToFeeder());
		
// sue's version - trying to call a command to do both things off the button press
		armToGround.whenPressed(new MoveArmTeleToGroundCG());
		armToSwitch.whenPressed(new MoveArmTeleToSwitchCG());
		armToScale.whenPressed(new MoveArmTeleToScaleCG());		
		armToFeeder.whenPressed(new MoveArmTeleToFeederCG());
		
		climbLowGear.whileActive(new ShiftClimberLow()); 
		climbHighGear.whileActive(new ShiftClimberHigh()); 

/*
 		.whileActive(new 
		.whenPressed(new
		.whileHeld(new
		.whenReleased(new 
*/
		}
}

