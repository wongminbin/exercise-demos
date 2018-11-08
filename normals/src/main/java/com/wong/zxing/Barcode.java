package com.wong.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
* @author HuangZhibin
* 
* 2018年2月28日 下午5:32:29
*/
public class Barcode {

	public static void main(String[] args) throws WriterException {
		int width = 75;  
        int height = 50;  
        // int width = 105;  
        // int height = 50;  
        // 条形码的输入是13位的数字  
        // String text = "6923450657713";  
        // 二维码的输入是字符串  
        String text = "F8MWQD831208130001";  
        String format = "png";  
        HashMap<EncodeHintType, String> hints = new HashMap<>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        // 条形码的格式是 BarcodeFormat.EAN_13  
        // 二维码的格式是BarcodeFormat.QR_CODE  
        BitMatrix bm = new MultiFormatWriter().encode(text,  
                BarcodeFormat.CODE_128, width, height, hints);  
  
        File out = new File("D:\\new.png");  
        // 生成条形码图片  
        // File out = new File("ean3.png");  
  
        WriteBitMatricToFile.writeBitMatricToFile(bm, format, out);  
	}
}

class WriteBitMatricToFile {  
    private static final int BLACK = 0xFF000000;  
    private static final int WHITE = 0xFFFFFFFF;  
  
    private static BufferedImage toBufferedImage(BitMatrix bm) {  
        int width = bm.getWidth();  
        int height = bm.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_3BYTE_BGR);  
        for (int i = 0; i < width; i++) {  
            for (int j = 0; j < height; j++) {  
                image.setRGB(i, j, bm.get(i, j) ? BLACK : WHITE);  
            }  
        }  
        return image;  
    }  
  
    public static void writeBitMatricToFile(BitMatrix bm, String format,  
            File file) {  
        BufferedImage image = toBufferedImage(bm);  
        try {  
            if (!ImageIO.write(image, format, file)) {  
                throw new RuntimeException("Can not write an image to file" + file);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void writeToStream(BitMatrix matrix, String format,  
            OutputStream stream) throws IOException {  
        BufferedImage image = toBufferedImage(matrix);  
        if (!ImageIO.write(image, format, stream)) {  
            throw new IOException("Could not write an image of format " + format);  
        }  
    }  
}  
