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
//		instance = new PracticeRobotConfig();
		instance = new CompetitionRobotConfig();
	}
	
	/**
	 * @return The singleton instance of the config;
	 */
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
	 * Get the channel that the left lifter motor is on.
	 * @return The left lifter motor channel.
	 */
	public abstract int getLIFTER_MOTOR_CHANNEL_LEFT();
	
	/**
	 * Get the channel that the left lifter motor is on.
	 * @return The left lifter motor channel.
	 */
	public abstract int getLIFTER_MOTOR_CHANNEL_RIGHT();

	/**
	 * Get the channel that the chute motor is on.
	 * @return The motor channel.
	 */
	public abstract int getCHUTE_MOTOR_CHANNEL();

	/**
	 * Get the channel that the X value omni-wheel's encoder is on.
	 * @return The encoder channel.
	 */
	public abstract int getX_OMNI_WHEEL_ENCODER_A();

	/**
	 * @return The B channel of the X omni-wheel
	 */
	public abstract int getX_OMNI_WHEEL_ENCODER_B();
	
	/**
	 * Get the channel that the Y value omni-wheel's encoder is on.
	 * @return The A channel.
	 */
	public abstract int getY_OMNI_WHEEL_ENCODER_A();
	
	/**
	 * @return The B channel of the Y omni-wheel
	 */
	public abstract int getY_OMNI_WHEEL_ENCODER_B();

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
	 * @return The P value for the Arm's PID.
	 */
	public abstract double getARM_P();
	
	/**
	 * @return The I value for the Arm's PID.
	 */
	public abstract double getARM_I();
	
	/**
	 * @return The D value for the Arm's PID.
	 */
	public abstract double getARM_D();

	/**
	 * Get the channel that the feed signal light is on.
	 * @return The signal light channel.
	 */
	public abstract int getFEED_SIGNAL_LIGHT();

	/**
	 * @return The channel of the gyro
	 */
	public abstract int getGYRO_CHANNEL();
	
	/**
	 * @return The channel that the relay for the lights is on.
	 */
	public abstract int getLIGHT_CHANNEL();
	
	/**
	 * @return The number of pixels the camera is off by.
	 */
	public abstract double getCAMERA_OFF_SET();
	
	public abstract int getARM_ENCODER_CODES_PER_REV();
}
