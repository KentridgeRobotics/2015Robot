package org.usfirst.frc.team3786.robot.config.ui;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 * The config to be used with the Cyborg controller
 * @author Driver Person
 *
 */
public class CyborgUI extends UIConfig {
	
	//Joystick information
	private final int JOYSTICK_CHANNEL = 0;
	private final AxisType ROTATION_AXIS = AxisType.kThrottle;
	
	//Buttons
	private final int SNAP_BUTTON = 4;
	private final int RETAIN_ANGLE_BUTTON = 3;
	private final int ARM_TO_ZERO_BTN = 1;
	private final int DROP_STACK_BUTTON = 2;
	private final int LIFT_UP_BUTTON = 5;
	private final int LIFT_DOWN_BUTTON = 6;
	
	//Rotation to snap to
	
	
	//Instance vars
	private final Joystick STICK;
	
	public CyborgUI()
	{
		STICK = new Joystick(JOYSTICK_CHANNEL);
	}

	@Override
	public double getDriveYValue() {
		return STICK.getY();
	}

	@Override
	public double getDriveXValue() {
		return STICK.getX();
	}

	@Override
	public double getDriveRotateValue() {
		return STICK.getAxis(ROTATION_AXIS);
	}

	@Override
	public boolean getSnapButton() {
		return STICK.getRawButton(SNAP_BUTTON);
	}

	@Override
	public boolean getRetainAngleButton() {
		return STICK.getRawButton(RETAIN_ANGLE_BUTTON);
	}

	@Override
	public double getAngleToRotateTo() {		
		return -1;
	}

	@Override
	public boolean getArmToZeroButton() {
		return STICK.getRawButton(ARM_TO_ZERO_BTN);
	}

	@Override
	public boolean getDropStackButton() {
		return STICK.getRawButton(DROP_STACK_BUTTON);
	}

	@Override
	public boolean getLifterUpButton() {
		return STICK.getRawButton(LIFT_UP_BUTTON);
	}

	@Override
	public boolean getLifterDownButton() {
		return STICK.getRawButton(LIFT_DOWN_BUTTON);
	}

	
}
