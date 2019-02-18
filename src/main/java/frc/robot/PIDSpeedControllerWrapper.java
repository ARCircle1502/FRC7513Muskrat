/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Add your docs here.
 */
public class PIDSpeedControllerWrapper extends SendableBase implements SpeedController{
    public PIDController PID;
    public boolean isInverted;
    public double maxPIDOutput;
    public PIDSpeedControllerWrapper(PIDController pid,double maxPIDOutput){
        this.PID=pid;
        this.maxPIDOutput=maxPIDOutput;
        this.isInverted=false;
        
    }


    private double scale(double percent) {
        if (percent > 1.0) {
          return maxPIDOutput;
        }
        if (percent < -1.0) {
          return -maxPIDOutput;
        }
        return percent*maxPIDOutput;
      }
    private double Limit(double percent) {
        if (percent > 1.0) {
          return 1.0;
        }
        if (percent < -1.0) {
          return -1.0;
        }
        return percent;
      }
    @Override
    public void pidWrite(double output) {
        PID.setSetpoint(Limit(output)) ;
    }

    @Override
    public void set(double speed) {
        PID.setSetpoint(scale(speed));
    }

    @Override
    public double get() {
        return PID.getSetpoint()/maxPIDOutput;
    }
    @Override
    public void setInverted(boolean isInverted) {
        this.isInverted=isInverted;
    }

    @Override
    public boolean getInverted() {
        return isInverted;
    }

    @Override
    public void disable() {
        PID.disable();
    }

    @Override
    public void stopMotor() {
        PID.setSetpoint(0.0);
        PID.pidWrite(0.0);
    }

    @Override
    public void initSendable(SendableBuilder builder) {

    }
    public PIDController getPID() {
        return PID;
    }
}
