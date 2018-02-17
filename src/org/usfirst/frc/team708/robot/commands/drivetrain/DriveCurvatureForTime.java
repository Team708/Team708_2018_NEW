package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCurvatureForTime extends Command {

	private double runTime;
	private double xSpeed;
    private double zRotation;
    private boolean isQuickTurn;
	
    public DriveCurvatureForTime(double xSpeed, double zRotation, boolean isQuickTurn, double runTime) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.xSpeed = xSpeed;
        this.zRotation = zRotation;
        this.isQuickTurn = isQuickTurn;
        this.runTime = runTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.curvatureDrive(xSpeed, zRotation, isQuickTurn);
//    	Curvature drive method for differential drive platform.
//    	The rotation argument controls the curvature of the robot's path rather than its rate of heading 
//    	change. This makes the robot more controllable at high speeds. 
//    	Also handles the robot's quick turn functionality - "quick turn" overrides 
//    	constant-curvature turning for turn-in-place maneuvers.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.timeSinceInitialized() >= runTime);
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
