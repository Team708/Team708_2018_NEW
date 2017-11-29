//package org.usfirst.frc.team708.robot.commands.drivetrain;
//
//import org.usfirst.frc.team708.robot.AutoConstants;
//import org.usfirst.frc.team708.robot.Robot;
//import edu.wpi.first.wpilibj.command.Command;
//
//
//
///**
// * DriveToShooterLocation
// * this command will utilize the vision data to drive the robot to the closest shooting location :P
// */
//public class DriveToShooterLocation extends Command {
//	
//	private double		moveSpeed;
//	private double		rotate;
//	private int			moveDirection;
//	/**
//	 * Constructor
//	 * @param targetDistance - the distance to stop in front of the target
//	 */
//
//	public DriveToShooterLocation() {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.drivetrain);
//        requires(Robot.visionBoiler);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.visionBoiler.putIsCentered(false);
//    	Robot.visionBoiler.putHasTarget(false);
//    	Robot.visionBoiler.putAtDistance(false);
//    	Robot.visionBoiler.putCounter(0);
//    	Robot.visionBoiler.putCurrentCenter(0);
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if(Robot.visionBoiler.isCentered()) {
//   
//    		if(Robot.visionBoiler.getClosestLocation() == AutoConstants.DISTANCE_TO_BOILER_LOCATION1) {
//    			moveDirection = 1;
//    		}
//    		else {
//    			moveDirection = -1;
//    		}
//        	Robot.visionBoiler.processData();
//        	rotate = Robot.visionBoiler.getRotate();  
//        	moveSpeed = moveDirection*AutoConstants.DRIVE_MOVE_MAX;
//
//     
//        	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);
//
//    	}
//    	else {
//    		
//	    	Robot.visionBoiler.processData();
//	    	rotate = Robot.visionBoiler.getRotate();  
//	    	moveSpeed = Robot.visionBoiler.getMove();   
//	
//	 
//	    	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);
//	    	}
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	if (Robot.visionBoiler.getCounter() >= AutoConstants.SWEEP3_MAX){
//    		
//    		return true;
//    	}
//    	//Check if the sonar distance is less then the target Distance, end
////    	if (Robot.drivetrain.getSonarDistance() < targetDistance  && Robot.visionProcessor.wasCentered()){
//        if (Robot.visionBoiler.isAtDistance() && Robot.visionBoiler.isCentered()){
//         		     		return true;
//    	}
////    	else if (Robot.drivetrain.getSonarDistance() < targetDistance && Robot.visionProcessor.isHasTarget()) {
//    	else {
//    		return false;
//    	}
//    	
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.drivetrain.stop();
//    	Robot.visionBoiler.putCounter(0);
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	end();
//    }
//}
