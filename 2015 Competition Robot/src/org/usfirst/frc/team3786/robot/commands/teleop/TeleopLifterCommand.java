package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopLifterCommand extends Command {

	protected void initialize() {
		requires(Lifter.getInstance());

	}
	
	public TeleopLifterCommand()
	{
		initialize();
		SmartDashboard.putNumber("Lifter Position", 0);
	}
	
	private static final double INCREMENT = 0.5;

	protected void execute() {
		
		if (UIConfig.get().getLifterDownButton() && UIConfig.get().getLifterUpButton())
		{
			return;
		}
		
		if (UIConfig.get().getLifterUpButton())
		{
			Lifter.getInstance().moveToPosition(Lifter.getInstance().getPosition() + INCREMENT);
		}
		else if (UIConfig.get().getLifterDownButton())
		{
			Lifter.getInstance().moveToPosition(Lifter.getInstance().getPosition() - INCREMENT);
		}
		
		SmartDashboard.putNumber("Lifter Position", Lifter.getInstance().getPosition());
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
