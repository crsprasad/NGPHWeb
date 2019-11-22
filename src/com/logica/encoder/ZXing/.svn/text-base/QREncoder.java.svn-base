package com.logica.encoder.ZXing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
import com.logica.encoder.common.EncodeData;
import com.logica.encoder.Encoder;
import com.logica.encoder.EncoderException;

public class QREncoder extends Encoder {

	public void encode(EncodeData eData, String fileName)
			throws EncoderException {

		QRCode qrcode = new QRCode();
		int errorLevel = 0;
		boolean quiteZone = true;
		int quitezoneValue = 1;
		int height = 2;
		
		try {
			switch (errorLevel) {
			case 0:
				com.google.zxing.qrcode.encoder.Encoder.encode(eData
						.getMessage(), ErrorCorrectionLevel.L, qrcode);
				break;
			case 1:
				com.google.zxing.qrcode.encoder.Encoder.encode(eData
						.getMessage(), ErrorCorrectionLevel.M, qrcode);
				break;
			case 2:
				com.google.zxing.qrcode.encoder.Encoder.encode(eData
						.getMessage(), ErrorCorrectionLevel.H, qrcode);
				break;
			default:
				com.google.zxing.qrcode.encoder.Encoder.encode(eData
						.getMessage(), ErrorCorrectionLevel.L, qrcode);
			}
		} catch (WriterException we) {
			throw new EncoderException(we);
		}
		int qrSize = qrcode.getMatrixWidth();
		int margin = 0;
		if (quiteZone) {
			margin = quitezoneValue;
		}
		int imageSize = qrSize + 2 * margin; // includes quiet zone
		int width = height * 50;

		if (width < imageSize)
			width = imageSize;

		int iResolution = width / imageSize;

		int remaining = width % imageSize;
		int topLeftPosition = ((remaining > 0) ? remaining / 2 : iResolution)
				+ margin * iResolution;
		int size = width;

		BufferedImage im = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_RGB);
		im.createGraphics();
		Graphics2D g = (Graphics2D) im.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, size, size);

		// paint the image using the ByteMatrik
		g.setColor(Color.BLACK);
		for (int i = 0; i < qrSize; i++) {
			for (int j = 0; j < qrSize; j++) {
				if (qrcode.at(i, j) == 1)
					g.fillRect(topLeftPosition + i * iResolution,
							topLeftPosition + j * iResolution, iResolution,
							iResolution);
			}
		}
		try {
			ImageIO.write(im, "png", new File(fileName));
		} catch (IOException e) {
			throw new EncoderException(e);
		}
	}
}
