package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;
import org.usfirst.frc.team3786.robot.subsystems.Vision;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
	
    public  AutonomousCommandGroup(TimeKeeper tkc) {
    	
    	requires(Wheels.getInstance());
    	requires(Vision.getInstance());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//Begin time keeping
    	new Thread(tkc).start(); //This instance will need to be kept elsewhere soon
    	
    	addParallel(new TimeKeeperCommand(tkc));
    	
    	//Pick up first tote
//    	addParallel(new LiftToteCommand());
    	
    	//Move while picking up to rough area
//    	addSequential(new DriveToNextToteCommand());
    	//Center on tote
    	addSequential(new CenterOnToteCommand());
    	addSequential(new CenterOnToteCommand());
    	
    	//Pick up 2nd tote when centered
//    	addParallel(new LiftToteCommand());
    	//Move to next tote
//    	addSequential(new DriveToNextToteCommand());
    	//Center twice for verification
//    	addSequential(new CenterOnToteCommand());
//    	addSequential(new CenterOnToteCommand());
    	//Pick up last tote
//    	addParallel(new LiftToteCommand());
    	
    	//Move backwards into the auto zone
//    	addSequential(new DriveBackwardsCommand());
//    	//Begin dropping totes
//    	addParallel(new DropTotesCommand());
//    	//Move away from totes
//    	addSequential(new DriveBackwardsCommand());
    	

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
    
    @Override
    public void end()
    {
    	System.out.println("Group Ended");
    }
}
