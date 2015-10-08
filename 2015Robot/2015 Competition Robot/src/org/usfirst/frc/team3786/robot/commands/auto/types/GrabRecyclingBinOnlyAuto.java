package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.auto.LiftToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LowerArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabRecyclingBinOnlyAuto extends CommandGroup {

	public GrabRecyclingBinOnlyAuto()
	{
		addSequential(new LowerArmCommand());
		
		addSequential(new LiftToteCommand());
	}

}
