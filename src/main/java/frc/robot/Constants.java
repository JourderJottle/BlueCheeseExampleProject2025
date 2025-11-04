package frc.robot;

public class Constants {

    public static final RobotName ROBOT_NAME = RobotName.KITBOT;

    public static final double DRIVE_DEADBAND = 0.1; // Minimum joystick magnitude before robot will move

    // Monterey Jack drive pidf constants
    public static final int MONTEREY_JACK_DRIVE_KFF = 0;
    public static final int MONTEREY_JACK_DRIVE_KP = 0;
    public static final int MONTEREY_JACK_DRIVE_KI = 0;
    public static final int MONTEREY_JACK_DRIVE_KD = 0;
}

enum RobotName {
    MONTEREY_JACK, KITBOT
}