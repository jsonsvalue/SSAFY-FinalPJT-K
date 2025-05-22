package com.nyam.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nyam.model.dao.FileDao;
import com.nyam.model.dto.FileDto;
import com.nyam.model.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileDao dao;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${app.savepath}")
	private String path;

	@Override
	public FileDto uploadFile(MultipartFile file) throws SQLException, IOException {
		String filename = UUID.randomUUID()+".png";
		File f = new File(path+filename);
		f.mkdirs();
		file.transferTo(f);
		
		FileDto dto = new FileDto(0, filename,null,null,null);
		
		int res = dao.insertFile(dto);
		if (res!=0) {
			return dto;
		} else {
			throw new SQLException("Insert Failed");
		}
	}
//	private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
//		Image tempImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = resizedImage.createGraphics();
//		g2d.drawImage(tempImage, 0, 0, null);
//		g2d.dispose();
//		return resizedImage;
//	}
}
