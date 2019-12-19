package com.inbravo.ds.file;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * amit.dixit
 */
import javax.imageio.ImageIO;

public class ImageUtils {

  private static final String mainLocation = "D:\\personal\\mygithub\\travel\\june-2019\\images";

  public static void main(final String[] args) throws Exception {

    final File folder = new File(mainLocation);

    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {

      /* If a file */
      if (listOfFiles[i].isFile()) {

        applySignature(new File(mainLocation + "\\" + listOfFiles[i].getName()));
      }
    }
  }

  private final static void applySignature(final File imageFile) throws Exception {

    final BufferedImage image = ImageIO.read(imageFile);

    Graphics g = image.getGraphics();

    /* Font attribute */
    Map<TextAttribute, Object> attributes = new HashMap<>();

    final Font currentFont = g.getFont();

    attributes.put(TextAttribute.FONT, new Font("Calibri", Font.PLAIN, (int) (currentFont.getSize() * 5)));
    attributes.put(TextAttribute.FAMILY, currentFont.getFamily());
    attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD);

    final Font myFont = Font.getFont(attributes);

    g.setFont(myFont);
    g.setColor(Color.DARK_GRAY);
    g.drawString("InBravo", 50, 50);
    g.dispose();

    ImageIO.write(image, "jpg", imageFile);
  }
}
