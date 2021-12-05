package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends Hardware{
    @Override
    public void runOpMode() throws InterruptedException {
        hardwareSetup();
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        boolean clawOpened = true;
        int clawPos = 1;
        waitForStart();
        claw.setPosition(clawPos);
        boolean clawOpen = true;
        while (opModeIsActive()) {
            tankControl(0.6); //CHANGE THIS

            //control claw fingers
            if (gamepad2.a) {
                if (clawOpen) {
                    claw.setPosition(0.55);
                    clawOpen = false;
                } else {
                    claw.setPosition(0.9);
                    clawOpen = true;
                }
            }

                //Control clawPulley
                if (gamepad2.dpad_up) {
                    raiseClaw(0.5);
                } else if (gamepad2.dpad_down) {
                    raiseClaw(-0.5);
                } else if (gamepad2.dpad_left) {
                    raiseClawPos(2590, 0.5);
                } else if (gamepad2.dpad_right) {
                    raiseClawPos(0, 0.5);
                } else {
                    raiseClaw(0);
                }

                if (gamepad2.x) {
                    carousel.setPower(0.9);
                } else if (gamepad2.y){
                    carousel.setPower(-0.9);
                }
                else{
                    setCarouselPower(0);
                }
                telemetry.addData("ClawPos", clawPos);
                telemetry.addData("clawPulley", clawPulley.getCurrentPosition());
                telemetry.addData("Left sensor", touchSensorLeft.getValue());
                telemetry.addData("Right sensor", touchSensorRight.getValue());
                telemetry.update();


                // Top should be 2545

            }
        }

    public void tankControl(double maxPower) /* 0 < maxPower <= 1 */ {
        double leftPower = -gamepad1.left_stick_y * maxPower;
        double rightPower = -gamepad1.right_stick_y * maxPower;
        double strafePower = (gamepad1.left_stick_x + gamepad1.right_stick_x)/2 * maxPower;

        //double strafePower = (gamepad1.right_trigger - gamepad1.left_trigger) * maxPower; //positive is to the right

        double strafePowerLimit = Math.min(1 - Math.abs(rightPower) , 1 - Math.abs(leftPower));
        strafePower = Range.clip(strafePower, -strafePowerLimit, strafePowerLimit);

        // This will set each motor to a power between -1 and +1 such that the equation for
        // holonomic wheels works.
        frontLeft.setPower(leftPower  + strafePower);
        backLeft.setPower(leftPower  - strafePower);
        frontRight.setPower(rightPower - strafePower);
        backRight.setPower(rightPower + strafePower);
    }
}
