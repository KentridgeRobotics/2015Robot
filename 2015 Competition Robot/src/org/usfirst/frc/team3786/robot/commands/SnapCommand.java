package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

public class SnapCommand extends Command {
	
	private double snapAngle;
	
	/**
	 * @param angle The angle to snap to (in degrees)
	 */
	public SnapCommand(double angle)
	{
		snapAngle = angle;
	}

	protected void initialize() {
		requires(Wheels.getInstance());
	}

	protected void execute() {
		// TODO Auto-generated method stub

		
		//TODO Determine if this could cause concurrency issues
	}

	protected boolean isFinished() {
		//Finishes when robot has snapped to snapAngle
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
