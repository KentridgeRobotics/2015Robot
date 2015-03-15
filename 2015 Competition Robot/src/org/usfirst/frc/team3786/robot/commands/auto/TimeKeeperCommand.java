package org.usfirst.frc.team3786.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimeKeeperCommand extends Command {

	private TimeKeeper keeper;
	
	private Command[] toInterrupt;
	
	public TimeKeeperCommand(TimeKeeper tk, Command... toInterrupt)
	{
		keeper = tk;
		this.toInterrupt = toInterrupt;
		System.out.println("Keeper constructed");
	}
	
	protected void initialize() {
		SmartDashboard.putBoolean("Time Keeper Running", true);
		System.out.println("Keeper init");
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return keeper.getTimeInSeconds() >= 12;
	}

	protected void end() {
		for (Command c : toInterrupt)
		{
			System.out.println("Interrupting: " + c.getName());
			c.cancel();
		}
	}

	protected void interrupted() {
		System.out.println("Keeper interrupt");
	}

}
