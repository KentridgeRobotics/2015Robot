package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public  AutonomousCommandGroup() {
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//Pick up first tote
    	addParallel(new LiftToteCommand());
    	
    	//Move while picking up to rough area
    	addSequential(new DriveToNextToteCommand());
    	//Center on tote
    	addSequential(new CenterOnToteCommand());
    	//Pick up 2nd tote when centered
    	addParallel(new LiftToteCommand());
    	
    	//Move to next tote
    	addSequential(new DriveToNextToteCommand());
    	//Center
    	addSequential(new CenterOnToteCommand());
    	//Pick up last tote
    	addParallel(new LiftToteCommand());
    	
    	//Move backwards into the auto zone
    	addSequential(new DriveBackwardsCommand());
    	//Begin dropping totes
    	addParallel(new DropTotesCommand());
    	//Move away from totes
    	addSequential(new DriveBackwardsCommand());
    	

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
