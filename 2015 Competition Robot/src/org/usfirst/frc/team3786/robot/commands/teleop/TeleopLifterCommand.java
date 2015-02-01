package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopLifterCommand extends Command {
	
	private final double LOWEST_POSITION = 0; // TBD
	private final double HIGHEST_POSITION = 1; // TBD
	private final double SPEED = 0.1;
	
	public double currentPosition;
	
	protected void initialize() {
		requires(Lifter.getInstance());
		currentPosition = Lifter.getInstance().getPosition();
	}

	protected void execute() {
		
		if (UIConfig.get().getLifterUpButton())
		{
			currentPosition += SPEED;
			if(currentPosition > HIGHEST_POSITION)
			{
				currentPosition = HIGHEST_POSITION;
			}
			Lifter.getInstance().moveToPosition(currentPosition);
		}
		if (UIConfig.get().getLifterDownButton())
		{
			currentPosition -= .01;
			if(currentPosition < LOWEST_POSITION)
			{
				currentPosition = LOWEST_POSITION;
			}
			Lifter.getInstance().moveToPosition(currentPosition);
		}
		
		currentPosition = Lifter.getInstance().getPosition();
	}

	protected boolean isFinished() {
		//Teleop commands are never done
		return false;
	}

	protected void end() {
	
	}

	protected void interrupted() {
	}

}
