package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToNextToteCommand extends Command {

	private final double distanceToTravel = 10;
	private double initialPosition;
	private double distanceTraveled;
	
    public DriveToNextToteCommand() {
        requires(Wheels.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialPosition = 
    	distanceTraveled = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	distanceTraveled = Wheels.getInstance().getY() - initialPosition;
    	
    	if (distanceTraveled <= distanceToTravel / 10)
        {
        	Wheels.getInstance().drive(0, .1, 0);
        }
    	else if (distanceTraveled <= 3 * (distanceToTravel / 10))
        {
        	Wheels.getInstance().drive(0, .3, 0);
        }
        else if (distanceTraveled <= 7 * (distanceToTravel / 10))
        {
        	Wheels.getInstance().drive(0, .7, 0);
        }
        else if (distanceTraveled <= 8 * (distanceToTravel / 10))
        {
        	Wheels.getInstance().drive(0,.3, 0);
        }
        else if (distanceTraveled <= 9 * (distanceToTravel / 10))
        {
        	Wheels.getInstance().drive(0, .2, 0);
        }
        else
        {
        	Wheels.getInstance().drive(0, .1, 0);
        }
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
