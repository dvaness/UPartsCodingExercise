/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upartsapiclient;

/**
 *
 * @author Dallas
 */
public class Fitment {
    
    private String section_width;
    private String aspect_ratio;
    private String rim;
    
    public Fitment(String section_width, String aspect_ratio, String rim)
    {
        this.section_width = section_width;
        this.aspect_ratio = aspect_ratio;
        this.rim = rim;
    }
    
    public String getSectionWidth()
    {
        return new String(this.section_width);
    }
    
    public String getAspectRatio()
    {
        return new String(this.aspect_ratio);
    }
    
    public String getRim()
    {
        return new String(this.rim);
    }
    
    public String toString()
    {
        String ret = "Section Width: " + this.section_width + "\n";
        ret += "Aspect Ratio: " + this.aspect_ratio + "\n";
        ret += "Rim: " + this.rim + "\n";
        return ret;
    }
            
    
}
