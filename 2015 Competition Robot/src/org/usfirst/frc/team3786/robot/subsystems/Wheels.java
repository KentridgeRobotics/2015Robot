package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopDriveCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wheels extends Subsystem {
    
	private static Wheels instance;
	
	private static final double DEAD_ZONE = 0.15;
	
	private CANJaguar frontLeft;
	private CANJaguar frontRight;
	private CANJaguar backLeft;
	private CANJaguar backRight;
	
	private Gyro gyro;
	
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
	private void setFrontLeft(double factor)
	{
		frontLeft.set(factor);
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the front right motor
	 */
	private void setFrontRight(double factor)
	{
		frontRight.set(factor);
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the back left motor
	 */
	private void setBackLeft(double factor)
	{
		backLeft.set(factor);
	}
	
	/**
	 * @param factor The speed, on a scale of [-1.0, 1.0], to run the back right motor
	 */
	private void setBackRight(double factor)
	{
		backRight.set(factor);
	}
	
	//Get encoder values
	/**
	 * @return The X position (in TBD units) of the robot from initial starting position.
	 */
	public double getX()
	{
		return 0.0;
	}
	
	/**
	 * @return The Y position (in TBD units) of the robot from initial starting position.
	 */
	public double getY()
	{
		return 0.0;
	}
	
	
	//Utility methods for drive
	/**
	 * Drives the robot based on a vector
	 * (Values should be on a scale of [-1.0, 1.0])
	 * @param x The X value of the vector
	 * @param y The Y value of the vector
	 * @param z The rotational speed
	 */
	public void drive(double x, double y, double z)
	{
		double theta = Math.toRadians(gyro.getAngle());
        
        double cosineTheta = Math.cos(theta);
        double sineTheta = Math.sin(theta);

        //Dead zones
        if (Math.abs(x) < DEAD_ZONE)
        {
            x = 0;
        }
        if (Math.abs(y) < DEAD_ZONE)
        {
            y = 0;
        }
        if (Math.abs(z) < DEAD_ZONE)
        {
            z = 0;
        }
		double frontLeftFactor = (x * (sineTheta + cosineTheta)) + (y * (cosineTheta - sineTheta)) - z;
        double frontRightFactor = (x * (sineTheta - cosineTheta)) + (y * (sineTheta + cosineTheta)) + z;
        double backLeftFactor = (x * (sineTheta - cosineTheta)) + (y * (sineTheta + cosineTheta)) - z;
        double backRightFactor = (x * (sineTheta + cosineTheta)) + (y * (cosineTheta - sineTheta)) + z;
        
        drive(frontLeftFactor, frontRightFactor, backLeftFactor, backRightFactor);
	}
	
	/**
	 * Drives the robot based upon raw motor factors
	 * (Factors are on a scale of [-1.0, 1.0])
	 * @param frontLeft The factor at which to run the front left motor
	 * @param frontRight The factor at which to run the front right motor
	 * @param backLeft The factor at which to run the backLeft motor
	 * @param backRight The factor at which to run the backRight motor
	 */
	private void drive(double frontLeft, double frontRight, double backLeft, double backRight)
	{
		setFrontLeft(frontLeft);
		setFrontRight(frontRight);
		setBackLeft(backLeft);
		setBackRight(backRight);
	}
	
	/**
	 * Stops the robot;
	 */
	public void stop()
	{
		setFrontLeft(0);
		setFrontRight(0);
		setBackLeft(0);
		setBackRight(0);
	}
	
	/**
	 * @return The current angle of the gyro. (In degrees)
	 */
	public double getGryoAngle()
	{
		return gyro.getAngle();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDriveCommand());
    }
}

