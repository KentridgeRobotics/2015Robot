package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArmCommand extends Command {

	protected void initialize() {

	}

	protected void execute() {
		if (Arm.getInstance().getPosition() > Arm.getPICK_UP_POSITION())
		{
			Arm.getInstance().moveArm(Arm.getPICK_UP_POSITION());
		}
		else
		{
			Arm.getInstance().moveArm(Arm.getDOWN_POSITION());
		}
	}

	protected boolean isFinished() {
		return Arm.getInstance().getPosition() < 5;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
