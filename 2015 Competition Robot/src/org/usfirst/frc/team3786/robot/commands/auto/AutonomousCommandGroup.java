package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.DropTotesCommand;
import org.usfirst.frc.team3786.robot.commands.auto.bailing.BailCommandGroup;
import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;
import org.usfirst.frc.team3786.robot.subsystems.Vision;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
	
	private LiftToteCommand lift = new LiftToteCommand();
	
	private StackToteFromArmCommand stack = new StackToteFromArmCommand();
	
	private DriveToNextToteCommand drive = new DriveToNextToteCommand();
	
	private DriveBackwardsCommand driveBack = new DriveBackwardsCommand();
	
	private DropTotesCommand drop = new DropTotesCommand();
	
	private CenterOnToteCommand center = new CenterOnToteCommand();
	
    public  AutonomousCommandGroup(TimeKeeper tkc, BailCommandGroup bcg) {
    	
    	requires(Wheels.getInstance());
    	requires(Vision.getInstance());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//Begin time keeping
    	//new Thread(tkc).start(); //This instance will need to be kept elsewhere soon
    	
    	//addParallel(new TimeKeeperCommand(tkc, this, bcg));//lift, stack, drive, center));
    	
    	//Pick up first tote
    	addSequential(lift);
    	
//    	addParallel(stack);
    	
    	//Move while picking up to rough area
//    	addSequential(drive);
    	//Center on tote
//    	addSequential(center);
//    	addSequential(center);
    	
    	//Pick up 2nd tote when centered
//    	addParallel(lift);
    	//Move to next tote
//    	addSequential(drive);
    	//Center twice for verification
//    	addSequential(center);
//    	addSequential(center);
    	//Pick up last tote
//    	addParallel(lift);
    	
    	//Move backwards into the auto zone
//    	addSequential(driveBack);
//    	//Begin dropping totes
//    	addParallel(drop);
//    	//Move away from totes
//    	addSequential(driveBack);
    	

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
