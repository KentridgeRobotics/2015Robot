package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGyroCommand extends Command {

	public ResetGyroCommand()
	{
		requires(Wheels.getInstance());
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		Wheels.getInstance().resetGyro();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
