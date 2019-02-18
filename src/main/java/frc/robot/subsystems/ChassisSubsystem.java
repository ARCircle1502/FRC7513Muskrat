/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PIDSpeedControllerWrapper;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisManualDrive;

/**
 * Add your docs here.
 */
public class ChassisSubsystem extends Subsystem {

  public WPI_VictorSPX leftFrontSPX;
  //public WPI_VictorSPX  leftBackSPX;
  public CANSparkMax leftBackMAX;
  public WPI_VictorSPX  rightFrontSPX;
  //public WPI_VictorSPX  rightBackSPX;
  public WPI_VictorSPX  PanelSPX;
  public CANSparkMax rightBackMAX;
  public SpeedControllerGroup leftMotorGroup;
  public SpeedControllerGroup rightMotorGroup;
  

  public PIDController leftPIDGroup;
  public PIDController rightPIDGroup;

  public CANEncoder leftChassisEncoder;
  public CANEncoder rightChassisEncoder;

  public double leftPosition;
  public double rightPosition;
  public double leftSpeed;
  public double rightSpeed;

  public DifferentialDrive drivetrain;
  public PIDSpeedControllerWrapper leftPIDcontroller;
  public PIDSpeedControllerWrapper rightPIDcontroller;
  public ChassisSubsystem(){
    leftFrontSPX=new WPI_VictorSPX(RobotMap.LeftFrontVictorID);
    leftBackMAX=new CANSparkMax(RobotMap.LeftBackSparkMAXID, MotorType.kBrushless);
    //leftBackSPX=new WPI_VictorSPX(RobotMap.LeftBackVictorID);
    rightFrontSPX=new WPI_VictorSPX(RobotMap.RightFrontVictorID);
    rightBackMAX=new CANSparkMax(RobotMap.RightBackSparkMAXID, MotorType.kBrushless);
    //rightBackSPX=new WPI_VictorSPX(RobotMap.RightBackVictorID);

    leftMotorGroup=new SpeedControllerGroup(leftFrontSPX, leftBackMAX);
    rightMotorGroup=new SpeedControllerGroup(rightFrontSPX, rightBackMAX);

    leftChassisEncoder=new CANEncoder(leftBackMAX);
    rightChassisEncoder=new CANEncoder(rightBackMAX);
    leftChassisEncoder.setPositionConversionFactor(RobotMap.WheelDiameter*Math.PI*RobotMap.ChassisGearRatio);//Distance By cm
    rightChassisEncoder.setPositionConversionFactor(RobotMap.WheelDiameter*Math.PI*RobotMap.ChassisGearRatio);//Distance By cm
    leftChassisEncoder.setVelocityConversionFactor(RobotMap.ChassisGearRatio);//Output Shaft RPM
    rightChassisEncoder.setVelocityConversionFactor(RobotMap.ChassisGearRatio);//Output Shaft RPM

    
    
    leftPIDGroup=new PIDController(RobotMap.ChassisLeft_P, RobotMap.ChassisLeft_I, RobotMap.ChassisLeft_D, new PIDSource(){
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        
      }
    
      @Override
      public double pidGet() {
        return leftChassisEncoder.getVelocity();
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kRate;
      }
    },leftMotorGroup,0.026);

   //////////////////////////////////////////////////////////////////////////////////////////
   
    rightPIDGroup=new PIDController(RobotMap.ChassisRight_P, RobotMap.ChassisRight_I, RobotMap.ChassisRight_D, new PIDSource(){
    
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        
      }
    
      @Override
      public double pidGet() {
        return rightChassisEncoder.getVelocity();
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kRate;
      }
    },rightMotorGroup,0.026);
    leftPIDGroup.setAbsoluteTolerance(1.3);
    rightPIDGroup.setAbsoluteTolerance(1.3);
    leftPIDGroup.enable();
    rightPIDGroup.enable();
    leftPIDcontroller=new PIDSpeedControllerWrapper(leftPIDGroup,RobotMap.MaxChassisWheelRPM);
    rightPIDcontroller=new PIDSpeedControllerWrapper(rightPIDGroup,RobotMap.MaxChassisWheelRPM);
    drivetrain=new DifferentialDrive(leftPIDcontroller,rightPIDcontroller);//leftPIDcontroller, leftPIDcontroller);
  }
  
  public void ArcadeDrive(double speed, double rotation){
    drivetrain.arcadeDrive(speed, rotation,true);
  }

  public void stopMotors(){
    leftPIDcontroller.stopMotor();
    rightPIDcontroller.stopMotor();
  }

  public void log() {
    SmartDashboard.putData("LeftMotors",leftMotorGroup);
    SmartDashboard.putData("RightMotors",rightMotorGroup);
    SmartDashboard.putData("Left PID",leftPIDGroup);
    SmartDashboard.putData("Right PID",rightPIDGroup);
    SmartDashboard.putData("Left PID Wrapper",leftPIDcontroller);
    SmartDashboard.putData("Right PID Wrapper",rightPIDcontroller);
    SmartDashboard.putNumber("Left Wheels Distance", leftChassisEncoder.getPosition());
    SmartDashboard.putNumber("Right Wheels Distance", rightChassisEncoder.getPosition());
    SmartDashboard.putNumber("Left Wheels RPM", leftChassisEncoder.getVelocity());
    SmartDashboard.putNumber("Right Wheels RPM", rightChassisEncoder.getVelocity());
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ChassisManualDrive());
  }
}
