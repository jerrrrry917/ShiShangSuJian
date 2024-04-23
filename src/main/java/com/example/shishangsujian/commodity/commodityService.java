package com.example.shishangsujian.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class commodityService {
    @Autowired
    private commodityRepository commodityRepository;

    @Autowired
    private commodityPayRepository commodityPayRepository;
    @Value("${spring.web.resources.static-locations}")
    private String staticLocations;

    public List<commodityModel> getCommodityList(){
        return commodityRepository.findAll();
    }

    public List<commodityPayModel> findSpecificCommodity(int staffId){
        return commodityPayRepository.findByStaffId(staffId);
    }

    public commodityModel createCommodity(double price, String description, MultipartFile photo) throws IOException {
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        String uploadDir = staticLocations.replace("file:", "") + "commodity/";

        // 保存图片到指定路径
        saveFile(uploadDir, fileName, photo);

        // 构建图片的相对路径
        String photoURL = "commodity/" + fileName;

        // 创建并保存商品信息到数据库
        commodityModel newCommodity = new commodityModel(price, description, photoURL);
        return commodityRepository.save(newCommodity);
    }

    private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }

    public void deleteCommodity(int id){
        commodityRepository.deleteById(id);
    }
}