package com.example.shishangsujian.savePhoto;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


//图片保存为相对路径
@Service
public class savePhotoService {
    @Autowired
    private savePhotoRepository repository;

    private static final String UPLOAD_DIR = "wxdata/static/";

    public void savePhoto(HttpSession session, int sign , String name , String newname,MultipartFile file){
        String fileName = file.getOriginalFilename();
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();  // 创建目录和任何必要的父目录
            System.out.println("创建成功");
        }

        // 添加时间戳前缀防止文件名冲突
        String newFileName = System.currentTimeMillis() + "_" + fileName;

        Path path = Paths.get(UPLOAD_DIR + newFileName);
        int memberId = Math.toIntExact((Long) session.getAttribute("Id"));

        try {
            Files.copy(file.getInputStream(), path);

            //保存
            if(!name.equals("empty")&&memberId>0){
                savePhotoModel newmodel = repository.findByMemberIdAndName(memberId, name);
                if(sign == 1)
                    newmodel.setPhotoFront(newFileName);
                else if(sign == 2)
                    newmodel.setPhotoSide(newFileName);
                else if(sign == 3)
                    newmodel.setPhotoBack(newFileName);

                repository.save(newmodel);
            } else {
                savePhotoModel newmodel = new savePhotoModel();
                newmodel.setMemberId(memberId);
                newmodel.setName(newname);
                if(sign == 1)
                    newmodel.setPhotoFront(newFileName);
                else if(sign == 2)
                    newmodel.setPhotoSide(newFileName);
                else if(sign == 3)
                    newmodel.setPhotoBack(newFileName);

                repository.save(newmodel);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getPath(HttpSession session,String name,int sign){//返回照片路径
        int memberId = Math.toIntExact((Long) session.getAttribute("Id"));
        savePhotoModel model=repository.findByMemberIdAndName(memberId,name);

        if(sign==1)
            return model.getPhotoFront();

        else if(sign==2)
            return model.getPhotoSide();

        else if(sign==3)
            return model.getPhotoBack();

        else
            return null;
    }

    public List<savePhotoModel> showList(HttpSession session){//考虑一个账号有多个成员留像,展示不同成员的名字
        int memberId = Math.toIntExact((Long) session.getAttribute("Id"));
        List<savePhotoModel> list=repository.findByMemberId(memberId);

        if(list==null)
            System.out.println("用户无留像"+memberId);

        return list;
    }
}
