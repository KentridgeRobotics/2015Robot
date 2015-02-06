package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
	private static Vision instance;
	
	private Vision()
	{
		
	}
	
	/**
	 * @return The singleton instance of the Vision
	 */
	public static Vision getInstance()
	{
		if (instance == null)
		{
			instance = new Vision();
		}
		
		return instance;
	}
	
	/**
	 * @param distance The distance that the tote is from the camera (TBD Units)
	 * @return The distance to travel in order to center (TBD Units)
	 */
	public double distanceToCenter(double distance)
	{
		//Determine angle off center
		double angleOffCenter = 0;
		
		
		return distance * Math.tan(Math.toRadians(angleOffCenter));
	}
	
	/**
	 * @return If the robot is centered on a tote
	 */
	public boolean isCentered()
	{
		return false;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new CenterOnToteCommand());
    }
}

