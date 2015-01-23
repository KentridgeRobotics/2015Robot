package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.ArmCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    
	private static Arm instance;
	
	private Arm()
	{
		
	}
	
	
	public static Arm getInstance()
	{
		if (instance == null)
		{
			instance = new Arm();
		}
		
		return instance;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ArmCommand());
    }
}

