package de.onetwotree.margaux.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by SebUndefined on 20/11/17.
 */
public class CountryDTO {
    private String name;
    private MultipartFile flag;

    public CountryDTO(String name, MultipartFile flag) {
        this.name = name;
        this.flag = flag;
    }

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFlag() {
        return flag;
    }

    public void setFlag(MultipartFile flag) {
        this.flag = flag;
    }
}
