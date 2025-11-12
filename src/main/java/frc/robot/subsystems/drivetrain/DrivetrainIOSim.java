package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import frc.robot.Constants;

public class DrivetrainIOSim extends DrivetrainIO {

    DifferentialDrivetrainSim sim;
    PIDController leftVelocityController, rightVelocityController;

    public DrivetrainIOSim(DifferentialDrivetrainSim sim, double DRIVE_KP, double DRIVE_KI, double DRIVE_KD, double DRIVE_FF) {
        this.sim = sim;
        this.leftVelocityController = new PIDController(DRIVE_KP, DRIVE_KI, DRIVE_KD, DRIVE_FF);
        this.rightVelocityController = new PIDController(DRIVE_KP, DRIVE_KI, DRIVE_KD, DRIVE_FF);
    }

    @Override
    public void setPercentOutputs(double left, double right) {
        this.sim.setInputs(left * 12, right * 12);
    }

    @Override
    public void setVelocities(double left, double right) {
        this.sim.setInputs(
            this.leftVelocityController.calculate(this.sim.getLeftVelocityMetersPerSecond()),
            this.rightVelocityController.calculate(this.sim.getRightVelocityMetersPerSecond()));
    }

    @Override
    public void periodic() {
        this.sim.update(Constants.dt);
    }
    
}
