package org.usfirst.frc.team708.robot.commands.intake_cube;

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
public class IntakeStop extends Command {

	
    public IntakeStop() {
    	requires(Robot.intake_cube);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake_cube.moveMotor(Constants.INTAKE_OFF);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(true);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake_cube.moveMotor(Constants.INTAKE_OFF);
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
