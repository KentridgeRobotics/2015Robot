package org.usfirst.frc.team3786.robot.commands.auto.bailing;

import org.usfirst.frc.team3786.robot.commands.auto.types.FullAuto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimeKeeperCommand extends Command {
	

	private static final int SECONDS_UNTIL_BAIL = 12;

	private TimeKeeper keeper;
	
//	private Command[] toInterrupt;
	
	private FullAuto acg;
	private BailCommandGroup bcg;
	
	public TimeKeeperCommand(TimeKeeper tk, FullAuto acg, BailCommandGroup bcg)//Command... toInterrupt)
	{
		keeper = tk;
//		this.toInterrupt = toInterrupt;
		this.acg = acg;
		this.bcg = bcg;
		System.out.println("Keeper constructed");
	}
	
	protected void initialize() {
		SmartDashboard.putBoolean("Time Keeper Running", true);
		System.out.println("Keeper init");
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return keeper.getTimeInSeconds() >= SECONDS_UNTIL_BAIL;
	}

	protected void end() {
		acg.cancel();
		bcg.start();
//		for (Command c : toInterrupt)
//		{
//			System.out.println("Interrupting: " + c.getName());
//			c.cancel();
//		}
	}

	protected void interrupted() {
		System.out.println("Keeper interrupted");
	}

}
