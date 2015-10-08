package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.RotateCCCommand;
import org.usfirst.frc.team3786.robot.commands.auto.RotateCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveBackOnlyAuto extends CommandGroup {

	public MoveBackOnlyAuto()
	{
		addSequential(new RotateCommand(90));
		
		addSequential(new DriveBackwardsCommand(50));//new DriveBackwardsCommand(125)); //TODO: Test Distance
		
		addSequential(new RotateCCCommand(0));
	}
	
}
