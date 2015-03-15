package org.usfirst.frc.team3786.robot.commands.teleop;

import org.usfirst.frc.team3786.robot.config.ui.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopArmCommand extends Command {

	protected void initialize() {
		requires(Arm.getInstance());
	}
	
	public TeleopArmCommand()
	{
		initialize();
		SmartDashboard.putNumber("Position", 0);
	}
	

	private static final double INCREMENT = 10;
	
	protected void execute() {
		if (UIConfig.get().getArmToUpButton())
		{
			Arm.getInstance().moveArmToUpPosition();
			return;
		}
		else if (UIConfig.get().getArmToStepButton())
		{
			Arm.getInstance().moveArm(Arm.getSTEP_POSITION());
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
		
//		SmartDashboard.putNumber("Current Position", Arm.getInstance().getPosition());
		
//		Arm.getInstance().moveArm(SmartDashboard.getNumber("Position"));
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
