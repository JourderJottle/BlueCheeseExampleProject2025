package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotMap;

public class DrivetrainIOTalonSRX extends DrivetrainIO {

    TalonSRX frontLeft, frontRight, backLeft, backRight;

    public DrivetrainIOTalonSRX() {
        this.frontLeft = new TalonSRX(RobotMap.KITBOT_DRIVETRAIN_FRONT_LEFT_ID);
        this.frontRight = new TalonSRX(RobotMap.KITBOT_DRIVETRAIN_FRONT_RIGHT_ID);
        this.backLeft = new TalonSRX(RobotMap.KITBOT_DRIVETRAIN_BACK_LEFT_ID);
        this.backRight = new TalonSRX(RobotMap.KITBOT_DRIVETRAIN_BACK_RIGHT_ID);
        
        this.frontLeft.setInverted(false);
        this.frontRight.setInverted(true);
        this.backLeft.follow(frontLeft);
        this.backRight.follow(frontRight);
    }

    @Override
    public void setPercentOutputs(double left, double right) {
        this.frontLeft.set(ControlMode.Current, left);
        this.backLeft.set(ControlMode.Current, right);
    }

    
}
