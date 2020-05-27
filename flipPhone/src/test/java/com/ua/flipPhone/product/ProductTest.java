
package com.ua.flipPhone.product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductTest {
    
    private Product product;
    
    public ProductTest(){
        product = new Product(
                "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
                "4 GB", 
                "300DPI", 
                "6,5 \"", 
                "sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch", 
                "Li-Ion 4000 mAh", 
                "Android 10.0", 
                "32.0 MP\n f/2.2",
                "f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",
                "Smartphone Samsung Galaxy A51 - A515F",
                "url/image");
    }
    
   /* @Test
    public void testGetProduct_id(){
        assertEquals(2,product.getProduct_id());
    }*/
    
    @Test
    public void testGetCpu_gpu(){
        assertEquals("Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz",product.getCpu_gpu());
    }
    
    @Test
    public void testGetRam_rom(){
        assertEquals("4 GB",product.getRam_rom());
    }
    
    @Test
    public void testGetImage(){
        assertEquals("300DPI",product.getImage());
    }
    
    @Test
    public void testGetScreen_size(){
        assertEquals("6,5 \"",product.getScreen_size());
    }
    
    @Test
    public void testGetScreen_type(){
        assertEquals("sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch",product.getScreen_type());
    }
    
    @Test
    public void testGetBattery(){
        assertEquals("Li-Ion 4000 mAh",product.getBattery());
    }
    
    @Test
    public void testGetOs(){
        assertEquals("Android 10.0",product.getOs());
    }
    
    @Test
    public void testSelfie_cam(){
        assertEquals("32.0 MP\n f/2.2",product.getSelfie_cam());
    }
    
    @Test
    public void testGetCamera(){
        assertEquals("f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",product.getCamera());
    }
    
    @Test
    public void testGetProduct_name(){
        assertEquals("Smartphone Samsung Galaxy A51 - A515F",product.getProduct_name());
    }
    
    @Test
    public void testGetPhotoUrl(){
        assertEquals("url/image",product.getPhotoUrl());
    }
    
    @Test
    public void testSetProduct_id(){
        product.setProduct_id(2);
        assertEquals(2,product.getProduct_id());
    }
    
    @Test
    public void testSetCpu_gpu(){
        product.setCpu_gpu("Qualcomm Snapdragon 865");
        assertEquals("Qualcomm Snapdragon 865",product.getCpu_gpu());
    }
    
    @Test
    public void testSetRam_rom(){
        product.setRam_rom("8 GB");
        assertEquals("8 GB",product.getRam_rom());
    }
    
    @Test
    public void testSetImage(){
        product.setImage("125DPI");
        assertEquals("125DPI",product.getImage());
    }
    
    @Test
    public void testSetScreen_size(){
        product.setScreen_size("6,55 \"");
        assertEquals("6,55 \"",product.getScreen_size());
    }
    
    @Test
    public void testSetScreen_type(){
        product.setScreen_type("Fluid AMOLED tátil capacitivo com multi-touch\n Vidro 3D Corning Gorilla Glass");
        assertEquals("Fluid AMOLED tátil capacitivo com multi-touch\n Vidro 3D Corning Gorilla Glass",product.getScreen_type());
    }
    
    @Test
    public void testSetBattery(){
        product.setBattery("Li-Po 4300 mAh\n Fast battery charging 5V/6A");
        assertEquals("Li-Po 4300 mAh\n Fast battery charging 5V/6A",product.getBattery());
    }
    
    @Test
    public void testSetOs(){
        product.setOs("Android 10.0\nOxygenOS");
        assertEquals("Android 10.0\nOxygenOS",product.getOs());
    }
    
    @Test
    public void testSetSelfie_cam(){
        product.setSelfie_cam("16.0 MP\n Sensor: Sony MX471, 1.0um, EIS, f/2.0");
        assertEquals("16.0 MP\n Sensor: Sony MX471, 1.0um, EIS, f/2.0",product.getSelfie_cam());
    }
    
    @Test
    public void testSetCamera(){
        product.setCamera("48.0 MP + 16.0 MP + 2.0 MP");
        assertEquals("48.0 MP + 16.0 MP + 2.0 MP",product.getCamera());
    }
    
    @Test
    public void testSetProduct_name(){
        product.setProduct_name("Smartphone OnePlus 8");
        assertEquals("Smartphone OnePlus 8",product.getProduct_name());
    }
    
    @Test
    public void testSetPhotoUrl(){
        product.setPhotoUrl("url/new_Image");
        assertEquals("url/new_Image",product.getPhotoUrl());
    }
}
