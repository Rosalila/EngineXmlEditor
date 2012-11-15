/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.mainfile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 *
 * @author oscarr
 */
public final class MainFile {
    private String mPath;
    private List<StringDeclaration> mStrings;
    private List<IntegerDeclaration> mIntegers;
    private List<HitboxDeclaration> mHitboxes;
    private List<ProjectileDeclaration> mProjectiles;
    private List<BarDeclaration> mBars;
      
    
    public MainFile(String path) {
        this(path,new ArrayList<StringDeclaration>(), new ArrayList<IntegerDeclaration>(),
                new ArrayList<HitboxDeclaration>(),new ArrayList<ProjectileDeclaration>(),new ArrayList<BarDeclaration>());
    }
    
    public MainFile(String path,List<StringDeclaration> strings, List<IntegerDeclaration> integers, 
            List<HitboxDeclaration> hitboxes,List<ProjectileDeclaration> projectiles,List<BarDeclaration> bars) {
        setPath(path);
        setStrings(strings);
        setIntegers(integers);
        setHitboxes(hitboxes);
        setProjectiles(projectiles);
        setBars(bars);       
    }
    
    public void readFromFile() throws ParsingException,IOException {
        Builder builder = new Builder();   
        Document doc = builder.build(new File(mPath));
        Element root = doc.getRootElement();
        Elements children = root.getChildElements();
        
        mStrings = new ArrayList<StringDeclaration>();
        mIntegers = new ArrayList<IntegerDeclaration>();
        mHitboxes = new ArrayList<HitboxDeclaration>();
        mProjectiles = new ArrayList<ProjectileDeclaration>();
        mBars = new ArrayList<BarDeclaration>();
        
        Element child = null;
        for (int i = 0 ; i < children.size() ; i++) {
            child = children.get(i);
            
            if ( child.getLocalName().compareTo(StringDeclaration.ELEMENT_NAME) == 0 ) {
                mStrings.add(new StringDeclaration(child));
            } else if ( child.getLocalName().compareTo(IntegerDeclaration.ELEMENT_NAME) == 0) {
                mIntegers.add(new IntegerDeclaration(child));
            } else if ( child.getLocalName().compareTo(HitboxDeclaration.ELEMENT_NAME) == 0 ) {
                mHitboxes.add(new HitboxDeclaration(child));
            }else if ( child.getLocalName().compareTo(ProjectileDeclaration.ELEMENT_NAME)==0) {
                mProjectiles.add(new ProjectileDeclaration(child));
            } else if ( child.getLocalName().compareTo(BarDeclaration.ELEMENT_NAME)==0) {
                mBars.add(new BarDeclaration(child));
            } else throw new ParsingException("Unknown element in main file");
        }
    }

    public List<StringDeclaration> getStrings() {
        return mStrings;
    }

    public void setStrings(List<StringDeclaration> strings) {
        this.mStrings = strings;
    }

    public List<IntegerDeclaration> getIntegers() {
        return mIntegers;
    }

    public void setIntegers(List<IntegerDeclaration> integers) {
        this.mIntegers = integers;
    }

    public List<HitboxDeclaration> getHitboxes() {
        return mHitboxes;
    }

    public void setHitboxes(List<HitboxDeclaration> hitboxes) {
        this.mHitboxes = hitboxes;
    }

    public List<ProjectileDeclaration> getProjectiles() {
        return mProjectiles;
    }

    public void setProjectiles(List<ProjectileDeclaration> projectiles) {
        this.mProjectiles = projectiles;
    }

    public List<BarDeclaration> getBars() {
        return mBars;
    }

    public void setBars(List<BarDeclaration> bars) {
        this.mBars = bars;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        this.mPath = path;
    }
}
