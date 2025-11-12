package frc.robot.subsystems.drivetrain;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DrivetrainIOSparkMax extends DrivetrainIO {

    SparkMax frontLeft, frontRight, backLeft, backRight;
    double metersPerSecondToRotationsPerMinuteMultiplier;

    public DrivetrainIOSparkMax(int FRONT_LEFT_ID, int FRONT_RIGHT_ID, int BACK_LEFT_ID, int BACK_RIGHT_ID, double MONTEREY_JACK_DRIVE_KP, double MONTEREY_JACK_DRIVE_KI, double MONTEREY_JACK_DRIVE_KD, double MONTEREY_JACK_DRIVE_KFF, double metersPerSecondToRotationsPerMinuteMultiplier) {
        // Initialize motor controllers
        this.frontLeft = new SparkMax(FRONT_LEFT_ID, MotorType.kBrushless);
        this.frontRight = new SparkMax(FRONT_RIGHT_ID, MotorType.kBrushless);
        this.backLeft = new SparkMax(BACK_LEFT_ID, MotorType.kBrushless);
        this.backRight = new SparkMax(BACK_RIGHT_ID, MotorType.kBrushless);

        // Configure motor controllers
        SparkMaxConfig frontLeftConfig = new SparkMaxConfig();
        frontLeftConfig.inverted(false);
        frontLeftConfig.closedLoop.pidf(MONTEREY_JACK_DRIVE_KP, MONTEREY_JACK_DRIVE_KI, MONTEREY_JACK_DRIVE_KD, MONTEREY_JACK_DRIVE_KFF);
        this.frontLeft.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig frontRightConfig = new SparkMaxConfig();
        frontRightConfig.inverted(true); // Right side is inverted because motors are positioned backwards on right side of differential drives
        frontRightConfig.closedLoop.pidf(MONTEREY_JACK_DRIVE_KP, MONTEREY_JACK_DRIVE_KI, MONTEREY_JACK_DRIVE_KD, MONTEREY_JACK_DRIVE_KFF);
        this.frontRight.configure(frontRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig backLeftConfig = new SparkMaxConfig();
        backLeftConfig.follow(this.frontLeft); // Second motor in gearbox follows first
        this.backLeft.configure(backLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig backRightConfig = new SparkMaxConfig();
        backRightConfig.follow(this.frontRight); // Second motor in gearbox follows first
        this.backRight.configure(backRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        this.metersPerSecondToRotationsPerMinuteMultiplier = metersPerSecondToRotationsPerMinuteMultiplier;
    }

    @Override
    public void setPercentOutputs(double left, double right) {
        this.frontLeft.set(left);
        this.frontRight.set(right);
    }

    @Override
    public void setVelocities(double left, double right) {
        this.frontLeft.getClosedLoopController().setReference(left * this.metersPerSecondToRotationsPerMinuteMultiplier, ControlType.kVelocity);
        this.frontRight.getClosedLoopController().setReference(right * this.metersPerSecondToRotationsPerMinuteMultiplier, ControlType.kVelocity);
    }
    
}
