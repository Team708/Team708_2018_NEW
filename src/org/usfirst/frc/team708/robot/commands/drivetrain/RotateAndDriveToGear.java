//package org.usfirst.frc.team708.robot.commands.drivetrain;
//
//import org.usfirst.frc.team708.robot.AutoConstants;
//import org.usfirst.frc.team708.robot.Robot;
//import edu.wpi.first.wpilibj.command.Command;
//
//
//
///**
// *RotateAndDriveToGear
// * this command will utilize the vision data to drive the robot to the center of the gear
// * and stop when it is at the gear stop at target distance
// */
//public class RotateAndDriveToGear extends Command {
//	
//	private double		moveSpeed;
//	private double		rotate;
//	/**
//	 * Constructor
//	 * @param targetDistance - the distance to stop in front of the target
//	 */
//// VIET ARE WE STILL USING THE TARGET DISTANCE HERE - I THINK WE ARE ACTUALLY CALCULATING IT IN THE SUBSYSTEM
//    public RotateAndDriveToGear() {
//        // Use requires() here to declare subsystem dependencies
////        requires(Robot.drivetrain);
////        requires(Robot.visionLiftGear);
//        
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.visionLiftGear.putGearIsCentered(false);
//    	Robot.visionLiftGear.putGearHasTarget(false);
//    	Robot.visionLiftGear.putGearAtDistance(false);
//    	Robot.visionLiftGear.putGearCounter(0);
//    	Robot.visionLiftGear.putGearCurrentCenter(0);
//    	
//    	Robot.visionLiftGear.setGearCamera();
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//
//    	Robot.visionLiftGear.gearProcessData();
//    	rotate 		= Robot.visionLiftGear.gearGetRotate();  
//    	moveSpeed 	= Robot.visionLiftGear.gearGetMove();   // was + made -
//
// 
//    	Robot.drivetrain.haloDrive(-1 * moveSpeed, rotate, false);
//
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	if (Robot.visionLiftGear.getGearCounter() >= AutoConstants.SWEEP3_MAX){
//    		
//    		return true;
//    	}
//    	//Check if the sonar distance is less then the target Distance, end
////    	if (Robot.drivetrain.getSonarDistance() < targetDistance  && Robot.visionProcessor.wasCentered()){
////     	if (Robot.visionProcessor.isAtY() && Robot.visionProcessor.wasCentered()){
//    	else if ((Robot.visionLiftGear.gearIsAtDistance() && Robot.visionLiftGear.gearIsCentered()) || Robot.intake_gear.hasGear()) {
//         		     		return true;
//    	}
////    	else if (Robot.drivetrain.getSonarDistance() < targetDistance && Robot.visionProcessor.isHasTarget()) {
////    	else if (Robot.visionLiftGear.gearIsAtDistance() && Robot.visionLiftGear.gearIsHasTarget()) {
////    		return false;
////    	}
//    	else        
//    	    return false;
//    	
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.drivetrain.stop();
//    	Robot.visionLiftGear.putGearCounter(0);
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	end();
//    }
//}
