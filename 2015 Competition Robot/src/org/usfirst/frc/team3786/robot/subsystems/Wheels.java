package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopDriveCommand;
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
		
		frontLeft.setPercentMode();
		frontRight.setPercentMode();
		backLeft.setPercentMode();
		backRight.setPercentMode();
		
		frontLeft.enableControl();
		frontRight.enableControl();
		backLeft.enableControl();
		backRight.enableControl();
	}
	
	/**
	 * @return The singleton instance of the Wheels
	 */
	public static Wheels getInstance()
	{
		if (instance == null)
		{
			instance = new Wheels();
		}
		
		return instance;
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the front left motor
	 */
	public void setFrontLeft(double factor)
	{
		frontLeft.set(factor);
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the front right motor
	 */
	public void setFrontRight(double factor)
	{
		frontRight.set(factor);
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the back left motor
	 */
	public void setBackLeft(double factor)
	{
		backLeft.set(factor);
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the back right motor
	 */
	public void setBackRight(double factor)
	{
		backRight.set(factor);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDriveCommand());
    }
}

