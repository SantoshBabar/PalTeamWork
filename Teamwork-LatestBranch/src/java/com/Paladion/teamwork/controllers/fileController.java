/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.services.AdminService;

import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.services.UserService;
import com.Paladion.teamwork.utils.CommonUtil;
import com.Paladion.teamwork.utils.SystemInfo;
import com.Paladion.teamwork.utils.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
/**
 *
 * @author Administrator
 */
@Controller
public class fileController {
    
 public fileController() 
 {   }
 
@Autowired
@Qualifier(value="Validator")
Validator SV;
   


@InitBinder
protected void initBinder(WebDataBinder binder) {
      binder.addValidators(SV);
}
 
 @Autowired
 @Qualifier(value="LoginService")
 LoginService LS;
 
 @Autowired
@Qualifier(value="CommonUtil")
 CommonUtil CU;
 
 @Autowired
@Qualifier(value="UserService")
 UserService userService;
 
 @Autowired
@Qualifier(value="ProjectService")
 ProjectService PS;
 
 UserDataBean lb=null;
 
@Autowired()
@Qualifier(value = "AdminService")
AdminService Aservice;
 
@ModelAttribute("LoginM")
 public UserDataBean PopulateLoginBean() 
{
   return new UserDataBean(); // populates form for the first time if its null
}


 
@RequestMapping(value="/fileDownload",method=RequestMethod.GET)
    public void fileDownload(@RequestParam String id,HttpServletRequest req,HttpServletResponse res) throws ParseException, IOException, FileNotFoundException, Exception
    {
System.out.println("fileDownload:");
  
HttpSession sess=req.getSession();    
    String PID=(String) sess.getAttribute("DownloadPID");    
    System.out.println("projectid"+id);    
    String filepath=Aservice.getSystemSettings().getUploadpath();
    
    File downloadfile = new File(filepath+File.separator+"files"+File.separator+id);
    System.out.println("folder " + downloadfile.toString());
   String sourceFolderName =  downloadfile.toString();
        String outputFileName = downloadfile.toString()+".zip";
 
        FileOutputStream fos = new FileOutputStream(outputFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);
        //level - the compression level (0-9)
        zos.setLevel(9);
 
        System.out.println("Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
        addFolder(zos, sourceFolderName, sourceFolderName);
 
        zos.close();
        System.out.println("Program ended successfully!");
        //file download
        
        
String downloadFolder = downloadfile.toString();
String filename1=downloadfile.toString()+".zip";
Path file = Paths.get(filename1);
  if (Files.exists(file)) {
   res.setContentType("application/zip");
   res.addHeader("Content-Disposition", "attachment; filename=" + filename1);
   try {
    Files.copy(file, res.getOutputStream());
    res.getOutputStream().flush();
   } catch (IOException e) {
    System.out.println("Error :- " + e.getMessage());
   }
  } else {
   System.out.println("Sorry File not found!!!!");
  }
        
        
        //file download
        
        
        
        
    }
 
    private static void addFolder(ZipOutputStream zos,String folderName,String baseFolderName)throws Exception{
        File f = new File(folderName);
        if(f.exists()){
 
            if(f.isDirectory()){
                //Thank to peter 
                //For pointing out missing entry for empty folder
                if(!folderName.equalsIgnoreCase(baseFolderName)){
                    String entryName = folderName.substring(baseFolderName.length()+1,folderName.length()) + File.separatorChar;
                    System.out.println("Adding folder entry " + entryName);
                    ZipEntry ze= new ZipEntry(entryName);
                    zos.putNextEntry(ze);    
                }
                File f2[] = f.listFiles();
                for(int i=0;i<f2.length;i++){
                    addFolder(zos,f2[i].getAbsolutePath(),baseFolderName);    
                }
            }else{
                //add file
                //extract the relative name for entry purpose
                String entryName = folderName.substring(baseFolderName.length()+1,folderName.length());
                System.out.print("Adding file entry " + entryName + "...");
                ZipEntry ze= new ZipEntry(entryName);
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(folderName);
                int len;
                byte buffer[] = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.closeEntry();
                System.out.println("OK!");
 
            }
        }else{
            System.out.println("File or directory not found " + folderName);
        }
}  
    }


