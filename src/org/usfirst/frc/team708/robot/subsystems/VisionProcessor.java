package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.AutoConstants;

//import org.team708.robot.commands.visionProcessor.ProcessData;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.util.Math708;

//import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionProcessor extends Subsystem {
	
//	private boolean hasTarget			= false;	
	private boolean isCentered			= false;
//	private boolean isAtY = false;		-Not using Y in 2018
	
	private final double 	imageWidth  = 320.0;
	private final double 	targetWidth = 13.0; //width of target in inches

	private double			hasTarget	= 0.0;	//Get from Network Table
	private double 			currentX 	= 0.0;	//Get from Network Table
//	private double			currentY = 0.0;		-Not using Y in 2018
	

	private double thresholdX = AutoConstants.X_THRESHOLD;
	private double thresholdY = AutoConstants.Y_THRESHOLD;
	
	NetworkTableInstance 	limeLightInstance 	= NetworkTableInstance.getDefault();
	NetworkTable			limeLightTable		= limeLightInstance.getTable("/limelight");
    	
	double rotate;
	
//	Method for getting different data from a Network Table	
	public double getNTInfo(String tableInfo, Double convertInfo) {
		NetworkTableEntry limeLightInfo = limeLightTable.getEntry(tableInfo);		
		convertInfo = limeLightInfo.getDouble(0);	
		return convertInfo;
	}
//	Method for setting different data into a Network Table    
	public void setNTInfo(String tableInfo, Double setValue) {
		NetworkTableEntry limeLightInfo = limeLightTable.getEntry(tableInfo);		
		limeLightInfo.setNumber(setValue);
	}
	
	public VisionProcessor() {
		super("Vision Processor");
	}
//	Method for centering with the cubes
	public double getRotate() {
		
		getNTInfo("tx", currentX);
		getNTInfo("tv", hasTarget);
		
		if (hasTarget == 1) 
		{
			rotate = Math708.getSignClippedPercentError(currentX, 0.0, 0.3, 0.5);
			
			if (Math.abs(currentX) <= thresholdX) {
				rotate = 0.0;
				isCentered = true;
			}
			else if (Math.abs(currentX) > thresholdX) {
				isCentered = false;
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
		
		else {		//Ask if we want to move at all if no targets are found
			//rotates if not target (default is right) if loses/doesn't have target
			rotate = -0.4;
			
		}
		
		return rotate;
	}
	
	//Returns how to move to get to target distance (targetAmount = target ratio)
	
/**	public double getMove() {
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
	public double hadTarget() {
		return hasTarget;
	}
	
	public boolean wasCentered() {
		return isCentered;
	}

	public void sendToDashboard() {
//		SmartDashboard.putBoolean("string name", boolean);

//		SmartDashboard.putNumber("string name", number);


	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}

