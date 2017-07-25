/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lenovo
 */
public class fileuploadBean implements Serializable 
{
    private static final long serialVersionUID = 74458L;
 
    
    private String name;
    private String description;
 
    private List<MultipartFile> files;
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

}
