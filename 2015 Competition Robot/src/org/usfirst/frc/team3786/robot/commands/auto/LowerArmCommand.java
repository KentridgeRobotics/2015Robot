package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LowerArmCommand extends Command {

	protected void initialize() {

	}

	protected void execute() {
//		if (Arm.getInstance().getPosition() > 50)
//		{
//			Arm.getInstance().moveArm(50);
//		}
//		else if (Arm.getInstance().getPosition() > 20)//Arm.getPICK_UP_POSITION())
//		{
//			Arm.getInstance().moveArm(20);//Arm.getPICK_UP_POSITION());
//		}
//		else
//		{
//			Arm.getInstance().moveArm(Arm.getDOWN_POSITION());
//		}
		
		if (Arm.getInstance().getPosition() < 105)
		{
			Arm.getInstance().moveArm(Arm.getInstance().getPosition() - 8);
		}
		
		SmartDashboard.putNumber("Current Position", Arm.getInstance().getPosition());
	}

	protected boolean isFinished() {
		return Arm.getInstance().getPosition() < 7;
	}

	protected void end() {
		// TODO Auto-generated method stubw

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
