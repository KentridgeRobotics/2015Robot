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

