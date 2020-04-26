package com.ekanek.fileProject.controller;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ekanek.fileProject.model.DBFile;
import com.ekanek.fileProject.model.Role;
import com.ekanek.fileProject.model.User;
import com.ekanek.fileProject.payload.UploadFileResponse;
import com.ekanek.fileProject.repositories.DBFileRepository;
import com.ekanek.fileProject.repositories.RoleRepository;
import com.ekanek.fileProject.repositories.UserRepository;
import com.ekanek.fileProject.service.DBFileStorageService;

@RestController
@RequestMapping("/dev-test")
public class FileController {

	@Autowired
	private DBFileStorageService dbFileStorageService;
	
	@Autowired
	private DBFileRepository dbRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/")
	public String message() {
		return "Welcome";
	}
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
			Authentication authentication) {
		DBFile dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

         return (new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(), getOwnerFromAuthentication(authentication)));		
	}
	
    @GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId){
		
		DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
	}
    
    @GetMapping("/deleteFile/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId, Authentication authentication){
    	dbFileStorageService.deleteFile(fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
	@GetMapping("/populate")
    public String populateDB() {
    	Role role = new Role();
    	role.setName("ADMIN");
    	role.setDescription("admin rights");
    	roleRepository.save(role);
    	HashSet<Role> set = new HashSet<Role>();
    	set.add(role);
    	User user = new User();
    	user.setEmail("vatsal@gmail.com");
    	user.setRoles(set);
    	user.setSaltedHashedPassword("vatsalgarg");
    	userRepository.save(user);
		return "populated";
    }
    
	/*
	 * @GetMapping("/listFile") public ResponseEntity<Iterable<DBFile>>
	 * list(Authentication authentication){ List<DBFile> allByOwner =
	 * dbRepository.findAllByOwner(getOwnerFromAuthentication(authentication));
	 * return new ResponseEntity<>(allByOwner, HttpStatus.OK); }
	 */
    
    private String getOwnerFromAuthentication(@AuthenticationPrincipal Principal user) {
    	return user.getName();
    }

}