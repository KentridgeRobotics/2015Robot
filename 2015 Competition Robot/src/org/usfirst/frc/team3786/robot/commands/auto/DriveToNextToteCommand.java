package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToNextToteCommand extends Command {

	private double distanceToTravel = 81;
	private double initialPosition;
	private double distanceTraveled;
	
	private static final double MAX_SPEED = -.5;
	private double rampUpDistance = 24;
	private double rampDownDistance = 48;
	
    public DriveToNextToteCommand() {
        requires(Wheels.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialPosition = Wheels.getInstance().getX();
    	distanceTraveled = 0;
    	if (distanceToTravel < 48)
    	{
    		rampUpDistance = distanceToTravel / 6;
    		rampDownDistance = (distanceToTravel / 6) * 5;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	distanceTraveled = Wheels.getInstance().getX() - initialPosition;
    	
    	System.out.println("Initial position: " + initialPosition);
    	
    	System.out.println("X position: " + Wheels.getInstance().getX());
    	
    	if (distanceTraveled <= distanceToTravel / 4)
    	{
    		Wheels.getInstance().drive(calculateRampUp(distanceTraveled, rampUpDistance), 0, 0);
    	}
    	else if (distanceTraveled > distanceToTravel / 4 && distanceTraveled < distanceTraveled * .75)
    	{
    		Wheels.getInstance().drive(MAX_SPEED, 0, 0);
    	}
    	else
    	{
    		Wheels.getInstance().drive(calculateRampDown(distanceTraveled, rampDownDistance), 0, 0);
    	}
    	
//    	if (distanceTraveled <= distanceToTravel / 10)
//        {
//        	Wheels.getInstance().drive(-.2, 0, 0);
//        }
//    	else if (distanceTraveled <= 3 * (distanceToTravel / 10))
//        {
//        	Wheels.getInstance().drive(-.5, 0, 0);
//        }
//        else if (distanceTraveled <= 4 * (distanceToTravel / 10))
//        {
//        	Wheels.getInstance().drive(-.5, 0, 0);
//        }
//        else if (distanceTraveled <= 7 * (distanceToTravel / 10))
//        {
//        	Wheels.getInstance().drive(-.3, 0, 0);
//        }
//        else if (distanceTraveled <= 8 * (distanceToTravel / 10))
//        {
//        	Wheels.getInstance().drive(-.1, 0, 0);
//        }
//        else
//        {
//        	Wheels.getInstance().drive(-.1, 0, 0);
//        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Should stop when reached the distanceToTravel
    	if (distanceTraveled >= distanceToTravel)
    	{
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Wheels.getInstance().stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		//This should not happen, but just in case
		Wheels.getInstance().stop();
    }
    
    @SuppressWarnings("unused")
	private double calculateRampUp(double distanceTraveled, double rampDownEnd)
    {
    	double speed = Math.sin((distanceTraveled / rampDownEnd) * (Math.PI / 4));
    	speed *= (Math.sqrt(2) / 2);
    	speed *= MAX_SPEED;
    	
    	if (Math.abs(speed) < .15)
    	{
    		if (MAX_SPEED < 0)
    		{
    			speed = -.15;
    		}
    		else
    		{
    			speed = .15;
    		}
    	}
    	
    	return speed;
    }
    
    private double calculateRampDown(double distanceTraveled, double rampDownBegin)
    {
    	double speed = Math.sin(((distanceToTravel - distanceTraveled) / rampDownBegin) * (Math.PI / 4));
    	speed *= (Math.sqrt(2) / 2);
    	speed *= MAX_SPEED;
    	return speed;
    }
}
