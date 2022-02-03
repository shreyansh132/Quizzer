package com.shreyansh.quizzer.auth.util;

import java.text.DecimalFormat;
import java.util.Random;

public class OtpGenerator {
    public static String generateOtp(){
        String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
        return otp;
    }
}
