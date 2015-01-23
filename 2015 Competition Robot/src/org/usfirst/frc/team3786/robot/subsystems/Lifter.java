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
		
		lifterMotor.setPercentMode();
		
		lifterMotor.enableControl();
	}
	
	public static Lifter getInstance()
	{
		if (instance == null)
		{
			instance = new Lifter();
		}
		
		return instance;
	}
	
	public void moveLifter(double speed)
	{
		lifterMotor.set(speed);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopLifterCommand());
    }
}

