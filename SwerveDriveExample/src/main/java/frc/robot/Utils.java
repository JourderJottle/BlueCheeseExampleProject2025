package frc.robot;

public class Utils {
    public static double normalizeAngle(double angle) {
        return (angle + Math.PI) % (2 * Math.PI) - Math.PI;
    }
}
