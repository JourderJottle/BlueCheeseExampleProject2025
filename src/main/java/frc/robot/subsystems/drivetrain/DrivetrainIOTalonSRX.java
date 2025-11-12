package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DrivetrainIOTalonSRX extends DrivetrainIO {

    TalonSRX frontLeft, frontRight, backLeft, backRight;

    public DrivetrainIOTalonSRX(int FRONT_LEFT_ID, int FRONT_RIGHT_ID, int BACK_LEFT_ID, int BACK_RIGHT_ID) {
        this.frontLeft = new TalonSRX(FRONT_LEFT_ID);
        this.frontRight = new TalonSRX(FRONT_RIGHT_ID);
        this.backLeft = new TalonSRX(BACK_LEFT_ID);
        this.backRight = new TalonSRX(BACK_RIGHT_ID);
        
        this.frontLeft.setInverted(false);
        this.frontRight.setInverted(true);
        this.backLeft.follow(this.frontLeft);
        this.backRight.follow(this.frontRight);
    }

    @Override
    public void setPercentOutputs(double left, double right) {
        this.frontLeft.set(ControlMode.Current, left);
        this.backLeft.set(ControlMode.Current, right);
    }

    @Override
    public void setVelocities(double linear, double angular) {
        throw new UnsupportedOperationException("Kitbot does not support velocity PID drive");
    }

}
