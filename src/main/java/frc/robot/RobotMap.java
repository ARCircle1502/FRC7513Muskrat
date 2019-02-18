/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public static double WheelDiameter=15.24;//cm
  public static double ChassisEncoderCPR=42.0;//42 Counts Per Rev
  public static double ChassisGearRatio=0.0933706816059757;//AndyMark Toughbox Mini 
  //                                                           Reduction Ratio 10.71:1   

  public static int LeftFrontVictorID=2;
  public static int LeftBackVictorID=3;
  public static int LeftBackSparkMAXID=3;
  public static int RightFrontVictorID=4;
  public static int RightBackVictorID=5;
  public static int RightBackSparkMAXID=5;
  public static int PanelVictorID=1;

  public static double ChassisLeft_P=0.00052;
  public static double ChassisLeft_I=0.0;
  public static double ChassisLeft_D=0.00155;
  public static double ChassisRight_P=0.00052;
  public static double ChassisRight_I=0.0;
  public static double ChassisRight_D=0.00155;

  public static double MaxChassisWheelRPM=400.0;//MAX Output Shaft RPM
}
