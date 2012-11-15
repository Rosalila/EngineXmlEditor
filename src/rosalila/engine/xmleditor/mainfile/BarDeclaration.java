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
public final class BarDeclaration {

    private String mVariable;
    private int mMaxValue;
    private int mCurrentValue;
    private int mPeriodicModifier;
    //If there is a periodic modifier this field sets the period to apply it
    private int mPeriod;
    private int mAlphaChannel;
    private int mRed;
    private int mGreen;
    private int mBlue;
    private int mX1;
    private int mX2;
    private int mY1;
    private int mY2;
    private String mImagePath;
    
    public static final String ELEMENT_NAME = "bar";
    private static final String VARIABLE_ATT_NAME = "variable";
    private static final String MAX_VALUE_ATT_NAME = "max_value";
    private static final String CURRENT_VALUE_ATT_NAME = "current_value";
    private static final String PERIODIC_MOD_ATT_NAME = "periodic_modifier";
    private static final String PERIOD_ATT_NAME = "period";
    private static final String ALPHA_ATT_NAME = "alpha";
    private static final String RED_ATT_NAME = "r";
    private static final String BLUE_ATT_NAME = "b";
    private static final String GREEN_ATT_NAME = "g";
    private static final String X1_ATT_NAME = "x1";
    private static final String X2_ATT_NAME = "x2";
    private static final String Y1_ATT_NAME = "y1";
    private static final String Y2_ATT_NAME = "y2";
    private static final String IMAGE_PATH_ATT_NAME = "image";
    

    public BarDeclaration(String variable, int maxValue, int currentValue,
            int periodicModifier, int period, int alpha, int red, int green, int blue, int x1, int x2, int y1, int y2, String imagePath) {
        setVariable(variable);
        setMaxValue(maxValue);
        setCurrentValue(currentValue);
        setPeriodicModifier(periodicModifier);
        setPeriod(period);
        setColor(red,green,blue,alpha);
        setX1(x1);
        setX2(x2);
        setY1(y1);
        setY2(y2);
        setImagePath(imagePath);
    }
    
    public BarDeclaration(Element e) throws ParsingException,NumberFormatException,NoSuchAttributeException {
        if ( e.getLocalName().compareTo(ELEMENT_NAME) != 0) throw new ParsingException("Not a valid bar declaration element");
        
        String attribute = e.getAttributeValue(VARIABLE_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing variable attribute in bar declaration element");
        setVariable(attribute);
        
        attribute = e.getAttributeValue(MAX_VALUE_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing max value attribute in bar declaration element");
        setMaxValue(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(CURRENT_VALUE_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing current value attribute in bar declaration element");
        setCurrentValue(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(PERIODIC_MOD_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing periodic modifier attribute in bar declaration element");
        setPeriodicModifier(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(PERIOD_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing period attribute in bar declaration element");
        setPeriod(Integer.parseInt(attribute));
        
        int red,blue,green,alpha;
        attribute = e.getAttributeValue(RED_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing red color attribute in bar declaration element");
        red = Integer.parseInt(attribute);
        
        attribute = e.getAttributeValue(GREEN_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing green color attribute in bar declaration element");
        green = Integer.parseInt(attribute);
        
        attribute = e.getAttributeValue(BLUE_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing blue color attribute in bar declaration element");
        blue = Integer.parseInt(attribute);
        
        attribute = e.getAttributeValue(ALPHA_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing alpha channel attribute in bar declaration element");
        alpha = Integer.parseInt(attribute);
        
        setColor(red, green, blue, alpha);
        
        attribute = e.getAttributeValue(X1_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing origin x attribute in bar declaration element");
        setX1(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(X2_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing final x attribute in bar declaration element");
        setX2(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(Y1_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing origin y attribute in bar declaration element");
        setY1(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(Y2_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing final y attribute in bar declaration element");
        setY2(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(IMAGE_PATH_ATT_NAME);
        if ( attribute == null )throw new NoSuchAttributeException("Missing image path attribute in bar declaration element");
        setImagePath(attribute);       
    }
    
    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        e.addAttribute(new Attribute(VARIABLE_ATT_NAME,mVariable));
        e.addAttribute(new Attribute(MAX_VALUE_ATT_NAME,String.valueOf(mMaxValue) ));
        e.addAttribute(new Attribute(CURRENT_VALUE_ATT_NAME,String.valueOf(mCurrentValue)));
        e.addAttribute(new Attribute(PERIODIC_MOD_ATT_NAME,String.valueOf(mPeriodicModifier)));
        e.addAttribute(new Attribute(PERIOD_ATT_NAME,String.valueOf(mPeriod)));
        e.addAttribute(new Attribute(RED_ATT_NAME,String.valueOf(mRed)));
        e.addAttribute(new Attribute(BLUE_ATT_NAME,String.valueOf(mBlue)));
        e.addAttribute(new Attribute(GREEN_ATT_NAME,String.valueOf(mGreen)));
        e.addAttribute(new Attribute(ALPHA_ATT_NAME,String.valueOf(mAlphaChannel)));
        e.addAttribute(new Attribute(X1_ATT_NAME,String.valueOf(mX1)));
        e.addAttribute(new Attribute(X2_ATT_NAME,String.valueOf(mX2)));
        e.addAttribute(new Attribute(Y1_ATT_NAME,String.valueOf(mY1)));
        e.addAttribute(new Attribute(Y2_ATT_NAME,String.valueOf(mY2)));
        e.addAttribute(new Attribute(IMAGE_PATH_ATT_NAME,mImagePath));
        return e;
    }

    public String getVariable() {
        return mVariable;
    }

    public void setVariable(String variable) {
        this.mVariable = variable;
    }

    public int getMaxValue() {
        return mMaxValue;
    }

    public void setMaxValue(int maxValue) {
        this.mMaxValue = maxValue;
    }

    public int getCurrentValue() {
        return mCurrentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.mCurrentValue = currentValue;
    }

    public int getPeriodicModifier() {
        return mPeriodicModifier;
    }

    public void setPeriodicModifier(int periodicModifier) {
        this.mPeriodicModifier = periodicModifier;
    }

    public int getPeriod() {
        return mPeriod;
    }

    public void setPeriod(int period) {
        this.mPeriod = period;
    }

    public void setColor(int red, int green, int blue, int alpha) {
        mRed = red;
        mGreen = green;
        mBlue = blue;
        mAlphaChannel = alpha;

    }

    public int getAlphaChannel() {
        return mAlphaChannel;
    }

    public int getRed() {
        return mRed;
    }

    public int getGreen() {
        return mGreen;
    }

    public int getBlue() {
        return mBlue;
    }

    public int getX1() {
        return mX1;
    }

    public void setX1(int x1) {
        this.mX1 = x1;
    }

    public int getX2() {
        return mX2;
    }

    public void setX2(int x2) {
        this.mX2 = x2;
    }

    public int getY1() {
        return mY1;
    }

    public void setY1(int y1) {
        this.mY1 = y1;
    }

    public int getY2() {
        return mY2;
    }

    public void setY2(int y2) {
        this.mY2 = y2;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        this.mImagePath = imagePath;
    }
}
