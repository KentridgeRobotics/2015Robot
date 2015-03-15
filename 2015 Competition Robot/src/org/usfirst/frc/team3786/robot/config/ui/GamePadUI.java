package org.usfirst.frc.team3786.robot.config.ui;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamePadUI extends UIConfig {

	//Gamepad
	private final int GAMEPAD_CHANNEL = 0;
	private final int SECOND_STICK_X = 4;
	private final int SECOND_STICK_Y = 5;
	private final Joystick GAMEPAD;
	
	//Operator Controls
	private final int JOYSTICK_CHANNEL = 1;
	private final Joystick JOYSTICK;
	
	//Alternative Drive
	private final int RIGHT_STATION_BUTTON = 6;
	private final int LEFT_STATION_BUTTON = 5;
	
	//Buttons
	private final int CLOCKWISE_BUTTON = 6;
	private final int COUNTER_CLOCKWISE_BUTTON = 5;
	
	private final int ARM_TO_UP_BUTTON = 8;
	private final int DROP_STACK_BUTTON = 9;
	
	private final int ZERO_DEGREES_BTN = 1;
	private final int NINETY_DEGREES_BTN = 3;
	private final int ONE_EIGHTY_BTN = 4;
	private final int TWO_SEVENTY_BTN = 2;
	
	private final int LIFTER_UP_BUTTON = 3;
	
	private final int LIFTER_DOWN_BUTTON = 2;

	private final int ARM_UP_BUTTON = 7;
	private final int ARM_DOWN_BUTTON = 6;
	
	private final int RETAIN_ANGLE_BUTTON = 10;
	
	public GamePadUI()
	{
		GAMEPAD = new Joystick(GAMEPAD_CHANNEL);
		JOYSTICK = new Joystick(JOYSTICK_CHANNEL);
	}
	
	@Override
	public double getDriveYValue() {
		return GAMEPAD.getY();
	}

	@Override
	public double getDriveXValue() {
		return GAMEPAD.getX();
	}

	@Override
	public double getDriveRotateValue() {
		return GAMEPAD.getZ();
	}

	@Override
	public boolean getSnapCWButton() {
		return GAMEPAD.getRawButton(CLOCKWISE_BUTTON);
	}

	@Override
	public boolean getSnapCCWButton() {
		return GAMEPAD.getRawButton(COUNTER_CLOCKWISE_BUTTON);
	}

	@Override
	public double getAngleToRotateTo() {
		if (GAMEPAD.getRawButton(ZERO_DEGREES_BTN))
		{
			if (GAMEPAD.getRawButton(NINETY_DEGREES_BTN))
			{
				return 45;
			}
			else if (GAMEPAD.getRawButton(TWO_SEVENTY_BTN))
			{
				return 315;
			}
			return 0;
		}
		if (GAMEPAD.getRawButton(NINETY_DEGREES_BTN))
		{
			if (GAMEPAD.getRawButton(ONE_EIGHTY_BTN))
			{
				return 135;
			}
			return 90;
		}
		if (GAMEPAD.getRawButton(ONE_EIGHTY_BTN))
		{
			if (GAMEPAD.getRawButton(TWO_SEVENTY_BTN))
			{
				return 225;
			}
			return 180;
		}
		if (GAMEPAD.getRawButton(TWO_SEVENTY_BTN))
		{
			return 270;
		}
		
		double XVal = GAMEPAD.getRawAxis(SECOND_STICK_X);
		double YVal = -GAMEPAD.getRawAxis(SECOND_STICK_Y);
		
		SmartDashboard.putNumber("X Val", XVal);
		SmartDashboard.putNumber("YVal", YVal);
		
		double temp = XVal;
		XVal = -YVal;
		YVal = -temp;
		
		if (Math.abs(XVal) <= 0.05 && Math.abs(YVal) <= 0.05)
		{
			return -1;
		}
		
		double angle = Math.toDegrees(Math.atan2(YVal, XVal));
		
		return angle;
	}

	@Override
	public boolean getArmToUpButton() {
		return JOYSTICK.getRawButton(ARM_TO_UP_BUTTON);
	}

	@Override
	public boolean getDropStackButton() {
		return JOYSTICK.getRawButton(DROP_STACK_BUTTON);
	}

	@Override
	public boolean getLifterUpButton() {
		return JOYSTICK.getRawButton(LIFTER_UP_BUTTON);
	}

	@Override
	public boolean getLifterDownButton() {
		return JOYSTICK.getRawButton(LIFTER_DOWN_BUTTON);
	}

	@Override
	public boolean getRetainAngleButton() {
		return GAMEPAD.getRawButton(RETAIN_ANGLE_BUTTON);
	}

	@Override
	public boolean getArmUpButton() {
		return JOYSTICK.getRawButton(ARM_UP_BUTTON);
	}

	@Override
	public boolean getArmDownButton() {
		return JOYSTICK.getRawButton(ARM_DOWN_BUTTON);
	}

	@Override
	public boolean getRightStationButton()
	{
		return GAMEPAD.getRawButton(RIGHT_STATION_BUTTON);
	}
	
	@Override
	public boolean getLeftStationButton()
	{
		return GAMEPAD.getRawButton(LEFT_STATION_BUTTON);
	}
	
	@Override
	public double getAlternateDriveX()
	{
		if (GAMEPAD.getPOV(0) == -1)
		{
			return 0;
		}
		return Math.sin(Math.toRadians(GAMEPAD.getPOV(0)));
	}
	
	@Override
	public double getAlternateDriveY()
	{
		if (GAMEPAD.getPOV(0) == -1)
		{
			return 0;
		}
		return Math.cos(Math.toRadians(GAMEPAD.getPOV(0)));
	}

	@Override
	public boolean getArmToStepButton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getArmDropButton() {
		// TODO Auto-generated method stub
		return false;
	}
}
