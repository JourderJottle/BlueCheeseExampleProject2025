package frc.robot.subsystems.drivetrain;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.RobotMap;

public class DrivetrainIOSparkMax extends DrivetrainIO {

    SparkMax frontLeft, frontRight, backLeft, backRight;

    public DrivetrainIOSparkMax() {
        // Initialize motor controllers
        frontLeft = new SparkMax(RobotMap.MONTY_DRIVETRAIN_FRONT_LEFT_ID, MotorType.kBrushless);
        frontRight = new SparkMax(RobotMap.MONTY_DRIVETRAIN_FRONT_RIGHT_ID, MotorType.kBrushless);
        backLeft = new SparkMax(RobotMap.MONTY_DRIVETRAIN_BACK_LEFT_ID, MotorType.kBrushless);
        backRight = new SparkMax(RobotMap.MONTY_DRIVETRAIN_BACK_RIGHT_ID, MotorType.kBrushless);

        // Configure motor controllers
        SparkMaxConfig frontLeftConfig = new SparkMaxConfig();
        frontLeftConfig.inverted(false);
        frontLeft.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig frontRightConfig = new SparkMaxConfig();
        frontRightConfig.inverted(true); // Right side is inverted because motors are positioned backwards on right side of differential drives
        frontRight.configure(frontRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig backLeftConfig = new SparkMaxConfig();
        backLeftConfig.follow(frontLeft); // Second motor in gearbox follows first
        backLeft.configure(backLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig backRightConfig = new SparkMaxConfig();
        backRightConfig.follow(frontRight); // Second motor in gearbox follows first
        backRight.configure(backRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void setPercentOutputs(double left, double right) {
        this.frontLeft.set(left);
        this.frontRight.set(right);
    }
    
}
