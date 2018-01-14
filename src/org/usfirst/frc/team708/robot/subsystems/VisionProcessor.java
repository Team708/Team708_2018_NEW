package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.AutoConstants;

//import org.team708.robot.commands.visionProcessor.ProcessData;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.util.Math708;

//import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionProcessor extends Subsystem {
    
	private NetworkTable roboRealmInfo;
// NumberArray deprecated	private NumberArray targetCrosshair;
	
	private boolean hasTarget;
	private boolean wasCentered;
	private boolean isAtY = false;
	
	private final double 	imageWidth = 320;
//	private final double 	targetWidth = 18; //width of target in inches

	private double 			centerX = 0.0;
	private double			targetY = AutoConstants.Y_TARGET;
	private double 			currentX = 0.0;
	private double			currentY = 0.0;

	private double thresholdX = AutoConstants.X_THRESHOLD;
	private double thresholdY = AutoConstants.Y_THRESHOLD;
	
    // High goal aspect ratio (11ft6in/3ft1in) in inches (3.729 repeating)
//    private final double targetAspectRatio = 3.73; 
    
    // Distance related measurements from the network table
 //   private double 			distanceToTarget 	= 0.0;
//    private int 			differencePx 		= 0;
//    private final double 	targetDiameterIn 	= 24;
    
    // Data sent from the network table
//    private double currentAspectRatio = 0.0;
//    private double upper_left_x = 0;
//    private double upper_left_y = 0;
//    private double upper_right_x = 0;
//    private double upper_right_y = 0;
//    private double lower_left_x = 0;
//    private double lower_left_y = 0;
//    private double radius = 0;
//    private double blob_count = 0;
    
//    private double lowerLengthX;
//    private double setProportion;
    	
    //daisy says to set this to 43.5 deg
//    private final double cameraFOVRads = Math.toRadians(47);
//    private double cameraFOVRads = Math.toRadians(43.5);
	double rotate;
    
	public VisionProcessor() {
		super("Vision Processor");
		roboRealmInfo = NetworkTable.getTable("vision");
		
// Number array deprecated		targetCrosshair = new NumberArray();
		centerX = imageWidth / 2;
	}
	
	/*
	public void processData() {
		try {
			currentX= roboRealmInfo.getNumber("cx", 0);
			currentY= roboRealmInfo.getNumber("cy", 0);
//			upper_left_x = (double) roboRealmInfo.getNumber("p1x");
//            upper_left_y = (double) roboRealmInfo.getNumber("p1y");
//            upper_right_x = (double)roboRealmInfo.getNumber("p2x");
//            upper_right_y = (double)roboRealmInfo.getNumber("p2y");
//            lower_left_x = (double) roboRealmInfo.getNumber("p3x");
//            lower_left_y = (double) roboRealmInfo.getNumber("p3y");
			
			if (currentX > 0) {
				hasTarget = true;
			} else {
				hasTarget = false;
			}
			
            
		} catch (TableKeyNotDefinedException e) {
			e.printStackTrace();
		}
	}
	*/
	public double getRotate() {
		
		if (hasTarget) 
		{
			
			double difference = centerX - (currentX);
			rotate = Math708.getSignClippedPercentError(currentX, centerX, 0.3, 0.5);
			
			if (Math.abs(difference) <= thresholdX) {
				rotate = 0.0;
				wasCentered = true;
			}
			else if (Math.abs(difference) > thresholdX) {
				wasCentered = false;
			}
			
			
			/*
			rotate = difference / centerX;
			
			
				if (rotate > 0.0) {
					//reverses the sign to turn left, when target is left
					rotate = -Constants.VISION_ROTATE_MOTOR_SPEED;
				}
				else {
					rotate = Constants.VISION_ROTATE_MOTOR_SPEED;
				}
				*/
		}
		
		else {
			//rotates if not target (default is right) if loses/doesn't have target
			rotate = -0.4;
			
		}
		
		return rotate;
	}
	
	//Returns how to move to get to target distance (targetAmount = target ratio)
	
	public double getMove() {
		double move;
		
		if (hasTarget) 
		{
			double difference = targetY - currentY;			
			move = Math708.getSignClippedPercentError(currentY, targetY, 0.4, 0.6); 
			//Check if target is at correct level within threshold
			if (difference <= thresholdY) {
				move = 0.0;
				isAtY = true;
			} else {
				isAtY = false;
			}
			
		} else {
 			move = 0.0;
		}
		
		return move;
	}
	
	/**
	 * Returns if the robot sees a container
	 * @return
	 */
	public boolean isHasTarget() {
		return hasTarget;
	}
	
	public boolean isAtY() {
		double difference = targetY - currentY;			
		//Check if target is at correct level within threshold
		if (Math.abs(difference) <= thresholdY) {
			isAtY = true;
		} else {
			isAtY = false;
		}
		return isAtY;
	}
	
	public boolean wasCentered() {
		return wasCentered;
	}

	public void sendToDashboard() {
		SmartDashboard.putBoolean("See Target", isHasTarget());
//		SmartDashboard.putBoolean("Was Centered", wasCentered());
		SmartDashboard.putBoolean("Is At Y", isAtY());
		SmartDashboard.putNumber("Current Y", currentY);
		SmartDashboard.putNumber("Center of Target", currentX);
//		SmartDashboard.putNumber("Rotation", rotate);
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}

