/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.PanelCommand;
import edu.wpi.first.wpilibj.SpeedController; 

/**
 *
 */
public class PanelSubsystem extends Subsystem {
	VictorSPX mPanelSpx;
public SpeedController PanelMotor;
public SpeedController PanelSpeedController;
public PanelSubsystem(){
	mPanelSpx=new WPI_VictorSPX(RobotMap.PanelVictorID);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void PanelSpeedController(double Speed) {
		PanelSpeedController.set(Speed);
		
	}
    
    public void stopMotor() {
			PanelSpeedController.stopMotor();
	}
}