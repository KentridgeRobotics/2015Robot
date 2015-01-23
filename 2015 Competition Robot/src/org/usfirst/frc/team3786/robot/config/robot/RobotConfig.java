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
	
	public static RobotConfig get()
	{		
		return instance;
	}

	/**
	 * Get the channel that the front right motor is on.
	 * @return The motor channel.
	 */
	public abstract int getFRONT_RIGHT_MOTOR_CHANNEL();

	/**
	 * Get the channel that the front left motor is on.
	 * @return The motor channel.
	 */
	public abstract int getFRONT_LEFT_MOTOR_CHANNEL();

	/**
	 * Get the channel that the back right motor is on.
	 * @return The motor channel.
	 */
	public abstract int getBACK_RIGHT_MOTOR_CHANNEL();

	/**
	 * Get the channel that the back left motor is on.
	 * @return The motor channel.
	 */
	public abstract int getBACK_LEFT_MOTOR_CHANNEL();

	/**
	 * Get the channel that the arm motor is on.
	 * @return The motor channel.
	 */
	public abstract int getARM_MOTOR_CHANNEL();

	/**
	 * Get the channel that the lifter motor is on.
	 * @return The motor channel.
	 */
	public abstract int getLIFTER_MOTOR_CHANNEL();

	/**
	 * Get the channel that the chute motor is on.
	 * @return The motor channel.
	 */
	public abstract int getCHUTE_MOTOR_CHANNEL();

	/**
	 * Get the channel that the potentiometer is on.
	 * @return The potentiometer channel.
	 */
	public abstract int getPOTENTIOMETER_CHANNEL();

	/**
	 * Get the channel that the X value omni-wheel's encoder is on.
	 * @return The encoder channel.
	 */
	public abstract int getX_OMNI_WHEEL_ENCODER();

	/**
	 * Get the channel that the Y value omni-wheel's encoder is on.
	 * @return The encoder channel.
	 */
	public abstract int getY_OMNI_WHEEL_ENCODER();

	/**
	 * @return The P value for the Lifter's PID.
	 */
	public abstract double getLIFTER_P();
	
	/**
	 * @return The I value for the Lifter's PID.
	 */
	public abstract double getLIFTER_I();
	
	/**
	 * @return The D value for the Lifter's PID.
	 */
	public abstract double getLIFTER_D();

	/**
	 * Get the channel that the laser is on.
	 * @return The laser channel.
	 */
	public abstract int getLASER();

	/**
	 * Get the channel that the feed signal light is on.
	 * @return The signal light channel.
	 */
	public abstract int getFEED_SIGNAL_LIGHT();
}
