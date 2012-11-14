/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalilaenginexmleditor;

import java.awt.Image;
import java.text.ParseException;
import javax.imageio.ImageIO;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author oscarr
 */
public final class SpriteElement {
    private static String ELEMENT_NAME = "Sprite";
    
    private String mVariableName;
    private boolean mToOponent;
    private float mScale;
    private String mImagePath;
    private float mAlignX;
    private float mAlignY;
    private int mIndex;
    
    private static String INDEX_ATT_NAME = "frame_number";
    private static String VARIABLE_ATT_NAME = "variable";
    private static String PATH_ATT_NAME = "path";
    private static String TO_OPPONENT_ATT_NAME = "to_opponent";
    private static String SCALE_ATT_NAME = "scale";
    private static String ALIGN_X_ATT_NAME = "align_x";
    private static String ALIGN_Y_ATT_NAME = "align_y";

    public SpriteElement(String image, float alignX, float alignY, int index,
            String variableName, float scale, boolean toOpponent) {
        setImagePath(image);
        setAlignX(alignX);
        setAlignY(alignY);
        setIndex(index);
        setScale(scale);
        setToOponent(toOpponent);
        setVariableName(variableName);
    }
    
    public SpriteElement(Element e) throws ParsingException {
        
        //TODO validate element is invalid or malformed
        String attribute = e.getAttributeValue(PATH_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing image path attribute in sprite element");
        setImagePath(attribute);
        
        attribute = e.getAttributeValue(ALIGN_X_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing align x attribute in sprite element");
        setAlignX(Float.parseFloat(attribute));
        
        attribute = e.getAttributeValue(ALIGN_Y_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing align y attribute in sprite element");
        setAlignY(Float.parseFloat(attribute));
        
        attribute = e.getAttributeValue(INDEX_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing frame number attribute in sprite element");
        setIndex(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(SCALE_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing scale attribute in sprite element");
        setScale(Float.parseFloat(attribute));
        
        attribute = e.getAttributeValue(TO_OPPONENT_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing to opponent attribute in sprite element");
        setToOponent(Boolean.parseBoolean(attribute));
        
        attribute = e.getAttributeValue(VARIABLE_ATT_NAME);
        if ( attribute == null ) throw new ParsingException("Missing variable name attribute in sprite element");
        setVariableName(e.getAttributeValue(VARIABLE_ATT_NAME));
    }

    /**
     * @return the Image
     */
    public String getImagePath() {
        return mImagePath;
    }

    /**
     * @param mImage the mImage to set
     */
    public void setImagePath(String image) {
        this.mImagePath = image;
    }

    /**
     * @return the mAlignX
     */
    public float getAlignX() {
        return mAlignX;
    }

    /**
     * @param mAlignX the mAlignX to set
     */
    public void setAlignX(float alignX) {
        this.mAlignX = alignX;
    }

    /**
     * @return the mAlignY
     */
    public float getAlignY() {
        return mAlignY;
    }

    /**
     * @param mAlignY the mAlignY to set
     */
    public void setAlignY(float alignY) {
        this.mAlignY = alignY;
    }

    /**
     * @return the mIndex
     */
    public int getIndex() {
        return mIndex;
    }

    /**
     * @param mIndex the mIndex to set
     */
    public void setIndex(int index) {
        this.mIndex = index;
    }

    /**
     * @return the variable name
     */
    public String getVariableName() {
        return mVariableName;
    }

    /**
     * @param variableName the variable name to set
     */
    public void setVariableName(String variableName) {
        this.mVariableName = variableName;
    }

    /**
     * @return the mToOponent
     */
    public boolean affectsOponent() {
        return mToOponent;
    }

    /**
     * @param mToOponent the mToOponent to set
     */
    public void setToOponent(boolean toOponent) {
        this.mToOponent = toOponent;
    }

    /**
     * @return the scale
     */
    public float getScale() {
        return mScale;
    }

    /**
     * @param mScale the mScale to set
     */
    public void setScale(float scale) {
        this.mScale = scale;
    }

    @Override
    public String toString() {
        return String.valueOf(mIndex);
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that.getClass() != this.getClass()) {
            return false;
        }
        SpriteElement thatSprite = (SpriteElement) that;

        return this.mAlignX == thatSprite.mAlignX && this.mAlignY == thatSprite.mAlignY
                && this.mToOponent == thatSprite.mToOponent && this.mImagePath.equals(thatSprite.mImagePath)
                && this.mIndex == thatSprite.mIndex && this.mScale == thatSprite.mScale && this.mVariableName.equals(thatSprite.mVariableName);
    }

    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        Attribute variableAtt = new Attribute(VARIABLE_ATT_NAME, mVariableName);
        Attribute indexAtt = new Attribute(INDEX_ATT_NAME,String.valueOf(mIndex));
        Attribute imagePathAtt = new Attribute(PATH_ATT_NAME,mImagePath);
        Attribute scaleAtt = new Attribute(SCALE_ATT_NAME,String.valueOf(mScale));
        Attribute alignXAtt = new Attribute(ALIGN_X_ATT_NAME,String.valueOf(mAlignX));
        Attribute alignYAtt = new Attribute(ALIGN_Y_ATT_NAME,String.valueOf(mAlignY));
        Attribute toOpponentAtt = new Attribute(TO_OPPONENT_ATT_NAME, String.valueOf(mToOponent));
        e.addAttribute(variableAtt);
        e.addAttribute(indexAtt);
        e.addAttribute(imagePathAtt);
        e.addAttribute(scaleAtt);
        e.addAttribute(alignXAtt);
        e.addAttribute(alignYAtt);
        e.addAttribute(toOpponentAtt);
        return e;
    }
    
}
