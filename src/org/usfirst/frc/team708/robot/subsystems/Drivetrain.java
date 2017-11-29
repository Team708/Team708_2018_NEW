package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;

import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.visionProcessor.SonarOverride;
import org.usfirst.frc.team708.robot.util.HatterDrive;
import org.usfirst.frc.team708.robot.util.IRSensor;
import org.usfirst.frc.team708.robot.util.UltrasonicSensor;
import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CanTalonJNI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
//import edu.wpi.first.wpilibj.GyroBase;
//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drivetrain extends PIDSubsystem {
	

	private ADXRS450_Gyro gyro;
	private int count = 0;
	
	// Variables specific for drivetrain PID loop
	private boolean usePID 		= false;
	private double moveSpeed 	= 0.0;
	private double pidOutput 	= 0.0;
	
	private CANTalon leftMaster, leftSlave, rightMaster, rightSlave;	// Motor Controllers

	private HatterDrive drivetrain;						// FRC provided drivetrain class
	
	private Encoder encoder;						// Encoder for the drivetrain
	private Encoder encoder2;						// Encoder for the drivetrain

	private double distancePerPulse;
//	private BuiltInAccelerometer accelerometer;				// Accelerometer that is built into the roboRIO
	
//	private IRSensor         	drivetrainIRSensor;			// IR Sensor for <=25inches
	private UltrasonicSensor 	drivetrainUltrasonicSensor;	// Sonar used for <=21feet
//	private DigitalInput     	opticalSensor;
//	private DigitalInput		gearSensor;
	
	public int sonarOverride 	= 0;		//0 = default, 1 = high, 2 = low; Used for overriding sonar
	private boolean brake 		= true;		// Whether the talons should be in coast or brake mode
	private boolean nobrake 	= false;	// Whether the talons should be in coast or brake mode

    public static Solenoid			pwr0;
    public static Solenoid			pwr1;
    public static Solenoid			pwr2;
    public static Solenoid			pwr3;    
    public static Solenoid			gearLight;    
    public static Solenoid			boilerLight;    

    public Drivetrain() {
    	// Passes variables from this class into the superclass constructor
    	super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
    	
    gyro = new ADXRS450_Gyro();						// Initializes the gyro
    gyro.reset();									// Resets the gyro so that it starts with a 0.0 value
    
 
    	// Initializes motor controllers with device IDs from RobotMap
	leftMaster  = new CANTalon(RobotMap.drivetrainLeftMotorMaster);
	leftSlave   = new CANTalon(RobotMap.drivetrainLeftMotorSlave);
	rightMaster = new CANTalon(RobotMap.drivetrainRightMotorMaster);
	rightSlave  = new CANTalon(RobotMap.drivetrainRightMotorSlave);
	
	drivetrain = new HatterDrive(leftMaster, rightMaster, Constants.DRIVE_USE_SQUARED_INPUT);		// Initializes drivetrain class
	
	setupMasterSlave();								// Sets up master and slave
		
//	accelerometer 	= new BuiltInAccelerometer();	// Initializes the accelerometer from the roboRIO
	
	encoder = new Encoder(RobotMap.drivetrainEncoderARt, RobotMap.drivetrainEncoderBRt, Constants.DRIVETRAIN_USE_LEFT_ENCODER);
//	encoder = new Encoder(leftMaster.getPinStateQuadA(), leftMaster.getPinStateQuadB(), Constants.DRIVETRAIN_USE_LEFT_ENCODER);
	
	encoder2 = new Encoder(RobotMap.drivetrainEncoderALeft, RobotMap.drivetrainEncoderBLeft, !Constants.DRIVETRAIN_USE_LEFT_ENCODER);
//	encoder2 = new Encoder(rightMaster.getPinStateQuadA(), rightMaster.getPinStateQuadB(), !Constants.DRIVETRAIN_USE_LEFT_ENCODER);
	
	distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) /
				                                    	(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV);

	leftMaster.configEncoderCodesPerRev(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV);
	rightMaster.configEncoderCodesPerRev(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV);

	encoder.setDistancePerPulse(distancePerPulse);  // Sets the distance per pulse of the encoder to read distance properly
	encoder.reset();								//	Resets the encoder so that it starts with a 0.0 value

	encoder2.setDistancePerPulse(distancePerPulse);
	encoder2.reset();								
	
	leftMaster.enableBrakeMode(brake);
	leftSlave.enableBrakeMode(brake);
	rightMaster.enableBrakeMode(brake);
	rightSlave.enableBrakeMode(brake);
	
