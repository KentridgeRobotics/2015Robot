package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopArmCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    
	private static Arm instance;
	
	private CANJaguar armMotor;
	
	private static final double UP_POSITION = 0;
	private static final double DOWN_POSITION = 113;
	private static final double LIFT_POSITION = 0;
	
	private static final double PICK_UP_POSITION = 113 - 12;
	
	private static final double DROP_INCREMENT = 12;
	private static final double STEP_POSITION = 113 - 60;
	
	private static final double MOVEMENT_TOLERANCE = 4;
	
	private Arm()
	{
		armMotor = new CANJaguar(RobotConfig.get().getARM_MOTOR_CHANNEL());

		armMotor.setPositionMode(CANJaguar.kQuadEncoder, RobotConfig.get().getARM_ENCODER_CODES_PER_REV(), RobotConfig.get().getARM_P(), RobotConfig.get().getARM_I(), RobotConfig.get().getARM_D());
		
//		armMotor.setSpeedMode(CANJaguar.kQuadEncoder, RobotConfig.get().getARM_ENCODER_CODES_PER_REV(), RobotConfig.get().getARM_P(), RobotConfig.get().getARM_I(), RobotConfig.get().getARM_D());
		
		armMotor.enableControl();
	}
	
	/**
	 * @return The singleton instance of the Arm
	 */
	public static Arm getInstance()
	{
		if (instance == null)
		{
			instance = new Arm();
		}
		
		return instance;
	}

	/**
	 * @param position The position to drive the arm to in degrees
	 */
	public void moveArm(double position)
	{
//		System.out.println("Position in Deg set to: " + position);
		position /= 360.0;
		
		System.out.println("Setting to: " + position);
		
		armMotor.set(-position);
	}
	
	public void moveArmToUpPosition()
	{
		armMotor.set(UP_POSITION);
	}
	
	/**
	 * @return The current position of the arm in degrees
	 */
	public double getPosition()
	{
		System.out.println("Current Position: " + armMotor.getPosition());
		
		return -(armMotor.getPosition() * 360.0);
	}
	
	public static double getUP_POSITION()
	{
		return UP_POSITION;
	}
	
	public static double getDOWN_POSITION()
	{
		return DOWN_POSITION;
	}
	
	public static double getLIFT_POSITION()
	{
		return LIFT_POSITION;
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopArmCommand());
    }

	public static double getDROP_INCREMENT() {
		return DROP_INCREMENT;
	}

	public static double getSTEP_POSITION() {
		return STEP_POSITION;
	}

	public static double getPICK_UP_POSITION() {
		return PICK_UP_POSITION;
	}
	
	public static double getMOVEMENT_TOLERANCE() {
		return MOVEMENT_TOLERANCE;
	}
}

