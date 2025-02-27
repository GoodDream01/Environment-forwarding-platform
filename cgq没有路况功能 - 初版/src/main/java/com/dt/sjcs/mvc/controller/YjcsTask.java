package com.dt.sjcs.mvc.controller;

import com.dt.common.utils.ComUtils;
import com.dt.sjcs.mvc.pojo.Sjpz;
import com.dt.sjcs.mvc.pojo.Yjfsjl;
import com.dt.sjcs.mvc.pojo.Yxpz;
import com.dt.sjcs.mvc.service.SjpzService;
import com.dt.sjcs.mvc.service.YjfsjlService;
import com.dt.sjcs.mvc.service.YxpzService;
import com.sun.mail.util.MailSSLSocketFactory;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import java.io.File;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@EnableScheduling
@Component
@Controller
@RequestMapping("/cgq/sjcs")
public class YjcsTask {

	@Resource
	private SjpzService sjpzservice;
	@Resource
	private YxpzService yxpzservice;
	@Resource
	private YjfsjlService yjfsjlservice;
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/outPutExcel")
	@ResponseBody
	@Scheduled(cron = "0 0 1 * * ?")
	public void outPutExcel() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Yxpz> yxpzlist = yxpzservice.findList();
		for (int i = 0; i < yxpzlist.size(); i++) {
			List<Sjpz> list = sjpzservice.findYxList(yxpzlist.get(i).getSbbh());
			if (list.size()>0) {
				String url = "/uploadfiles/files/"+sdf.format(new Date())+"/";
				String saveurl = servletContext.getRealPath(File.separator)+url;

				File filePath = new File(saveurl);
				if (!filePath.exists()){
					filePath.mkdirs();
				}
				String xlsFile = saveurl + "\\" + ComUtils.getUniqueString() + ".xls";
				WritableWorkbook wwb =null;
				try {
					wwb = Workbook.createWorkbook(new File(xlsFile));
				} catch (Exception e) {
					e.printStackTrace();
				}
				WritableSheet sheet = wwb.createSheet("数据信息",0);
				String[] titles = ("设备id,采集时间,"+yxpzlist.get(i).getZdmc()).split(",");
				String[] zdbm = yxpzlist.get(i).getZdbm().split(",");
				Label label = null;
				for(int n = 0; n<titles.length; n++){
					label = new Label(n,0,titles[n]);
					sheet.addCell(label);
				}
				for(int n = 0; n<list.size(); n++){
					sheet.addCell(new Label(0,n+1,list.get(n).getSbid()));
					sheet.addCell(new Label(1,n+1,sdf1.format(list.get(n).getDate())));
					for (int m = 0; m < zdbm.length; m++) {
						if(zdbm[m].equals("sunVolt")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getDy()));//太阳能充电电压
						} else if(zdbm[m].equals("power")){
							double sunVolt = Double.parseDouble(list.get(n).getDy());
							int power = Integer.parseInt(new java.text.DecimalFormat("0").format(1+(sunVolt-9.6)/1*33));
							sheet.addCell(new Number(m+2,n+1,power));//电量
						} else if(zdbm[m].equals("temperature")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getDqwdz()));//空气温度
						} else if(zdbm[m].equals("humidity")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getDqsdz()));//空气湿度
						}else if(zdbm[m].equals("pressure")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getDqylz()));//大气压力
						} else if(zdbm[m].equals("windSpeed")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getFsssz()));//风速
						} else if(zdbm[m].equals("windDirection")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getFxssz()));//风向
						} else if(zdbm[m].equals("rainfall")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getDqjyz()));//降雨量
						} else if(zdbm[m].equals("cumulativerainfall")){
							double rainfall = Double.parseDouble(list.get(n).getDqjyz());
							sheet.addCell(new Label(m+2,n+1,Double.toString(rainfall*60)));//雨量累计
						} else if(zdbm[m].equals("solarRadiation")){
							sheet.addCell(new Label(m+2,n+1,list.get(n).getZfsz()));//太阳辐射
						}
					}
					//list.get(n).setTsyx(yxpzlist.get(i).getYxzh());
					//sjpzservice.updateYx(list.get(n));
				}
				wwb.write();
				wwb.close();
				SendComplexEmail(xlsFile,yxpzlist.get(i).getYxzh());
				Yjfsjl yjfsjl = new Yjfsjl();
				yjfsjl.setDwid(yxpzlist.get(i).getDwid());
				yjfsjl.setDwmc(yxpzlist.get(i).getDwmc());
				yjfsjl.setYxzh(yxpzlist.get(i).getYxzh());
				yjfsjl.setWjlj(xlsFile.replace(servletContext.getRealPath(File.separator),""));
				yjfsjlservice.insert(yjfsjl);
			}
		}
	}

	public void SendComplexEmail(String url,String yxzh) throws MessagingException, GeneralSecurityException {

		//创建一个配置文件并保存
		Properties properties = new Properties();
		properties.setProperty("mail.host", "smtp.qq.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");

		//QQ存在一个特性设置SSL加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);

		//创建一个session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("3290566453@qq.com", "kfwdsgrmjjhfcihe");
			}
		});

		//开启debug模式
		session.setDebug(true);

		//通过session获取transport对象
		Transport transport = session.getTransport();

		//通过transport对象邮箱用户名和授权码连接邮箱服务器
		//transport.connect("smtp.163.com", "18231894979@163.com", "MJSQVSRZVENNGWZR");
		transport.connect("smtp.qq.com", "3290566453@qq.com", "kfwdsgrmjjhfcihe");

		//创建邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);

		//邮件发送人
		mimeMessage.setFrom(new InternetAddress("3290566453@qq.com"));

		//邮件接收人
		// mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("991441064@qq.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(yxzh));

		//邮件标题
		mimeMessage.setSubject("数据传输");

		//传入mimeMessage
		mimeMessage = complexEmail(mimeMessage,url);

		//发送邮件
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		//关闭连接
		transport.close();

	}

	public MimeMessage complexEmail(MimeMessage mimeMessage,String url) throws MessagingException {

		//邮件内容
		//准备图片数据
		//MimeBodyPart image = new MimeBodyPart();
		//DataHandler handler = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\Desktop\\111\\111.jpg"));
		//image.setDataHandler(handler);
		//image.setContentID("111.jpg"); //设置图片id

		//准备文本
		MimeBodyPart text = new MimeBodyPart();
		//text.setContent("这是一段文本<img src='cid:111.jpg'>","text/html;charset=utf-8");
		text.setContent("数据传输","text/html;charset=utf-8");

		//附件
		MimeBodyPart appendix = new MimeBodyPart();
		appendix.setDataHandler(new DataHandler(new FileDataSource(url)));
		appendix.setFileName("sj.xls");

		//拼装邮件正文
		MimeMultipart mimeMultipart = new MimeMultipart();
		//mimeMultipart.addBodyPart(image);
		mimeMultipart.addBodyPart(text);
		mimeMultipart.setSubType("related");//文本和图片内嵌成功

		//将拼装好的正文内容设置为主体
		MimeBodyPart contentText = new MimeBodyPart();
		contentText.setContent(mimeMultipart);

		//拼接附件
		MimeMultipart allFile = new MimeMultipart();
		allFile.addBodyPart(appendix);//附件
		allFile.addBodyPart(contentText);//正文
		allFile.setSubType("mixed"); //正文和附件都存在邮件中，所有类型设置为mixed

		//放到Message消息中
		mimeMessage.setContent(allFile);
		mimeMessage.saveChanges();//保存修改
		return mimeMessage;

	}

}
