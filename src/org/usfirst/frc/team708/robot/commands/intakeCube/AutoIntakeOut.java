package org.usfirst.frc.team708.robot.commands.intakeCube;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
//import org.team708.robot.subsystems.*;
//import org.usfirst.frc.team708.robot.subsystems.Loader;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author James_Makovics
 *@author Alex Tysak
 *@author Thomas Zhao
 */
public class AutoIntakeOut extends Command {

	private double maxTime;

    public AutoIntakeOut(double maxTime) {
    	requires(Robot.intakeCube);
    	this.setTimeout(maxTime);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.intakeCube.moveMotor(-.6);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeCube.moveMotor(-.6);  //was .4 moved to .6
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(isTimedOut());
//    	return(true);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeCube.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
