package com.pickx3.service;

import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.WorkImg;
import com.pickx3.domain.entity.work_package.dto.WorkImgForm;
import com.pickx3.domain.repository.WorkImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class WorkImgService {

    private String path = "C:\\dev\\teamProject\\pppick-backend\\src\\main\\resources\\img";
    private Path uploadPath;

    @Autowired
    private WorkImgRepository workImgRepository;

    /**
     * 상품 이미지 업로드
     */
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

                // target location에서 파일 복사
                Path targetLocation = this.uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                WorkImg workImg = WorkImg.builder()
                        .workImgName(fileName)
                        .workImgOriginName(originalFilename)
                        .workImgSrcPath(targetLocation.toString())
                        .work(work)
                        .build();

                workImgRepository.save(workImg);

                file.getInputStream().close();

//                return fileName;
            } catch (IOException ex) {
                throw new IllegalStateException("파일이 저장되지않았습니다");
            }
        }
    }

    /**
     * 상품 이미지 목록 조회
     * @param workNum
     * @return
     */
    public List<WorkImgForm> getWorkImages(Long workNum){
        return workImgRepository.findByWork_workNum(workNum);
    }

    /**
     * 상품 이미지 삭제
     * @param workImgNum
     */
    public void removeWorkImage(Long workImgNum){
        WorkImg workImg = workImgRepository.findById(workImgNum).get();

        Path filePath = Paths.get(workImg.getWorkImgSrcPath());
//      파일 삭제가 성공한 경우, 테이블에 데이터 삭제
        try{
//          파일 삭제
            Files.delete(filePath);
//          테이블에 해당 데이터 샂게
            workImgRepository.delete(workImg);
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
