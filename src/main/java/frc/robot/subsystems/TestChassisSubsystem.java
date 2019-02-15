/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TestChassisCommand;

/**
 * Add your docs here.
 */
public class TestChassisSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_VictorSPX leftFrontSPX;
  public WPI_VictorSPX  leftBackSPX;
  public WPI_VictorSPX  rightFrontSPX;
  public WPI_VictorSPX  rightBackSPX;
  public SpeedControllerGroup leftSPXGroup;
  public SpeedControllerGroup rightSPXGroup;
  public DifferentialDrive drivetrain;
  public TestChassisSubsystem(){
    leftFrontSPX=new WPI_VictorSPX(RobotMap.LeftFrontVictorID);
    leftBackSPX=new WPI_VictorSPX(RobotMap.LeftBackVictorID);
    rightFrontSPX=new WPI_VictorSPX(RobotMap.RightFrontVictorID);
    rightBackSPX=new WPI_VictorSPX(RobotMap.RightBackVictorID);
    leftSPXGroup=new SpeedControllerGroup(leftFrontSPX, leftBackSPX);
    rightSPXGroup=new SpeedControllerGroup(rightFrontSPX, rightBackSPX);
    drivetrain=new DifferentialDrive(leftSPXGroup, rightSPXGroup);
  }
public void TankDrive(double leftSpeed, double rightSpeed){
  drivetrain.tankDrive(leftSpeed,rightSpeed);
}
public void stop() {
  leftSPXGroup.set(0.0);
  rightSPXGroup.set(0.0);
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TestChassisCommand());
  }
}
