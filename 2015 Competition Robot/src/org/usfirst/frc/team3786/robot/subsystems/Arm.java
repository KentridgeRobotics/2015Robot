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
	
	private Arm()
	{
		armMotor = new CANJaguar(RobotConfig.get().getARM_MOTOR_CHANNEL());
		
		//TODO Determine if this should be Percent or Position mode
		armMotor.setPercentMode();
		
		armMotor.enableControl();
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
	 * @param speed The speed at which to drive the arm on a scale of [-1.0, 1.0]
	 */
	public void moveArm(double position)
	{
		armMotor.set(position);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopArmCommand());
    }
    
    public double getPosition()
    {
    	//TODO get position of arm for when to start lifter
    	return 0;
    }
    /**
     * Determines if arm is moving
     * @return true if moving, false if not
     */
    public boolean isMoving()
    {
    	//TODO check if moving
    	return false;
    }
}

