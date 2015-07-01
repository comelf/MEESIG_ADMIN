package com.meesig.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import com.meesig.model.PhotoInfo;

public class ImageTransferUtil {
	
	private static final int ORIGIN_QUALITY = 0;
	List<File> files = new ArrayList<File>();
	int type;
	int[] 	sizes = { 800, 560, 400, 320, 240, 160, 80 };
	int[] quality = {  1,   2,   3,   4,   5,   6,   7 };
	
	public ImageTransferUtil() {
	}

	public void save(MultipartFile file, PhotoInfo pi) throws IllegalStateException, IOException {		
		File origin = new File(pi.getSavePathWithQuality(ORIGIN_QUALITY));
		file.transferTo(origin);
		files.add(origin);
		
		BufferedImage originalImg = ImageIO.read(origin);
		type = originalImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImg.getType();
		
		int len = sizes.length;
		
		for (int i=0; i<len; i++) {
			BufferedImage bi = Scalr.resize(originalImg, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, sizes[i],Scalr.OP_ANTIALIAS);
			
			if(bi!=null){
				File resize = new File(pi.getSavePathWithQuality(quality[i]));
				ImageIO.write(bi, pi.getFileExt(), resize);
				files.add(resize);
			}
		}
		
	}

	public void delete() {
		Iterator<File> f = files.iterator();
		while (f.hasNext()) {
			File file = (File) f.next();
			file.delete();
		}
	}

	public void saveOne(MultipartFile file, PhotoInfo pi) throws IllegalStateException, IOException {
		File origin = new File(pi.getSavePathWithQuality(ORIGIN_QUALITY));
		file.transferTo(origin);
		files.add(origin);
	}

}
