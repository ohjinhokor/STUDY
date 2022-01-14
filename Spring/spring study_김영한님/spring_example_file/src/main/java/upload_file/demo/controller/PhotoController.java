package upload_file.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/photo")
@Slf4j
@RequiredArgsConstructor
public class PhotoController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam(value = "file") MultipartFile multipartFile , HttpServletRequest httpServletRequest) throws IOException {

        log.info("request={}",httpServletRequest);
        log.info("itemName={}",itemName);
        log.info("multipartFile={}", multipartFile);

        if(!multipartFile.isEmpty()){
            String fullpath = fileDir + multipartFile.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullpath);
            System.out.println(fullpath);
            multipartFile.transferTo(new File(fullpath));
        }
        return "upload-form";
    }
}