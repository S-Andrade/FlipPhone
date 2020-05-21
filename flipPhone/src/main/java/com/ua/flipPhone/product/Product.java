package com.ua.flipPhone.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="product_id")
    private Integer product_id;

    @Column(name="cpu_gpu")
    private String cpu_gpu;

    @Column(name="ram_rom")
    private String ram_rom;

    @Column(name="image")
    private String image;

    @Column(name="screen_size")
    private String screen_size;

    @Column(name="screen_type")
    private String screen_type;

    @Column(name="battery")
    private String battery;

    @Column(name="os")
    private String os;
 
    @Column(name="selfie_cam")
    private String selfie_cam;

    @Column(name="camera")
    private String camera;

    @Column(name="product_name")
    private String product_name;

    public Product(){}
    
    public Product(String cpu_gpu, String ram_rom, String image, String screen_size, String screen_type, String battery, String os, String selfie_cam, String camera, String product_name) {
        this.cpu_gpu = cpu_gpu;
        this.ram_rom = ram_rom;
        this.image = image;
        this.screen_size = screen_size;
        this.screen_type = screen_type;
        this.battery = battery;
        this.os = os;
        this.selfie_cam = selfie_cam;
        this.camera = camera;
        this.product_name = product_name;
    }



    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer id) {
        this.product_id = id;
    }

    public String getCpu_gpu() {
        return cpu_gpu;
    }

    public void setCpu_gpu(String cpu_gpu) {
        this.cpu_gpu = cpu_gpu;
    }

    public String getRam_rom() {
        return ram_rom;
    }

    public void setRam_rom(String ram_rom) {
        this.ram_rom = ram_rom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(String screen_size) {
        this.screen_size = screen_size;
    }

    public String getScreen_type() {
        return screen_type;
    }

    public void setScreen_type(String screen_type) {
        this.screen_type = screen_type;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getSelfie_cam() {
        return selfie_cam;
    }

    public void setSelfie_cam(String selfie_cam) {
        this.selfie_cam = selfie_cam;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    
    

}
