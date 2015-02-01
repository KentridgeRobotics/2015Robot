package org.usfirst.frc.team3786.robot.config.ui;

/**
 * Configuration system for the UI.
 * Extend this for each different UI setup.
 * (e.g. OneJoystickUI, TankDriveUI)
 * @author Driver Person
 *
 */
public abstract class UIConfig {

	/**
	 * Single location to change UIConfig type
	 */
	private static UIConfig instance;
	
	static
	{
		instance = new CyborgUI();
	}
	
	/**
	 * @return The singleton instance of the UIConfig
	 */
	public static UIConfig get() {
		return instance;
	}
	
	/**
	 * @return The magnitude in the Y direction that the robot should move. [-1.0, 1.0]
	 */
	public abstract double getDriveYValue();
	
	/**
	 * @return The magnitude in the X direction that the robot should move. [-1.0,1.0]
	 */
	public abstract double getDriveXValue();
	
	/**
	 * @return The speed at which the robot should rotate. [-1.0, 1.0]
	 */
	public abstract double getDriveRotateValue();
	
	/**
	 * @return If the robot should snap to the next CW right angle.
	 */
	public abstract boolean getSnapCWButton();
	
	/**
	 * @return If the robot should snap to the next CW right angle.
	 */
	public abstract boolean getSnapCCWButton();
	
	/**
	 * @return The angle to which the robot should rotate and maintain in degrees.
	 */
	public abstract double getAngleToRotateTo();
	
	/**
	 * @return If the arm should move to zero.
	 */
	public abstract boolean getArmToZeroButton();
	
	/**
	 * @return If the robot should drop the stack.
	 */
	public abstract boolean getDropStackButton();
	
	/**
	 * @return If the lifter should move up.
	 */
	public abstract boolean getLifterUpButton();
	
	/**
	 * @return If the lifter should move down.
	 */
	public abstract boolean getLifterDownButton();
	
	/**
	 * 
	 * @return If the arm should move up.
	 */
	public abstract boolean getArmUpButton();
	
	/**
	 * 
	 * @return If the arm should move down.
	 */
	public abstract boolean getArmDownButton();
}
