package frc.robot;

public class Constants {

    public static final RobotName ROBOT_NAME = RobotName.KITBOT;

    public static final double DRIVE_DEADBAND = 0.1; // Minimum joystick magnitude before robot will move

    public static final boolean USE_VELOCITY_DRIVE = false;

    // Monterey Jack Constants
    public static final double MONTEREY_JACK_GEAR_RATIO = 1 / 8.98;
    public static final double MONTEREY_JACK_TRACK_WIDTH = 0;
    public static final double MONTEREY_JACK_WHEEL_CIRCUMFERENCE = 2 * Math.PI * 3; // inches
    public static final double MONTEREY_JACK_METERS_PER_SECOND_TO_ROTATIONS_PER_MINUTE = 60 / MONTEREY_JACK_WHEEL_CIRCUMFERENCE / MONTEREY_JACK_GEAR_RATIO;
    public static final double MONTEREY_JACK_MAX_LINEAR_SPEED = 4.8; // m/s
    public static final double MONTEREY_JACK_MAX_ANGULAR_SPEED = 0; // radians/s

    public static final double MONTEREY_JACK_DRIVE_KFF = 1 / (MONTEREY_JACK_MAX_LINEAR_SPEED * MONTEREY_JACK_METERS_PER_SECOND_TO_ROTATIONS_PER_MINUTE);
    public static final double MONTEREY_JACK_DRIVE_KP = 0;
    public static final double MONTEREY_JACK_DRIVE_KI = 0;
    public static final double MONTEREY_JACK_DRIVE_KD = 0;

    // Kitbot Constants
    public static final double KITBOT_WHEEL_BASE = 0;    

}

enum RobotName {
    MONTEREY_JACK, KITBOT
}