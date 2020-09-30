package com.springboot.bootdemo.xgame.common;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Enumeration;

public class LicenseUtil {
	private static String Algorithm = "DESede";//加密算法的名称
    private static Cipher c;//密码器
    private static byte[] cipherByte;
    private static SecretKey deskey;//密钥
    private static String keyString = "A3F2569DESJEIWBCJOTY45DYQWF68H1Y";//获得密钥的参数

	
	 //对base64编码的string解码成byte数组
    public static byte[] deBase64(String parm) throws IOException {
       BASE64Decoder dec=new BASE64Decoder();
       byte[] dnParm = dec.decodeBuffer(parm);
       return dnParm;
    }
   //把密钥参数转为byte数组
    public static byte[] dBase64(String parm) throws IOException {
       BASE64Decoder dec=new BASE64Decoder();
       byte[] dnParm = dec.decodeBuffer(parm);
       return dnParm;
    }

	
	public static void getKey() throws IOException, InvalidKeyException,
    InvalidKeySpecException {
    byte[] dKey = dBase64(keyString);
      try {
        deskey=new javax.crypto.spec.SecretKeySpec(dKey,Algorithm);
        c = Cipher.getInstance(Algorithm);
      }
      catch (NoSuchPaddingException ex) {
      }
      catch (NoSuchAlgorithmException ex) {
      }
   }
	
	
	/**
     * 对 Byte 数组进行解密
     * @param buff 要解密的数据
     * @return 返回加密后的 String
	 * @throws IOException 
	 * @throws InvalidKeySpecException 
     */
     public static String createDecryptor(byte[] buff) throws
      NoSuchPaddingException, NoSuchAlgorithmException,
      InvalidKeySpecException, IOException {
        try {
           getKey();
           c.init(Cipher.DECRYPT_MODE, deskey);//初始化密码器，用密钥deskey,进入解密模式
           cipherByte = c.doFinal(buff);
        }
        catch(InvalidKeyException ex){
            ex.printStackTrace();
        }
        catch(javax.crypto.BadPaddingException ex){
            ex.printStackTrace();
        }
        catch(javax.crypto.IllegalBlockSizeException ex){
            ex.printStackTrace();
        }
        return (new String(cipherByte,"UTF-8"));
     }

	

	
	//解密授权文件
	private static String DeLiencecode(String data) throws Exception {
		  
		  byte[] dBy = deBase64(data);
	      return createDecryptor(dBy);

	}
	
	public static boolean isRishtLicense(ServletContext servletContext) throws Exception
	{
		String contend="";
		contend=FileUtil.ReadFileContent(servletContext.getRealPath("/")+"bzw.license");
		contend=DeLiencecode(contend);
		
		return AnalysisLicense(contend);
	}
	
