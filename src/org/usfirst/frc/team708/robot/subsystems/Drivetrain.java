package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.util.HatterDrive;
import org.usfirst.frc.team708.robot.util.IRSensor;
import org.usfirst.frc.team708.robot.util.UltrasonicSensor;
import org.usfirst.frc.team708.robot.util.Math708;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
//import edu.wpi.first.wpilibj.GyroBase;
//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is a drivetrain subsystem that uses PID to drive straight.
 * @author Nam Tran & Thomas Zhao & Viet Tran
 */

public class Drivetrain extends PIDSubsystem {

	private WPI_TalonSRX leftMaster, rightMaster;	// Motor Controllers
	private WPI_VictorSPX	leftSlave1, leftSlave2, rightSlave2, rightSlave1;

	
	// Variables specific for drivetrain PID loop
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	

	private DifferentialDrive drivetrain;						// FRC provided drivetrain class
	
	private Encoder encoder;						// Encoder for the drivetrain
	private Encoder encoder2;						// Encoder for the drivetrain

	private double distancePerPulse;
	private BuiltInAccelerometer accelerometer;				// Accelerometer that is built into the roboRIO
	private ADXRS450_Gyro gyro;							// Gyro that is used for drift correction
	private Solenoid butterflySolenoid;
	private DoubleSolenoid gearShiftSolenoid;
	
	private IRSensor drivetrainIRSensor;					// IR Sensor for <=25inches
	private UltrasonicSensor drivetrainUltrasonicSensor;	// Sonar used for <=21feet
	private DigitalInput opticalSensor;
	private DigitalInput opticalSensor1;
	
	private boolean brake = true;		// Whether the talons should be in coast or brake mode
						// (this could be important if a jerky robot causes things to topple
	private boolean usePID = false;
	
	private boolean gear_high;
	private boolean butterfly_on = false;

    /**
     * Constructor
     */
    public Drivetrain() {
    	// Passes variables from this class into the superclass constructor
    	super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
    	
    	// Initializes motor controllers with device IDs from RobotMap
		leftMaster  = new WPI_TalonSRX(RobotMap.drivetrainLeftMotorMaster);
		leftSlave1   = new WPI_VictorSPX(RobotMap.drivetrainLeftMotorSlave1);
		leftSlave2   = new WPI_VictorSPX(RobotMap.drivetrainLeftMotorSlave2);
		rightMaster = new WPI_TalonSRX(RobotMap.drivetrainRightMotorMaster);
		rightSlave1  = new WPI_VictorSPX(RobotMap.drivetrainRightMotorSlave1);
		rightSlave2  = new WPI_VictorSPX(RobotMap.drivetrainRightMotorSlave2);
		
		/* Peak Current and Duration must be exceeded before current limit is activated.
		 * When activated, current will be limited to Continuous Current.
		 * Set Peak Current params to 0 if desired behavior is to immediately current-limit. 
		 * (10 ms timeout)*/
//		leftMaster.configPeakCurrentLimit(45, 10); /* 45 A */
//		leftMaster.configPeakCurrentDuration(200, 10); /* 200ms */
//		leftMaster.configContinuousCurrentLimit(40, 10); /* 40A */
//		leftMaster.enableCurrentLimit(true); /* turn it on */
//	
//		rightMaster.configPeakCurrentLimit(45, 10); /* 45 A */
//		rightMaster.configPeakCurrentDuration(200, 10); /* 200ms */
//		rightMaster.configContinuousCurrentLimit(40, 10); /* 40A */
//		rightMaster.enableCurrentLimit(true); /* turn it on */
		
		SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftMaster, leftSlave1, leftSlave2);
		SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightMaster, rightSlave1, rightSlave2);
		
		drivetrain = new DifferentialDrive(leftMotors, rightMotors);	// Initializes drivetrain class
		
		accelerometer 	= new BuiltInAccelerometer();	// Initializes the accelerometer from the roboRIO
		gyro 			= new ADXRS450_Gyro();			// Initializes the gyro
		gyro.reset();									// Resets the gyro so that it starts with a 0.0 value
		encoder = new Encoder(RobotMap.drivetrainEncoderARt, RobotMap.drivetrainEncoderBRt, Constants.DRIVETRAIN_USE_LEFT_ENCODER);
		encoder2 = new Encoder(RobotMap.drivetrainEncoderALeft, RobotMap.drivetrainEncoderBLeft, !Constants.DRIVETRAIN_USE_LEFT_ENCODER);
														// Initializes the encoder
		distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) /
						(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV);
												// Sets the distance per pulse of the encoder to read distance properly
		encoder.setDistancePerPulse(distancePerPulse);
		encoder.reset();								// Resets the encoder so that it starts with a 0.0 value
		encoder2.setDistancePerPulse(distancePerPulse);
		encoder2.reset();								// Resets the encoder so that it starts with a 0.0 value
		
