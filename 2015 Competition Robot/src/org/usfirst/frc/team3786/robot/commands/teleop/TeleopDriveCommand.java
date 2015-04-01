package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDriveCommand extends Command {
	
	public TeleopDriveCommand()
	{
		requires(Wheels.getInstance());
	}
	
	protected void initialize() {
		SmartDashboard.putBoolean("resetGyro", false);
	}

	protected void execute() {
//		SmartDashboard.putNumber("X Encoder", Wheels.getInstance().getX());
//		SmartDashboard.putNumber("Y Encoder", Wheels.getInstance().getY());
		
		if (UIConfig.get().getResetGyroButton())
		{
			Wheels.getInstance().resetGyro();
		}
		
		if (SmartDashboard.getBoolean("resetGyro"))
		{
			Wheels.getInstance().resetGyro();
		}
		
		double xVal = UIConfig.get().getDriveXValue();
		double yVal = UIConfig.get().getDriveYValue();
		double zVal = UIConfig.get().getDriveRotateValue();
		
		if (UIConfig.get().getRightStationButton())
		{
			//Alternate drive overrides standard drive if used
			xVal = UIConfig.get().getAlternateDriveX();
			yVal = UIConfig.get().getAlternateDriveY();
			
//			System.out.println("Alt X: " + xVal);
//			System.out.println("Alt Y: " + yVal);
			
			Wheels.getInstance().driveWithoutGyro(xVal, -yVal, zVal); //TODO TEST
		}
		else if (UIConfig.get().getLeftStationButton())
		{
			xVal = UIConfig.get().getAlternateDriveX();
			yVal = UIConfig.get().getAlternateDriveY();
			
			Wheels.getInstance().driveWithoutGyro(-xVal, yVal, zVal); //TODO TEST
		}
		else
		{
			//Drive the robot based on UI (May need some work to avoid conflicts)
			System.out.println("Driving: X: " + xVal + " Y: " + yVal + " Z: " + zVal);
			Wheels.getInstance().drive(xVal, yVal, zVal);
		}
		
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
