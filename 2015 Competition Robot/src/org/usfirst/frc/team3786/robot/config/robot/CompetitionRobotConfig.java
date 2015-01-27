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
	
	//Limit Switches
	
	//Tote detector
	private static final int LASER = 21;
	
	//RG Feed Signal light
	private static final int FEED_SIGNAL_LIGHT = 22;
	
	
	//PID values
	private static final double LIFTER_P = 0;
	private static final double LIFTER_I = 0;
	private static final double LIFTER_D = 0;

	@Override
	public int getFRONT_RIGHT_MOTOR_CHANNEL() {
		return FRONT_RIGHT_MOTOR_CHANNEL;
	}

	@Override
	public int getFRONT_LEFT_MOTOR_CHANNEL() {
		return FRONT_LEFT_MOTOR_CHANNEL;
	}

	@Override
	public int getBACK_RIGHT_MOTOR_CHANNEL() {
		return BACK_RIGHT_MOTOR_CHANNEL;
	}

	@Override
	public int getBACK_LEFT_MOTOR_CHANNEL() {
		return BACK_LEFT_MOTOR_CHANNEL;
	}

	@Override
	public int getARM_MOTOR_CHANNEL() {
		return ARM_MOTOR_CHANNEL;
	}

	@Override
	public int getLIFTER_MOTOR_CHANNEL() {
		return LIFTER_MOTOR_CHANNEL;
	}

	@Override
	public int getCHUTE_MOTOR_CHANNEL() {
		return CHUTE_MOTOR_CHANNEL;
	}

	@Override
	public int getPOTENTIOMETER_CHANNEL() {
		return POTENTIOMETER_CHANNEL;
	}

	@Override
	public int getX_OMNI_WHEEL_ENCODER() {
		return X_OMNI_WHEEL_ENCODER;
	}

	@Override
	public int getY_OMNI_WHEEL_ENCODER() {
		return Y_OMNI_WHEEL_ENCODER;
	}

	@Override
	public double getLIFTER_P() {
		return LIFTER_P;
	}
	
	@Override
	public double getLIFTER_I()
	{
		return LIFTER_I;
	}

	@Override
	public double getLIFTER_D()
	{
		return LIFTER_D;
	}
	
	@Override
	public int getLASER() {
		return LASER;
	}

	@Override
	public int getFEED_SIGNAL_LIGHT() {
		return FEED_SIGNAL_LIGHT;
	}
}
