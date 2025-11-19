package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Module {
    
    ModuleIO io;

    public Module(ModuleIO io) {
        this.io = io;
    }

    public void setState(SwerveModuleState state) {
        this.io.setSpeed(state.speedMetersPerSecond);
        this.io.setOrientation(state.angle.getRadians());
    }
}
