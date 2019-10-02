package frc.robot;


import edu.wpi.first.cameraserver.*;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends TimedRobot {
  
  private CANSparkMax leftFront, leftRear, rightFront, rightRear;
  private SpeedControllerGroup left, right;
  private Joystick driver, mechanic;
  private DifferentialDrive drive;
  private WPI_TalonSRX ballIntake;
  private DriverStation driverStation;
  private Spark blinky;

  @Override
  public void robotInit() {
    //Drive
      leftFront     = new CANSparkMax(2, MotorType.kBrushless);
      leftRear      = new CANSparkMax(4, MotorType.kBrushless);
      rightFront    = new CANSparkMax(3, MotorType.kBrushless);
      rightRear     = new CANSparkMax(1, MotorType.kBrushless);
      left          = new SpeedControllerGroup(leftFront, leftRear);
      right         = new SpeedControllerGroup(rightFront, rightRear);
      drive         = new DifferentialDrive(left, right);
      driver        = new Joystick(0);
      mechanic      = new Joystick(1);

    //Control
      ballIntake    = new WPI_TalonSRX(7);

    //Camera
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
    
    //LEDs
    blinky          = new Spark(0);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {
    driveyThingy();
  }

  @Override
  public void teleopPeriodic() {
    driveyThingy();
  }

  @Override
  public void testPeriodic() {
  }

  public void driveyThingy() {
      //LED Colors
      if (driverStation.getAlliance() == DriverStation.Alliance.Red) {
        //set red
      } else if (driverStation.getAlliance() == DriverStation.Alliance.Blue) {
        //set blue
      } else {
        //set white
      }
  
    //Drive
    drive.arcadeDrive(driver.getRawAxis(1) * -1, driver.getRawAxis(4));
      
    //Mecanics
      if (mechanic.getRawButton(1) == true) {
        ballIntake.set(-.5);
      } else if (mechanic.getRawButton(2) == true) {
        ballIntake.set(.5);
      } else {
        ballIntake.set(0);
      }

  }
}
