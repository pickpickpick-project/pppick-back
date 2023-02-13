package com.pickx3.service;

import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.WorkImg;
import com.pickx3.domain.entity.work_package.dto.work.WorkImgForm;
import com.pickx3.domain.repository.WorkImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Transactional
@Service
public class WorkImgService {


//    private String path2 = "C:\\dev\\teamProject\\pppick-backend\\src\\main\\resources\\img";
//    private String path = new File("").getAbsolutePath() + File.separator + File.separator+"images"+ File.separator + File.separator+"work";
//    Path directoryPath = Paths.get(path);
//    private Path uploadPath;


    @Autowired
    private WorkImgRepository workImgRepository;

    /**
     * 상품 이미지 업로드
     */
    public List<WorkImg> uploadWorkImg(List<MultipartFile> files, Work work) throws Exception {
        List<WorkImg> fileList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(files)) {
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            String current_date = now.format(dateTimeFormatter);

            String absolutePath = new File("").getAbsolutePath() + File.separator +  File.separator;

            // 파일을 저장할 세부 경로 지정
            String path = "images" + File.separator +"work"+ File.separator+current_date;

            File file = new File(path);

            // 디렉터리가 존재하지 않을 경우
            if (!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
                // 디렉터리 생성에 실패했을 경우
                if (!wasSuccessful) System.out.println("file: was not successful");
            }


            for (MultipartFile multipartFile : files) {
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                // 확장자명이 존재하지 않을 경우 처리 x
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                    if (contentType.contains("image/jpeg")) originalFileExtension = ".jpg";
                    else if (contentType.contains("image/png")) originalFileExtension = ".png";
                    else  // 다른 확장자일 경우 처리 x
                        break;
                }

                // 파일명 중복 피하고자 나노초까지 얻어와 지정
                String new_file_name = System.nanoTime() + originalFileExtension;

                WorkImg workImg = WorkImg.builder()
                        .workImgName(new_file_name)
                        .workImgOriginName(multipartFile.getOriginalFilename())
                        .workImgSrcPath(absolutePath+ path + File.separator + new_file_name)
                        .work(work)
                        .build();

                fileList.add(workImg);

                workImgRepository.save(workImg);

                file = new File(absolutePath + path + File.separator + new_file_name);
                multipartFile.transferTo(file);


                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }

        }
        return fileList;


//            String fileType = multipartFile.getContentType();
//            String originalFilename = multipartFile.getOriginalFilename();
//
////           파일 타입이 png/jpeg가 아닐 경우  exception 처리
//            String fileName = timeStamp + StringUtils.cleanPath(multipartFile.getOriginalFilename());


    }


    /**
     * 상품 이미지 목록 조회
     *
     * @param workNum
     * @return
     */
    public List<WorkImgForm> getWorkImages(Long workNum) {
        return workImgRepository.findByWork_workNum(workNum);
    }

    /**
     * 상품 이미지 단일 삭제
     *
     * @param workImgNum
     */
    public void removeWorkImage(Long workImgNum) {
        WorkImg workImg = workImgRepository.findById(workImgNum).get();

        Path filePath = Paths.get(workImg.getWorkImgSrcPath());
//      파일 삭제가 성공한 경우, 테이블에 데이터 삭제
        try {
//          파일 삭제
            Files.delete(filePath);
//          테이블에 해당 데이터 샂게
            workImgRepository.delete(workImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 상품의 이미지 목록 삭제
     *
     * @param workNum
     */
    public void removeWorkImages(Long workNum) {
        List<WorkImgForm> images = workImgRepository.findByWork_workNum(workNum);

        try {
            for (WorkImgForm workImgForm : images) {
                WorkImg workImg = new WorkImg();

                workImg.setWorkImgNum(workImgForm.getWorkImgNum());
                workImg.setWorkImgName(workImgForm.getWorkImgName());
                workImg.setWorkImgName(workImgForm.getWorkImgName());
                workImg.setWorkImgOriginName(workImgForm.getWorkImgOriginName());
                workImg.setWorkImgSrcPath(workImgForm.getWorkImgSrcPath());

                Path filePath = Paths.get(workImgForm.getWorkImgSrcPath());
                Files.delete(filePath);

                workImgRepository.delete(workImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
