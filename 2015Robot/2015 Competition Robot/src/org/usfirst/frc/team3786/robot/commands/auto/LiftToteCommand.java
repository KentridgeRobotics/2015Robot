package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Arm;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftToteCommand extends Command {
	
    public LiftToteCommand() {
        requires(Arm.getInstance());
        requires(Lifter.getInstance());
    }
    
    private boolean isFinished = false;

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Arm.getPICK_UP_POSITION() - Arm.getInstance().getPosition() > Arm.getMOVEMENT_TOLERANCE())
    	{
    		Arm.getInstance().moveArm(Arm.getPICK_UP_POSITION());
    	}
    	else if (Arm.getLIFT_POSITION() - Arm.getInstance().getPosition() > Arm.getMOVEMENT_TOLERANCE())
    	{
    		Arm.getInstance().moveArm(Arm.getLIFT_POSITION());
    	}
    	else
    	{
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Interrupted!");
    }
}
