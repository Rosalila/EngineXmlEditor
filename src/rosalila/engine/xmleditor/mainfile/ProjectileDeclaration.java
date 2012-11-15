/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.mainfile;

import java.util.ArrayList;
import java.util.List;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.NoSuchAttributeException;
import nu.xom.ParsingException;

/**
 *
 * @author oscarr
 */
public final class ProjectileDeclaration {
    private String mName;
    private int mPositionX;
    private int mPositionY;
    private int mFrameNumber;
    private int mFrameDuration;
    private int mSpeedX;
    private int mSpeedY;
    private int mDamage;
    private List<ProjectileSprite> mSprites;
    
    private int mStartingFrameForHitbox;
    private List<ProjectileHitbox> mHitboxes;
    
    private static final String NAME_ATT_NAME = "name";
    private static final String POSITION_X_ATT_NAME = "position_x";
    private static final String POSITION_Y_ATT_NAME = "position_y";
    private static final String FRAME_NUMBER_ATT_NAME = "frames";
    private static final String FRAME_DURATION_ATT_NAME = "frame_duration";
    private static final String SPEED_X_ATT_NAME = "speed_x";
    private static final String SPEED_Y_ATT_NAME = "speed_y";
    private static final String DAMAGE_ATT_NAME = "damage";
    
    private static final String SPRITES_CHILD_NAME = "Sprites";
    private static final String HITBOXES_CHILD_NAME = "Hitboxes";
    private static final String STARTING_FRAME_HITBOX_ATT_NAME = "frame";
    public static final String ELEMENT_NAME = "projectile";
    
    
    public ProjectileDeclaration(String name,int positionX,int positionY,int frameNumber,int frameDuration,
            int speedX,int speedY,int damage,List<ProjectileSprite> sprites,int startingFramForHitbox,List<ProjectileHitbox> hitboxes) {
        setName(name);
        setPositionX(positionX);
        setPositionY(positionY);
        setFrameNumber(frameNumber);
        setFrameDuration(frameDuration);
        setSpeedX(speedX);
        setSpeedY(speedY);
        setDamage(damage);
        setSprites(sprites);
        setStartingFrameForHitbox(startingFramForHitbox);
        setHitboxes(hitboxes);
    }
    
    
    public ProjectileDeclaration(Element e) throws ParsingException,NoSuchAttributeException{
        if ( e.getLocalName().compareTo(ELEMENT_NAME) != 0 )throw new ParsingException("Not a valid projectile declaration element");
        
        String attribute = e.getAttributeValue(NAME_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing name attribute in projectile declaration element");
        setName(attribute);
        
        attribute = e.getAttributeValue(POSITION_X_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing position x attribute in projectile declaration element");
        setPositionX(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(POSITION_Y_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing position y attribute in projectile declaration element");
        setPositionY(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(FRAME_NUMBER_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing frame number attribute in projectile declaration element");
        setFrameNumber(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(FRAME_DURATION_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing frame duration attribute in projectile declaration element");
        setFrameDuration(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(SPEED_X_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing speed x attribute in projectile declaration element");
        setSpeedX(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(SPEED_Y_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing speed y attribute in projectile declaration element");
        setSpeedY(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(DAMAGE_ATT_NAME);
        if ( attribute == null ) throw new NoSuchAttributeException("Missing damage attribute in projectile declaration element");
        setDamage(Integer.parseInt(attribute));
        
        Elements children = e.getChildElements(SPRITES_CHILD_NAME);
        mSprites = new ArrayList<ProjectileSprite>();
        if (children.size() > 0 ){ 
            //FIXME Just take the first defined list into account 
            Elements sprites = children.get(0).getChildElements();
            for ( int i = 0 ; i < sprites.size(); i++ ) {
                mSprites.add(new ProjectileSprite(sprites.get(i)));
            }
        }
        
        children = e.getChildElements(HITBOXES_CHILD_NAME);        
        mHitboxes = new ArrayList<ProjectileHitbox>();
        if ( children.size() > 0 ) {
            Element hitboxesChild = children.get(0);
            
            if ( hitboxesChild.getAttribute(STARTING_FRAME_HITBOX_ATT_NAME) == null )
                throw new NoSuchAttributeException("Missing frame attribute in projectile hitboxes declaration");
            
            setStartingFrameForHitbox(Integer.parseInt(hitboxesChild.getAttributeValue(STARTING_FRAME_HITBOX_ATT_NAME)));
            for ( int i = 0 ; i < hitboxesChild.getChildElements().size() ; i++)
                mHitboxes.add(new ProjectileHitbox(hitboxesChild.getChildElements().get(i)));
        }

        
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getPositionX() {
        return mPositionX;
    }

    public void setPositionX(int positionX) {
        this.mPositionX = positionX;
    }

    public int getPositionY() {
        return mPositionY;
    }

    public void setPositionY(int positionY) {
        this.mPositionY = positionY;
    }

    public int getFrameNumber() {
        return mFrameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.mFrameNumber = frameNumber;
    }

    public int getFrameDuration() {
        return mFrameDuration;
    }

    public void setFrameDuration(int frameDuration) {
        this.mFrameDuration = frameDuration;
    }

    public int getSpeedX() {
        return mSpeedX;
    }

    public void setSpeedX(int speedX) {
        this.mSpeedX = speedX;
    }

    public int getSpeedY() {
        return mSpeedY;
    }

    public void setSpeedY(int speedY) {
        this.mSpeedY = speedY;
    }

    public int getDamage() {
        return mDamage;
    }

    public void setDamage(int damage) {
        this.mDamage = damage;
    }

    public void addProjectileSprite(ProjectileSprite sprite){
        mSprites.add(sprite);    
    }
    
    public void removeProjectileSprite(int index) {
        mSprites.remove(index);    
    }
    
    public ProjectileSprite getSprite(int index) {
        return mSprites.get(index);    
    }
    
    public void setSprites(List<ProjectileSprite> mSprites) {
        this.mSprites = mSprites;
    }

    public int getStartingFrameForHitbox() {
        return mStartingFrameForHitbox;
    }

    public void setStartingFrameForHitbox(int startingFrameForHitbox) {
        this.mStartingFrameForHitbox = startingFrameForHitbox;
    }

    public List<ProjectileHitbox> getHitboxes() {
        return mHitboxes;
    }

    public void setHitboxes(List<ProjectileHitbox> hitboxes) {
        this.mHitboxes = hitboxes;
    }
}
