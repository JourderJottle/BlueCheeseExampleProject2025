package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;

public abstract class ModuleIO {

    public abstract double getSpeed();

    public abstract Rotation2d getOrientation();

    public abstract void setSpeed(double speed);

    public abstract void setOrientation(Rotation2d orientation);

    public abstract void setOrientationOffset(double offset);
    
}
