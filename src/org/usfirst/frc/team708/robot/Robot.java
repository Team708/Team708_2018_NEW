
package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.vision.CameraServer;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.AxisCamera;


import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.subsystems.Shooter;
import org.usfirst.frc.team708.robot.subsystems.Feeder;
//import org.usfirst.frc.team708.robot.subsystems.Loader;
import org.usfirst.frc.team708.robot.subsystems.Intake_Ball;
import org.usfirst.frc.team708.robot.subsystems.Intake_Gear;
import org.usfirst.frc.team708.robot.subsystems.Pivot_Gear;
import org.usfirst.frc.team708.robot.subsystems.Climber;
import org.usfirst.frc.team708.robot.subsystems.VisionLift;
import org.usfirst.frc.team708.robot.subsystems.VisionBoiler;
//import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.subsystems.LED;

import org.usfirst.frc.team708.robot.OI;

import org.usfirst.frc.team708.robot.commands.feeder.*;
import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.intake_ball.*;
import org.usfirst.frc.team708.robot.commands.intake_gear.*;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.Climber.*;
import org.usfirst.frc.team708.robot.commands.loader.*;
import org.usfirst.frc.team708.robot.commands.shooter.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team708.robot.commands.led_out.*;
import org.usfirst.frc.team708.robot.commands.AllianceSelection.*;

public class Robot extends IterativeRobot {
    
    Timer statsTimer;										// Timer used for Smart Dash statistics
    
    public static Drivetrain 		drivetrain;
    public static Shooter	 		shooter;
    public static Feeder	 		feeder;
    public static Intake_Ball		intake_ball;
    public static Intake_Gear		intake_gear;
    public static Pivot_Gear		pivot_gear;

    public static Climber			climber;
    
//    public static VisionProcessor 	visionProcessor;
    public static VisionLift 			visionLift;
    public static VisionBoiler 			visionBoiler;

    public static LED			led1;
    
    public static OI	 		oi;

    public static DriverStation 			ds;
    public static DriverStation.Alliance 	alliance;
    public static int 						allianceColor;
    public static byte						ledAllianceColor;
    
//    public static Solenoid			pwr0;
//    public static Solenoid			pwr1;
//    public static Solenoid			pwr2;
//    public static Solenoid			pwr3;    
//    public static Solenoid			gearLight;    
//    public static Solenoid			boilerLight;    

    SendableChooser 	autonomousMode 		= new SendableChooser<>();
    SendableChooser 	allianceSelection 	= new SendableChooser<>();
    
    Command 			autonomousCommand;
    Command 			allianceCommand;
    Preferences			prefs;
    
    int		            AllianceSelectionInt;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
        statsTimer = new Timer();	// Initializes the timer for sending Smart Dashboard data
        statsTimer.start();		// Starts the timer for the Smart Dashboard
        
		
        
// Subsystem Initialization

    drivetrain 			= new Drivetrain();
    shooter				= new Shooter();
    intake_ball			= new Intake_Ball();
    intake_gear			= new Intake_Gear();
    pivot_gear			= new Pivot_Gear();
    feeder				= new Feeder();
    led1				= new LED();
    climber				= new Climber();
    visionLift			= new VisionLift();
    visionBoiler		= new VisionBoiler();
            
    oi 				= new OI();	// Initializes the OI. 
						// This MUST BE LAST or a NullPointerException will be thrown

	UsbCamera ucamera=CameraServer.getInstance().startAutomaticCapture("cam1", 1);
    ucamera.setResolution(180, 240);
	
    
//    pwr0 			= new Solenoid(RobotMap.PWR0);
//    pwr1 			= new Solenoid(RobotMap.PWR1);
//    pwr2 			= new Solenoid(RobotMap.PWR2);
//    pwr3 			= new Solenoid(RobotMap.PWR3);
//    gearLight  		= new Solenoid(RobotMap.GEARLIGHT);
//    boilerLight		= new Solenoid(RobotMap.BOILERLIGHT);
//
//    pwr0.set(true);
//    pwr1.set(true);
//    pwr2.set(true);
//    pwr3.set(true);
//    gearLight.set(true);
//    boilerLight.set(true);
    