//	drivetrainIRSensor 			= new IRSensor(RobotMap.gearIRSensor, IRSensor.GP2Y0A21YK0F);
	drivetrainUltrasonicSensor 	= new UltrasonicSensor(RobotMap.dtSonar, UltrasonicSensor.MB1010);

//  gearSensor = new DigitalInput(RobotMap.gearSensorSwitch);
    
    pwr0 			= new Solenoid(RobotMap.PWR0);
    pwr1 			= new Solenoid(RobotMap.PWR1);
    pwr2 			= new Solenoid(RobotMap.PWR2);
    pwr3 			= new Solenoid(RobotMap.PWR3);
    gearLight  		= new Solenoid(RobotMap.GEARLIGHT);
    boilerLight		= new Solenoid(RobotMap.BOILERLIGHT);

    pwr0.set(true);
    pwr1.set(true);
    pwr2.set(true);
    pwr3.set(true);
    
//    setGearLight(true);  
//    setBoilerLight(true); 
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
    	
    	if (usePID) 
    	    {
	        // Enables the PID controller if it is not already
	    	if (!getPIDController().isEnabled()) 
	    	    {
	    		getPIDController().setPID(Constants.Kp, Constants.Ki, Constants.Kd);
	    		getPIDController().reset();
	    		enable();
	    		}
	    	else 
	    	    {
	    		// Disables the PID controller if it enabled so the drivetrain can move freely
	    		if (getPIDController().isEnabled()) 
	    		    {
	    			getPIDController().reset();
	    		    }
	    	    }
	    	drivetrain.arcadeDrive(move, rotate);
    	    } 
    	    else 
    	    {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnabled()) 
    		    {
    			getPIDController().reset();
    		    }
    		drivetrain.arcadeDrive(move, rotate);
    	    }
    }
	
	public void haloDrive(double move, double rotate) {
		haloDrive(move, rotate, this.usePID);
	}

	public void setGearLight(boolean on) {
	    gearLight.set(on);
	}
	
	public void setBoilerLight(boolean on) {
		boilerLight.set(on);
	}
	
	public boolean getUsePID() {
		return usePID;
	}
	
	public void setUsePID(boolean usePID) {
		this.usePID = usePID;
	}
    
    public void stop() {
    	leftMaster.set(Constants.MOTOR_OFF);
    	rightMaster.set(Constants.MOTOR_OFF);
    }
    
    /**
     * Gets the degrees that the gyro is reading
     * @return
     */
    public double getAngle() {
    	return -gyro.getAngle(); //gyro is mounted upside down
    }
    
    /**
     * Resets the gyro reading
     */
    public void resetGyro() {
//    	count++;
//    	SmartDashboard.putNumber("resetgyro: ", count);
    	gyro.reset();
    }
    
//    public boolean hasGear() {
//    	return gearSensor.get();
//  
//    }
    
    public double rotateByGyro(double targetAngle, double tolerance) {
    	double difference = getAngle() - targetAngle;
 
    	if (Math708.isWithinThreshold(gyro.getAngle(), targetAngle, tolerance)) {
    		difference = 0.0;
    	}
    	
    	return difference / targetAngle;
    }
    
//    public double getIRDistance() {
//    	return drivetrainIRSensor.getAverageDistance();
//    }
    
    public double getSonarDistance() {
    	return drivetrainUltrasonicSensor.getClippedAverageDistance();
    }
    
    /**
     * Returns the move speed of the robot needed to get to a certain IR distance reading.
     * This assumes that the IR sensor is in the front of the robot.
     * @param targetDistance
     * @return
     */
