package com.neosoft.user.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;
@Component
public class PasswordUtil {

	public static String getEncodedPwd(String pazzword) {
		byte[] pwdByts = pazzword.getBytes();
		Encoder encoder = Base64.getEncoder();
		String encodedPwd = encoder.encodeToString(pwdByts);
		return encodedPwd;
	}

	public static String getDecodedPwd(String encodedPwd) {
		byte[] encPwdByts = encodedPwd.getBytes();
		Decoder decoder = Base64.getDecoder();
		byte[] decodePwdByts = decoder.decode(encPwdByts);
		String DecodedPwd = new String(decodePwdByts);
		return DecodedPwd;
	}
}
