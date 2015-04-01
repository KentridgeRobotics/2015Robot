package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveForwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LiftToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LowerArmCommand;
import org.usfirst.frc.team3786.robot.commands.auto.RotateCCCommand;
import org.usfirst.frc.team3786.robot.commands.auto.RotateCommand;
import org.usfirst.frc.team3786.robot.commands.auto.StackToteFromArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabToteAndMoveBackAuto extends CommandGroup {

	public GrabToteAndMoveBackAuto()
	{
		addSequential(new LowerArmCommand());
		
		addSequential(new DriveForwardsCommand(5));
		
		addSequential(new LiftToteCommand());
		
		addSequential(new RotateCommand(90));
		
		addSequential(new DriveBackwardsCommand(50));//new DriveBackwardsCommand(125)); //TODO: Test Distance
		
//		addSequential(new LowerArmCommand());
		
		addSequential(new RotateCCCommand(0));
	}
}
