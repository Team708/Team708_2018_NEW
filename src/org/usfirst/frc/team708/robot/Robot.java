
package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
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
import org.usfirst.frc.team708.robot.commands.intakeCube.*;
import org.usfirst.frc.team708.robot.commands.drivetrain.*;

import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.subsystems.Arm;
import org.usfirst.frc.team708.robot.subsystems.Telescope;
import org.usfirst.frc.team708.robot.subsystems.IntakeCube;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsCube;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsClimber;
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
 * @cleanup of vision & gamedata - 1/28 @11:24
 */
public class Robot extends IterativeRobot {
	
	Timer statsTimer;										// Timer used for Smart Dash statistics
    
    public static Drivetrain 		drivetrain;
	public static VisionProcessor 	visionProcessor;
	public static PneumaticsCube    pneumaticsCube;
	public static PneumaticsClimber pneumaticsClimber;
	public static IntakeCube		intakeCube;
	public static Arm				arm;
	public static Telescope			tele;
	public static OI 				oi;

   	public String 	gameData;
   	public String 	robotLocation;
   	public String 	autoMode;

	public boolean climber=true;
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
        
	    drivetrain 			= new Drivetrain();
	    intakeCube			= new IntakeCube();
	    pneumaticsCube		= new PneumaticsCube();
	    pneumaticsClimber	= new PneumaticsClimber();
	    visionProcessor		= new VisionProcessor();
	    arm 				= new Arm();
	    tele	 			= new Telescope();


