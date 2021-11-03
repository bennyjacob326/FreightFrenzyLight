package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo leftFinger;
    public Servo rightFinger;

    public Claw(Servo leftFinger, Servo rightFinger) {
        this.leftFinger = leftFinger;
        this.rightFinger = rightFinger;
        this.leftFinger.setDirection(Servo.Direction.FORWARD);
        this.rightFinger.setDirection(Servo.Direction.REVERSE);
    }

    public void setPosition(double endPosition) {
        this.leftFinger.setPosition(endPosition);
        this.rightFinger.setPosition(endPosition);
    }

    public void grabCube() {
        this.setPosition(0.05);
    }
}
