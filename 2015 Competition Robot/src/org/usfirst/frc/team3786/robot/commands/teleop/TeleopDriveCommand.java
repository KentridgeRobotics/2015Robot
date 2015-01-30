package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDriveCommand extends Command {
	
	public TeleopDriveCommand()
	{
		requires(Wheels.getInstance());
	}
	
	protected void initialize() {
	}

	protected void execute() {
		
		double xVal = UIConfig.get().getDriveXValue();
		double yVal = UIConfig.get().getDriveYValue();
		double zVal = UIConfig.get().getDriveRotateValue();
		
		//Drive the robot based on UI (May need some work to avoid conflicts)
		Wheels.getInstance().drive(xVal, yVal, zVal);
		
		if (UIConfig.get().getRetainAngleButton())
		{
			if (Wheels.getInstance().isStickingToAngle())
			{
				Wheels.getInstance().unstickFromAngle();
			}
			else
			{
				Wheels.getInstance().stickToAngle();
			}
		}
		//Rotates the robot to the given angle from the UI
		if (UIConfig.get().getAngleToRotateTo() != -1)
		{
			Wheels.getInstance().rotateToAngle(UIConfig.get().getAngleToRotateTo(), 1);
		}
	}

	protected boolean isFinished() {
		//Teleop commands are never done
		return false;
	}

	protected void end() {
		//This should never be called
	}

	protected void interrupted() {
		//This should not happen, but just in case
		Wheels.getInstance().stop();
	}

}
