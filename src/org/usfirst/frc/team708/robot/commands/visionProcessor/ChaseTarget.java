///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.wpi.first.wpilibj.templates.commands.Driving;
//
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.templates.commands.CommandBase;
//
///**
// * This command tells the robot to chase the practice target
// * (or any target that is held in front of it).
// * @author Connor Willison + Debbie Musselman
// */
//public class ChaseTarget extends CommandBase {
//    
//    private double chaseDistanceIn = 120;
//    private double rotation, movement;
//    private double distanceDifferenceToTarget;
//    private static final double distanceToleranceIn = 50;
//    private static final double rotationTolerancePx = 40;
//    private static final double rotationSpeed = .6;
//    private static final double movementSpeed = .7;
//    
//    public ChaseTarget() {
//        requires(visionProcessor);
//        requires(drivetrain);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//        rotation = 0.0;
//        movement = 0.0;
//        distanceDifferenceToTarget = 0.0;
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//        visionProcessor.processData();
//        
//        if(visionProcessor.hasTarget())
//        {
//            //calculate the rotation needed to line up target
//            if(visionProcessor.getDifferencePx() > rotationTolerancePx)
//            {
//                rotation = rotationSpeed;
//            }else if(visionProcessor.getDifferencePx() < -rotationTolerancePx)
//            {
//                rotation = -rotationSpeed;
//            }else{
//                rotation = 0.0;
//            }
//
//            //calculate the movement speed needed to chase target
//            distanceDifferenceToTarget = chaseDistanceIn - visionProcessor.getDistanceToTarget();
//            if(distanceDifferenceToTarget > distanceToleranceIn)
//            {
//                movement = -movementSpeed;
//            } else if(distanceDifferenceToTarget < distanceToleranceIn)
//            {
//                movement = movementSpeed;
//            } else
//            {
//                movement = 0;
//            }
//
//            drivetrain.arcadeDrive(movement, rotation);
//        }else{
//            drivetrain.arcadeDrive(0.0,0.0);
//        }
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
