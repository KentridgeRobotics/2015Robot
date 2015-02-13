package org.usfirst.frc.team3786.robot.commands.vision;

import org.usfirst.frc.team3786.robot.subsystems.Vision;
import org.usfirst.frc.team3786.robot.subsystems.Wheels;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CenterOnToteCommand extends Command {

//	private Ultrasonic uSonic;
//	private double distance;
	
	private int lastDirection = 1;
	
    public CenterOnToteCommand() {
         requires(Vision.getInstance());
         requires(Wheels.getInstance());
         setTimeout(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	uSonic = new Ultrasonic(0, 0); //TODO Update Channels
//    	uSonic.setAutomaticMode(true);
    	System.out.println("Initializing CenterOnTote");
    	Vision.getInstance().setLights(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	distance = uSonic.getRangeInches();
//    	double moveDist = Vision.getInstance().distanceToCenter(distance);
    	
    	int currentDir = Vision.getInstance().directionToCenter();
    	
    	System.out.println("Current Dir: " + currentDir);
    	
    	if (currentDir != lastDirection)
    	{
    		Wheels.getInstance().drive(0.1 * currentDir, 0, 0);
    	}
    	else
    	{
    		Wheels.getInstance().drive(0.15 * currentDir, 0, 0);
    	}
    	
    	lastDirection = currentDir;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		
    	//Finishes when robot is centered on tote
        return lastDirection == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
