package frc.robot.subsystems.drivetrain;

public abstract class DrivetrainIO {

    /**
     * Sets the percent outputs of the drivetrain motors with domains [-1, 1]
     * @param left
     * @param right
     */
    public abstract void setPercentOutputs(double left, double right);

    /**
     * Sets linear and angular drivetrain speeds in m/s and radians/s, respectively
     * @param linear
     * @param angular
     */
    public abstract void setVelocity(double linear, double angular);
}
