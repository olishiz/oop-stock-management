/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

public class Staff{
	private String username;
	private String password;
	private String userID;
	private String profileImgSrc;
	private String pos;
	private String email;
	private String phoneNo;
	
	public Staff()
	{
		username = "";
		password = "";
		userID = "";
		profileImgSrc = "";
		pos = "";
		email = "";
		phoneNo = "";
	}
	
	public Staff(String username, String password, String userID, String profileImgSrc, String pos, String email, String phoneNo)
	{
		this.username = username;
		this.password = password;
		this.userID = userID;
		this.profileImgSrc = profileImgSrc;
		this.pos = pos;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	/**
	 * Used to copy the details of the current user to another Staff object
	 * 
	 *@param copySrc
	 * 			method receiving staff object to copy details from
	 * 
	 */
	public void copyOf(Staff copySrc) 
	{
		this.username = copySrc.username;
		this.password = copySrc.password;
		this.userID = copySrc.userID;
		this.profileImgSrc = copySrc.profileImgSrc;
		this.pos = copySrc.pos;
		this.email = copySrc.email;
		this.phoneNo = copySrc.phoneNo;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	
	public String getUserID()
	{
		return userID;
	}
	
	public void setProfileImgSrc(String profileImgSrc)
	{
		this.profileImgSrc = profileImgSrc;
	}
	
	public String getProfileImgSrc()
	{
		return profileImgSrc;
	}
	
	public void setPos(String pos)
	{
		this.pos = pos;
	}
	
	public String getPos()
	{
		return pos;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	public String getPhoneNo()
	{
		return phoneNo;
	}
}
