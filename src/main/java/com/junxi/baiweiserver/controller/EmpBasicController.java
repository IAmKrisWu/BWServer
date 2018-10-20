package com.junxi.baiweiserver.controller;

import com.github.pagehelper.PageInfo;
import com.junxi.baiweiserver.model.Employee;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.EmployeeService;
import com.junxi.baiweiserver.utils.POIUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService service;
    @GetMapping("/emp")
    public PageInfo<Employee> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer count, @RequestParam(defaultValue = "") String name){
        return service.getEmployeeByPage(page,count,name);
    }

    @DeleteMapping("/emp/{ids}")
    public RespBean deleteByIds(@PathVariable("ids") String ids){
        String[] split = ids.split(",");
        boolean b = service.deleteByIds(split);
        if(b){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @GetMapping("/download2")
    public ResponseEntity<byte[]> download2(){
        File file = new File("/Users/kris/Downloads", "图片.png");
//
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = IOUtils.toByteArray(fileInputStream);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentDispositionFormData("attachment",new String(file.getName().getBytes(),"ISO-8859-1"));
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(bytes,httpHeaders,HttpStatus.CREATED);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(){
        return POIUtils.export(service.getAll());
    }

    @PostMapping("/import")
    public RespBean importEmp(MultipartFile file){
        try {

            List<Employee> importemp = POIUtils.importemp(file);
            service.allEmployee(importemp);

            System.out.println(importemp);
            return RespBean.ok("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return RespBean.error("上传失败");
    }

}
