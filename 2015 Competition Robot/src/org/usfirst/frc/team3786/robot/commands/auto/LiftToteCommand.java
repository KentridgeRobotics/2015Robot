package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Arm;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class LiftToteCommand extends Command {
	
	public double armPosition; //TBD, arm position to start lifter to get crate
	private double top = 1; //position to move to for the highest position
    public LiftToteCommand() {
        requires(Arm.getInstance());
        requires(Lifter.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Arm.getInstance().moveArm(top);
    	if(Arm.getInstance().getPosition() >= 0.5)
    	{
    		Lifter.getInstance().moveToPosition(top);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Should end when tote picked up
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
