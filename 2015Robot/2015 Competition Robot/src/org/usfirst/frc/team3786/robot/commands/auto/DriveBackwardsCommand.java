package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBackwardsCommand extends Command {

	private double distanceToTravel;//= 54; //107
	private double current;
	
	private boolean isFinished;
	
    public DriveBackwardsCommand(double distanceToTravel) {
        requires(Wheels.getInstance());
        this.distanceToTravel = distanceToTravel;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	current = 0;
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (current < distanceToTravel)
		{
			Wheels.getInstance().drive(0, -1, 0);
		}
		else
		{
			isFinished = true;
		}
    	
    	current++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Should end after reaching distanceToTravel
    	return isFinished;
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
