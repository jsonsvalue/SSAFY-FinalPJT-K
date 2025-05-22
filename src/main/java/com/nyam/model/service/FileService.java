package com.nyam.model.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.nyam.model.dto.FileDto;

public interface FileService {
	
	FileDto uploadFile(MultipartFile file) throws SQLException, IOException;
}
