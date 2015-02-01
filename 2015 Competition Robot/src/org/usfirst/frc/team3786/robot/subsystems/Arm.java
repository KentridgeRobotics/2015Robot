package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopArmCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    
	private static Arm instance;
	
	private CANJaguar armMotor;
	
	private double currentPosition;
	
	private Arm()
	{
		armMotor = new CANJaguar(RobotConfig.get().getARM_MOTOR_CHANNEL());
		
		armMotor.setPositionMode(CANJaguar.kQuadEncoder, RobotConfig.get().getENCODER_CODES_PER_REV(), RobotConfig.get().getARM_P(), RobotConfig.get().getARM_I(), RobotConfig.get().getARM_D());
		
		armMotor.enableControl();
		
		//This may need to be changed based upon starting position of the arm.
		currentPosition = 0;
	}
	
	/**
	 * @return The singleton instance of the Arm
	 */
	public static Arm getInstance()
	{
		if (instance == null)
		{
			instance = new Arm();
		}
		
		return instance;
	}

	/**
	 * @param position The position to drive the arm to.
	 */
	public void moveArm(double position)
	{
		armMotor.set(position);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopArmCommand());
    }
    
    /**
     * @return The current position of the arm.
     */
    public double getPosition()
    {
    	return 0;
    }
    /**
     * Determines if arm is moving
     * @return true if moving, false if not
     */
    public boolean isMoving()
    {
    	if (currentPosition == armMotor.get())
    	{
    		return false;
    	}
    	return true;
    }
}