//		opticalSensor  = new DigitalInput(7);
		opticalSensor1 = new DigitalInput(RobotMap.colorSensor);

		butterflySolenoid = new Solenoid(RobotMap.butterflyShift);
		gearShiftSolenoid = new DoubleSolenoid(RobotMap.shifterLow, RobotMap.shifterHigh);
		
		butterflySolenoid.set(false);
		butterflySolenoid.setPulseDuration(Constants.BUTTERFLY_PULSE_TIME);
    }
    

    /**
     * Initializes the default command for this subsystem
     */
    @Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
   
 
    /**
     * Drives the drivetrain using a forward-backward value and a rotation value
     * @param move
     * @param rotate
     */
    public void haloDrive(double move, double rotate, boolean usePID) {
    	// Checks whether drift correction is needed
    	
    	// Sets multiplier for max drive speed
    	move = move * Constants.DRIVE_MOTOR_MAX_SPEED;
    	rotate = rotate * Constants.ROTATE_MOTOR_MAX_SPEED;
    	
    	if (usePID) {
	    	if (rotate == 0.0 && move > 0.0) {
	    		// Enables the PID controller if it is not already
	    		if (!getPIDController().isEnabled()) {
	    			getPIDController().setPID(Constants.KpForward, Constants.KiForward, Constants.KdForward);
	    			getPIDController().reset();
	    			gyro.reset();
	    			enable();
	    			gyro.reset();
	    		}
	    		// Sets the forward move speed to the move parameter
	    		moveSpeed = move;
	    	} else if (rotate == 0.0 && move < 0.0){
	    		// Enables the PID controller if it is not already
	    		if (!getPIDController().isEnabled()) {
	    			getPIDController().setPID(Constants.KpBackward, Constants.KiBackward, Constants.KdBackward);
	    			getPIDController().reset();
	    			gyro.reset();
	    			enable();
	    			gyro.reset();
	    		}
	    		// Sets the forward move speed to the move parameter
	    		moveSpeed = move;
	    	} else {
	    		// Disables the PID controller if it enabled so the drivetrain can move freely
	    		if (getPIDController().isEnabled()) {
	    			getPIDController().reset();
	    		}
	    		drivetrain.arcadeDrive(move, rotate);
	    	}
    	} else {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnabled()) {
    			getPIDController().reset();
    		}
    		drivetrain.arcadeDrive(move, rotate);
    	}
    }
	
	public void haloDrive(double move, double rotate) {
		haloDrive(move, rotate, this.usePID);
	}
	
	
	/**
	 * Drive the drivetrain using curvature drive
	 * @param xSpeed
	 * @param zRotation
	 * @param isQuickTurn
	 */
    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    	drivetrain.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }
	
    
    /**
     * Drives the drivetrain using a left motor(s) value and a right motor(s) value
     * @param left
     * @param right
     */
    public void tankDrive(double left, double right) {
    	// Checks whether drift correction is needed
    	if (Math.abs(left - right) < Constants.TANK_STICK_TOLERANCE && left != 0.0 && right != 0.0) {
    		// Enables the PID controller if it is not already
    		if (!getPIDController().isEnabled()) {
    			gyro.reset();
    			getPIDController().reset();
    			enable();
    		}
    		// Sets the forward move speed to the average of the two sticks
    		moveSpeed = ((left + right) / 2);
    	} else {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnabled()) {
    			disable();
    		}
    		drivetrain.tankDrive(left, right);
    	}
    }

	public boolean getUsePID() {
		return usePID;
	}
	
	public void setUsePID(boolean usePID) {
		this.usePID = usePID;
	}
    
    public void stop() {
    	leftMaster.set(Constants.DRIVE_MOTOR_OFF);
    	rightMaster.set(Constants.DRIVE_MOTOR_OFF);
    }
    
    /**
     * Gets the degrees that the gyro is reading
     * @return
     */
    public double getAngle() {
    	return Math708.round(gyro.getAngle(),0);
//    	return gyro.getAngle();
    }
    
    /**
     * Resets the gyro reading
     */
    public void resetGyro() {
    	gyro.reset();
    }
    
    public double rotateByGyro(double targetAngle, double tolerance) {
    	double difference = getAngle() - targetAngle;
 
    	if (Math708.isWithinThreshold(gyro.getAngle(), targetAngle, tolerance)) {
    		difference = 0.0;
    	}
    	
    	return difference / targetAngle;
    }
    
    public double getIRDistance() {
    	return drivetrainIRSensor.getAverageDistance();
    }
    
    public double getSonarDistance() {
    	return drivetrainUltrasonicSensor.getClippedAverageDistance();
    }
    
    /**
     * Returns the move speed of the robot needed to get to a certain IR distance reading.
     * This assumes that the IR sensor is in the front of the robot.
     * @param targetDistance
     * @return
     */
    public double moveByIR(double targetDistance, double minSpeed, double maxSpeed, double tolerance) {
    	double current_location = getIRDistance();
    	
    	double value = Math708.getClippedPercentError(current_location, targetDistance, minSpeed, maxSpeed);
    	
    	if (value <= 0.0 || ((Math.abs(current_location - targetDistance)) <= tolerance)) {
    		
    		return 0.0;
    	}
    	return value;
    }

    /**
     * Returns the move speed of the robot needed to get to a certain Sonar distance reading.
     * This assumes that the Sonar sensor is in the front of the robot.
     * @param targetDistance
     * @return
     */
    public double moveByUltrasonic(double targetDistance, double minSpeed, double maxSpeed, double tolerance) {
    	double value = Math708.getClippedPercentError(getSonarDistance(), targetDistance, minSpeed, maxSpeed);
    	
    	if (value <= 0.0 || ((Math.abs(getSonarDistance() - targetDistance)) <= tolerance)) {
    		return 0.0;
    	}
    	return value;
    }
    

    public void toggleBrakeMode() {
    	brake = !brake;
    	if (brake) {
    		leftMaster.setNeutralMode(NeutralMode.Brake);
    		leftSlave1.setNeutralMode(NeutralMode.Brake);
    		leftSlave2.setNeutralMode(NeutralMode.Brake);
    		rightMaster.setNeutralMode(NeutralMode.Brake);
    		rightSlave1.setNeutralMode(NeutralMode.Brake);
    		rightSlave2.setNeutralMode(NeutralMode.Brake);
    	} else {
    		leftMaster.setNeutralMode(NeutralMode.Coast);
    		leftSlave1.setNeutralMode(NeutralMode.Coast);
    		leftSlave2.setNeutralMode(NeutralMode.Coast);
    		rightMaster.setNeutralMode(NeutralMode.Coast);
    		rightSlave1.setNeutralMode(NeutralMode.Coast);
    		rightSlave2.setNeutralMode(NeutralMode.Coast);
    	}
    }
    
    public void setBrakeMode(boolean setBrake) {
    	brake = setBrake;
    	if (brake) {
    		leftMaster.setNeutralMode(NeutralMode.Brake);
    		leftSlave1.setNeutralMode(NeutralMode.Brake);
    		leftSlave2.setNeutralMode(NeutralMode.Brake);
    		rightMaster.setNeutralMode(NeutralMode.Brake);
    		rightSlave1.setNeutralMode(NeutralMode.Brake);
    		rightSlave2.setNeutralMode(NeutralMode.Brake);
    	} else {
    		leftMaster.setNeutralMode(NeutralMode.Coast);
    		leftSlave1.setNeutralMode(NeutralMode.Coast);
    		leftSlave2.setNeutralMode(NeutralMode.Coast);
    		rightMaster.setNeutralMode(NeutralMode.Coast);
    		rightSlave1.setNeutralMode(NeutralMode.Coast);
    		rightSlave2.setNeutralMode(NeutralMode.Coast);
    	}
    }
    
    public void toggleButterfly()
    {
    	if(butterflySolenoid.get() == true)
    		butterflySolenoid.set(false);
    	else
    		butterflySolenoid.set(true);
    	switch_butterfly();
    }
    
    //Activate Butterfly Solenoid for a set duration
    public void pulseButterfly() {
    	butterflySolenoid.startPulse();
    }
    
    public void shiftGearForward() {
    	gearShiftSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void shiftGearReverse() {
    	gearShiftSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
        
    /**
     * Sets encoder direction depending on which side of the drivetrain it is on
     */
//    public void setEncoderReading() {
//    	encoder.setReverseDirection(Constants.DRIVETRAIN_USE_LEFT_ENCODER);
//    }
    
    public void setEncoderReading2() {
    	encoder.setReverseDirection(!Constants.DRIVETRAIN_USE_LEFT_ENCODER);
    }
    
    /**
     * 
     * @return Distance traveled since last encoder reset
     */
//    public double getEncoderDistance() {
//    	return encoder.getDistance();
//    }
    public double getEncoderDistance2() {
    	return encoder2.getDistance();
    }
    /**
     * Resets the encoder to 0.0
     */
    public void resetEncoder() {
    	encoder.reset();
    }
    public void resetEncoder2() {
    	encoder2.reset();
    }
    /**
     * Returns if the optical sensor detects the color white
     * @return
     */
//    public boolean isOpticalSensorWhite() {
//    	return !opticalSensor.get();
//    }
    
    public void setgear(boolean gear)
    {
    	gear_high=gear;
    }
    
    public void switch_butterfly()
    {
    	butterfly_on = !butterfly_on;
    }
    public boolean isOpticalSensor1White() {
    	return !opticalSensor1.get();
    }
    /**
     * Returns a process variable to the PIDSubsystem for correction
     */
    @Override
	protected double returnPIDInput() {
    	return gyro.getAngle();
    }
    
    /**
     * Performs actions using the robot to correct for any error using the outputed value
     */
    @Override
	protected void usePIDOutput(double output) {
        pidOutput = output;
        drivetrain.arcadeDrive(moveSpeed, -output);
    }
    
  
    
    
    /**
     * Sends data for this subsystem to the dashboard
     */
    public void sendToDashboard() {
    	if (Constants.DEBUG) {
	    	// Accelerometer Info
//	    	SmartDashboard.putNumber("Accelerometer X", accelerometer.getX());
//	    	SmartDashboard.putNumber("Accelerometer Y", accelerometer.getY());
//	    	SmartDashboard.putNumber("Accelerometer Z", accelerometer.getZ());
//	    	
//	    	SmartDashboard.putNumber("Gyro Rate", gyro.getRate());			// Gyro rate
//	    	SmartDashboard.putNumber("PID Output", pidOutput);			// PID Info
//	    	SmartDashboard.putNumber("DT Encoder Raw", encoder.get());		// Encoder raw count
//	    	SmartDashboard.putBoolean("Brake", brake);					// Brake or Coast
////	    	SmartDashboard.putNumber("DT IR Distance", getIRDistance());			// IR distance reading
//	    	
//	    	SmartDashboard.putNumber("DT Rt Master", rightMaster.getTemperature());
	    	SmartDashboard.putNumber("DT Rt Master", rightMaster.getOutputCurrent());
	    	SmartDashboard.putNumber("DT Rt Master", leftMaster.getOutputCurrent());
//	    	SmartDashboard.putNumber("DT Rt Slave", rightSlave.getTemperature());
//	    	SmartDashboard.putNumber("DT Lft Master", leftMaster.getTemperature());
//	    	SmartDashboard.putNumber("DT Lft Slave", leftSlave.getTemperature());
    	}
    	
//    	SmartDashboard.putNumber("DT Sonar Distance", getSonarDistance());		// Sonar distance reading
//    	SmartDashboard.putNumber("DT Encoder right Distance", encoder.getDistance());	// Encoder reading
    	SmartDashboard.putNumber("DT Encoder Distance", Math708.round(encoder2.getDistance(),2));		// Encoder reading
    	SmartDashboard.putNumber("Gyro angle", Math708.round(gyro.getAngle(),2));
    	SmartDashboard.putBoolean("Brake", brake);					// Brake or Coast
    	SmartDashboard.putBoolean("gear high", gear_high);					// Brake or Coast
    	SmartDashboard.putBoolean("butterfly", butterfly_on);
    	SmartDashboard.putBoolean("Optical1", isOpticalSensor1White());
//    	SmartDashboard.putNumber("DT Rt Master", rightMaster.getOutputCurrent());
//    	SmartDashboard.putNumber("DT Lt Master", leftMaster.getOutputCurrent());
    }
}
