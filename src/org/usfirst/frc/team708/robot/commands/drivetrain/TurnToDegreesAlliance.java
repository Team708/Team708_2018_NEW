package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToDegreesAlliance extends Command {
	
	private double rotationSpeed;
	private double goalDegrees;
	private double localColor;
	private int direction;

	/**
	 * Constructor
	 * @param rotationSpeed
	 * @param goalDegrees
	 */
    public TurnToDegreesAlliance(double rotationSpeed, double goalDegrees, int direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.rotationSpeed = rotationSpeed;
        this.goalDegrees = goalDegrees;
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
//    	goalDegrees = goalDegrees * SmartDashboard.getInt("AllianceColor");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Color from dashbaord= " + SmartDashboard.getInt("AllianceColor"));
//    	System.out.println("GoalDegress passed in  = " + goalDegrees);
//    	System.out.println("local color  = " + localColor);
//    	System.out.println("robot.allianceColor  = " + Robot.allianceColor);
    	if (localColor != Robot.allianceColor){
        	System.out.println("resetting goal degress");
    		localColor = Robot.allianceColor;
    		goalDegrees = Math.abs(goalDegrees) * Robot.allianceColor;
    		goalDegrees = goalDegrees * direction;
//    		goalDegrees = goalDegrees * Robot.allianceColor;
    	}
    		
//    	System.out.println("local color after = " + localColor);
//    	System.out.println("goalDegress after =" + goalDegrees);
    	
    	if (goalDegrees >= 0) {
    		Robot.drivetrain.haloDrive(0.0, -rotationSpeed, false);
    	} else {
    		Robot.drivetrain.haloDrive(0.0, rotationSpeed, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (goalDegrees > 0) {
//    		System.out.println("returning goal degress >=, " + goalDegrees + " return = " + (Robot.drivetrain.getAngle() >= goalDegrees));
    		return (Robot.drivetrain.getAngle() >= goalDegrees);
    	} else if (goalDegrees < 0){
//    		System.out.println("returning goal degress <=, " + goalDegrees + " return = " + (Robot.drivetrain.getAngle() < goalDegrees));
    		return (Robot.drivetrain.getAngle() < goalDegrees);
    	} else {
//    		System.out.println("returening goalDegress = 0???");
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
