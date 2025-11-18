package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;

public abstract class GyroIO {

    public abstract Rotation2d getOrientation();

    public abstract void resetOrientation(double orientation);
    
}