//    public double moveByIR(double targetDistance, double minSpeed, double maxSpeed, double tolerance) {
//    	double current_location = getIRDistance();
//    	
//    	double value = Math708.getClippedPercentError(current_location, targetDistance, minSpeed, maxSpeed);
//    	
//    	if (value <= 0.0 || ((Math.abs(current_location - targetDistance)) <= tolerance)) {
//    		
//    		return 0.0;
//    	}
//    	return value;
//    }

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
        
    
    /**
     * Sets up the drivetrain motors to have a master that is controlled by the 
     * default FRC RobotDrive class and slaves that do whatever the master
     * talon is doing
     */
    public void setupMasterSlave() {
    	leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		leftSlave.set(leftMaster.getDeviceID());
		rightSlave.set(rightMaster.getDeviceID());
    }
    
    /**
     * Toggles between brake and coast mode for the talons
     */
    public void toggleBrakeMode() {
    	brake = !brake;
    	leftMaster.enableBrakeMode(brake);
    	leftSlave.enableBrakeMode(brake);
    	rightMaster.enableBrakeMode(brake);
    	rightSlave.enableBrakeMode(brake);
    }
    
    public void setBrakeMode(Boolean brake) {
    	leftMaster.enableBrakeMode(brake);
    	leftSlave.enableBrakeMode(brake);
    	rightMaster.enableBrakeMode(brake);
    	rightSlave.enableBrakeMode(brake);
    }
    /**
     * Sets encoder direction depending on which side of the drivetrain it is on
     */
    public void setEncoderReading() {
    	encoder.setReverseDirection(Constants.DRIVETRAIN_USE_LEFT_ENCODER);
 //   	leftMaster.setInverted(true);
    }
    
    public void setEncoderReading2() {
    	encoder2.setReverseDirection(!Constants.DRIVETRAIN_USE_LEFT_ENCODER);
//    	rightMaster.setInverted(true);
    }
    
    /**
     * 
     * @return Distance traveled since last encoder reset
     */
    public double getEncoderDistance() {
    	return encoder.getDistance();
//   	    return leftMaster.getEncPosition();
   	    

    }
    public double getEncoderDistance2() {
    	return encoder2.getDistance();
//   	    return rightMaster.getEncPosition();
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
//    	return opticalSensor.get();
//    }
    
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
    
    public int getAlliance(){
    	return Robot.allianceColor;
    }
    
    public void setAlliance(int c)
    {
    	Robot.allianceColor = c;
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
//	    	SmartDashboard.putNumber("PID Output", pidOutput);				// PID Info
//	    	SmartDashboard.putNumber("DT Encoder Raw", encoder.get());		// Encoder raw count
//	    	SmartDashboard.putNumber("DT IR Distance", getIRDistance());	// IR distance reading
//	    	
//	    	SmartDashboard.putNumber("DT Rt Master", rightMaster.getTemperature());
//	    	SmartDashboard.putNumber("DT Rt Slave", rightSlave.getTemperature());
//	    	SmartDashboard.putNumber("DT Lft Master", leftMaster.getTemperature());
//	    	SmartDashboard.putNumber("DT Lft Slave", leftSlave.getTemperature());
//    	SmartDashboard.putNumber("Sonar Mode", sonarOverride);		
//    		SmartDashboard.putNumber("DT Encoder 1 Distance", encoder.getDistance());	// Encoder reading
    		SmartDashboard.putNumber("DT Encoder 2 Distance", encoder2.getDistance());	// Encoder reading
    	}
    	
    	SmartDashboard.putBoolean("Brake", brake);						// Brake or Coast
    	SmartDashboard.putNumber("AllianceColor", getAlliance());
    	SmartDashboard.putNumber("Gyro angle", ( (int)getAngle()));			// Gyro angle
//    	SmartDashboard.putNumber("DT Sonar Distance", getSonarDistance());			// Sonar distance reading
    }
}
