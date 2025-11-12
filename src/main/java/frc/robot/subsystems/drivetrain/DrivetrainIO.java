package frc.robot.subsystems.drivetrain;

public abstract class DrivetrainIO {

    /**
     * Sets the percent outputs of the drivetrain motors with domains [-1, 1]
     * @param left
     * @param right
     */
    public abstract void setPercentOutputs(double left, double right);

    /**
     * Sets the left and right wheel speeds in m/s
     * @param left
     * @param right
     */
    public abstract void setVelocities(double left, double right);
}
