// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.DrivetrainIOSparkMax;
import frc.robot.subsystems.drivetrain.DrivetrainIOTalonSRX;

public class RobotContainer {

    Drivetrain drivetrain;

    CommandXboxController controller;

    public RobotContainer() {
        
        // Easy switching between robots for testing, just change ROBOT_NAME parameter in Constants.java
        switch (Constants.ROBOT_NAME) {
            case MONTEREY_JACK :
                drivetrain = new Drivetrain(new DrivetrainIOSparkMax());
                break;
            
            case KITBOT :
                drivetrain = new Drivetrain(new DrivetrainIOTalonSRX());
                break;
        }

        controller = new CommandXboxController(RobotMap.CONTROLLER_PORT);

        configureBindings();
    }

    private void configureBindings() {

        // Sets drivetrain default command (command the Drivetrain subsystem will run when no other commands are scheduled for it) to percent output arcade drive
        drivetrain.setDefaultCommand(drivetrain.percentOutputArcadeDriveCommand(
            () -> modulateDriveInput(-controller.getLeftY()),
            () -> modulateDriveInput(controller.getRightX())));

    }

    /**
     * Squares inputs to steepen acceleration curve and applies a deadband
     * @param in drive percent input
     * @return modulated drive percent input
     */
    private double modulateDriveInput(double in) {
        double absIn = Math.abs(in);
        return absIn > Constants.DRIVE_DEADBAND ? in * absIn : 0;
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}