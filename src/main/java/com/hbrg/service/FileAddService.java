package com.hbrg.service;

import com.hbrg.entity.Hfile;
import com.hbrg.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class FileAddService {

    // @Value 어노테이션을 통해 application.properties 파일에 등록한
    // itemImgLocation을 불러와 itemImgLocation 변수에 넣어줌
    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final FileRepository fileRepository;

    private final FileService fileService;

    public void saveFile(Hfile file, MultipartFile imgFile) throws Exception{
        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, imgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        // 상품 이미지 정보 저장
        file.updateFile(oriImgName, imgName, imgUrl);
        fileRepository.save(file);
    }

//    public void updateFile(Long fileId, MultipartFile imgFile) throws Exception{
//        if(!imgFile.isEmpty()){
//            HFile savedFile = fileRepository.findByFileId(fileId);
//            if (!StringUtils.isEmpty(savedFile.getFileNm())){
//                fileService.deleteFile(itemImgLocation + "/" + savedFile.getFileNm());
//            }
//            String oriImgName = imgFile.getOriginalFilename();
//            String imgName = "";
//            String imgUrl = "";
//
//            // 파일 업로드
//            if(!StringUtils.isEmpty(oriImgName)){
//                imgName = fileService.uploadFile(itemImgLocation, oriImgName, imgFile.getBytes());
//                imgUrl = "/images/item/" + imgName;
//            }
//
//            // 상품 이미지 정보 저장
//            savedFile.updateFile(oriImgName, imgName, imgUrl);
//            fileRepository.save(savedFile);
//        }
//    }

    public void updateFile(Long fileId, MultipartFile imgFile) throws Exception{
        if(!imgFile.isEmpty()){
            Hfile savedFile = fileRepository.findByFileId(fileId);

            if (!StringUtils.isEmpty(savedFile.getFileNm())){
                fileService.deleteFile(itemImgLocation + "/" + savedFile.getFileNm());
            }

            String oriImgName = imgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, imgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedFile.updateFile(oriImgName,imgName,imgUrl);
            fileRepository.save(savedFile);
        }
    }
}