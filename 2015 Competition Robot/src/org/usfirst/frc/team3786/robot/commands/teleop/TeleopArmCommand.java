package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Arm;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmCommand extends Command {
private static double position = 0; //position of arm
private double highest = 1; //TBD, highest position arm can go
private double lowest = 0; //TBD, lowest position arm can go
	protected void initialize() {
		requires(Arm.getInstance());
	}

	protected void execute() {
		if(UIConfig.get().getArmUpButton())
		{
			position += .01;
			if(position > highest)
			{
				position = highest;
			}
			Lifter.getInstance().moveToPosition(position);
		}
		if(UIConfig.get().getArmDownButton())
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
