package org.usfirst.frc.team3786.robot.commands.auto.bailing;

import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BailCommandGroup extends CommandGroup{

	public BailCommandGroup()
	{
		//NEEDS testing
		addSequential(new DriveBackwardsCommand(107)); //TODO: Test Distance
	}
}
