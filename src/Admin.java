/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

public class Admin extends Staff{
	private int adminCode;
	
	public Admin()
	{
		adminCode = 0;
	}
	
	public Admin(String username, String password, String userID, String profileImgSrc, String pos, String email, String phoneNo, int adminCode)
	{
		super(username, password, userID, profileImgSrc, pos, email, phoneNo);
		this.adminCode = adminCode;
	}
	
	/**
	 * Used to copy the details of the current user to another Admin object
	 * 
	 * @param copySrc
	 * 			method receiving admin object to copy details from
	 */
	public void copyOf(Admin copySrc) 
	{
		super.setUsername(copySrc.getUsername());
		super.setPassword(copySrc.getPassword());
		super.setUserID(copySrc.getUserID());
		super.setProfileImgSrc(copySrc.getProfileImgSrc());
		super.setPos(copySrc.getPos());
		super.setEmail(copySrc.getEmail());
		super.setPhoneNo(copySrc.getPhoneNo());
		this.adminCode = copySrc.getAdminCode();
	}
	
	public void setAdminCode(int adminCode)
	{
		this.adminCode = adminCode;
	}
	
	public int getAdminCode()
	{
		return adminCode;
	}
}
