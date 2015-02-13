package org.usfirst.frc.team3786.robot.subsystems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.usfirst.frc.team3786.robot.commands.vision.CenterOnToteCommand;
import org.usfirst.frc.team3786.robot.config.robot.RobotConfig;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {
    
	private static Vision instance;
	
	private Relay lights;
	
	private final double PIXLE_DEAD_ZONE = 5;
	
	//A structure to hold measurements of a particle
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double PercentAreaToImageArea;
		double Area;
		double ConvexHullArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.Area - this.Area);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.Area - r2.Area);
		}
	};

	//Structure to represent the scores for the various tests used for target identification
	public class Scores {
		double Trapezoid;
		double LongAspect;
		double ShortAspect;
		double AreaToConvexHullArea;
	};

	//Images
	Image frame;
	Image binaryFrame;
	int imaqError;
	int session;

	//Constants
	NIVision.Range TOTE_RED_RANGE = new NIVision.Range(240, 255); //new NIVision.Range(0, 150);	//Default hue range for yellow tote
	NIVision.Range TOTE_GREEN_RANGE = new NIVision.Range(240, 255); //new NIVision.Range(240, 255);	//Default saturation range for yellow tote
	NIVision.Range TOTE_BLUE_RANGE = new NIVision.Range(240, 255); //new NIVision.Range(0, 247);	//Default value range for yellow tote
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 2.22; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 1.4; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 75.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 60; //View angle of camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	Scores scores = new Scores();
	private static final int IMAGE_WIDTH = 640;
	
	private Vision()
	{
	    // create images
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
		
		session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        lights = new Relay(RobotConfig.get().getLIGHT_CHANNEL(), Direction.kForward);
	}
	
	/**
	 * @return The singleton instance of the Vision
	 */
	public static Vision getInstance()
	{
		if (instance == null)
		{
			instance = new Vision();
		}
		return instance;
	}
	
	/**
	 * Sets the lights to either on or off
	 * @param on Whether or not the lights should be on.
	 */
	public void setLights(boolean on)
	{
		if (on)
		{
			lights.set(Value.kForward);
		}
		else
		{
			lights.set(Value.kOff);
		}
	}
	
	public Value getLights()
	{
		return lights.get();
	}
	
	/**
	 * @param distance The distance that the tote is from the camera (TBD Units)
	 * @return The distance to travel in order to center (TBD Units)
	 */
	public double distanceToCenter(double distance)
	{
		//Determine angle off center
		double angleOffCenter = 0;
		
		return distance * Math.tan(Math.toRadians(angleOffCenter));
	}
	
	/**
	 * @return If the robot is centered on a tote
	 */
	public boolean isCentered()
	{
		return directionToCenter() == 0;
	}
	
	/**
	 * @return -1 if the robot should move left, 1 if the robot should move right
	 */
	public int directionToCenter()
	{
		//read file in from disk. For this example to run you need to copy image20.jpg from the SampleImages folder to the
		//directory shown below using FTP or SFTP: http://wpilib.screenstepslive.com/s/4485/m/24166/l/282299-roborio-ftp
		NIVision.IMAQdxGrab(session, frame, 1);

		//Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.RGB, TOTE_RED_RANGE, TOTE_GREEN_RANGE, TOTE_BLUE_RANGE);

		//Send particle count to dashboard
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Masked particles", numParticles);

		//Send masked image to dashboard to assist in tweaking mask.
		CameraServer.getInstance().setImage(binaryFrame);

		//filter out small particles
		float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
		criteria[0].lower = areaMin;
		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);

		//Send particle count after filtering to dashboard
		numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Filtered particles", numParticles);
		
		
		if(numParticles > 0)
		{
			//Measure particles and sort by particle size
			List<ParticleReport> particles = new ArrayList<ParticleReport>();
			for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
			{
				ParticleReport par = new ParticleReport();
				par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.ConvexHullArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
				par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.BoundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
//				checkIfSingleL(par);
				particles.add(par);
			}
//			particles.sort(null);
			if (particles.size() > 0)
			{
//				checkIfSingleL(particles.get(0));
			}
			if (particles.size() > 1)
			{
				angleOracle(particles.get(0), particles.get(1));
//				angleByArea(particles.get(0), particles.get(1));
				return -centerInator(particles.get(0), particles.get(1));
			}
			else
			{
				return 0;
			}
			
//			//This exampgele only scores the largest particle. Extending to score all particles and choosing the desired one is left as an exercise
//			//for the reader. Note that the long and short side scores expect a single tote and will not work for a stack of 2 or more totes.
//			//Modification of the code to accommodate 2 or more stacked totes is left as an exercise for the reader.
//			scores.Trapezoid = TrapezoidScore(particles.elementAt(0));
//			SmartDashboard.putNumber("Trapezoid", scores.Trapezoid);
//			scores.LongAspect = LongSideScore(particles.elementAt(0));
//			SmartDashboard.putNumber("Long Aspect", scores.LongAspect);
//			scores.ShortAspect = ShortSideScore(particles.elementAt(0));
//			SmartDashboard.putNumber("Short Aspect", scores.ShortAspect);
//			scores.AreaToConvexHullArea = ConvexHullAreaScore(particles.elementAt(0));
//			SmartDashboard.putNumber("Convex Hull Area", scores.AreaToConvexHullArea);
//			boolean isTote = scores.Trapezoid > SCORE_MIN && (scores.LongAspect > SCORE_MIN || scores.ShortAspect > SCORE_MIN) && scores.AreaToConvexHullArea > SCORE_MIN;
//			boolean isLong = scores.LongAspect > scores.ShortAspect;
//
//			//Send distance and tote status to dashboard. The bounding rect, particularly the horizontal center (left - right) may be useful for rotating/driving towards a tote
//			SmartDashboard.putBoolean("IsTote", isTote);
//			SmartDashboard.putNumber("Distance", computeDistance(binaryFrame, particles.elementAt(0), isLong));
		} else {
			return 0;
		}
	}
	
	/**
	 * @param leftParticle
	 * @param rightParticle
	 * @return -1 for left, 1 for right, 0 for centered
	 */
	private int centerInator (ParticleReport leftParticle, ParticleReport rightParticle) 
	{
		double middleOfParticles = Math.abs(((rightParticle.BoundingRectRight - leftParticle.BoundingRectLeft)/2) + leftParticle.BoundingRectLeft);
		int direction = 0;
		
		
		if (middleOfParticles < IMAGE_WIDTH/2 - PIXLE_DEAD_ZONE + RobotConfig.get().getCAMERA_OFF_SET())
		{
			direction = -1;
		}
		else if (middleOfParticles > IMAGE_WIDTH/2 + PIXLE_DEAD_ZONE + RobotConfig.get().getCAMERA_OFF_SET()) 
		{
			direction = 1;
		}
		SmartDashboard.putNumber("Which way to Move.", direction);
		SmartDashboard.putNumber("Center of Particles", middleOfParticles);
		SmartDashboard.putNumber("Differance", middleOfParticles - IMAGE_WIDTH/2);
		return direction;
	}
	
	private double angleOracle(ParticleReport particleOne, ParticleReport particleTwo) 
	{
		double deltaX = Math.abs(particleTwo.BoundingRectLeft - particleOne.BoundingRectRight); 
		double deltaY = Math.abs(particleOne.BoundingRectTop - particleTwo.BoundingRectTop);
		double hypotenuse = (Math.pow(deltaX, 2))+(Math.pow(deltaY, 2));
		double toteAngle = Math.acos(deltaX / hypotenuse);
		
		SmartDashboard.putNumber("Delta X (px)", deltaX);
		SmartDashboard.putNumber("Delta Y (px)", deltaY);
		SmartDashboard.putNumber("Hypotenuse (px)", hypotenuse);
		SmartDashboard.putNumber("Angle (rads)", toteAngle);
		SmartDashboard.putNumber("Tote Angle (Deg)", Math.toDegrees(toteAngle));
		
		double heightOfFirst = Math.abs(particleOne.BoundingRectTop - particleOne.BoundingRectBottom);
		double heightOfSecond = Math.abs(particleTwo.BoundingRectTop - particleTwo.BoundingRectBottom);
		double widthOfFirst = Math.abs(particleOne.BoundingRectRight - particleOne.BoundingRectLeft);
		double widthOfSecond = Math.abs(particleTwo.BoundingRectRight - particleTwo.BoundingRectLeft);
		double areaOfFirst = particleOne.Area;
		double areaOfSecond = particleTwo.Area;
		
		SmartDashboard.putNumber("Height of First", heightOfFirst);
		SmartDashboard.putNumber("Height of Second", heightOfSecond);
		SmartDashboard.putNumber("Width of First", widthOfFirst);
		SmartDashboard.putNumber("Width of Second", widthOfSecond);
		SmartDashboard.putNumber("Area of First", areaOfFirst);
		SmartDashboard.putNumber("Area of Second", areaOfSecond);
		
		String moveDirection = "";
		double threshold = 0.15;
		
		if(areaOfFirst - threshold > areaOfSecond)
		{
			moveDirection = "Rotate CCW";
		}
		else if(areaOfSecond + threshold > areaOfFirst)
		{
			moveDirection = "Rotate CW";
		}
		else
		{
			moveDirection = "STOP!";
		}
		
		SmartDashboard.putString("Which way to Rotate", moveDirection);
		return Math.toDegrees(toteAngle);
	}

    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

