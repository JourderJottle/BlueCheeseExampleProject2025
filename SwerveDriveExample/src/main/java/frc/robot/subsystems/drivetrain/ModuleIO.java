package frc.robot.subsystems.drivetrain;

public abstract class ModuleIO {

    public abstract double getSpeed();

    public abstract double getOrientation();

    public abstract void setSpeed(double speed);

    public abstract void setOrientation(double orientation);

    public abstract void setOrientationOffset(double offset);
    
}