	sendDashboardSubsystems();		// Sends each subsystem's currently running command to the Smart Dashboard
	queueAutonomousModes();			// Adds autonomous modes to the selection box    
    }
	
    /**
     * Runs periodically while the robot is enabled
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendStatistics();
		prefs = Preferences.getInstance();
		/*try {
     		if (ds.isSysActive()){
	            if (ds.isFMSAttached())
		        {
			    alliance = ds.getAlliance();
	             if (ds.getAlliance() == Alliance.Blue){
	        	    led1.send_to_led(Constants.SET_ALLIANCE_BLUE);
					allianceColor = Constants.ALLIANCE_BLUE;
					ledAllianceColor = Constants.SET_ALLIANCE_BLUE;
				}
    	        else if (ds.getAlliance() == Alliance.Red){
	            	led1.send_to_led(Constants.SET_ALLIANCE_RED);
					allianceColor = Constants.ALLIANCE_RED;
					ledAllianceColor = Constants.SET_ALLIANCE_RED;
    	        }
	            else {
	            	led1.send_to_led(Constants.SET_ALLIANCE_INVALID);
					allianceColor = 0;
	            }
		   }
	}
		}
		catch (Exception e)
		{
			led1.send_to_led(Constants.MAX_LED_CODE);
		}*/
	}

	/**
	 * Runs at the start of autonomous mode
	 */
    	public void autonomousInit() {
    	
//    	AllianceSelectionDouble =  (Double)AllianceSelection.getSelected();

    	// schedule the autonomous command (example)  
    	allianceCommand = (Command)allianceSelection.getSelected();
    	SmartDashboard.putString("From Alliance Command", allianceCommand.getName());
    	
    	if(allianceCommand.getName().equals("BlueAlliance"))
    		allianceColor = Constants.ALLIANCE_BLUE;
    	else
    		allianceColor = Constants.ALLIANCE_RED;
    	
    	if (allianceCommand != null) 
    		allianceCommand.start();
    	autonomousCommand = (Command)autonomousMode.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();

    	Robot.drivetrain.setGearLight(true);
	    Robot.drivetrain.setBoilerLight(true);
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
	    // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
        if (autonomousCommand != null) autonomousCommand.cancel();
        drivetrain.toggleBrakeMode();
        
		Robot.drivetrain.setGearLight(false);
	    Robot.drivetrain.setBoilerLight(false);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
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
 //       if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) {
 //           statsTimer.reset();

            // Various debug information
            drivetrain.sendToDashboard();
            feeder.sendToDashboard();
            shooter.sendToDashboard();
            led1.sendToDashboard();
            climber.sendToDashboard();
            intake_ball.sendToDashboard();
            intake_gear.sendToDashboard();
            pivot_gear.sendToDashboard();
//          visionProcessor.sendToDashboard();
            visionLift.sendToDashboard();
            visionBoiler.sendToDashboard();
//       }
    }
	
	/**
     * Adds every autonomous mode to the selection box and adds the box to the Smart Dashboard
     */
    private void queueAutonomousModes() {
    	
    	autonomousMode.addDefault("Do Nothing", 		new DoNothing());
    	autonomousMode.addObject("Drive Over Line", 	new driveDistance());
    	autonomousMode.addObject("One Gear Center", 	new OneGearCenter());
    	autonomousMode.addObject("One Gear Open Side", 	new OneGearLeft());
    	autonomousMode.addObject("10 Ball", 			new TenBalls());
    	autonomousMode.addObject("Just 10 Ball", 		new JustTenBalls());
    	autonomousMode.addObject("60 Ball", 			new SixtyBalls());

	autonomousMode.addObject("Drive To Lift", new RotateAndDriveToLift());
	autonomousMode.addObject("Drive to Boiler", new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION2));

//	autonomousMode.addObject("Drive to Boiler Location 2", new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION2));
//	autonomousMode.addObject("Rotate And Drive To Gear", new RotateAndDriveToGear());

//  autonomousMode.addObject("Drive Straight for time", new DriveStraightForTime(.5, 3));
//	autonomousMode.addObject("Find Target", new FindTarget());
//	autonomousMode.addObject("Drive in Square", new DriveInSquare());
//	autonomousMode.addObject("turn", new turn(allianceColor));
//	autonomousMode.addObject("Realease Gear Test", new AutoGearTest());

		allianceSelection.addDefault("RED", new RedAlliance());
    	allianceSelection.addObject("BLUE", new BlueAlliance());
    	
    	SmartDashboard.putData("Alliance Color", allianceSelection);    	   	
    	SmartDashboard.putData("Autonomous Selection", autonomousMode);    	

    }
    
    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
    	SmartDashboard.putData(shooter);
    	SmartDashboard.putData(feeder);
    	SmartDashboard.putData(drivetrain);
    	SmartDashboard.putData(led1);
    	SmartDashboard.putData(intake_ball);
    	SmartDashboard.putData(intake_gear);
    	SmartDashboard.putData(pivot_gear);
//    	SmartDashboard.putData(visionProcessor);
    	SmartDashboard.putData(visionLift);
    	SmartDashboard.putData(visionBoiler);
    	SmartDashboard.putData(climber);
    }
}