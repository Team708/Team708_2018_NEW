
package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team708.robot.commands.intake_cube.*;

import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.subsystems.Intake_Cube;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsTest;
import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author omn0mn0m
 * @author Viet Tran
 * @author wutnut
 */
public class Robot extends IterativeRobot {
	
	Timer statsTimer;										// Timer used for Smart Dash statistics
    
    public static Drivetrain 		drivetrain;
	public static VisionProcessor 	visionProcessor;
	public static PneumaticsTest    pneumaticsTest;
	public static Intake_Cube		intake_cube;
	public static Arm				arm;
	public static Telescope			telescope;
	public static OI 				oi;
 
	SendableChooser<Command> autonomousMode = new SendableChooser<>();
    Command 			autonomousCommand;
    Preferences			prefs;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
        statsTimer = new Timer();	// Initializes the timer for sending Smart Dashboard data
        statsTimer.start();		// Starts the timer for the Smart Dashboard
        
        // Subsystem Initialization
        
	    drivetrain 		= new Drivetrain();
	    pneumaticsTest  = new PneumaticsTest();
	    intake_cube			= new Intake_Cube();
	    visionProcessor	= new VisionProcessor();
	    
	    visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_ON);
	    
		sendDashboardSubsystems();		// Sends each subsystem's currently running command to the Smart Dashboard
			
		queueAutonomousModes();			// Adds autonomous modes to the selection box
		
//		NetworkTableInstance  table = NetworkTableInstance.getDefault();    
//
//		NetworkTable limelightNT = table.getTable("limelight");
//		double targetOffsetAngle_Horizontal = limelightNT.
//		double targetOffsetAngle_Horizontal = double.valueof(limelightNT.getEntry("tx", 0));
//		double targetOffsetAngle_Vertical = table.getNumber("ty", 0);
//		double targetArea = table.getNumber("ta", 0);
//		double targetSkew = table.getNumber("ts", 0);
		
		// This MUST BE LAST or a NullPointerException will be thrown
        oi 				= new OI();		// Initializes the OI		
    }
	
    /**
     * Runs periodically while the robot is enabled
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendStatistics();
		prefs = Preferences.getInstance();
	}

	/**
	 * Runs at the start of autonomous mode
	 */
    public void autonomousInit() {
    	// schedule the autonomous command   		
    	autonomousCommand = (Command)autonomousMode.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
        
        visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_OFF);
        visionProcessor.setNTInfo("camMode", Constants.VISION_PROCESSING_ON);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }

    /**
     * Runs when teleop mode initializes
     */
    public void teleopInit() {
	    //This makes sure that the autonomous stops running when teleop starts running. 
    	//If you want the autonomous to continue until interrupted by another command, 
    	//remove this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_OFF);
        visionProcessor.setNTInfo("camMode", Constants.VISION_PROCESSING_OFF);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	//testing
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        sendStatistics();
    }
    
    /**
     * Sends data from each subsystem periodically as set by sendStatsIntervalSec
     */
    
    
    
    private void sendStatistics() {
//        if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) statsTimer.reset();
    	drivetrain.sendToDashboard();
        intake_cube.sendToDashboard();
    }
    
    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart Dashboard
     */
    private void queueAutonomousModes() {
    	
    	autonomousMode.addObject("Test Auto 1", null);
    	autonomousMode.addObject("Do Nothing", new DoNothing());

    	autonomousMode.addObject("Drive time distance", 	new driveDistance());
    	autonomousMode.addObject("Drive in Square", 		new DriveInSquare());
    	autonomousMode.addObject("Drive encoder distance", 	new driveDistanceEncoder());

    	SmartDashboard.putData("Autonomous Selection", autonomousMode);    	   	
    }
    
    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
    	SmartDashboard.putData(drivetrain);
    	SmartDashboard.putData(intake_cube);    	
    }
}


