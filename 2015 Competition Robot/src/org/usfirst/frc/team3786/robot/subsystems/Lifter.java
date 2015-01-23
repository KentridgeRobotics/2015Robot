package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopLifterCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {
    
	private static Lifter instance;
	
	private CANJaguar lifterMotor;

	private Lifter()
	{
		lifterMotor = new CANJaguar(RobotConfig.get().getLIFTER_MOTOR_CHANNEL());
		
		lifterMotor.setPositionMode(CANJaguar.kQuadEncoder, 360, RobotConfig.get().getLIFTER_P(), RobotConfig.get().getLIFTER_I(), RobotConfig.get().getLIFTER_D());
		
		lifterMotor.enableControl();
	}
	
	/**
	 * @return The singleton instance of the Lifter
	 */
	public static Lifter getInstance()
	{
		if (instance == null)
		{
			instance = new Lifter();
		}
		
		return instance;
	}
	
	/**
	 * Moves the lifter to a given position
	 * @param position The position, in rotations, to move to.
	 */
	public void moveToPosition(double position)
	{
		lifterMotor.set(position);
	}
	
	/**
	 * @return The current position of the lifter
	 */
	public double getPosition()
	{
		return lifterMotor.get();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopLifterCommand());
    }
}

