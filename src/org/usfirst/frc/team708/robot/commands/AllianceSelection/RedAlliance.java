package org.usfirst.frc.team708.robot.commands.AllianceSelection;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

    public class RedAlliance  extends Command {
 
    	public RedAlliance() {
    	    }
    	    
    	    protected void initialize() {
    	    	//Robot.drivetrain.setAlliance(Constants.ALLIANCE_RED);
    	    	Robot.ledAllianceColor = Constants.SET_ALLIANCE_RED;
    	    	Robot.led1.send_to_led(Constants.SET_ALLIANCE_RED);
    	    }

    	    protected void execute() {    	
    	    }

    	    protected boolean isFinished() {
    	    	return true;
    	    }

    	    protected void end() {
    	    }

    	    protected void interrupted() {
    	    	end();
    	    }   	
	}