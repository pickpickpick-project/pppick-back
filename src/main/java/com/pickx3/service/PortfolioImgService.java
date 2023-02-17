package com.pickx3.service;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioImg;
import com.pickx3.domain.entity.portfolio_package.PortfolioImgForm;
import com.pickx3.domain.repository.PortfolioImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class PortfolioImgService {


    private final PortfolioImgRepository portfolioImgRepository;

    @PersistenceContext // 영속성 객체를 자동으로 삽입해줌
    private EntityManager em;

    //이미지 업로드
    public List<PortfolioImg> uploadPortfolioImg(List<MultipartFile> files, Portfolio portfolio) throws Exception {
        List<PortfolioImg> fileList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(files)) {
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            String current_date = now.format(dateTimeFormatter);

            String absolutePath = new File("").getAbsolutePath() + File.separator;

            // 파일을 저장할 세부 경로 지정
            String path = "images" + File.separator +"Portfolio"+ File.separator+current_date;

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

                PortfolioImg portfolioImg = PortfolioImg.builder()
                        .portfolioImgName(new_file_name)
                        .portfolioImgOriginName(multipartFile.getOriginalFilename())
                        .portfolioImgAddr(path+File.separator +new_file_name)
                        .portfolio(portfolio)
                        .build();

                log.debug(" =========== portfolioImg ======= ============================== " + portfolioImg.getPortfolio());

                fileList.add(portfolioImg);

                portfolioImgRepository.save(portfolioImg);

                file = new File(absolutePath + path + File.separator + new_file_name);
                multipartFile.transferTo(file);


                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }

        }
        return fileList;

    }
    // 이미지 목록 삭제
    public void removeWorkImages(Long portfolioNum) {
        List<PortfolioImgForm> portfolioImgForms = portfolioImgRepository.findByPortfolio_portfolioNum(portfolioNum);

        log.debug(" portfolioImgForm check   =================  " + portfolioImgForms);

        try {
            for (PortfolioImgForm portfolioImgForm : portfolioImgForms) {
                PortfolioImg portfolioImgList = new PortfolioImg();

                portfolioImgList.setPortfolioImgNum(portfolioImgForm.getPortfolioImgNum());
                portfolioImgList.setPortfolioImgName(portfolioImgForm.getPortfolioImgName());
                portfolioImgList.setPortfolioImgOriginName(portfolioImgForm.getPortfolioImgOriginName());
                portfolioImgList.setPortfolioImgAddr(portfolioImgForm.getPortfolioImgAddr());

                Path filePath = Paths.get(portfolioImgForm.getPortfolioImgAddr());
                log.debug(" path =================  " + filePath);

                Files.delete(filePath);

                portfolioImgRepository.delete(portfolioImgList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
