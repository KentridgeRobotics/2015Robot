package org.usfirst.frc.team3786.robot.commands.vision;

import org.usfirst.frc.team3786.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterOnToteCommand extends Command {

	private Ultrasonic uSonic;
	private double distance;
	
    public CenterOnToteCommand() {
         requires(Vision.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	uSonic = new Ultrasonic(0, 0); //TODO Update Channels
    	uSonic.setAutomaticMode(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distance = uSonic.getRangeInches();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Finishes when robot is centered on tote
        return Vision.getInstance().isCentered();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
