package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ModuleIOTalonFX extends ModuleIO {

    TalonFX drive, steer;
    double zero = 0;
    double driveMetersPerRotation;
    double steerRadiansPerRotation;

    public ModuleIOTalonFX(int driveID, int steerID, int absoluteEncoderID, double driveKFF, double driveKP, double driveKI, double driveKD, double steerKP, double steerKI, double steerKD, double zero, double driveMetersPerRotation, double steerRadiansPerRotation) {
        this.drive = new TalonFX(driveID);
        this.steer = new TalonFX(steerID);
        this.zero = zero;
        this.driveMetersPerRotation = driveMetersPerRotation;
        this.steerRadiansPerRotation = steerRadiansPerRotation;

        TalonFXConfiguration driveConfig = new TalonFXConfiguration();
        driveConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        driveConfig.Slot0.kV = driveKFF;
        driveConfig.Slot0.kP = driveKP;
        driveConfig.Slot0.kI = driveKI;
        driveConfig.Slot0.kD = driveKD;
        StatusCode configStatus = this.drive.getConfigurator().apply(driveConfig);
        while (!configStatus.isOK()) configStatus = this.drive.getConfigurator().apply(driveConfig);

        TalonFXConfiguration steerConfig = new TalonFXConfiguration();
        driveConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        driveConfig.Slot0.kP = steerKP;
        driveConfig.Slot0.kI = steerKI;
        driveConfig.Slot0.kD = steerKD;
        driveConfig.Feedback.FeedbackRemoteSensorID = absoluteEncoderID;
        configStatus = this.steer.getConfigurator().apply(steerConfig);
        while (!configStatus.isOK()) configStatus = this.steer.getConfigurator().apply(steerConfig);
    }

    @Override
    public double getSpeed() {
        return this.drive.getVelocity().getValueAsDouble() * this.driveMetersPerRotation;
    }

    @Override
    public double getOrientation() {
        return (this.steer.getPosition().getValueAsDouble() - this.zero) * this.steerRadiansPerRotation;
    }

    @Override
    public void setSpeed(double speed) {
        this.drive.setControl(new VelocityVoltage(speed / this.driveMetersPerRotation));
    }

    @Override
    public void setOrientation(double orientation) {
        this.drive.setControl(new PositionVoltage((orientation + this.zero) / this.steerRadiansPerRotation));
    }

    @Override
    public void setOrientationOffset(double offset) {
        this.zero = offset;
    }


}
