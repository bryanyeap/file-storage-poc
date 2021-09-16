package org.mta.file_storage_poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class FileStorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("ping")
    public String ping() {
        return "PONG";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public String upload(@RequestBody MultipartFile file) {
        storageService.uploadFile(file);

        return "Upload successful";
    }

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e) {

        return "Upload failed";
    }

}
