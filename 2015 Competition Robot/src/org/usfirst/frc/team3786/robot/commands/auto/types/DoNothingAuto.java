package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.auto.ResetGyroCommand;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothingAuto extends CommandGroup {
	
	public DoNothingAuto()
	{		
		addSequential(new ResetGyroCommand());
	}

}
