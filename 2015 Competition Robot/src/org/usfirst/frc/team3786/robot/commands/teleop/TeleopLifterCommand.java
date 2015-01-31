package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopLifterCommand extends Command {
	public double position = Lifter.getInstance().getPosition();
	private final double lowest = 0; // TBD
	private final double highest = 1; // TBD
	
	protected void initialize() {
		requires(Lifter.getInstance());
		
	}

	protected void execute() {
		// TODO Auto-generated method stub
		if(UIConfig.get().getLifterUpButton())
		{
			position += .01;
			if(position > highest)
			{
				position = highest;
			}
			Lifter.getInstance().moveToPosition(position);
		}
		if(UIConfig.get().getLifterDownButton())
		{
			position -= .01;
			if(position < lowest)
			{
				position = lowest;
			}
			Lifter.getInstance().moveToPosition(position);
		}
		
	}

	protected boolean isFinished() {
		//Teleop commands are never done
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
	
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
