package frc.robot.subsystems.drivetrain;

public abstract class DrivetrainIO {

    /**
     * Sets the percent outputs of the drivetrain motors with domains [-1, 1]
     * @param left
     * @param right
     */
    public abstract void setPercentOutputs(double left, double right);

    /**
     * Sets speeds of left and right sides of differential drive in m/s
     * @param left
     * @param right
     */
    public abstract void setVelocity(double left, double right);
}