	private static boolean AnalysisLicense(String contend) throws Exception
	{
		String sed=contend.substring(contend.lastIndexOf('|')+1,contend.length()).trim();
		
        String sed1 = sed.substring(sed.length() - 2, sed.length());
        String sed2 = sed.substring(sed.length() - 4 , sed.length()-2);
        String sed3 = sed.substring(sed.length() - 6, sed.length()-4);
        String sed4 = sed.substring(sed.length() - 8, sed.length()-6);
        
        //取ed后倒数12位
        int ed1 = getPNum(sed1);
        //取ed的倒数34位
        int ed2 = getPNum(sed2);
        //取ed的前56位
        int ed3 = getPNum(sed3);
        //取ed的78位
        int ed4 = getPNum(sed4);
        
        String tem = "";
        
        String mac =  "";
        int index = ed1;
        tem = contend.substring(index);
        
        mac = tem.substring(0,tem.indexOf("|"));
        
        if(!mac.equals(getMac())){
        	System.out.print("非法授权.\n"); 
        	return false;
        }
        
        String ip="";
        index = ed1+mac.length()+1+ed2;
        tem= contend.substring(index);
        ip = tem.substring(0,tem.indexOf("|"));
        
        if(!ip.equals(getIP())){
        	System.out.print("非法授权.\n");
        	return false;
        }
        
        String startDate = "";
        index = ed1+mac.length()+1+ed2+ip.length()+1+ed3;
        tem= contend.substring(index);
        startDate = tem.substring(0,tem.indexOf("|"));
        
        String endDate = "";
        index = ed1+mac.length()+1+ed2+ip.length()+1+ed3+startDate.length()+1+ed4;
        tem= contend.substring(index);
        endDate = tem.substring(0,tem.indexOf("|"));
        
        System.out.print("当前授权结束日期为： "+startDate+"-"+endDate);
        
        if(System.currentTimeMillis()>Long.parseLong(sed))
        {
        	System.out.print("授权过期.\n");
        	return false;
        }
        
		return true;
	}
	
	
	 private static int getPNum(String ac) throws Exception
     {
		 try
		 {
	         boolean isnumber = true;
	         if (ac.length() == 0)
	         {
	             isnumber = false;
	             return 0;
	         }
	
	         if (isnumber)
	         {
	             if (ac.trim().charAt(0) == '0')
	             {
	                 ac = ac.substring(1, ac.length());
	                 return getPNum(ac);
	             }
	         }
		 }
		 catch(Exception e)
		 {
			 System.out.print("授权文件不合法!");
			 throw new Exception("授权文件不合法!");
		 }

         return Integer.parseInt(ac);
     }
	
 

	 
	  /**    
	   *  获取当前操作系统名称.    
	   *  return 操作系统名称 例如:windows,Linux,Unix等.    
	   */      
	  public static String getOSName() {      
	      return System.getProperty("os.name").toLowerCase();      
	  }      
	                
	   /**    
	    * 获取Unix网卡的mac地址.    
	    * @return mac地址    
	   */      
	   public static String getUnixMACAddress() {      
	      String mac = null;      
	      BufferedReader bufferedReader = null;      
	      Process process = null;      
	      try {      
	             /**   
	              *  Unix下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息     
	              */   
	              process = Runtime.getRuntime().exec("ifconfig eth0");    
	              bufferedReader = new BufferedReader(new InputStreamReader(process      
	                      .getInputStream()));      
	              String line = null;      
	              int index = -1;      
	              while ((line = bufferedReader.readLine()) != null) {      
	                  /**   
	                   *  寻找标示字符串[hwaddr]    
	                  */   
	                  index = line.toLowerCase().indexOf("hwaddr");     
	                  /**   
	                   * 找到了   
	                   */   
	                  if (index != -1) {      
	                      /**   
	                       *   取出mac地址并去除2边空格     
	                       */   
	                      mac = line.substring(index +"hwaddr".length()+ 1).trim();    
	                      break;      
	                  }      
	              }      
	          } catch (IOException e) {      
	              e.printStackTrace();      
	          } finally {      
	              try {      
	                  if (bufferedReader != null) {      
	                      bufferedReader.close();      
	                  }      
	              } catch (IOException e1) {      
	                 e1.printStackTrace();      
	             }      
	              bufferedReader = null;      
	              process = null;      
	          }      
	        
	         return mac;      
	   }      
	            
	              
	              
