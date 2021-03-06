package org.usfirst.frc.team3786.robot.config.robot;


public class CompetitionRobotConfig extends RobotConfig {

	private static final int FRONT_RIGHT_MOTOR_CHANNEL = 16;
	private static final int FRONT_LEFT_MOTOR_CHANNEL = 17;
	private static final int BACK_RIGHT_MOTOR_CHANNEL = 5;
	private static final int BACK_LEFT_MOTOR_CHANNEL = 23;
	private static final int ARM_MOTOR_CHANNEL = 18;
	private static final int LIFTER_MOTOR_CHANNEL_LEFT = 15;
	private static final int LIFTER_MOTOR_CHANNEL_RIGHT = 14;
	private static final int CHUTE_MOTOR_CHANNEL = 0;
	
	//Encoders
	private static final int ARM_ENCODER_CODES_PER_REV = 360;
	private static final int X_OMNI_WHEEL_ENCODER_A = 1;
	private static final int X_OMNI_WHEEL_ENCODER_B = 2;
	private static final int Y_OMNI_WHEEL_ENCODER_A = 3;
	private static final int Y_OMNI_WHEEL_ENCODER_B = 4;
	
	//Camera
	private static final int LIGHTS_CHANNEL = 0;
	private static final int CAMERA_OFFSET = 20;
	
	//RG Feed Signal light
	private static final int FEED_SIGNAL_LIGHT = 22;
	
	//Gyro
	private static final int GYRO_CHANNEL = 0;	
	
	//PID values
	private static final double LIFTER_P = 5;
	private static final double LIFTER_I = 0;
	private static final double LIFTER_D = 0;
                                              //3 Tote	|	2 Tote	|	1 Tote	|	W/O tote 	| Quickly     
	private static final double ARM_P = 2000; //	|				|	1000	|	4000  		|	2000
	private static final double ARM_I = 0;    //	|				|	0		|	0			|	1
	private static final double ARM_D = 4;    //	|				|	2

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
	public int getLIFTER_MOTOR_CHANNEL_LEFT() {
		return LIFTER_MOTOR_CHANNEL_LEFT;
	}

	@Override
	public int getLIFTER_MOTOR_CHANNEL_RIGHT() {
		return LIFTER_MOTOR_CHANNEL_RIGHT;
	}

	
	@Override
	public int getCHUTE_MOTOR_CHANNEL() {
		return CHUTE_MOTOR_CHANNEL;
	}

	@Override
	public int getX_OMNI_WHEEL_ENCODER_A() {
		return X_OMNI_WHEEL_ENCODER_A;
	}

	@Override
	public int getY_OMNI_WHEEL_ENCODER_A() {
		return Y_OMNI_WHEEL_ENCODER_A;
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
	public int getFEED_SIGNAL_LIGHT() {
		return FEED_SIGNAL_LIGHT;
	}

	@Override
	public int getGYRO_CHANNEL() {
		return GYRO_CHANNEL;
	}

	@Override
	public int getLIGHT_CHANNEL() {
		return LIGHTS_CHANNEL;
	}

	@Override
	public double getCAMERA_OFF_SET() {
		return CAMERA_OFFSET;
	}

	@Override
	public int getARM_ENCODER_CODES_PER_REV() {
		return ARM_ENCODER_CODES_PER_REV;
	}

	@Override
	public double getARM_P() {
		return ARM_P;
	}

	@Override
	public double getARM_I() {
		return ARM_I;
	}

	@Override
	public double getARM_D() {
		return ARM_D;
	}

	@Override
	public int getY_OMNI_WHEEL_ENCODER_B() {
		return Y_OMNI_WHEEL_ENCODER_B;
	}
	@Override
	public int getX_OMNI_WHEEL_ENCODER_B() {
		return X_OMNI_WHEEL_ENCODER_B;
	}
}
