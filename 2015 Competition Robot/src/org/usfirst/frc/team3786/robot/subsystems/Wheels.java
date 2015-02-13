package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.teleop.TeleopDriveCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Wheels extends Subsystem {
    
	private static Wheels instance;
	
	private static final double DEAD_ZONE = 0.15;
	private static final double ROTATION_DEAD_ZONE = 0.15;
	private static final double ENCODER_DISTANCE_PER_PULSE = 1 / 360.0;
	
	private CANJaguar frontLeft;
	private CANJaguar frontRight;
	private CANJaguar backLeft;
	private CANJaguar backRight;
	
	private Gyro gyro;
	
//	private Encoder xEncoder;
//	private Encoder yEncoder;
	
	private double desiredAngle;
	private double rotateSpeed;
	private int rotationDir;
	
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
//		xEncoder = new Encoder(0, 0, false, EncodingType.k4X); //TODO Update channels
//		yEncoder = new Encoder(0, 0, false, EncodingType.k4X); //TODO Update channels
//		
//		xEncoder.setDistancePerPulse(ENCODER_DISTANCE_PER_PULSE);
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
//	/**
//	 * @return The X position (in TBD units) of the robot from initial starting position.
//	 */
//	public double getX()
//	{
//		return xEncoder.getDistance();
//	}
//	
//	/**
//	 * @return The Y position (in TBD units) of the robot from initial starting position.
//	 */
//	public double getY()
//	{
//		return yEncoder.getDistance();
//	}
	
	
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
        
        //Swap x and y while inverting
        double temp = x;
        x = y;
        y = temp;

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
        
        //Adjust the rotation of the robot to rotate to given angle
        shouldRotate = shouldRotate(Math.cos(Math.toRadians(getGyroAngle())), Math.sin(Math.toRadians(getGyroAngle())), desiredAngle);
        if (shouldRotate || shouldStickToAngle)
        {
	        SmartDashboard.putNumber("Desired Angle", desiredAngle);
	        SmartDashboard.putNumber("Rotation Dir", rotationDir);
	        if (rotationDir > 0)
	        {
	        	z += factorRotationSpeed(rotateSpeed, desiredAngle, gyro.getAngle());
	        }
	        else if (rotationDir < 0)
	        {
	        	z -= factorRotationSpeed(rotateSpeed, desiredAngle, gyro.getAngle());
	        }
	        SmartDashboard.putNumber("Z Value", z);
        }
        
        //Invert z value
        z *= -1;
        
        SmartDashboard.putNumber("Gyro", gyro.getAngle());
		double backLeftFactor = (x * (sineTheta + cosineTheta)) + (y * (cosineTheta - sineTheta)) - z;
        double frontLeftFactor = (x * (sineTheta - cosineTheta)) + (y * (sineTheta + cosineTheta)) + z;
        double backRightFactor = (x * (sineTheta - cosineTheta)) + (y * (sineTheta + cosineTheta)) - z;
        double frontRightFactor = (x * (sineTheta + cosineTheta)) + (y * (cosineTheta - sineTheta)) + z;
        
        
        //Invert front motors
        frontLeftFactor *= -1;
        frontRightFactor *= -1;
        
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
        rotationDir = -determineRotateDirection(Math.sin(Math.toRadians(gyro.getAngle())), Math.cos(Math.toRadians(gyro.getAngle())), angle);
		SmartDashboard.putNumber("Rotation Speed", speed);
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
	public double getGyroAngle()
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
    	double desiredCosine = Math.cos(Math.toRadians(desiredAngle));
    	double desiredSine = Math.sin(Math.toRadians(desiredAngle));
    	
    	double temp = currentCosine;
    	currentCosine = currentSine;
    	currentSine = temp;
    	
    	int startingQuadrant = 0;
    	int angleQuadrant = 0;
    	
    	SmartDashboard.putNumber("Desired Cosine", desiredCosine);
    	SmartDashboard.putNumber("Current Cosine", currentCosine);
    	
    	//Will become unnecessary, debug purposes right now.
         if (currentCosine >= 0)
         {
         	//First or fourth quadrant
         	if (currentSine >= 0)
         	{
         		//First quadrant
         		startingQuadrant = 1;         		
         	}
         	else
         	{
         		//Fourth quadrant
         		startingQuadrant = 4;
         	}
         }
         else
         {
         	//Second or third quadrant
         	if (currentSine >= 0)
         	{
         		//Second quadrant
         		startingQuadrant = 2;
         	}
         	else
         	{
         		//Third Quadrant
         		startingQuadrant = 3;
         	}
         }
         
         SmartDashboard.putNumber("Starting Quadrant", startingQuadrant);
         
         if (desiredCosine >= 0)
         {
         	//First or fourth quadrant
         	if (desiredSine >= 0)
         	{
         		//First quadrant
         		angleQuadrant = 1;         		
         	}
         	else
         	{
         		//Fourth quadrant
         		angleQuadrant = 4;
         	}
         }
         else
         {
         	//Second or third quadrant
         	if (desiredSine >= 0)
         	{
         		//Second quadrant
         		angleQuadrant = 2;
         	}
         	else
         	{
         		//Third Quadrant
         		angleQuadrant = 3;
         	}
         }
         
         SmartDashboard.putNumber("Angle Quadrant", angleQuadrant);
         
         if (desiredSine > 0 && currentSine > 0)
         {
        	 if(currentCosine < desiredCosine)
        	 {
        		 return 1;
        	 }
        	 else
        	 {
        		 return -1;
        	 }
         }
         else if (desiredSine < 0 && currentSine < 0)
         {
        	 if (currentCosine < desiredCosine)
        	 {
        		 return -1;
        	 }
        	 else
        	 {
        		 return 1;
        	 }
         }
         else if (desiredCosine > 0 && currentCosine > 0)
         {
        	 if (currentSine < desiredSine)
        	 {
        		 return -1;
        	 }
        	 else
        	 {
        		 return 1;
        	 }
         }
         else
         {
        	 if (currentSine < desiredSine)
        	 {
        		 return 1;
        	 }
        	 else
        	 {
        		 return -1;
        	 }
         }
    }

    /**
     * Determines if the robot should keep rotating
     * @param currentCosine The current cosine of the angle
     * @param currentSine The current sine of the angle
     * @param desiredAngle The angle to rotate to
     * @return If the robot should keep rotating
     */
	private boolean shouldRotate(double currentCosine, double currentSine, double desiredAngle) {
		//Determine if within tolerances
    	 double cosineDif = Math.abs(currentCosine - Math.cos(Math.toRadians(desiredAngle)));
    	 double sineDif = Math.abs(currentSine - Math.sin(Math.toRadians(desiredAngle)));;
    	 if (cosineDif <= ROTATION_DEAD_ZONE && sineDif <= ROTATION_DEAD_ZONE)
    	 {
    		 rotationDir = 0;
    		 return false;
    	 }
    	 return true;
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
        		if (!isClockwise)
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
        		if (!isClockwise)
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
        		if (!isClockwise)
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
        		if (!isClockwise)
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
    
    private double factorRotationSpeed(double speed, double desiredAngle, double currentAngle)
    {
    	double angleDif = Math.abs(desiredAngle - currentAngle);
    	
    	if (angleDif <= 5)
    	{
    		return 0;
    	}
    	else if (angleDif <= 10)
    	{
    		return speed * 0.15;
    	}
    	else if (angleDif <= 20)
    	{
    		return speed * 0.3;
    	}
    	else if (angleDif <= 30)
    	{
    		return speed * 0.5;
    	}
    	else
    	{
    		return speed;
    	}
    }
}

