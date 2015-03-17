package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopLifterCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lifter extends Subsystem {
    
	private static Lifter instance;
	
	private CANJaguar leftLifterMotor;
	private CANJaguar rightLifterMotor;
	
	private static final double AUTO_CLEAR_POSITION = -50;
	private static final double AUTO_GRAB_POSITION = -51;
	private static final double UP_POSITION = 0;
	private static final double DOWN_POSITION = -268.5;
	private static final double LOAD_CLEAR_POSITION = -169.85;
	private static final double LOAD_GRAB_POSITION = -253.643;
	private static final double RECYCLE_GRAB_POSITION = -35;
	private static final double RECYCLE_CLEAR_POSITON = -50;
	
	private static final double TOLERANCE = 3;
	
	private static final int ENCODER_CODES_PER_REV = 7;

	private Lifter()
	{
		leftLifterMotor = new CANJaguar(RobotConfig.get().getLIFTER_MOTOR_CHANNEL_LEFT());
		rightLifterMotor = new CANJaguar(RobotConfig.get().getLIFTER_MOTOR_CHANNEL_RIGHT());
		
		leftLifterMotor.setPositionMode(CANJaguar.kQuadEncoder, ENCODER_CODES_PER_REV, RobotConfig.get().getLIFTER_P(), RobotConfig.get().getLIFTER_I(), RobotConfig.get().getLIFTER_D());
		rightLifterMotor.setPositionMode(CANJaguar.kQuadEncoder, ENCODER_CODES_PER_REV, RobotConfig.get().getLIFTER_P(), RobotConfig.get().getLIFTER_I(), RobotConfig.get().getLIFTER_D());
		
//		rightLifterMotor.configSoftPositionLimits(UP_POSITION, DOWN_POSITION);
		rightLifterMotor.disableSoftPositionLimits();
		
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
		return rightLifterMotor.getPosition();
	}
	
	public static double getDOWN_POSITION()
	{
		return DOWN_POSITION;
	}
	
	public static double getAUTO_CLEAR_POSITION()
	{
		return AUTO_CLEAR_POSITION;
	}
	
	public static double getAUTO_GRAB_POSITION() {
		return AUTO_GRAB_POSITION;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopLifterCommand());
    }
    
    public void stack()
    {
    	while(Math.abs(getPosition() - LOAD_GRAB_POSITION) > TOLERANCE)
		{
    		System.out.println("Grabbing... Off By: " + Math.abs(getPosition() - LOAD_GRAB_POSITION));
			moveToPosition(LOAD_GRAB_POSITION);
		}
		
		while(Math.abs(getPosition() - LOAD_CLEAR_POSITION) > TOLERANCE)
		{
			System.out.println("Clearing...");
			moveToPosition(LOAD_CLEAR_POSITION);
		}
    }
    
    public void stackRecycle()
    {
    	while((Math.abs(getPosition()) - RECYCLE_GRAB_POSITION) > TOLERANCE)
		{
			moveToPosition(RECYCLE_GRAB_POSITION);
		}
		
		while((Math.abs(getPosition()) - RECYCLE_CLEAR_POSITON) > TOLERANCE)
		{
			moveToPosition(RECYCLE_CLEAR_POSITON);
		}
    }

	public static double getRECYCLE_GRAB_POSITION() {
		return RECYCLE_GRAB_POSITION;
	}

	public static double getRECYCLE_CLEAR_POSITON() {
		return RECYCLE_CLEAR_POSITON;
	}

	public static double getUP_POSITION() {
		return UP_POSITION;
	}

}

