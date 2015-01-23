package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.LifterCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {
    
private static Lifter instance;
	
	private Lifter()
	{
		
	}
	
	
	public static Lifter getInstance()
	{
		if (instance == null)
		{
			instance = new Lifter();
		}
		
		return instance;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new LifterCommand());
    }
}

