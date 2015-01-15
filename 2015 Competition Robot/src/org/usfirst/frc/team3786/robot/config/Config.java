package org.usfirst.frc.team3786.robot.config;

public abstract class Config {

	private static Class<? extends Config> configType = BaseConfig.class;
	
	public abstract Config get();
}