	    visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_OFF);
	    
		sendDashboardSubsystems();		// Sends each subsystem's currently running command to the Smart Dashboard
			
		queueAutonomousModes();			// Adds autonomous modes to the selection box
		
		
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
    	
        visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_OFF);
        visionProcessor.setNTInfo("camMode", Constants.VISION_PROCESSING_ON);
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//original dashboard code
//    	autonomousCommand = (Command)autonomousMode.getSelected();
//      if (autonomousCommand != null) autonomousCommand.start();
		
		// get the autocommand entered on the dashboard
		// robotLocation is the 1st character of the autoMode - to be used to determine whether the robot is lined up on the right or left side of the field
    	autonomousCommand = (Command)autonomousMode.getSelected();
    	robotLocation = autonomousCommand.getName().substring(0,1); 
    	autoMode = autonomousCommand.getName();
        
    	
		SmartDashboard.putString("gameData", gameData);
		SmartDashboard.putString("robotLocation", robotLocation);
		SmartDashboard.putString("Auto Mode", autoMode);
		
		// if the autoMode is one of the 2 "special modes" - those that run based on the gamedata
		// run the autoMode based on the state LL LR RL RR AND robotLocation combination
		if ((autoMode.equals("Right_RobotLocation")) ||
				(autoMode.equals("Left_RobotLocation")))
			{
				SmartDashboard.putString("HERE", "running the special modes");
				switch(gameData.substring(0,2))
				{
					case "LL":
					{
						SmartDashboard.putString("Auto State", "LL");
						
						if (robotLocation.equals("L"))
						{
					    	autoLeft_LL command_Left_LL = new autoLeft_LL();
					    	command_Left_LL.start();
						}
						else
						{
					    	autoRight_LL command_Right_LL = new autoRight_LL();
					    	command_Right_LL.start();
						}
					
						break;
					}
					case "RR":
					{
						SmartDashboard.putString("Auto State", "RR");				
						
						if (robotLocation.equals("L"))
						{
					    	autoLeft_RR command_Left_RR = new autoLeft_RR();
					    	command_Left_RR.start();
						}
						else
						{
					    	autoRight_RR command_Right_RR = new autoRight_RR();
					    	command_Right_RR.start();
						}
		
						break;
					}
					case "LR":
					{
						SmartDashboard.putString("Auto State", "LR");
						
						if (robotLocation.equals("L"))
						{
					    	autoLeft_LR command_Left_LR = new autoLeft_LR();
					    	command_Left_LR.start();
						}
						else
						{
					    	autoRight_LR command_Right_LR = new autoRight_LR();
					    	command_Right_LR.start();
						}
		
						break;
					}
					case "RL":
					{
						SmartDashboard.putString("Auto State", "RL");
						
						if (robotLocation.equals("L"))
						{
					    	autoLeft_RL command_Left_RL = new autoLeft_RL();
					    	command_Left_RL.start();
						}
						else
						{
					    	autoRight_RL command_Right_RL = new autoRight_RL();
					    	command_Right_RL.start();
						}
		
						break;
					}
					default:
					{
						SmartDashboard.putString("Auto State", "DO NOTHING");
						break;
					}
				
				}
			} // end if - using both switch and scale gamedata
		
			else if ((autoMode.equals("Right_SwitchOnly_RobotLocation")) ||
					(autoMode.equals("Left_SwitchOnly_RobotLocation"))) {
				
				SmartDashboard.putString("HERE", "running the switchonly specials");
				switch(gameData.substring(0,1))
				{
					case "L":
					{
						SmartDashboard.putString("Auto State", "L");
						
						if (robotLocation.equals("L"))
						{
					    	autoLeft_L_SwitchOnly command_Left_L_SwitchOnly = new autoLeft_L_SwitchOnly();
					    	command_Left_L_SwitchOnly.start();
						}
						else
						{
					    	autoRight_L_SwitchOnly command_Right_L_SwitchOnly = new autoRight_L_SwitchOnly();
					    	command_Right_L_SwitchOnly.start();
						}
					
						break;
					}
					case "R":
					{
						SmartDashboard.putString("Auto State", "R");				
						
						if (robotLocation.equals("L"))
						{
					    	autoLeft_R_SwitchOnly command_Left_R_SwitchOnly = new autoLeft_R_SwitchOnly();
					    	command_Left_R_SwitchOnly.start();
						}
						else
						{
					    	autoRight_R_SwitchOnly command_Right_R_SwitchOnly = new autoRight_R_SwitchOnly();
					    	command_Right_R_SwitchOnly.start();
						}
		
						break;
					}
				}//switch
			} //end else if - using switch only
		
		
			else
			{
				SmartDashboard.putString("HERE", "running the other modes");

				SmartDashboard.putString("Auto State", autoMode);
				if (autonomousCommand != null) autonomousCommand.start();
			}


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
        drivetrain.setBrakeMode(false);
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
        sendStatistics();
    }
    
    /**
     * Sends data from each subsystem periodically as set by sendStatsIntervalSec
     */
    
    
    
    private void sendStatistics() {
//        if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) statsTimer.reset();
    	drivetrain.sendToDashboard();
        intakeCube.sendToDashboard();
        visionProcessor.sendToDashboard();
        pneumaticsCube.sendToDashboard();
        pneumaticsClimber.sendToDashboard();
        arm.sendToDashboard();
        tele.sendToDashboard();
    }
    
    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart Dashboard
     */
    private void queueAutonomousModes() {
    	
    	autonomousMode.addObject("Test Auto 1", null);
    	autonomousMode.addObject("Do Nothing", new DoNothing());

    	autonomousMode.addObject("Drive in Square", 		new DriveInSquare());
    	autonomousMode.addObject("Drive encoder distance", 	new driveDistanceEncoder());
    	autonomousMode.addObject("Curvature Drive", 		new driveCurvatureForTime());
    	autonomousMode.addObject("Left RobotLocation", 		new Left_RobotLocation());
    	autonomousMode.addObject("Right RobotLocation", 	new Right_RobotLocation());
    	autonomousMode.addObject("Left SwitchOnlyt RobotLocation", 		new Left_SwitchOnly_RobotLocation());
    	autonomousMode.addObject("Right SwitchOnly RobotLocation", 		new Left_SwitchOnly_RobotLocation());


    	SmartDashboard.putData("Autonomous Selection", autonomousMode);    	   	
    }
    
    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
    	SmartDashboard.putData(drivetrain);
    	SmartDashboard.putData(intakeCube); 
    	SmartDashboard.putData(visionProcessor);
    	SmartDashboard.putData(arm);
    	SmartDashboard.putData(pneumaticsCube);
    	SmartDashboard.putData(pneumaticsClimber);
    	SmartDashboard.putData(tele);
    }
}


