package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCommand extends Command {

	private double currentAngle;
	
	private double desiredAngle;

	private boolean isFinished;
	
	public RotateCommand(double desired)
	{
		requires(Wheels.getInstance());
		this.desiredAngle = desired;
	}
	
	protected void initialize() {
		currentAngle = Wheels.getInstance().getGyroAngle();
		isFinished = false;
	}

	protected void execute() {
		currentAngle = Wheels.getInstance().getGyroAngle();
		
		if(currentAngle < desiredAngle)
		{
			Wheels.getInstance().drive(0, 0, .5);
		}
		else
		{
			isFinished = true;
		}
	}

	protected boolean isFinished() {
		return isFinished;
	}

	protected void end() {
		Wheels.getInstance().stop();
	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
