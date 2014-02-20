/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week4;

/**
 * @author Nathan Shih
 */
public interface Contact {
	
	public int getLength();
	
	public void setLength(int length) throws Exception;
	
	public int getSpeed();
	
	public void setSpeed(int speed) throws Exception;
	
	public void setSpeed(String speed) throws Exception;
	
	public String getName();
	
	public void setName(String name);
	
	public String getType();
	
	public void setType(String type);
}
