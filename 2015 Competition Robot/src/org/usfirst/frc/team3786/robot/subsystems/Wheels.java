package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.DriveCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wheels extends Subsystem {
    
	private static Wheels instance;
	
	private CANJaguar frontLeft;
	private CANJaguar frontRight;
	private CANJaguar backLeft;
	private CANJaguar backRight;
	
	private Wheels()
	{
		frontLeft = new CANJaguar(RobotConfig.get().getFRONT_LEFT_MOTOR_CHANNEL());
		frontRight = new CANJaguar(RobotConfig.get().getFRONT_RIGHT_MOTOR_CHANNEL());
		backLeft = new CANJaguar(RobotConfig.get().getBACK_LEFT_MOTOR_CHANNEL());
		backRight = new CANJaguar(RobotConfig.get().getBACK_RIGHT_MOTOR_CHANNEL());
	}
	
	
	public static Wheels getInstance()
	{
		if (instance == null)
		{
			instance = new Wheels();
		}
		
		return instance;
	}
	
	public void setFrontLeft(double factor)
	{
		frontLeft.set(factor);
	}
	
	public void setFrontRight(double factor)
	{
		frontRight.set(factor);
	}
	
	public void setBackLeft(double factor)
	{
		backLeft.set(factor);
	}
	
	public void setBackRight(double factor)
	{
		backRight.set(factor);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
}

