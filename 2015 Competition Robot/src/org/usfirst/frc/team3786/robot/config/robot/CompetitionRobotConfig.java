package org.usfirst.frc.team3786.robot.config.robot;


public class CompetitionRobotConfig extends RobotConfig {

	private static final int FRONT_RIGHT_MOTOR_CHANNEL = 10;
	private static final int FRONT_LEFT_MOTOR_CHANNEL = 11;
	private static final int BACK_RIGHT_MOTOR_CHANNEL = 12;
	private static final int BACK_LEFT_MOTOR_CHANNEL = 13;
	private static final int ARM_MOTOR_CHANNEL = 14;
	private static final int LIFTER_MOTOR_CHANNEL = 15;
	private static final int CHUTE_MOTOR_CHANNEL = 16;
	
	//Potentiometer
	private static final int POTENTIOMETER_CHANNEL = 17;
	
	//Omni-wheels for X-Y distance
	private static final int X_OMNI_WHEEL_ENCODER = 18;
	private static final int Y_OMNI_WHEEL_ENCODER = 19;
	
	//Lifter encoder
	private static final int LIFTER_ENCODER_CHANNEL = 20;
	
	//Limit Switches
	
	//Tote detector
	private static final int LASER = 21;
	
	//RG Feed Signal light
	private static final int FEED_SIGNAL_LIGHT = 22;
	
	public CompetitionRobotConfig()
	{
		super(FRONT_RIGHT_MOTOR_CHANNEL, FRONT_LEFT_MOTOR_CHANNEL, 
				BACK_RIGHT_MOTOR_CHANNEL, BACK_LEFT_MOTOR_CHANNEL,
				ARM_MOTOR_CHANNEL, LIFTER_MOTOR_CHANNEL,
				CHUTE_MOTOR_CHANNEL, POTENTIOMETER_CHANNEL,
				X_OMNI_WHEEL_ENCODER, Y_OMNI_WHEEL_ENCODER,
				LIFTER_ENCODER_CHANNEL, LASER,
				FEED_SIGNAL_LIGHT);
	}
}
