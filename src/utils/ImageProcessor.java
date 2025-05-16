/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */

public class ImageProcessor {
    
    /**
     * Converte um byteArray em uma BufferedImage
     * 
     * @param imageData A imagem como byteArray
     * @return A imagem como BufferedImage
     * @throws IOException Se a imagem não puder ser lida
     */
    public static BufferedImage byteArrayToImage(byte[] imageData) throws IOException {
        if (imageData == null || imageData.length == 0) {
            throw new IOException("Dados de imagem inválido: O array está vazio ou é nulo");
        }
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
        BufferedImage image = ImageIO.read(inputStream);
        
        if (image == null) {
            throw new IOException("Não foi possível ler os dados. O formato pode não ser suportado ou a imagem está corrompida.");
        }
        
        return image;
    }
    
    /**
     * Convere uma BufferedImage em byteArray
     * 
     * @param image A BufferedImage a converter
     * @param format O formato da imagem (e.g., "PNG", "JPEG")
     * @return A image como byteArray
     * @throws IOException Se a imagem não puder ser escrita
     */
    public static byte[] imageToByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean written = ImageIO.write(image, format, outputStream);
        
        if (!written) {
            throw new IOException("Falha ao codificar a imagem em " + format);
        }
        
        return outputStream.toByteArray();
    }

    /**
     * Processa a imagem para padronização do banco:
     * 1. Altera o tamanho para 144x144px
     * 2. Arredonda os cantos com um raio de 10px
     * 3. Adiciona uma borda de 8px com a cor #202020
     * 
     * @param imageData A imagem original como um array de bytes
     * @return A imagem processada como um array de bytes
     */
    public static byte[] processImage(byte[] imageData) throws IOException {
        // Le a imagem original
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
        
        // Passo 1
        BufferedImage resizedImage = resizeImage(originalImage, 144, 144);
        
        // Passo 2 e 3
        BufferedImage finalImage = roundCornersWithBorder(resizedImage, 10, 8, new Color(0x202020));
        
        // Converte a imagem novamente para um array de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(finalImage, "PNG", outputStream);
        return outputStream.toByteArray();
    }
    
    /**
     * Altera o tamanho da imagem para a altura e comprimento desejados
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        
        // Para melhor qualidade de renderização
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        
        return resizedImage;
    }
    
    /**
     * Arredonda os cantos e adiciona uma borda para a imagem
     * @param image Imagem original
     * @param cornerRadius Raio de arredondamento dos cantos em pixels
     * @param borderWidth Largura da borda em pixels
     * @param borderColor Cor da borda
     * @return Imagem modificada com borda arredondada
     */
    private static BufferedImage roundCornersWithBorder(BufferedImage image, int cornerRadius, int borderWidth, Color borderColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        // Cria uma imagem transparente
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        
        // Seta a qualidade da imagem
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Cria uma forma arredondada com o tamanho reduzido pela largura da borda
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                borderWidth, borderWidth, 
                width - 2 * borderWidth, height - 2 * borderWidth, 
                cornerRadius * 2, cornerRadius * 2);
        
        // Cria um retangulo redondo maior para a borda
        RoundRectangle2D borderRectangle = new RoundRectangle2D.Float(
                0, 0, width, height, 
                cornerRadius * 2 + borderWidth, cornerRadius * 2 + borderWidth);
        
        // Desenha a borda
        g2.setColor(borderColor);
        g2.fill(borderRectangle);
        
        // Cria um 'clip' para o conteúdo da imagem
        g2.setClip(roundedRectangle);
        
        // Desenha a imagem dentro do retangulo redondo criado
        g2.drawImage(image, borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth, null);
        
        g2.dispose();
        return output;
    }

    /*
     * Grava a imagem no banco de dados
     */
    public static void storeImageInDatabase(Connection connection, int imageId, byte[] processedImage) throws SQLException {
        String sql = "UPDATE spotifei.music SET music_photo = ? WHERE music_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBytes(1, processedImage);
            statement.setInt(2, imageId);
            statement.executeUpdate();
        }
    }
}