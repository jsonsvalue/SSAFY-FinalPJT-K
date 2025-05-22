package com.nyam.model.service.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyam.model.dao.FileDao;
import com.nyam.model.dto.FileDto;
import com.nyam.model.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileDao dao;

	@Override
	public FileDto uploadFile(byte[] file) throws SQLException {
		// TODO Auto-generated method stub
		BufferedImage orgImage=null,compImage=null,thumbImage=null;
		String orgName=null,compName=null,thumbName=null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(file);
			orgImage = ImageIO.read(bis);
			compImage = resizeImage(orgImage,600,400);
			thumbImage = resizeImage(orgImage,300,300);
			orgName = UUID.randomUUID()+".png";
			compName = UUID.randomUUID()+".png";
			thumbName = UUID.randomUUID()+".png";
			ImageIO.write(orgImage, "png", new File(orgName));
			ImageIO.write(compImage, "png", new File(orgName));
			ImageIO.write(thumbImage, "png", new File(orgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileDto dto = new FileDto(0, thumbName, compName, orgName, "", "", "");
		    
//		int res = dao.insertFile(dto);
//		if (res!=0) {
//			return dto;
//		} else {
//			throw new SQLException("Insert Failed");
//		}
		return dto;
	}
	private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
		Image tempImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(tempImage, 0, 0, null);
		g2d.dispose();
		return resizedImage;
	}
}
