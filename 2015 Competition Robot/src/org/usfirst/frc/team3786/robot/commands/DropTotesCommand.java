package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.Arm;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropTotesCommand extends Command {
	
    public DropTotesCommand() {
    	requires(Arm.getInstance());
    	requires(Lifter.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Move arm out of the way just to be sure.
    	Arm.getInstance().moveArmToUpPosition();
    	//Lower the Lifter to a point where it can release totes.
		Lifter.getInstance().moveToPosition(Lifter.getDOWN_POSITION());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Waits for the lifter to reach the down position
    	if (Lifter.getInstance().getPosition() <= Lifter.getDOWN_POSITION())
    	{
    		return true;
    	}
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
