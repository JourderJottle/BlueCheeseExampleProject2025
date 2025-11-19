package frc.robot.subsystems.drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    Module[] modules;
    GyroIO gyro;
    SwerveDriveKinematics kinematics;

    public Drivetrain(
        ModuleIO frontLeft, Translation2d frontLeftOffset,
        ModuleIO frontRight, Translation2d frontRightOffset,
        ModuleIO backLeft, Translation2d backLeftOffset,
        ModuleIO backRight, Translation2d backRightOffset, 
        GyroIO gyro) {

        this.modules = new Module[] {new Module(frontLeft), new Module(frontRight), new Module(backLeft), new Module(backRight)};
        this.gyro = gyro;

        this.kinematics = new SwerveDriveKinematics(frontLeftOffset, frontRightOffset, backLeftOffset, backRightOffset);
    }

    public Command swerveDriveCommand(Supplier<Double> vx, Supplier<Double> vy, Supplier<Double> vTheta) {
        ChassisSpeeds robotRelativeSpeeds = new ChassisSpeeds(vx.get(), vy.get(), vTheta.get());
        ChassisSpeeds fieldRelativeSpeeds = ChassisSpeeds.fromRobotRelativeSpeeds(robotRelativeSpeeds, this.gyro.getOrientation());
        SwerveModuleState[] states = this.kinematics.toSwerveModuleStates(fieldRelativeSpeeds);
        return this.run(() -> {
            for (int i = 0; i < 4; i++) {
                this.modules[i].setState(states[i]);
            }
        });
    }
}
