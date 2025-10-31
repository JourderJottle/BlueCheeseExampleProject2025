package frc.robot.subsystems.drivetrain;

public abstract class DrivetrainIO {

    /**
     * Sets the percent outputs of the drivetrain motors with domains [-1, 1]
     */
    public abstract void setPercentOutputs(double left, double right);
}
