package com.pickx3.service;

import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.WorkImg;
import com.pickx3.domain.repository.WorkImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class WorkImgService {

    private String path = "C:\\dev\\teamProject\\pppick-backend\\src\\main\\resources\\img";
    private Path uploadPath;

    @Autowired
    private WorkImgRepository workImgRepository;

    public void uploadWorkImg(List<MultipartFile> files, Work work) {

        uploadPath = Paths.get(path).toAbsolutePath().normalize();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss_");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStamp = sdf.format(timestamp);

        for (MultipartFile file : files) {

            String fileType = file.getContentType();
            String originalFilename = file.getOriginalFilename();

//           파일 타입이 png/jpeg가 아닐 경우  exception 처리
            String fileName = timeStamp + StringUtils.cleanPath(file.getOriginalFilename());

            try {
                if (fileName.contains("..")) {
                    //에러 발생
                    throw new IllegalStateException("형식이 잘못되었습니다");
                }

                // Copy file to the target location (Replacing existing file with the same name)
                Path targetLocation = this.uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                WorkImg workImg = new WorkImg();
                workImg.setWork(work);
                workImg.setWorkImgName(fileName);
                workImg.setWorkImgOriginName(originalFilename);
                workImg.setWorkImgSrcPath(targetLocation.toString());

                workImgRepository.save(workImg);

//                return fileName;
            } catch (IOException ex) {
                throw new IllegalStateException("파일이 저장되지않았습니다");
            }
        }
    }

}
