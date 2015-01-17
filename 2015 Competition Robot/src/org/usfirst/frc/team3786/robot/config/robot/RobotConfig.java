package org.usfirst.frc.team3786.robot.config.robot;


/**
 * Configuration system for the robot.
 * Extend this for each different robot setup.
 * (e.g. PracticeRobotConfig, CompetitionRobotConfig)
 * @author Driver Person
 *
 */
public abstract class RobotConfig {

	/**
	 * Single location to change RobotConfig type
	 */
	private static RobotConfig instance;
	
	static
	{
		instance = new CompetitionRobotConfig();
	}
	
	protected RobotConfig()
	{
	}
	
	public static RobotConfig get()
	{		
		return instance;
	}

	public abstract int getFRONT_RIGHT_MOTOR_CHANNEL();

	public abstract int getFRONT_LEFT_MOTOR_CHANNEL();

	public abstract int getBACK_RIGHT_MOTOR_CHANNEL();

	public abstract int getBACK_LEFT_MOTOR_CHANNEL();

	public abstract int getARM_MOTOR_CHANNEL();

	public abstract int getLIFTER_MOTOR_CHANNEL();

	public abstract int getCHUTE_MOTOR_CHANNEL();

	public abstract int getPOTENTIOMETER_CHANNEL();

	public abstract int getX_OMNI_WHEEL_ENCODER();

	public abstract int getY_OMNI_WHEEL_ENCODER();

	public abstract int getLIFTER_ENCODER_CHANNEL();

	public abstract int getLASER();

	public abstract int getFEED_SIGNAL_LIGHT() ;
}
