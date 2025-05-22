package com.nyam.model.service;

import java.sql.SQLException;

import com.nyam.model.dto.FileDto;

public interface FileService {
	
	FileDto uploadFile(byte[] file) throws SQLException;
}
