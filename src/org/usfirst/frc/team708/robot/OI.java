

package org.usfirst.frc.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.intakeCube.*;
import org.usfirst.frc.team708.robot.commands.arm.*;
//import org.usfirst.frc.team708.robot.commands.pneumatics.*;
//import org.usfirst.frc.team708.robot.commands.cubegrabber.*;
//;

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
//  private static final int ShiftColsonWheels		= Gamepad.shoulderAxisRight;
	private static final int OMNI_TRACTION_BUTTON	= Gamepad.button_X;
	private static final int COLSON_TRACTION_BUTTON = Gamepad.button_Y;
	private static final int HIGH_GEAR_BUTTON       = Gamepad.button_R_Shoulder;
	private static final int LOW_GEAR_BUTTON		= Gamepad.button_L_Shoulder;
	
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


	
	
/*
 * Driver Button Commands
 */
	public static final Button onmiOn	 	= new JoystickButton(driverGamepad, OMNI_TRACTION_BUTTON);
	public static final Button colsonOn	 	= new JoystickButton(driverGamepad, COLSON_TRACTION_BUTTON);
	public static final Button highGearOn	= new JoystickButton(driverGamepad, HIGH_GEAR_BUTTON);
	public static final Button lowGearOn 	= new JoystickButton(driverGamepad, LOW_GEAR_BUTTON);

/*
 * Operator Button Commands
 */
	public static final Button releaseCube		= new JoystickButton(operatorGamepad, RELEASE_CUBE_BUTTON);
	public static final Button squeezeCube		= new JoystickButton(operatorGamepad, SQUEEZE_CUBE_BUTTON);
	public static final Trigger intakeCubeIn	= new AxisUp(operatorGamepad, INTAKE_CUBE_BUTTON);
	public static final Trigger intakeCubeOut	= new AxisDown(operatorGamepad, INTAKE_CUBE_BUTTON);
	
	public static final Trigger operateArmDown = new AxisUp(operatorGamepad, OPERATE_ARM_BUTTON);
	public static final Trigger operateArmUp	= new AxisDown(operatorGamepad, OPERATE_ARM_BUTTON);
	public static final Button operateTelescope	= new JoystickButton(operatorGamepad, OPERATE_TELESCOPE_BUTTON);
	public static final Button armToGround		= new JoystickButton(operatorGamepad, ARM_UP_TO_GROUND_BUTTON);
	public static final Button armToSwitch		= new JoystickButton(operatorGamepad, ARM_UP_TO_SWITCH_BUTTON);
	public static final Button armToScale		= new JoystickButton(operatorGamepad, ARM_UP_TO_SCALE_BUTTON);
	public static final Button armToFeeder		= new JoystickButton(operatorGamepad, ARM_UP_TO_HUMAN_FEEDER_BUTTON);
	public static final Trigger climbLowGear	= new AxisUp(operatorGamepad, CLIMB_LOW_GEAR_BUTTON);


	/**
	 * Constructor
	 * Assigns commands to be called when each button is pressed.
	 */

	public OI() {

//		onmiOn.whenPressed(new EnableOnmi());
//		colsonOn.whenPressed(new EnableColson());
//		highGearOn.whenPressed(new ShiftHigh());
//		lowGearOn.whenPressed(new ShiftLow());
//		
		releaseCube.whenPressed(new ReleaseCube());
		squeezeCube.whenPressed(new SqueezeCube());
		intakeCubeIn.whileActive(new IntakeIn());
		intakeCubeOut.whileActive(new IntakeOut());
//		operateArmDown.whileActive(new ControlArmDown());
//		operateArmUp.whileActive(new ControlArmUp());
//		operateTelescope.whileActive(new ControlTelescope());
//		armToGround.whenPressed(new MoveToGround());
//		armToSwitch.whenPressed(new MoveToSwitch());
//		armToScale.whenPressed(new MoveToScale());
//		armToFeeder.whenPressed(new MoveToFeeder());
//		climbLowGear.whileHeld(new ShiftClimberLowGear()); 
		
/*
 		.whileActive(new 
		.whenPressed(new
		.whileHeld(new
		.whenReleased(new 
*/
		}
}

