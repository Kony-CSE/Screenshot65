package com.kony.image.modifications;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Vector;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import com.konylabs.android.KonyMain;

public class ImageResize {

	public static String resizeImageAsperReq(String ImgUri, int maxSideOutput) {

		String base64Data = "";
		try {

			Uri ur1 = Uri.parse(ImgUri);

			System.out.println("entered in thumbnailImage");

			String filePath = URIConversion.getPath(KonyMain.getAppContext(), ur1);
			File imgPath = new File(filePath);
			System.out.println("the actual name of the file s " + imgPath.getName());

			long actualImgSize = imgPath.length();
			System.out.println("actual size of the image is" + actualImgSize);

			base64Data = getImageBase64DataforResizedImages(imgPath, maxSideOutput);
			System.out.println("the base64 data after the compression" + base64Data);

		} catch (Exception e) {
			System.out.println("exception occured in resizing the image " + e);

		}

		return base64Data;

	}

	public static String getImageBase64DataforResizedImages(File imgPath, long maxSideOutput) {
		String resizedbase64Data = "";
		try {
			byte[]b1 = (byte[])null;

			Bitmap resized = null;
			BitmapFactory.Options options = new BitmapFactory.Options();

			options.inMutable = true;
			options.inPurgeable = true;
			options.inJustDecodeBounds = false;
			options.inSampleSize = 4;

			Bitmap bitmapURI = BitmapFactory.decodeFile(imgPath.getAbsolutePath(),
					options);
			float actualImageHeight = bitmapURI.getHeight();
			float actualImageWidth = bitmapURI.getWidth();

			if ((actualImageHeight * 4) < 5000 || (actualImageHeight * 4) < 5000) {
				options = new BitmapFactory.Options();
				options.inMutable = true;
				options.inPurgeable = true;
				options.inJustDecodeBounds = false;
				bitmapURI = BitmapFactory.decodeFile(imgPath.getAbsolutePath(), options);
				actualImageHeight = bitmapURI.getHeight();
				actualImageWidth = bitmapURI.getWidth();

			}

			System.out.println("Before height of the image: height of the image" +
				bitmapURI.getHeight() + " Width of the image:" +
				bitmapURI.getWidth() + " size of the image:" +
				imgPath.length());
			if ((actualImageWidth > (float)maxSideOutput) &&
				(actualImageHeight > (float)maxSideOutput)) {
				System.out.println("if(actualImageWidth>maxSideOutput && actualImageHeight>maxSideOutput ){");
				if (actualImageWidth > actualImageHeight) {
					System.out.println("if(actualImageWidth>actualImageHeight){");
					float imageRatio = actualImageWidth / actualImageHeight;
					System.out.println("imageRatio " + imageRatio);

					actualImageWidth = (float)maxSideOutput;
					actualImageHeight = (float)maxSideOutput / imageRatio;
					System.out.println("actualImageHeight   :" + actualImageHeight + "actualImageWidth : " + actualImageWidth);
				} else if (actualImageHeight > actualImageWidth) {
					System.out.println("}else if(actualImageHeight>actualImageWidth){");
					float imageRatio = actualImageHeight / actualImageWidth;
					System.out.println("imageRatio " + imageRatio);
					actualImageHeight = (float)maxSideOutput;
					actualImageWidth = (float)maxSideOutput / imageRatio;
					System.out.println("actualImageHeight   :" + actualImageHeight + "actualImageWidth :" + actualImageWidth);
				} else {
					actualImageWidth = (float)maxSideOutput;
					actualImageHeight = (float)maxSideOutput;
				}
			} else if (actualImageWidth > (float)maxSideOutput) {
				System.out.println("  }else if(actualImageWidth>maxSideOutput){");
				float imageRatio = actualImageWidth / actualImageHeight;
				System.out.println("imageRatio " + imageRatio);
				actualImageWidth = (float)maxSideOutput;
				actualImageHeight = (float)maxSideOutput / imageRatio;
				System.out.println("actualImageHeight   :" + actualImageHeight + "actualImageWidth : " + actualImageWidth);
			} else if (actualImageHeight > (float)maxSideOutput) {
				System.out.println("}else if(actualImageHeight>maxSideOutput){");
				float imageRatio = actualImageHeight / actualImageWidth;
				System.out.println("imageRatio " + imageRatio);
				actualImageHeight = (float)maxSideOutput;
				actualImageWidth = (float)maxSideOutput / imageRatio;
				System.out.println("actualImageHeight   :" + actualImageHeight + "actualImageWidth : " + actualImageWidth);
			}
			System.out.println("actualImageWidth value :" + actualImageWidth +
				" actualImageHeight value:" + actualImageHeight);
			resized = Bitmap.createScaledBitmap(bitmapURI, (int)actualImageWidth,
					(int)actualImageHeight, true);
			int compFactor = 80;
			ByteArrayOutputStream blob = null;
			try {
				//for (int i = 0; compFactor >= 0; i++)
				//{
				blob = new ByteArrayOutputStream();
				System.out.println("entered the compression logic of the image with the compression factor" + compFactor);
				resized.compress(Bitmap.CompressFormat.JPEG, compFactor, blob);
				resizedbase64Data = Base64.encodeToString(blob.toByteArray(),
						0);
				b1 = Base64.decode(resizedbase64Data, 0);

				System.out.println("the actual size of the image byte size is" + b1.length);
				blob.flush();

				//}
			} catch (Exception e) {
				System.out.println("exception occured in compressing the image" + e);

			}
			finally {
				if (blob != null) {
					try {
						blob.close();
					} catch (Exception localException2) {
						System.out.println("exception occured in compressing the image" + localException2);
					}
				}
			}

			System.out.println("After height of the image:" + resized.getHeight() +
				" Width of the image:" + resized.getWidth() +
				" size of the image:" + b1.length);

		} catch (Exception e) {
			System.out.println("exception occured in getImageBase64DataforResizedImages" + e);
		}

		return resizedbase64Data;
	}
	
}