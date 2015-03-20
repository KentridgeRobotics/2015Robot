package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveToNextToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LiftToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.StackToteFromArmCommand;
import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;
import org.usfirst.frc.team3786.robot.commands.vision.RangeToToteCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoToteAuto extends CommandGroup {

	public TwoToteAuto()
	{
		//addParallel(new TimeKeeperCommand(tkc, this, bcg));//lift, stack, drive, center));
    	
    	//Pick up first tote
    	addSequential(new LiftToteCommand());
    	System.out.println("Finished Lifting");
    	addParallel(new StackToteFromArmCommand());
    	
    	//Move while picking up to rough area
    	addSequential(new DriveToNextToteCommand());
    	System.out.println("Finished Driving");
    	//Center on tote
    	addSequential(new CenterOnToteCommand());
    	addSequential(new CenterOnToteCommand());
    	
    	addSequential(new RangeToToteCommand());
    	
    	//Pick up 2nd tote when centered
    	addSequential(new LiftToteCommand());
    	
    	//Stack 2nd tote
    	addParallel(new StackToteFromArmCommand());
    	
    	//Move backwards into the auto zone
    	addSequential(new DriveBackwardsCommand(96)); //TODO: TEST Distances
//    	//Begin dropping totes
    	addParallel(new DropTotesCommand());
//    	//Move away from totes
    	addSequential(new DriveBackwardsCommand(48)); //TODO: Test distances
	}
	
}
