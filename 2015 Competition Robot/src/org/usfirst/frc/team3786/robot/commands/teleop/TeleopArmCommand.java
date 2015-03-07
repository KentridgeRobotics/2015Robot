package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopArmCommand extends Command {

	protected void initialize() {
		requires(Arm.getInstance());
	}
	
	public TeleopArmCommand()
	{
		initialize();
	}
	

	private static final double INCREMENT = 0.01;
	
	protected void execute() {
		if (UIConfig.get().getArmToZeroButton())
		{
			Arm.getInstance().zero();
			return;
		}
		
		if (UIConfig.get().getArmDownButton() && UIConfig.get().getArmUpButton())
		{
			return;
		}
		
		if (UIConfig.get().getArmDownButton())
		{
			Arm.getInstance().moveArm(Arm.getInstance().getPosition() - INCREMENT);
		}
		else if (UIConfig.get().getArmUpButton())
		{
			Arm.getInstance().moveArm(Arm.getInstance().getPosition() + INCREMENT);
		}
	}

	protected boolean isFinished() {
		//Teleop commands are never finished
		return false;
	}

	protected void end() {
		//This should never happen
	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
