package frc.robot.subsystems.drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    ModuleIO frontLeft, frontRight, backLeft, backRight;
    GyroIO gyro;
    SwerveDriveKinematics kinematics;

    public Drivetrain(
        ModuleIO frontLeft, Translation2d frontLeftOffset,
        ModuleIO frontRight, Translation2d frontRightOffset,
        ModuleIO backLeft, Translation2d backLeftOffset,
        ModuleIO backRight, Translation2d backRightOffset, 
        GyroIO gyro) {

        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.gyro = gyro;

        this.kinematics = new SwerveDriveKinematics(frontLeftOffset, frontRightOffset, backLeftOffset, backRightOffset);
    }

    public Command swerveDriveCommand(Supplier<Double> vx, Supplier<Double> vy, Supplier<Double> vTheta) {
        ChassisSpeeds robotRelativeSpeeds = new ChassisSpeeds(vx.get(), vy.get(), vTheta.get());
        ChassisSpeeds fieldRelativeSpeeds = ChassisSpeeds.fromRobotRelativeSpeeds(robotRelativeSpeeds, this.gyro.getOrientation());
        SwerveModuleState[] states = this.kinematics.toSwerveModuleStates(fieldRelativeSpeeds);
        return this.run(() -> this.setSwerveModuleStates(states));
    }

    public void setSwerveModuleStates(SwerveModuleState[] states) {
        this.frontLeft.setSpeed(states[0].speedMetersPerSecond);
        this.frontLeft.setOrientation((states[0].angle.getRadians()));
        this.frontRight.setSpeed(states[1].speedMetersPerSecond);
        this.frontRight.setOrientation((states[1].angle.getRadians()));
        this.backLeft.setSpeed(states[2].speedMetersPerSecond);
        this.backLeft.setOrientation((states[2].angle.getRadians()));
        this.backRight.setSpeed(states[3].speedMetersPerSecond);
        this.backRight.setOrientation((states[3].angle.getRadians()));
    }
}
