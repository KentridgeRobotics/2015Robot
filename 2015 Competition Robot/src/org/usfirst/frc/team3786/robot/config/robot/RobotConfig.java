package org.usfirst.frc.team3786.robot.config.robot;


/**
 * Configuration system for the robot.
 * Extend this for each different robot setup.
 * (e.g. PracticeRobotConfig, CompetitionRobotConfig)
 * @author Driver Person
 *
 */
public class RobotConfig {

	/**
	 * Single location to change RobotConfig type
	 */
	private static RobotConfig instance;
	
	static
	{
		instance = new CompetitionRobotConfig();
	}
	
	//Motor controller channels
	private final int FRONT_RIGHT_MOTOR_CHANNEL;
	private final int FRONT_LEFT_MOTOR_CHANNEL;
	private final int BACK_RIGHT_MOTOR_CHANNEL;
	private final int BACK_LEFT_MOTOR_CHANNEL;
	private final int ARM_MOTOR_CHANNEL;
	private final int LIFTER_MOTOR_CHANNEL;
	private final int CHUTE_MOTOR_CHANNEL;
	
	//Potentiometer
	private final int POTENTIOMETER_CHANNEL;
	
	//Omni-wheels for X-Y distance
	private final int X_OMNI_WHEEL_ENCODER;
	private final int Y_OMNI_WHEEL_ENCODER;
	
	//Lifter encoder
	private final int LIFTER_ENCODER_CHANNEL;
	
	//Limit Switches
	
	//Tote detector
	private final int LASER;
	
	//RG Feed Signal light
	private final int FEED_SIGNAL_LIGHT;
	
	protected RobotConfig(int frontRightChannel, int frontLeftChannel, 
			int backRightChannel, int backLeftChannel,
			int armMotorChannel, int lifterMotorChannel,
			int chuteMotorChannel, int potentiometerChannel,
			int xOmniWheelEncoder, int yOmniWheelEncoder,
			int lifterEncoderChannel, int laserChannel, 
			int feedSignalLight)
	{
		FRONT_RIGHT_MOTOR_CHANNEL = frontRightChannel;
		FRONT_LEFT_MOTOR_CHANNEL = frontLeftChannel;
		BACK_RIGHT_MOTOR_CHANNEL = backRightChannel;
		BACK_LEFT_MOTOR_CHANNEL = backLeftChannel;
		ARM_MOTOR_CHANNEL = armMotorChannel;
		LIFTER_MOTOR_CHANNEL = lifterMotorChannel;
		CHUTE_MOTOR_CHANNEL = chuteMotorChannel;
		
		POTENTIOMETER_CHANNEL = potentiometerChannel;
		
		X_OMNI_WHEEL_ENCODER = xOmniWheelEncoder;
		Y_OMNI_WHEEL_ENCODER = yOmniWheelEncoder;
		
		LIFTER_ENCODER_CHANNEL = lifterEncoderChannel;
		
		LASER = laserChannel;
		
		FEED_SIGNAL_LIGHT = feedSignalLight;
	}
	
	public static RobotConfig get()
	{		
		return instance;
	}

	public int getFRONT_RIGHT_MOTOR_CHANNEL() {
		return FRONT_RIGHT_MOTOR_CHANNEL;
	}

	public int getFRONT_LEFT_MOTOR_CHANNEL() {
		return FRONT_LEFT_MOTOR_CHANNEL;
	}

	public int getBACK_RIGHT_MOTOR_CHANNEL() {
		return BACK_RIGHT_MOTOR_CHANNEL;
	}

	public int getBACK_LEFT_MOTOR_CHANNEL() {
		return BACK_LEFT_MOTOR_CHANNEL;
	}

	public int getARM_MOTOR_CHANNEL() {
		return ARM_MOTOR_CHANNEL;
	}

	public int getLIFTER_MOTOR_CHANNEL() {
		return LIFTER_MOTOR_CHANNEL;
	}

	public int getCHUTE_MOTOR_CHANNEL() {
		return CHUTE_MOTOR_CHANNEL;
	}

	public int getPOTENTIOMETER_CHANNEL() {
		return POTENTIOMETER_CHANNEL;
	}

	public int getX_OMNI_WHEEL_ENCODER() {
		return X_OMNI_WHEEL_ENCODER;
	}

	public int getY_OMNI_WHEEL_ENCODER() {
		return Y_OMNI_WHEEL_ENCODER;
	}

	public int getLIFTER_ENCODER_CHANNEL() {
		return LIFTER_ENCODER_CHANNEL;
	}

	public int getLASER() {
		return LASER;
	}

	public int getFEED_SIGNAL_LIGHT() {
		return FEED_SIGNAL_LIGHT;
	}
}
