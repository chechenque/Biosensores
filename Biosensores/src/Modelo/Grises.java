package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * Clase que convierte una imagen a tonos de grises
 * @author Luis Angel Leyva Castillo
 */
public class Grises {
    
    /**
     * Metodo que carga una imagen en memoria
     * @param ruta La ruta de la imagen
     * @return La imagen guardada en un BufferedImage
     * @throws IOException En caso de que no encuentre la imagen
     */
    public BufferedImage leeImagen(String ruta) throws IOException{
        InputStream input = new FileInputStream(ruta);
        ImageInputStream imageInput = ImageIO.createImageInputStream(input);
        return ImageIO.read(imageInput);
    }
    

    /**
     * Metodo que guarda una imagen en un archivo png
     * @param imagen La imagen a guardar
     * @param nombre El nombre del archivo que se le dara
     * @throws IOException 
     */
    public void guardaImagen(BufferedImage imagen, String nombre) throws IOException{
        ImageIO.write(imagen,"png",new File(nombre));
    }
    
    /**
     * Metodo que aplica el metodo averaging o promedio para la escala de grises
     * Usa la formula Gray = (Red+Green+Blue)/3
     * @param ima La imagen a usar
     * @return La imagen con la escala dada
     * @throws IOException 
     */
    public BufferedImage averaging(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = (c.getRed() + c.getGreen() + c.getBlue())/3;
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
            }
        }
        return imagen;
    }
    
    /**
     * Metododo que aplica correctness para la escala de grises
     * Usa la formula Gray = (Red*0.3+Green*0.59+Blue*0.11)
     * @param ima La imagen
     * @return La imagen en la escala de grises dada
     * @throws IOException 
     */
    public BufferedImage correctness(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = (int) Math.round(c.getRed()*0.3) + (int) Math.round(c.getGreen()*0.59) + (int) Math.round(c.getBlue()*0.11);
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
            }
        }
        return imagen;
    }
    
    /**
     * Metodo que usa el metodo correctness para la escala de grises
     * usa la formula Gray = (Red*0.2126+Green*0.7152+Blue*0.0722)
     * @param ima La imagen
     * @return La imagen con la escala de grises dada
     * @throws IOException 
     */
    public BufferedImage correctness2(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = (int) Math.floor(c.getRed()*0.2126) + (int) Math.floor(c.getGreen()*0.7152) + (int) Math.floor(c.getBlue()*0.0722);
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }
    
    /**
     * Metodo que usa el meetodo desaturation para la escala de grises
     * Se usa la formula Gray =(Max(Red,Blue,Green) + Min(Red,Blue,Green))
     * @param ima La imagen
     * @return La imagen con la escala de grises dada
     * @throws IOException 
     */
    public BufferedImage desaturation(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = (Math.max(c.getRed(), Math.max(c.getBlue(),c.getGreen())) + Math.min(c.getRed(), Math.min(c.getBlue(),c.getGreen())))/2;
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }    
    
    /**
     * Metodo que usa el metodo Decomposition Max
     * Se usa la formula Gray = Max(Red,Blue,Green)
     * @param ima La imagen
     * @return La imagen con la escala de grises dada
     * @throws IOException 
     */
    public BufferedImage decompositionMax(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = Math.max(c.getRed(), Math.max(c.getBlue(),c.getGreen()));
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }   

    /**
     * Metodo que usa el metodo Decomposition Min para la escala de grises
     * La formula usada es Gray = Min(Red,Blue,Green)
     * @param ima La imagen
     * @return La imagen ya con la escala de grises dada
     * @throws IOException 
     */
    public BufferedImage decompositionMin(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = Math.min(c.getRed(), Math.min(c.getBlue(),c.getGreen()));
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }

    /**
     * Metodo que usa el metodo Single Color usando Rojo como base para la escala de grises
     * La formula usada es Gray = Red
     * @param ima La imagen
     * @return La imagen con la escala dada
     * @throws IOException 
     */
    public BufferedImage singleColorRed(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = c.getRed();
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }
    
    /**
     * Metodo que usa el metodo Single Color usando Verde como base para la escala de grises
     * La formula usada es Gray = Green
     * @param ima La imagen
     * @return La imagen con la escala dada
     * @throws IOException 
     */
    public BufferedImage singleColorGreen(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = c.getGreen();
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }
    
    /**
     * Metodo que usa el metodo Single Color usando Azul como base para la escala de grises
     * La formula usada es Gray = Blue
     * @param ima La imagen
     * @return La imagen con la escala dada
     * @throws IOException 
     */
    public BufferedImage singleColorBlue(BufferedImage ima) throws IOException{
        BufferedImage imagen = ima;
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = imagen.getRGB(x,y);
                Color c = new Color(pixel);
                int gray = c.getBlue();
                imagen.setRGB(x,y,new Color(gray,gray,gray).getRGB());
                
            }
        }
        return imagen;
    }
    
    /**
     * Metodo que copia una imagen en una nueva
     * @param img La imagen a copiar
     * @param imageType El tipo de imagen
     * @return La copia de la imagen
     */
    public static BufferedImage copia(BufferedImage img, int imageType){
        int width = img.getWidth();
        int height = img.getHeight();
        
        BufferedImage newImage = new BufferedImage(width, height, imageType);
        Graphics g = newImage.createGraphics();

        g.drawImage(img, 0, 0, null);

        g.dispose();

        return newImage;
    }
    
    /**
     * Metodo que nos da la cantidad promedio de cada Canal
     * @param img
     * @return Una cadena con las cantidades promedio
     */
    public int obtenPromedioDeGris(BufferedImage img){
        int ancho = img.getWidth();
        int alto = img.getHeight();
        long total = 0;
        
        for(int y = 0; y < alto; ++y){
            for(int x = 0; x < ancho; ++x){
                int pixel = img.getRGB(x,y);
                Color c = new Color(pixel);
                
                total += c.getRed();


            }
        }
        
        int div = alto * ancho;
        
        return (int)(total/div);
    }

    
    public String concentracion(int total){
        //0 -> 153
        //10 -> 151
        //30 -> 142
        //60 -> 128
        //90 -> 125
        //sample -> [134,131]
        String cadena = "La concentracion es posiblemente de: ";
        if(total >= 153){
            return cadena + 0;
        }else if(total < 153 && total >= 151){
            return cadena + 10;
        }else if(total < 151 && total >= 142){
            return cadena + 30;
        }else if(total < 142 && total >= 128){
            return cadena + 60;
        }else if(total < 128 && total >= 120){
            return cadena + 90;
        }else{
            return "No fue posible determinar la concentracion, posiblmente sea la muestra";
        }
    }
}