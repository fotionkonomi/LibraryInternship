package org.lms.utils;

public class Encryptor {

	public static String encrypt(String text, int s) {
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isDigit(text.charAt(i))) {
				if (Character.isUpperCase(text.charAt(i))) {
					char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
					result = result + ch;
				} else {
					char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
					result = result + ch;
				}
			} else {
				result = result + text.charAt(i);
			}
		}
		return result;
	}

	public static String decrypt(String text, int s) {
		return encrypt(text, 26 - s); // dekriptimi eshte thjesht enkriptim me shiftim = 26-shiftim
	}
}
