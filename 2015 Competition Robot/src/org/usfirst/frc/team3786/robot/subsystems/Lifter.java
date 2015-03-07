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
	
	private CANJaguar leftLifterMotor;
	private CANJaguar rightLifterMotor;
	
	private static final double AUTO_CLEAR_POSITION = 5;
	private static final double AUTO_GRAB_POSITION = 4;
	private static final double DOWN_POSITION = 0;
	private static final double LOAD_CLEAR_POSITION = 3;
	private static final double LOAD_GRAB_POSITION = 2;

	private Lifter()
	{
		leftLifterMotor = new CANJaguar(RobotConfig.get().getLIFTER_MOTOR_CHANNEL_LEFT());
		rightLifterMotor = new CANJaguar(RobotConfig.get().getLIFTER_MOTOR_CHANNEL_RIGHT());
		
		leftLifterMotor.setPositionMode(CANJaguar.kQuadEncoder, 360, RobotConfig.get().getLIFTER_P(), RobotConfig.get().getLIFTER_I(), RobotConfig.get().getLIFTER_D());
		rightLifterMotor.setPositionMode(CANJaguar.kQuadEncoder, 360, RobotConfig.get().getLIFTER_P(), RobotConfig.get().getLIFTER_I(), RobotConfig.get().getLIFTER_D());
		
		leftLifterMotor.enableControl();
		rightLifterMotor.enableControl();
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
		leftLifterMotor.set(position);
		rightLifterMotor.set(position);
	}
	
	/**
	 * @return The current position of the lifter
	 */
	public double getPosition()
	{
		return leftLifterMotor.get();
	}
	
	public static double getAUTO_CLEAR_POSITION()
	{
		return AUTO_CLEAR_POSITION;
	}
	
	public static double getAUTO_GRAB_POSITION()
	{
		return AUTO_GRAB_POSITION;
	}
	
	public static double getDOWN_POSITION()
	{
		return DOWN_POSITION;
	}
	
	public static double getLOAD_CLEAR_POSITION()
	{
		return LOAD_CLEAR_POSITION;
	}
	
	public static double getLOAD_GRAB_POSITION()
	{
		return LOAD_GRAB_POSITION;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopLifterCommand());
    }
}

