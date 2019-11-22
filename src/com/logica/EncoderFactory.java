package com.logica;

import com.logica.encoder.Encoder;
import com.logica.encoder.ZXing.QREncoder;

public class EncoderFactory {

	private static EncoderFactory encoderFac;
	private static Object mutex = new Object();

	public static EncoderFactory getInstance() {
		if (encoderFac == null) {
			synchronized (mutex) {
				if (encoderFac == null) {
					encoderFac = new EncoderFactory();
				}
			}
		}
		return encoderFac;
	}

	public Encoder getEncoder(int encoderType) {
		Encoder enc = null;
		switch (encoderType) {
		case 0:
			//enc = new DataMatrixEncoder();
			enc = null;
			break;
		case 1:
			enc = new QREncoder();
			break;
		}
		return enc;
	}
}
