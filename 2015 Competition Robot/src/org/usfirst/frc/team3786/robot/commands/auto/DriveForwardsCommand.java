package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardsCommand extends Command {

	private double distanceToTravel;
	
	private double startDistance;
	
	private boolean isFinished;
	
	public DriveForwardsCommand(double distance)
	{
		requires(Wheels.getInstance());
		distanceToTravel = distance;
	}

	@Override
	protected void initialize() {
		startDistance = Wheels.getInstance().getY();
		isFinished = false;
	}

	@Override
	protected void execute() {
		
		if ((Wheels.getInstance().getY() - startDistance) < distanceToTravel)
		{
			Wheels.getInstance().drive(0, 1, 0);
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
		Wheels.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		Wheels.getInstance().stop();
	}
	
	
}
