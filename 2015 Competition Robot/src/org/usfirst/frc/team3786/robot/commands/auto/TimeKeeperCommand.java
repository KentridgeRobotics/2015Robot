package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimeKeeperCommand extends Command {

	private TimeKeeper keeper;
	
	public TimeKeeperCommand(TimeKeeper tk)
	{
		keeper = tk;
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
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		System.out.println("Keeper interrupt");
	}

}
