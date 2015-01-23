package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class ArmCommand extends Command {

	protected void initialize() {
		requires(Arm.getInstance());
	}

	protected void execute() {
		// TODO Auto-generated method stub

	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
