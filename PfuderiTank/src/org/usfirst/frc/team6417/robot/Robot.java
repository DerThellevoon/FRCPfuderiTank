package org.usfirst.frc.team6417.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import TimFunctions.Deadzone;

public class Robot extends TimedRobot {
	
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public static final Victor motorFrontRight = new Victor(0);
	public static final Victor motorFrontLeft = new Victor(2);
	public static final Victor motorBackRight = new Victor(1);
	public static final Victor motorBackLeft = new Victor(3);
	
	public static final Joystick joystick1 = new Joystick(0);
	public static Deadzone deadzone = new Deadzone();
	
	public static double joystickYAxis = 0;
	public static double joystickXAxis = 0;
	public static double deadzoneValue = 0.15;
	
	

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		joystickYAxis = -joystick1.getY();
		joystickXAxis = joystick1.getX();
		
		joystickYAxis = deadzone.getAxis(joystickYAxis, deadzoneValue);
		joystickXAxis = deadzone.getAxis(joystickXAxis, deadzoneValue);

		motorFrontRight.set((joystickYAxis - joystickXAxis) * (-joystick1.getThrottle() / 2 + 0.5));
		motorFrontLeft.set((joystickYAxis + joystickXAxis) * (-joystick1.getThrottle() / 2 + 0.5));
		motorBackRight.set((joystickYAxis - joystickXAxis) * (-joystick1.getThrottle() / 2 + 0.5));
		motorBackLeft.set((joystickYAxis + joystickXAxis) * (-joystick1.getThrottle() / 2 + 0.5));
	}
}