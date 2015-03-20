package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LiftToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LowerArmCommand;
import org.usfirst.frc.team3786.robot.commands.auto.StackToteFromArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabToteAndMoveBackAuto extends CommandGroup {

	public GrabToteAndMoveBackAuto()
	{
		addSequential(new LiftToteCommand());
		
		addSequential(new DriveBackwardsCommand(107)); //TODO: Test Distance
		
		addSequential(new LowerArmCommand());
	}
}