	        /**    
	        * 获取Linux网卡的mac地址.    
	        * @return mac地址    
	        */      
	        public static String getLinuxMACAddress() {      
	              String mac = null;      
	              BufferedReader bufferedReader = null;      
	              Process process = null;      
	              try {      
	                    /**   
	                     *  linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息     
	                     */   
	                  process = Runtime.getRuntime().exec("ifconfig eth0");    
	                  bufferedReader = new BufferedReader(new InputStreamReader(process      
	                          .getInputStream()));      
	                  String line = null;      
	                  int index = -1;      
	                  while ((line = bufferedReader.readLine()) != null) {      
	                      index = line.toLowerCase().indexOf("硬件地址");     
	                         /**   
	                          * 找到了   
	                          */   
	                      if (index != -1) {      
	                             /**   
	                              *   取出mac地址并去除2边空格     
	                              */   
	                          mac = line.substring(index+4).trim();    
	                          break;      
	                      }      
	                  }      
	              } catch (IOException e) {      
	                  e.printStackTrace();      
	              } finally {      
	                  try {      
	                      if (bufferedReader != null) {      
	                          bufferedReader.close();      
	                      }      
	                  } catch (IOException e1) {      
	                     e1.printStackTrace();      
	                 }      
	                  bufferedReader = null;      
	                  process = null;      
	              }    
	            
	              return mac;      
	          }    
	         
	         
	          /**    
	           * 获取widnows网卡的mac地址.    
	           * @return mac地址    
	           */      
	          public static String getWindowsMACAddress() {      
	              String mac = null;      
	              BufferedReader bufferedReader = null;      
	              Process process = null;      
	              try {      
	                    /**   
	                     * windows下的命令，显示信息中包含有mac地址信息     
	                     */   
	                  process = Runtime.getRuntime().exec("ipconfig /all");    
	                  bufferedReader = new BufferedReader(new InputStreamReader(process      
	                          .getInputStream()));      
	                  String line = null;      
	                  int index = -1;      
	                  while ((line = bufferedReader.readLine()) != null) {      
	                         /**   
	                          *  寻找标示字符串[physical address]    
	                          */   
	                      index = line.toLowerCase().indexOf("physical address");     
	                      if (index != -1||line.indexOf("物理地址")!=-1) {    
	                          index = line.indexOf(":");    
	                          if (index != -1) {    
	                                 /**   
	                                  *   取出mac地址并去除2边空格   
	                                  */   
	                             mac = line.substring(index + 1).trim();     
	                         }    
	                          break;      
	                      }    
	                  }    
	              } catch (IOException e) {      
	                  e.printStackTrace();      
	              }finally {      
	                  try {      
	                      if (bufferedReader != null) {      
	                          bufferedReader.close();      
	                        }      
	                  }catch (IOException e1) {      
	                      e1.printStackTrace();      
	                    }      
	                  bufferedReader = null;      
	                  process = null;      
	              }      
	            
	              return mac;      
	          }      
	            
	         /**    
	          * 测试用的GetMac方法.    
	          * @param argc    
	          * 运行参数.    
	          */      
	         public static String getMac() {      
	              String os = getOSName();      
	              String mac = "";  
	              if(os.startsWith("windows")){      
	                  mac = getWindowsMACAddress();       
	                  System.out.println("本地是windows:"+mac);      
	              }else if(os.startsWith("linux")){      
	                    mac = getLinuxMACAddress();      
	                  System.out.println("本地是Linux系统,MAC地址是:"+mac);    
	              }else{      
	                  mac = getUnixMACAddress();                          
	                  System.out.println("本地是Unix系统 MAC地址是:"+mac);    
	              }      
	              return mac;
	         }
	         
	         
	         public static String getIP() throws SocketException
	         {
	        	 String os = getOSName();      
	              String mac = "";  
	              if(os.startsWith("windows")){      
	                  mac = getWinIP();      
	                  System.out.println("本地是windows:"+mac);      
	              }else if(os.startsWith("linux")){      
	                    mac = getLinuxIP();      
	                  System.out.println("本地是Linux系统,MAC地址是:"+mac);    
	              }else{      
	                  mac = getLinuxIP();                          
	                  System.out.println("本地是Unix系统 MAC地址是:"+mac);    
	              }      
	              return mac;
	         }
	         
	         public static String getWinIP()
	         {
	        	 String ip="";
	        	 try {
					ip = InetAddress.getLocalHost().toString().split("\\/")[1];
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				return ip;

	         }
	         
	         
	         public static String getLinuxIP() throws SocketException
	         {
	        	 Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
	        	 InetAddress ip = null;
	        	 while (allNetInterfaces.hasMoreElements())
	        	 {
		        	 NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
		        	 System.out.println(netInterface.getName());
		        	 Enumeration addresses = netInterface.getInetAddresses();
		        	 while (addresses.hasMoreElements())
		        	 {
			        	 ip = (InetAddress)addresses.nextElement();
			        	 if (ip != null && ip instanceof Inet4Address)
			        	 {
			        		 break;
			        	 } 
		        	 }
	        	 }

	        	 return ip.getHostAddress().split("////")[1];

	         }
	         
	      
}
