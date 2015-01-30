package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopDriveCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wheels extends Subsystem {
    
	private static Wheels instance;
	
	private static final double DEAD_ZONE = 0.15;
	private static final double ROTATION_DEAD_ZONE = 0.15;
	
	private CANJaguar frontLeft;
	private CANJaguar frontRight;
	private CANJaguar backLeft;
	private CANJaguar backRight;
	
	private Gyro gyro;
	
	private double desiredAngle;
	private double rotateSpeed;
	
	private boolean shouldStickToAngle;
	
	private boolean shouldRotate;
	
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
		
		gyro = new Gyro(RobotConfig.get().getGYRO_CHANNEL());
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
        
        //Adjust the rotation of the robot to rotate to given angle (Overrides manual control for now)
        if (shouldRotate || shouldStickToAngle)
        {
	        int direction = determineRotateDirection(cosineTheta, sineTheta, desiredAngle);
	        if (direction > 0)
	        {
	        	z = rotateSpeed;
	        }
	        else if (direction < 0)
	        {
	        	z = rotateSpeed;
	        }
	        else
	        {
	        	shouldRotate = false;
	        	z = 0;
	        }
        }
        
		double frontLeftFactor = (x * (sineTheta + cosineTheta)) + (y * (cosineTheta - sineTheta)) - z;
        double frontRightFactor = (x * (sineTheta - cosineTheta)) + (y * (sineTheta + cosineTheta)) + z;
        double backLeftFactor = (x * (sineTheta - cosineTheta)) + (y * (sineTheta + cosineTheta)) - z;
        double backRightFactor = (x * (sineTheta + cosineTheta)) + (y * (cosineTheta - sineTheta)) + z;
        
        drive(frontLeftFactor, frontRightFactor, backLeftFactor, backRightFactor);
	}
	
	/**
	 * Rotates the robot to a given angle
	 * @param angle The angle to rotate to in degrees
	 * @param speed The speed at which to rotate [-1.0, 1.0]
	 * @param stickToAngle If the robot should hold at the given angle
	 */
	public void rotateToAngle(double angle, double speed)
	{
		this.desiredAngle = angle;
		this.rotateSpeed = speed;
		this.shouldRotate = true;
	}
	
	/**
	 * @return If the robot is currently sticking to it's angle
	 */
	public boolean isStickingToAngle()
	{
		return shouldStickToAngle;
	}
	
	/**
	 * Tells the robot to stay at whatever angle it is currently at
	 */
	public void stickToAngle()
	{
		this.shouldStickToAngle = true;
		if (!this.shouldRotate)
		{
			this.desiredAngle = gyro.getAngle();
		}
	}
	
	/**
	 * If the robot is sticking to an angle, this will release that lock
	 */
	public void unstickFromAngle()
	{
		this.shouldStickToAngle = false;
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
    
    /**
     * Determines which direction is the most efficient for the robot to rotate in.
     * @param currentCosine The cosine of the current angle
     * @param currentSine The sine of the current angle
     * @param desiredAngle The angle to rotate to
     * @return Positive if clockwise, negative if counterclockwise, zero if at angle
     */
    private int determineRotateDirection(double currentCosine, double currentSine, double desiredAngle)
    {
    	 
    	 //Determine if within tolerances
    	 double cosineDif = Math.abs(currentCosine - Math.cos(Math.toRadians(desiredAngle)));
    	 double sineDif = Math.abs(currentSine - Math.sin(Math.toRadians(desiredAngle)));;
    	 if (cosineDif <= ROTATION_DEAD_ZONE && sineDif <= ROTATION_DEAD_ZONE)
    	 {
    		 return 0;
    	 }
    	 
         if (currentCosine >= 0)
         {
         	//First or fourth quadrant
         	if (currentSine >= 0)
         	{
         		//First quadrant
         		if (currentCosine <= Math.cos(Math.toRadians(desiredAngle)))
 	        	{
 		        	return 1;
 		        }
         	}
         	else
         	{
         		//Fourth quadrant
         		if (currentCosine >= Math.cos(Math.toRadians(desiredAngle)))
         		{
         			return 1;
         		}
         	}
         }
         else
         {
         	//Second or third quadrant
         	if (currentSine >= 0)
         	{
         		//Second quadrant
         		if (currentCosine >= Math.cos(Math.toRadians(desiredAngle)))
         		{
         			return 1;
         		}
         	}
         	else
         	{
         		//ThirdQuadrant
         		if (currentCosine <= Math.cos(Math.toRadians(desiredAngle)))
         		{
         			return 1;
         		}
         	}
         }
         
         return -1;
    }
    
    /**
     * Determines what angle to robot should snap to from the standard 90 degree angles
     * @param isClockwise Whether or not the rotation is in the clockwise direction
     * @return The angle to snap to (in degrees)
     */
    public double determineSnapAngle(boolean isClockwise)
    {
    	double currentAngle = gyro.getAngle();
    	double currentCosine = Math.cos(Math.toRadians(currentAngle));
    	double currentSine = Math.sin(Math.toRadians(currentAngle));

	   	if ((currentCosine <= ROTATION_DEAD_ZONE && 1 - currentSine <= ROTATION_DEAD_ZONE) || (currentSine <= ROTATION_DEAD_ZONE && 1 - currentCosine <= ROTATION_DEAD_ZONE))
	   	{
	   		return currentAngle;
	   	}
   	 
        if (currentCosine >= 0)
        {
        	//First or fourth quadrant
        	if (currentSine >= 0)
        	{
        		//First quadrant
        		if (isClockwise)
	        	{
		        	return 0;
		        }
        		else
        		{
        			return 90;
        		}
        	}
        	else
        	{
        		//Fourth quadrant
        		if (isClockwise)
        		{
        			return 270;
        		}
        		else
        		{
        			return 0;
        		}
        	}
        }
        else
        {
        	//Second or third quadrant
        	if (currentSine >= 0)
        	{
        		//Second quadrant
        		if (isClockwise)
        		{
        			return 90;
        		}
        		else
        		{
        			return 180;
        		}
        	}
        	else
        	{
        		//ThirdQuadrant
        		if (isClockwise)
        		{
        			return 180;
        		}
        		else
        		{
        			return 270;
        		}
        	}
        }
    }
}

