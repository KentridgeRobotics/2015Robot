package org.usfirst.frc.team3786.robot.commands.vision;

import org.usfirst.frc.team3786.robot.subsystems.Vision;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.command.Command;

public class RangeToToteCommand extends Command {

	private static final double DESIRED_DISTANCE = 21.25;
	
	private static final double DISTANCE_TOLERANCE = 2;
	
	private double distance;
	
	
	public RangeToToteCommand()
	{
		requires(Vision.getInstance());
		requires(Wheels.getInstance());
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
    	distance = Vision.getInstance().getDistance();
    	
    	double yVal = 0;
    	
    	if (distance + DISTANCE_TOLERANCE <= DESIRED_DISTANCE)
    	{
    		yVal = .25; //Move back (this is in the positive Y dir)
    	}
    	else if (distance - DISTANCE_TOLERANCE >= DESIRED_DISTANCE)
    	{
    		yVal = -.25; //Move forward (this is in the negative Y dir)
    	}
    	
    	Wheels.getInstance().driveWithoutGyro(0, yVal, 0);
	}

	protected boolean isFinished() {
		return Math.abs(distance - DESIRED_DISTANCE) < DISTANCE_TOLERANCE;
	}

	protected void end() {

	}

	protected void interrupted() {
	}

}
