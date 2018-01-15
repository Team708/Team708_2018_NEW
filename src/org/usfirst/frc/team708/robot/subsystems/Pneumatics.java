package org.usfirst.frc.team708.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Solenoid {
	public Pneumatics(int moduleNumber, int channel) {
		super(moduleNumber, channel);
		// TODO Auto-generated constructor stub
	}

	private final DoubleSolenoid ExampleSolenoid;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	
//	 public Solenoid(final int moduleNumber, final int channel) {
//		    super(moduleNumber);
}
