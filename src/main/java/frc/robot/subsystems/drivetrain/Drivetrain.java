package frc.robot.subsystems.drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    DrivetrainIO drivetrain;

    public Drivetrain(DrivetrainIO drivetrain) {
        this.drivetrain = drivetrain;
    }

    public Command percentOutputArcadeDriveCommand(Supplier<Double> forward, Supplier<Double> turn) {
        return this.run(() -> this.drivetrain.setPercentOutputs(forward.get() + turn.get(), forward.get() - turn.get()));
    }

    public Command velocityArcadeDriveCommand(Supplier<Double> forward, Supplier<Double> turn) {
        return this.run(() -> this.drivetrain.setVelocity(forward.get(), turn.get()));
    }

    @Override
    public void periodic() {

    }
}
