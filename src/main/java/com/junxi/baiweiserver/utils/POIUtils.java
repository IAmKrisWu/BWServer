package com.junxi.baiweiserver.utils;

import com.junxi.baiweiserver.model.Department;
import com.junxi.baiweiserver.model.Employee;
import com.junxi.baiweiserver.model.Nation;
import com.junxi.baiweiserver.model.Politicsstatus;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class POIUtils {
    public static ResponseEntity<byte[]> export(List<Employee> employees){
        HSSFWorkbook workbook = new HSSFWorkbook();
//        创建信息配置
        workbook.createInformationProperties();
//        cellStyle用于设置单元格属性
        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        数据格式修改。 注意如果修改的是日期，则是d/m/yy
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

//        设置基本信息
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
        information.setCategory("员工资料");
        information.setCompany("腾讯");
        information.setLanguage("中文");
        information.setManager("kris");
//        设置摘要信息
        SummaryInformation summaryInformation = workbook.getSummaryInformation();
        summaryInformation.setAuthor("kris");
        summaryInformation.setTitle("员工资料表");
//        创建一个表单
        HSSFSheet sheet = workbook.createSheet("员工资料表");
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell cell0 = row0.createCell(0);cell0.setCellValue("员工编号");
        HSSFCell cell1 = row0.createCell(1);cell1.setCellValue("员工姓名");
        HSSFCell cell2 = row0.createCell(2);cell2.setCellValue("员工性别");
        HSSFCell cell3 = row0.createCell(3);cell3.setCellValue("出生日期");
//        设置某列的宽度
        sheet.setColumnWidth(3,30*256);
        HSSFCell cell4 = row0.createCell(4);cell4.setCellValue("身份证号码");
        HSSFCell cell5 = row0.createCell(5);cell5.setCellValue("是否已婚");
        HSSFCell cell6 = row0.createCell(6);cell6.setCellValue("籍贯");
        HSSFCell cell7 = row0.createCell(7);cell7.setCellValue("民族");
        HSSFCell cell8 = row0.createCell(8);cell8.setCellValue("政治面貌");
        HSSFCell cell9 = row0.createCell(9);cell9.setCellValue("联系电话");
        HSSFCell cell10 = row0.createCell(10);cell10.setCellValue("邮箱");
        HSSFCell cell11 = row0.createCell(11);cell11.setCellValue("所属部门");
        HSSFCell cell12 = row0.createCell(12);cell12.setCellValue("nationid");
        HSSFCell cell13 = row0.createCell(13);cell13.setCellValue("politicid");
        HSSFCell cell14 = row0.createCell(14);cell14.setCellValue("departmentid");
        HSSFCell cell15 = row0.createCell(15);cell15.setCellValue("joblevelid");
        HSSFCell cell16 = row0.createCell(16);cell16.setCellValue("posid");

        for (int i = 0; i < employees.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);

            Employee employee = employees.get(i);

            HSSFCell cell112 = row.createCell(0);
            cell112.setCellValue(employee.getId());
            HSSFCell cell113 = row.createCell(1);
            cell113.setCellValue(employee.getName());
            HSSFCell cell114 = row.createCell(2);
            cell114.setCellValue(employee.getGender());
            HSSFCell cell115 = row.createCell(3);
            //        设置单元格属性
            cell115.setCellStyle(cellStyle);
            cell115.setCellValue(employee.getBirthday());

            HSSFCell cell116 = row.createCell(4);
            cell116.setCellValue(employee.getIdcard());
            HSSFCell cell17 = row.createCell(5);
            cell17.setCellValue(employee.getWedlock());
            HSSFCell cell18 = row.createCell(6);
            cell18.setCellValue(employee.getNativeplace());
            HSSFCell cell19 = row.createCell(7);
            cell19.setCellValue(employee.getNation().getName());
            HSSFCell cell20 = row.createCell(8);
            cell20.setCellValue(employee.getPoliticsstatus().getName());
            HSSFCell cell21 = row.createCell(9);
            cell21.setCellValue(employee.getPhone());
            HSSFCell cell22 = row.createCell(10);
            cell22.setCellValue(employee.getEmail());
            HSSFCell cell23 = row.createCell(11);
            cell23.setCellValue(employee.getDepartment().getName());
            HSSFCell cell24 = row.createCell(12);
            cell24.setCellValue(employee.getNationid());
            HSSFCell cell25 = row.createCell(13);
            cell25.setCellValue(employee.getPoliticid());
            HSSFCell cell26 = row.createCell(14);
            cell26.setCellValue(employee.getDepartmentid());
            HSSFCell cell27 = row.createCell(15);
            cell27.setCellValue(employee.getJoblevelid());
            HSSFCell cell28 = row.createCell(16);
            cell28.setCellValue(employee.getPosid());
        }

        try {
//            字节流进行导出
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            workbook.write(arrayOutputStream);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentDispositionFormData("attachment",new String("员工资料表.xls".getBytes(),"ISO-8859-1"));
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(arrayOutputStream.toByteArray(),httpHeaders,HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Employee> importemp(MultipartFile file) throws IOException, ParseException {

        List<Employee> employees = new ArrayList<>();

        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < physicalNumberOfRows; j++) {
                if(j==0){
                    continue;
                }
                HSSFRow row = sheet.getRow(j);
                Employee employee = new Employee();
                employee.setName(row.getCell(1).getStringCellValue());
                employee.setGender(row.getCell(2).getStringCellValue());
                employee.setBirthday(row.getCell(3).getDateCellValue());
                employee.setIdcard(row.getCell(4).getStringCellValue());
                employee.setWedlock(row.getCell(5).getStringCellValue());
                employee.setNativeplace(row.getCell(6).getStringCellValue());
                employee.setNation(new Nation(row.getCell(7).getStringCellValue()));
                employee.setPoliticsstatus(new Politicsstatus(row.getCell(8).getStringCellValue()));
                employee.setPhone(row.getCell(9).getStringCellValue());
                employee.setEmail(row.getCell(10).getStringCellValue());
                employee.setDepartment(new Department(row.getCell(11).getStringCellValue()));
                employee.setNationid(((int) row.getCell(12).getNumericCellValue()));
                employee.setPoliticid(((int) row.getCell(13).getNumericCellValue()));
                employee.setDepartmentid(((int) row.getCell(14).getNumericCellValue()));
                employee.setJoblevelid(((int) row.getCell(15).getNumericCellValue()));
                employee.setPosid(((int) row.getCell(16).getNumericCellValue()));

                employees.add(employee);
            }
        }

        return employees;
    }
}
