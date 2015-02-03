package org.usfirst.frc.team3786.robot.config.ui;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamePadUI extends UIConfig {

	//Gamepad
	private final int JOYSTICK_CHANNEL = 0;
	private final int SECOND_STICK_X = 5;
	private final int SECOND_STICK_Y = 4;
	private final Joystick STICK;
	
	//Buttons
	private final int RETAIN_ANGLE_BUTTON = 6;
	private final int ARM_TO_ZERO_BUTTON = 8;
	private final int DROP_STACK_BUTTON = 7;
	
	private final int ZERO_DEGREES_BTN = 4;
	private final int NINETY_DEGREES_BTN = 2;
	private final int ONE_EIGHTY_BTN = 1;
	private final int TWO_SEVENTY_BTN = 3;
	
	public GamePadUI()
	{
		STICK = new Joystick(JOYSTICK_CHANNEL);
	}
	
	@Override
	public double getDriveYValue() {
		return STICK.getY();
	}

	@Override
	public double getDriveXValue() {
		return -STICK.getX();
	}

	@Override
	public double getDriveRotateValue() {
		return STICK.getZ();
	}

	@Override
	public boolean getRetainAngleButton() {
		return STICK.getRawButton(RETAIN_ANGLE_BUTTON);
	}

	@Override
	public double getAngleToRotateTo() {
		if (STICK.getRawButton(ZERO_DEGREES_BTN))
		{
			return 0;
		}
		if (STICK.getRawButton(NINETY_DEGREES_BTN))
		{
			return 90;
		}
		if (STICK.getRawButton(ONE_EIGHTY_BTN))
		{
			return 180;
		}
		if (STICK.getRawButton(TWO_SEVENTY_BTN))
		{
			return 270;
		}
		
		
		
		double XVal = -STICK.getRawAxis(SECOND_STICK_X);
		double YVal = STICK.getRawAxis(SECOND_STICK_Y);
		
		
		double angle = Math.toDegrees(Math.atan2(YVal, XVal));
		SmartDashboard.putNumber("Gamepad Angle", angle);
		
		return angle;
	}

	@Override
	public boolean getArmToZeroButton() {
		return STICK.getRawButton(ARM_TO_ZERO_BUTTON);
	}

	@Override
	public boolean getDropStackButton() {
		return STICK.getRawButton(DROP_STACK_BUTTON);
	}

	@Override
	public boolean getLifterUpButton() {
		return false;
	}

	@Override
	public boolean getLifterDownButton() {
		// TODO Auto-generated method stub
		return false;
	}

}
