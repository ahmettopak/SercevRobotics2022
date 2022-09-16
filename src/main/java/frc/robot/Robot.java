package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  private final WPI_TalonSRX kLeftLeader = new WPI_TalonSRX(5);
  private final WPI_TalonSRX kRightLeader  = new WPI_TalonSRX(7);
  private final WPI_TalonSRX kLeftMotor2 = new WPI_TalonSRX(6);
  private final WPI_TalonSRX kRightMotor2 = new WPI_TalonSRX(8);

  private final CANSparkMax kShooter = new CANSparkMax(1, MotorType.kBrushed);
  private final CANSparkMax kTopFeeder = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax kDownFeeder = new CANSparkMax(4, MotorType.kBrushed);

  private final DifferentialDrive m_drive = new DifferentialDrive(kLeftLeader,kRightLeader);

  private final Joystick joystick = new Joystick(0);

  @Override
  public void robotInit() {
    kLeftMotor2.follow(kLeftLeader);
    kRightMotor2.follow(kRightLeader);

    kShooter.setInverted(true);
    kTopFeeder.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
   
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    m_drive.arcadeDrive(joystick.getRawAxis(2) / 1.5, joystick.getY());

    if (joystick.getRawButton(1)) {
      kShooter.set(1);
    } else {
      kShooter.set(0);
    }

    if (joystick.getRawButton(2)) {
      kTopFeeder.set(0.35);
      kDownFeeder.set(1);
    } else {
      kTopFeeder.set(0);
      kDownFeeder.set(0);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
