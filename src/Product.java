/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import java.util.*; 


public class Product 
{
   private String prodCode;
   private String prodName;
   private String prodDesc;
   private String prodImgSrc;
   private String manufacture;
   private double price;
   private double weight;
   private int qty;
   private String manuDate;
   private String expDate;
   
   public Product()
   {
	  prodCode = "";
	  prodName = "";
	  prodDesc = "";
	  prodImgSrc = "";
	  manufacture = "";
	  price = 0;
	  weight = 0;
	  qty = 0;
	  manuDate = "";
	  expDate = "";
   }
	
   public Product(String prodCode, String prodName, String prodDesc, String prodImgSrc, String manufacture, double price, double weight, int qty, String manuDate, String expDate)
   {
	   this.prodCode = prodCode;
	   this.prodName = prodName;
	   this.prodDesc = prodDesc;
	   this.prodImgSrc = prodImgSrc;
	   this.manufacture = manufacture;
	   this.price = price;
	   this.weight = weight;
	   this.qty = qty;
	   this.manuDate = manuDate;
	   this.expDate = expDate;
   }

  public String getProdCode() 
  {
	return prodCode;
  }

  public void setProdCode(String prodCode) 
  {
	this.prodCode = prodCode;
  }

  public String getProdName() 
  {
	return prodName;
  }

  public void setProdName(String prodName)
  {
	this.prodName = prodName;  
  }

  public String getProdDesc() 
  {
	return prodDesc;
  }

  public void setProdDesc(String prodDesc)
  {
	this.prodDesc = prodDesc;
  }
  
  public String getProdImgSrc()
  {
	  return prodImgSrc;
  }
  
  public void setProdImgSrc(String prodImgSrc)
  {
	  this.prodImgSrc = prodImgSrc;
  }

  public String getManufacture()
  {
	return manufacture;
  }

  public void setManufacture(String manufacture)
  {
	this.manufacture = manufacture;
  }

  public double getPrice() 
  {
	return price;
  }

  public void setPrice(double price)
  {
	this.price = price;
  }

  public double getWeight() 
  {
	return weight;
  }

  public void setWeight(double weight) 
  {
	this.weight = weight;
  }

  public int getQty() 
  {
	return qty;
  }

  public void setQty(int qty) 
  {
	this.qty = qty;
  }

  public String getManuDate() 
  {
	return manuDate;
  }

  public void setManuDate(String manuDate)
  {
	this.manuDate = manuDate;
  }

  public String getExpDate()
  {
	return expDate;
  }

  public void setExpDate(String expDate)
  {
	this.expDate = expDate;
  }
		
}
