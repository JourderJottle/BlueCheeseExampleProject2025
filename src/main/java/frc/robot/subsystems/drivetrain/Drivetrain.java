package frc.robot.subsystems.drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    DrivetrainIO drivetrain;
    DifferentialDriveKinematics kinematics;

    public Drivetrain(DrivetrainIO drivetrain, double wheelBase) {
        this.drivetrain = drivetrain;
        this.kinematics = new DifferentialDriveKinematics(wheelBase);
    }

    public Command percentOutputArcadeDriveCommand(Supplier<Double> forward, Supplier<Double> turn) {
        return this.run(() -> this.drivetrain.setPercentOutputs(forward.get() + turn.get(), forward.get() - turn.get()));
    }

    public Command velocityArcadeDriveCommand(Supplier<Double> forward, Supplier<Double> turn) {
        ChassisSpeeds speeds = new ChassisSpeeds(forward.get(), 0, turn.get());
        DifferentialDriveWheelSpeeds wheelSpeeds = this.kinematics.toWheelSpeeds(speeds);
        return this.run(() -> this.drivetrain.setVelocities(wheelSpeeds.leftMetersPerSecond, wheelSpeeds.rightMetersPerSecond));
    }

    @Override
    public void periodic() {

    }
}
