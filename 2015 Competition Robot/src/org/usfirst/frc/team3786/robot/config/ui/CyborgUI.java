package org.usfirst.frc.team3786.robot.config.ui;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 * The config to be used with the Cyborg controller
 * @author Driver Person
 *
 */
public class CyborgUI extends UIConfig {
	
	//Joystick information
	private final int CYBORG_CHANNEL = 0;
	private final int JOYSTICK_CHANNEL = 1;
	private final AxisType ROTATION_AXIS = AxisType.kThrottle;
	
	//Cyborg Buttons
	private final int CLOCKWISE_BUTTON = 4;
	private final int COUNTER_CLOCKWISE_BUTTON = 3;
	
	//Joystick Buttons
	private final int ARM_TO_ZERO_BTN = 3;
	private final int DROP_STACK_BUTTON = 2;
	private final int LIFT_UP_BUTTON = 11;
	private final int LIFT_DOWN_BUTTON = 10;
	private final int ARM_UP_BUTTON = 6;
	private final int ARM_DOWN_BUTTON = 7;
	
	
	//Rotation to snap to
	private final int RETAIN_ANGLE_BUTTON = 2;
	
	//Instance vars
	private final Joystick CYBORG;
	private final Joystick JOYSTICK;
	
	public CyborgUI()
	{
		CYBORG = new Joystick(CYBORG_CHANNEL);
		JOYSTICK = new Joystick(JOYSTICK_CHANNEL);
	}

	@Override
	public double getDriveYValue() {
		return CYBORG.getY();
	}

	@Override
	public double getDriveXValue() {
		return CYBORG.getX();
	}

	@Override
	public double getDriveRotateValue() {
		return CYBORG.getAxis(ROTATION_AXIS);
	}

	@Override
	public boolean getSnapCWButton() {
		return CYBORG.getRawButton(CLOCKWISE_BUTTON);
	}

	@Override
	public boolean getSnapCCWButton() {
		return CYBORG.getRawButton(COUNTER_CLOCKWISE_BUTTON);
	}

	@Override
	public double getAngleToRotateTo() {		
		if (CYBORG.getRawButton(CLOCKWISE_BUTTON))
		{
			return Wheels.getInstance().determineSnapAngle(true);
		}
		else if (CYBORG.getRawButton(COUNTER_CLOCKWISE_BUTTON))
		{
			return Wheels.getInstance().determineSnapAngle(false);
		}
		else
		{
			return -1;
		}
	}

	@Override
	public boolean getArmToUpButton() {
		return JOYSTICK.getRawButton(ARM_TO_ZERO_BTN);
	}

	@Override
	public boolean getDropStackButton() {
		return JOYSTICK.getRawButton(DROP_STACK_BUTTON);
	}

	@Override
	public boolean getLifterUpButton() {
		return JOYSTICK.getRawButton(LIFT_UP_BUTTON);
	}

	@Override
	public boolean getLifterDownButton() {
		return JOYSTICK.getRawButton(LIFT_DOWN_BUTTON);
	}

	@Override
	public boolean getRetainAngleButton() {
		return CYBORG.getRawButton(RETAIN_ANGLE_BUTTON);
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
	public boolean getRightStationButton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getLeftStationButton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getAlternateDriveX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAlternateDriveY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getArmToStepButton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getLifterStackButton() {
		// TODO Auto-generated method stub
		return false;
	}
}
