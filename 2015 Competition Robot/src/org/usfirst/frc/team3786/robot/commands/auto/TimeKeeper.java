package org.usfirst.frc.team3786.robot.commands.auto;

public class TimeKeeper implements Runnable {

	private static final int SECONDS_UNTIL_BAIL = 12;
	
	private long initialSystemTime;
	
	public TimeKeeper() {
		initialSystemTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		if ((System.currentTimeMillis() - initialSystemTime) >= SECONDS_UNTIL_BAIL * 1000)
		{
			return;
		}
	}
	
	public double getTimeInSeconds()
	{
		return ((System.currentTimeMillis() - initialSystemTime) / 1000.0);
	}
	
	public void reset()
	{
		initialSystemTime = System.currentTimeMillis();
	}
}
