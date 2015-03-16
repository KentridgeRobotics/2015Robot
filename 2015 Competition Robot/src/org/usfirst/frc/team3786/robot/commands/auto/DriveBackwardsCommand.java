package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBackwardsCommand extends Command {

	private final double distanceToTravel = 10;
	private double currentDistance;
	
    public DriveBackwardsCommand() {
        requires(Wheels.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentDistance = 0; 
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    		
    	Wheels.getInstance().drive(-1, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Should end after reaching distanceToTravel
    	if(currentDistance >= distanceToTravel)
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
}
