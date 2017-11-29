package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class turn extends CommandGroup {

    protected void initialize() {
    	
    }
    public turn(int c) {
    	
//    	addSequential(new TurnToDegrees(.3, -90));  // turn counter clockwise
//    	addSequential(new TurnToDegrees(.3, 90));  // turn  clockwise
//    	addSequential(new WaitCommand(2));
    	addSequential(new TurnToDegreesAlliance(0.5, 15, 1));  //red clock blue clounter
//    	addSequential(new TurnToDegreesAlliance(0.5, 15, -1));  //blue clockwise, red counter
//    	System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
//    	addSequential(new TurnToDegrees(-.5, 90));  //add alliance direction
    }
	
	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
    
}
