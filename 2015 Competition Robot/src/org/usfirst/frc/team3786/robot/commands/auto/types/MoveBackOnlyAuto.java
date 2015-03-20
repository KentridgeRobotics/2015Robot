package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveBackOnlyAuto extends CommandGroup {

	public MoveBackOnlyAuto()
	{
		addSequential(new DriveBackwardsCommand(107)); //Distance untested
	}
	
}
