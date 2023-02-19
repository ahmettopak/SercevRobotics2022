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
 
  private final CANSparkMax armShoulder = new CANSparkMax(1, MotorType.kBrushed);
  private final CANSparkMax armClamp = new CANSparkMax(2, MotorType.kBrushed);
  private final CANSparkMax arm4 = new CANSparkMax(4, MotorType.kBrushed);
  private final CANSparkMax arm3 = new CANSparkMax(3, MotorType.kBrushed);

  private final DifferentialDrive m_drive = new DifferentialDrive(kLeftLeader,kRightLeader);
  
  private final Joystick joystick = new Joystick(0);

  double auto_timer = 0;

  @Override
  public void robotInit() {
    kLeftMotor2.follow(kLeftLeader);
    kRightMotor2.follow(kRightLeader);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    auto_timer = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double curr = Timer.getFPGATimestamp();
    m_drive.setSafetyEnabled(false);

    if (curr - auto_timer <= 3.5) {
      m_drive.arcadeDrive(0, -0.63);
    } else { 
      m_drive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    m_drive.arcadeDrive(joystick.getRawAxis(2), -joystick.getY());
    
    
    if (joystick.getRawButton(5)) {    
      armShoulder.set(-0.7);
      armClamp.set(1);
    }
    
    else if (joystick.getRawButton(3)) {
      armShoulder.set(0.3);
     
    } 
    else {
      armClamp.set(0);
      armShoulder.set(0);
    }
    if (joystick.getRawButton(1)) {
      arm4.set(0.5);
    }
    else if(joystick.getRawButton(2)){
      arm4.set(-0.8);
     
    } else {
      arm4.set(0);
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