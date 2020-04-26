package com.ekanek.fileProject.payload;

import com.ekanek.fileProject.model.User;

import lombok.Getter;
import lombok.Setter;

public class UploadFileResponse {

	@Getter
	@Setter
	private String fileName;

	@Getter
	@Setter
	private String fileDownloadUri;

	@Getter
	@Setter
	private String fileType;

	@Getter
	@Setter
	private long size;
	
	@Getter
	@Setter
	private String name;

	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String name) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.name = name;
	}
}
