package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Arm;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class StackToteFromArmCommand extends Command
{

	private boolean isFinished = false;
	
	@Override
	protected void initialize() {
		isFinished = false;
	}

	@Override
	protected void execute() {
    	if (Lifter.getInstance().getPosition() > Lifter.getAUTO_GRAB_POSITION())
    	{
    		Lifter.getInstance().moveToPosition(Lifter.getAUTO_GRAB_POSITION());
    	}
    	else if (Lifter.getInstance().getPosition() < Lifter.getAUTO_CLEAR_POSITION())
    	{
    		Lifter.getInstance().moveToPosition(Lifter.getAUTO_CLEAR_POSITION());
    	}
    	else if (Arm.getInstance().getPosition() > Arm.getPICK_UP_POSITION())
    	{
    		Arm.getInstance().moveArm(Arm.getPICK_UP_POSITION());
    	}
    	else if (Arm.getInstance().getPosition() > Arm.getDOWN_POSITION())
    	{
    		Arm.getInstance().moveArm(Arm.getDOWN_POSITION());
    	}
    	else
    	{
    		isFinished = true;
    	}
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		//TODO Determine what to do if interrupted
	}
}
