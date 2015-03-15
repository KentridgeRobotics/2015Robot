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
		instance = new GamePadUI();
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
	public abstract boolean getArmToUpButton();
	
	public abstract boolean getArmUpButton();
	
	public abstract boolean getArmDownButton();
	
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

	public abstract boolean getRetainAngleButton();
	
	/**
	 * @return If should use alternate control for right station
	 */
	public abstract boolean getRightStationButton();
	
	/**
	 * @return If should use alternate control for left station
	 */
	public abstract boolean getLeftStationButton();
	
	/**
	 * @return The X componenet of the alternate drive
	 */
	public abstract double getAlternateDriveX();
	
	/**
	 * @return The Y component of the alternate drive
	 */
	public abstract double getAlternateDriveY();
	
	/**
	 * @return If the arm should move to step height
	 */
	public abstract boolean getArmToStepButton();
	
	/**
	 * @return If the arm should move the drop amount
	 */
	public abstract boolean getArmDropButton();
}
