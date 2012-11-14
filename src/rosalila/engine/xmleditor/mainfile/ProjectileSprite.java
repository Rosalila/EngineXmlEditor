/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.mainfile;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.NoSuchAttributeException;
import nu.xom.ParsingException;

/**
 *
 * @author oscarr
 */
public final class ProjectileSprite {
    private String mPath;
    private float mScale;
    private boolean mToOpponent;
    private int mAlignX;
    private int mAlignY;
    private int mIgnoreRed;
    private int mIgnoreBlue;
    private int mIgnoreGreen;
    
    private static final String ELEMENT_NAME = "Sprite";
    private static final String PATH_ATT_NAME = "path";
    private static final String SCALE_ATT_NAME = "scale";
    private static final String TO_OPPONENT_ATT_NAME = "to_opponent";
    private static final String ALIGN_X_ATT_NAME = "align_x";
    private static final String ALIGN_Y_ATT_NAME = "align_y";
    private static final String IGNORE_RED_ATT_NAME = "ignore_red";
    private static final String IGNORE_BLUE_ATT_NAME = "ignore_blue";
    private static final String IGNORE_GREEN_ATT_NAME = "ignore_green";
    
    public ProjectileSprite() {
        this("",1.0f,false,0,0,0,0,0);
    }
    
    public ProjectileSprite(String path,float scale,boolean toOpponent,int alignX,
            int alignY,int ignoreRed,int ignoreBlue,int ignoreGreen) {
        setPath(path);
        setScale(scale);
        setAffectsToOpponent(toOpponent);
        setAlignX(alignX);
        setAlignY(alignY);
        setIgnoreRed(ignoreRed);
        setIgnoreBlue(ignoreBlue);
        setIgnoreGreen(ignoreGreen);
    }
    
    public ProjectileSprite(Element e) 
            throws ParsingException,NumberFormatException,NoSuchAttributeException {
        if ( e.getLocalName().compareTo(ELEMENT_NAME) != 0 )throw new ParsingException("Not a valid projectile sprite element");
        
        String attribute = e.getAttributeValue(PATH_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing path attribute in projectile sprite element");
        setPath(attribute);
        
        attribute = e.getAttributeValue(SCALE_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing scale attribute in projectile sprite element");
        setScale(Float.parseFloat(attribute));
        
        attribute = e.getAttributeValue(TO_OPPONENT_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing to opponent attribute in projectile sprite element");
        if ( attribute.compareTo("yes") !=0 && attribute.compareTo("no") !=0 )throw new ParsingException("Incorrect to opponent value in sprite element");
        setAffectsToOpponent(attribute.compareTo("yes")==0? true:false);
        
        attribute = e.getAttributeValue(ALIGN_X_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing align x attribute in projectile sprite element");
        setAlignX(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(ALIGN_Y_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing align y attribute in projectile sprite element");
        setAlignY(Integer.parseInt(attribute));
        
        //TODO check if ignore fields are mandatory
        attribute = e.getAttributeValue(IGNORE_BLUE_ATT_NAME);
        if ( attribute == null )
            setIgnoreBlue(0);
        else
            setIgnoreBlue(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(IGNORE_GREEN_ATT_NAME);
        if ( attribute == null )
            setIgnoreGreen(0);
        else
            setIgnoreGreen(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(IGNORE_RED_ATT_NAME);
        if ( attribute == null )
            setIgnoreRed(0);
        else
            setIgnoreRed(Integer.parseInt(attribute));        
        
    }
    
    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        e.addAttribute(new Attribute(PATH_ATT_NAME,mPath));
        e.addAttribute(new Attribute(SCALE_ATT_NAME,String.valueOf(mScale)) );
        e.addAttribute(new Attribute(ALIGN_X_ATT_NAME,String.valueOf(mAlignX)));
        e.addAttribute(new Attribute(ALIGN_Y_ATT_NAME,String.valueOf(mAlignY) ));
        e.addAttribute(new Attribute(TO_OPPONENT_ATT_NAME, mToOpponent?"yes":"no" ));
        e.addAttribute(new Attribute(IGNORE_BLUE_ATT_NAME,String.valueOf(mIgnoreBlue)));
        e.addAttribute(new Attribute(IGNORE_GREEN_ATT_NAME,String.valueOf(mIgnoreGreen)));
        e.addAttribute(new Attribute(IGNORE_RED_ATT_NAME,String.valueOf(mIgnoreRed)));
        
        return e;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return mPath;
    }

    /**
     * @param path the mPath to set
     */
    public void setPath(String path) {
        this.mPath = path;
    }

    /**
     * @return the scale
     */
    public float getScale() {
        return mScale;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(float scale) {
        this.mScale = scale;
    }

    /**
     * @return does this sprite affects the opponent
     */
    public boolean affectsOpponent() {
        return mToOpponent;
    }

    /**
     * @param toOpponent set if this sprite affects the opponent
     */
    public void setAffectsToOpponent(boolean toOpponent) {
        this.mToOpponent = toOpponent;
    }

    /**
     * @return the sprite align on the x axis
     */
    public int getAlignX() {
        return mAlignX;
    }

    /**
     * @param alignX the align on the x axis to set
     */
    public void setAlignX(int alignX) {
        this.mAlignX = alignX;
    }

    /**
     * @return the sprite align on the y axis
     */
    public int getAlignY() {
        return mAlignY;
    }

    /**
     * @param alignY the align on the y axis to set
     */
    public void setAlignY(int alignY) {
        this.mAlignY = alignY;
    }

    public int getIgnoreRed() {
        return mIgnoreRed;
    }


    public void setIgnoreRed(int ignoreRed) {
        this.mIgnoreRed = ignoreRed;
    }

    public int getIgnoreBlue() {
        return mIgnoreBlue;
    }

    public void setIgnoreBlue(int ignoreBlue) {
        this.mIgnoreBlue = ignoreBlue;
    }

    
    public int getIgnoreGreen() {
        return mIgnoreGreen;
    }

    public void setIgnoreGreen(int ignoreGreen) {
        this.mIgnoreGreen = ignoreGreen;
    }
}
