package org.usfirst.frc.team3786.robot.commands.auto.types;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveBackwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveForwardsCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveToNextToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LiftToteCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LowerArmCommand;
import org.usfirst.frc.team3786.robot.commands.auto.StackToteFromArmCommand;
import org.usfirst.frc.team3786.robot.commands.auto.bailing.BailCommandGroup;
import org.usfirst.frc.team3786.robot.commands.auto.bailing.TimeKeeper;
import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;
import org.usfirst.frc.team3786.robot.commands.vision.RangeToToteCommand;
import org.usfirst.frc.team3786.robot.subsystems.Vision;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FullAuto extends CommandGroup {
	
    public  FullAuto(TimeKeeper tkc, BailCommandGroup bcg) {
    	
    	requires(Wheels.getInstance());
    	requires(Vision.getInstance());
    	
    	//Begin time keeping
    	//new Thread(tkc).start(); //This instance will need to be kept elsewhere soon
    	
    	//addParallel(new TimeKeeperCommand(tkc, this, bcg));
    	
    	//Pick up first tote
    	addSequential(new LiftToteCommand());
    	
    	addParallel(new StackToteFromArmCommand());
    	addSequential(new DriveBackwardsCommand(10));
    	
    	//Move while picking up to rough area
    	addSequential(new DriveToNextToteCommand(81));
    	
    	//Center on tote
    	addSequential(new CenterOnToteCommand());
    	addSequential(new CenterOnToteCommand());
    	
//    	addSequential(new RangeToToteCommand()); //Will most likely not work
    	
    	//Pick up 2nd tote when centered
    	addSequential(new LowerArmCommand());
    	addSequential(new DriveForwardsCommand(10)); //Replaced if Ranging works
    	addSequential(new LiftToteCommand());
    	
    	addParallel(new StackToteFromArmCommand());
    	addSequential(new DriveBackwardsCommand(10));
    	
    	//Move to next tote
    	addSequential(new DriveToNextToteCommand(81));
    	//Center twice for verification
    	addSequential(new CenterOnToteCommand());
    	addSequential(new CenterOnToteCommand());
    	
//    	addSequential(new RangeToToteCommand()); //Will most likely not work
    	
//    	Pick up last tote
    	addSequential(new LowerArmCommand());
    	addSequential(new DriveForwardsCommand(10));
    	addParallel(new LiftToteCommand());
    	
    	//Move backwards into the auto zone
    	addSequential(new DriveBackwardsCommand(96));
//    	//Begin dropping totes
    	addParallel(new DropTotesCommand());
//    	//Move away from totes
    	addSequential(new DriveBackwardsCommand(48));
    }
    
    @Override
    public void end()
    {
    	System.out.println("Group Ended");
    }
}
