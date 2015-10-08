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
	
	protected UIConfig()
	{		
	}
	
	public static UIConfig get() {
		return instance;
	}
	
	/**
	 * @return The value to use for the Y component of driving
	 */
	public abstract double getDriveYValue();
	public abstract double getDriveXValue();
	public abstract double getDriveRotateValue();
	
	public abstract boolean getSnapCWButton();
	public abstract boolean getSnapCCWButton();
	
	public abstract double getAngleToRotateTo();
	
	public abstract boolean getArmToZeroButton();
	
	public abstract boolean getDropStackButton();
	
	public abstract boolean getLifterUpButton();
	public abstract boolean getLifterDownButton();
}
