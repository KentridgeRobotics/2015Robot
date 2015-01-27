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
			
		Wheels.getInstance().drive(xVal, yVal, zVal);
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
