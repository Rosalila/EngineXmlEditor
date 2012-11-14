/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.mainfile;

import java.util.List;

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
    
    public ProjectileDeclaration() {
        
    } 
    
    public ProjectileDeclaration(String name,int positionX,int positionY,int frameNumber,int frameDuration,
            int speedX,int speedY,int damage,List<ProjectileSprite> sprites) {
        setName(name);
        setPositionX(positionX);
        setPositionY(positionY);
        setFrameNumber(frameNumber);
        setFrameDuration(frameDuration);
        setSpeedX(speedX);
        setSpeedY(speedY);
        setDamage(damage);
        setSprites(sprites);
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
}
