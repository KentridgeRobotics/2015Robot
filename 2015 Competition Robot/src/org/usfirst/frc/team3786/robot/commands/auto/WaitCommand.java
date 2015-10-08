package org.usfirst.frc.team3786.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {

	private final int ticks;
	private int current;
	
	public WaitCommand(int ticks)
	{
		this.ticks = ticks;
	}
	
	protected void initialize() {
		current = 0;
	}

	protected void execute() {
		current++;
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return current >= ticks;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
