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
public final class IntegerDeclaration {

    private String mVariable;
    private int mValue;
    private static final String VARIABLE_ATT_NAME = "variable";
    private static final String VALUE_ATT_NAME = "value";
    private static final String ELEMENT_NAME = "integer";

    public IntegerDeclaration() {
        this("", 0);
    }

    public IntegerDeclaration(String variable, int value) {
        setValue(value);
        setVariable(variable);
    }

    public IntegerDeclaration(Element e)
            throws ParsingException, NumberFormatException, NoSuchAttributeException {

        if (e.getLocalName().compareTo(ELEMENT_NAME) != 0) {
            throw new ParsingException("Not a valid integer declaration element");
        }

        String attribute = e.getAttributeValue(VARIABLE_ATT_NAME);
        if (attribute == null) {
            throw new NoSuchAttributeException("Missing variable attribute in integer declaration element.");
        }
        setVariable(attribute);

        attribute = e.getAttributeValue(VALUE_ATT_NAME);
        if (attribute == null) {
            throw new NoSuchAttributeException("Missing value attribute in integer declaration element.");
        }
        setValue(Integer.parseInt(attribute));
    }

    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        e.addAttribute(new Attribute(VARIABLE_ATT_NAME, mVariable));
        e.addAttribute(new Attribute(VALUE_ATT_NAME, String.valueOf(mValue)));
        return e;
    }

    public void setVariable(String variable) {
        mVariable = variable;
    }

    public String getVariable() {
        return mVariable;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
